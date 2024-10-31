package zhijian.servlet;


import zhijian.dao.daoImp.UserDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignInServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("username"); //获取jsp页面传过来的参数
        String pwd = request.getParameter("password");
        String sex = request.getParameter("sex");
        String home = request.getParameter("home");
        String info = request.getParameter("info");

        zhijian.entity.User user = new zhijian.entity.User(); //实例化一个对象，组装属性
        user.setName(name);
        user.setPwd(pwd);
        user.setSex(sex);
        user.setHome(home);
        user.setInfo(info);

        zhijian.dao.UserDao ud = new UserDaoImpl();

        if(ud.register(user)){
            request.setAttribute("username", name);  //向request域中放置参数
            //request.setAttribute("xiaoping", "注册成功");
            request.getRequestDispatcher("/denglu.jsp").forward(request, response);  //转发到登录页面
        }else{
            //如果注册失败这跳转
            response.sendRedirect("index.jsp");//重定向到首页
        }
    }

}

