package cn.itcast.web.proxy;

public class Lenovo implements SaleComputer{


    @Override
    public String sale(double money) {
        System.out.println("花了"+money+"钱。。。");
        return "Lenovo电脑";
    }

    @Override
    public void show() {
        System.out.println("show...");
    }
}
