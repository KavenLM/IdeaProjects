package cn.itcast.web.download;

import cn.itcast.web.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet2")
public class DownLoadServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数中的文件名
        String filename = request.getParameter("filename");
        ServletContext servletContext = this.getServletContext();
        //2.根据文件名获取其在服务器中的真实地址
        String realPath = servletContext.getRealPath("/img/"+filename);

        //3.读取写出文件给请求客户端
        //3.1将文件读取进内存中
        FileInputStream fis = new FileInputStream(realPath);

        //3.2.根据文件名后缀得到mimeType，设置响应头类型，告诉客户端怎么解析
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);

        //3.3.解决中文文件名问题
        //由于不同浏览器解析方式不同，所以需要根据user-agent写判断条件(由工具类判断)
        String agent = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent, filename);
        //3.4.设置响应头content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);

        //3.5.将文件流写出到输出流中
        ServletOutputStream os = response.getOutputStream();
        byte[] buff = new byte[1024*8];
        int len = 0;
        while ((len=fis.read(buff))!=-1){
            os.write(buff,0,len);
        }

        fis.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
