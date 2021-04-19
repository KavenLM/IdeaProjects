package cn.itcast.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式测试
 */
public class ProxyTest {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();
        //2.创建代理对象
        /**
         * lenovo.getClass().getClassLoader():真实对象的类加载器
         * lenovo.getClass().getInterfaces()：真实对象的接口数组
         * new InvocationHandler()：控制器
         */
        SaleComputer proxy_lenovo = (SaleComputer)Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /**
             *执行方法
             * @param proxy 代理对象
             * @param method 代理对象所调用真实对象的方法
             * @param args 方法的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("该方法执行了。。。");
                if(method.getName().equals("sale")){
                    System.out.println("专车接你");
                    double money = (double)args[0] * 0.85;
                    String res =(String) method.invoke(lenovo, money);
                    System.out.println("免费配送");
                    return res+"+鼠标垫";
                }else {
                    String res =(String) method.invoke(lenovo, args);
                    return res;
                }

            }
        });
//        proxy_lenovo.show();
        String sale = proxy_lenovo.sale(8000);
        System.out.println(sale);
    }
}
