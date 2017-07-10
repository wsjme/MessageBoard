package web;

import domain.User;
import service.ServiceDemo;
import utils.CharsetUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by wsj on 2017/7/4.
 */
@WebServlet(name = "SignInServlet", urlPatterns = "/sis")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            response.setContentType("text/html; charset=utf-8");
            CharsetUtil cu = new CharsetUtil();
            String username = cu.changeCharset(request.getParameter("username"));
            String password = cu.changeCharset(request.getParameter("password"));
            ServiceDemo sd = new ServiceDemo();
            User user = sd.signIn(username);
            if (user == null) {
                String str = "{message:'用户不存在！'}";
                response.getWriter().println(str);
            }else if(!password.equals(user.getPassword())){
                String str = "{message:'密码错误！'}";
                response.getWriter().println(str);
            } else {
                if (user.getRole() == 1) {
                    String str = "{message:'1'}";
                    response.getWriter().println(str);
                } else {
                    String str = "{message:'0'}";
                    response.getWriter().println(str);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
