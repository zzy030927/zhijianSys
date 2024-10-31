package zhijian.servlet;

import zhijian.dao.UserDao;
import zhijian.dao.daoImp.UserDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogInServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        UserDao ud = new UserDaoImpl();
        if(ud.login(name, pwd)){
            request.setAttribute("xiaoping", "管理员："+name);
            request.getRequestDispatcher("/menu.jsp").forward(request, response);
//            request.getRequestDispatcher("/analyze.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.jsp");
        }
    }

}


