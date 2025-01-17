package zhijian.servlet;


import zhijian.dao.UserDao;
import zhijian.dao.daoImp.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        int userId = Integer.parseInt(id);

        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        String home = request.getParameter("home");
        String info = request.getParameter("info");

        System.out.println("------------------------------------" + userId);

        UserDao ud = new UserDaoImpl();

        if (ud.update(userId, name, pwd, sex, home, info)) {
            request.setAttribute("xiaoping", "更新成功");
            request.getRequestDispatcher("/SearchallServlet").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}


