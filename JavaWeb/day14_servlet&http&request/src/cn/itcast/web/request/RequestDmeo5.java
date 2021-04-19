package cn.itcast.web.request;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/requestdemo5")
public class RequestDmeo5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求体
        //1.获取字符流
       /* BufferedReader br = request.getReader();
        //2.读取数据
        String line = null;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }*/

        ServletInputStream is = request.getInputStream();
        byte[] bytes = new byte[1024];
        int temp = 0;
        int len = 0;
        while((temp = is.read())!=-1){
            bytes[len] = (byte)temp;
            len++;
        }
        System.out.println(new String(bytes,0,len));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
