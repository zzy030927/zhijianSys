package zhijian.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/previewFolder")
@MultipartConfig
public class PreviewFolderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Part> parts = request.getParts();
        StringBuilder sb = new StringBuilder();

        for (Part part : parts) {
            if (part.getName().equals("files")) {
                File file = new File(part.getSubmittedFileName());
                if (file.isDirectory()) {
                    sb.append("文件目录："+file.getName()).append("<br>");
                    listFiles(file, sb, file.getAbsolutePath().length());
                } else {
                    sb.append("文件："+file.getName()).append("<br>");
                }
            }
        }

        request.setAttribute("xiaoping", "目录："+sb.toString());
        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }

    private void listFiles(File folder, StringBuilder sb, int basePathLength) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        sb.append("文件目录："+file.getName()).append("<br>");
                        listFiles(file, sb, basePathLength);
                    } else {
                        sb.append("文件："+file.getName()).append("<br>");
                    }
                }
            }
        }
    }
}


