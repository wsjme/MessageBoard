package web;

import domain.User;
import service.ServiceDemo;
import utils.CharsetUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by wsj on 2017/7/4.
 */
@WebServlet(name = "JumpServlet", urlPatterns = "/js")
public class JumpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("text/html; charset=utf-8");
            CharsetUtil cu = new CharsetUtil();
            String username = cu.changeCharset(request.getParameter("username"));
            String role = cu.changeCharset(request.getParameter("role"));

            HttpSession session = request.getSession();
            if(role.equals("1")){
                session.setAttribute("username", "管理员");
            }else{
                session.setAttribute("username", username);
            }
            ServiceDemo sd = new ServiceDemo();
            User user = sd.signIn(username);
            String userid = String.valueOf(user.getId());
            String sex = String.valueOf(user.getSex());
            session.setAttribute("role", role);
            session.setAttribute("userid", userid);
            session.setAttribute("sex", sex);
            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
