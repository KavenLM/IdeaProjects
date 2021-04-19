package cn.itcast.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width = 100;
        int height = 50;

        //1.创建对象，在内存中图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2.美化图片
        //2.1填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.pink);
        g.fillRect(0,0,width,height);

        //2.2画边框
        g.setColor(Color.blue);
        g.drawRect(0,0,width-1,height-1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        //生成随机角标
        //创建数组存验证码
//        char[] checkCode = new char[4];
        StringBuilder checkCode = new StringBuilder();
        Random ran = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            checkCode.append(ch);
            g.drawString(ch+"",width/5*i,height/2);
        }
        //使用session将验证码共享
        HttpSession session = request.getSession();
        session.setAttribute("checkCode",checkCode.toString());
        //画干扰线
        g.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);

            g.drawLine(x1,y1,x2,y2);
        }


        //3.将图片输出到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
