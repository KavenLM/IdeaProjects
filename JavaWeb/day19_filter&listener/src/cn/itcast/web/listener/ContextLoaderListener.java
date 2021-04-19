package cn.itcast.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        System.out.println("我被创建了");
        ServletContext servletContext = servletContextEvent.getServletContext();
        String initParameter = servletContext.getInitParameter("contextConfigLocation");
        String realPath = servletContext.getRealPath(initParameter);
        try {
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("ServletContext对象被创建了");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("我被销毁了");
    }
}
