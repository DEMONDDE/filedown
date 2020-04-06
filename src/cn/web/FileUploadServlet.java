package cn.web;

import cn.service.Impl.ServiceImpl;
import cn.service.Service;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@WebServlet("/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Service service = new ServiceImpl();
        //让浏览器用utf8来解析返回的数据
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");
        //解决文件名
        request.setCharacterEncoding("utf-8");
        //设置文件上传基本路径
        String savePath = this.getServletContext().getRealPath("/file");
        //设置临时文件路径
        String tempPath = this.getServletContext().getRealPath("/tempFiles");
        File tempFile = new File(tempPath);
        File savefile = new File(savePath);
        //不知什么原因导致打包时空文件夹不存在所以只好检测来创建文件夹
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        if (!savefile.exists()) {
            savefile.mkdir();
        }
        //创建file items工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置缓冲区大小
        factory.setSizeThreshold(1024 * 100);
        //设置临时文件路径
        factory.setRepository(tempFile);
        //创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
        upload.setSizeMax(1024 * 1024 * 30);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems){
                String name = fileItem.getName();//文件名称
                //处理不同浏览器传来的文件名问题
                int index=name.lastIndexOf("\\");
                if(index!=-1) {
                    name=name.substring(index+1);
                }
                Boolean b = service.addFile(name);
                if(b){
                    response.getWriter().write("已存在文件");
                    return;
                }
                InputStream ips = fileItem.getInputStream();
                File file = new File(savePath+File.separator+name);
                //IO流拷贝
                FileOutputStream ops = new FileOutputStream(file);
                byte[] date = new byte[2048];//缓存
                int read = 0;
                try {
                    while ((read = ips.read(date,0,date.length)) != -1){
                        ops.write(date, 0, read);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //关闭资源
                    if(ips != null){
                        ips.close();
                    }
                    if (ops != null){
                        ops.close();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        response.getWriter().write("上传成功");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
