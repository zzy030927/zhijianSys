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

public class SearchallServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserDao ud = new UserDaoImpl();
        List<User> userAll = ud.getUserAll();
        request.setAttribute("userAll", userAll);
        request.getRequestDispatcher("/showall.jsp").forward(request, response);
    }


}

