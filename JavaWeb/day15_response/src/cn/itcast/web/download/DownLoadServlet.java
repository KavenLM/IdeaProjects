package cn.itcast.web.download;

import cn.itcast.web.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/downLoadServlet")
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数中的文件名
        String filename = request.getParameter("filename");
        //获取文件在服务器中的真实地址
        ServletContext servletContext = this.getServletContext();
        String filePath = servletContext.getRealPath("/img/"+filename);

        //使用文件流将文件读取到内存中
        FileInputStream fis = new FileInputStream(filePath);


        //设置响应头类型
        String mimeType = servletContext.getMimeType(filename);
        response.setContentType(mimeType);

        //解决文件中文名问题
        String agent = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent, filename);

        //设置响应头打开方式content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);


        //将输入流的数据写出到输出流中
        ServletOutputStream os = response.getOutputStream();
        byte[] buff = new byte[1024*8];
        int len = 0;
        while((len = fis.read(buff))!=-1){
            os.write(buff,0,len);
        }

        fis.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
