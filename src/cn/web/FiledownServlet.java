package cn.web;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet("/filedownServlet")
public class FiledownServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        //解决获得中文参数的乱码
        name = new String(name.getBytes("ISO8859-1"),"UTF-8");//美女.jpg
        //获得请求头中的User-Agent
        String agent = request.getHeader("User-Agent");
        //根据不同浏览器进行不同的编码
        String fileNameEncoder = "";
        if (agent.contains("MSIE")) {
            // IE浏览器
            fileNameEncoder = URLEncoder.encode(name, "utf-8");
            fileNameEncoder = fileNameEncoder.replace("+", " ");
        } else if (agent.contains("Firefox")) {
                        // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            fileNameEncoder = "=?utf-8?B?" + base64Encoder.encode(name.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            fileNameEncoder = URLEncoder.encode(name, "utf-8");
        }
        response.setContentType("text/html;charset=utf-8");
        ServletContext servletContext = this.getServletContext();
        InputStream fileInputStream = servletContext.getResourceAsStream("/file/"+name);
        String minmeType = servletContext.getMimeType(name);
        response.setHeader("content-type",minmeType);
        response.setHeader("content-disposition","attachment;filename="+name);
        OutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while((len = fileInputStream.read(buff,0,buff.length)) != -1){
            sos.write(buff, 0, len);
        }

        fileInputStream.close();
        sos.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);

        String[] option = request.getParameterValues("option");
    }
}
