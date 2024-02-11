package team.light.cyberfarm.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.exception.FileTooLargeException;
import team.light.cyberfarm.service.UploadGood;
import team.light.cyberfarm.serviceImpl.UploadGoodImpl;
import team.light.cyberfarm.tool.FileUploadUtil;
import team.light.cyberfarm.tool.JavaBeanUtil;

@MultipartConfig
@WebServlet("/mod/uploadGoods")
public class UploadGoodsServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "upload" + File.separator;

    private final UploadGood uploadGood = new UploadGoodImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        String uploadDir = request.getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
        response.setContentType("text/html;charset=UTF-8");

        Collection<Part> parts = request.getParts();

        PrintWriter writer = response.getWriter();
        if (parts.size() < 2) {
            writer.write("上传失败，由于排版需要请上传两个图片");
            return;
        }
        try {
            Good good = JavaBeanUtil.mappingObject(Good.class, request, "setPicture01", "setPicture02");
            List<String> urls = FileUploadUtil.saveImage(parts, uploadDir);
            good.setPicture01(urls.get(0));
            good.setPicture02(urls.get(1));
            uploadGood.upload(good);
        } catch (FileTooLargeException e) {
            writer.write("上传失败，可怜以下这个垃圾服务器吧，图片大小不得超过2MB");
        } catch (Exception e) {
            writer.println(e);
            writer.write("上传失败，出现未知异常请及时联系我");
        }

        writer.write("上传成功");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}