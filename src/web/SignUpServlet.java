package web;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import domain.User;
import net.sf.json.JSONString;
import service.ServiceDemo;
import utils.CharsetUtil;
import utils.ValidatUtil;

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
@WebServlet(name = "SignUpServlet", urlPatterns = "/sus")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html; charset=utf-8");
            CharsetUtil cu = new CharsetUtil();
            ValidatUtil vd = new ValidatUtil();

            String username = cu.changeCharset(request.getParameter("username").trim());
            String password = cu.changeCharset(request.getParameter("password").trim());
            String sex = request.getParameter("sex");

            boolean isusername = vd.isusername(username);
            boolean ispassword = vd.ispassword(password);

            if (username.equals("")) {
                String str = "{message:'请输入用户名！'}";
                response.getWriter().println(str);
                return;
            }
            if (!isusername) {
                String str = "{message:'用户名格式不正确！'}";
                response.getWriter().println(str);
                return;
            }
            if (password.equals("")) {
                String str = "{message:'请输入密码！'}";
                response.getWriter().println(str);
                return;
            }
            if (!ispassword) {
                String str = "{message:'密码格式不正确！'}";
                response.getWriter().println(str);
                return;
            }
            ServiceDemo sd = new ServiceDemo();
            User user = sd.signIn(username);
            if (user != null) {
                String str = "{message:'用户名已被注册！'}";
                response.getWriter().println(str);
            } else {
                sd.signUp(username, password,sex);
                String str = "{message:'注册成功！'}";
                response.getWriter().println(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
