package zhijian.servlet;


import zhijian.dao.UserDao;
import zhijian.dao.daoImp.UserDaoImpl;
import zhijian.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        int userId = Integer.parseInt(id);

        UserDao ud = new UserDaoImpl();

        if (ud.delete(userId)) {
            List<User> userAll = ud.getUserAll();
            request.setAttribute("userAll", userAll);
            request.getRequestDispatcher("/viewUsers.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}

