package web;

import domain.Message;
import domain.User;
import net.sf.json.JSONArray;
import service.ServiceDemo;
import utils.CharsetUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wsj on 2017/7/3.
 */
@WebServlet(name = "AddServlet", urlPatterns = "/as")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(new Date());
            response.setContentType("text/html; charset=utf-8");
            String message = request.getParameter("message");
            String username = request.getParameter("username");
            String userid = request.getParameter("userid");
            String ip = request.getRemoteAddr();
            ServiceDemo sd = new ServiceDemo();
            sd.addMessage(message, username, time, ip, userid);

            List<Message> messages = sd.findMessage();

            for (Message message_new : messages) {
                int userid_new = message_new.getUserid();
                User newUser = sd.findId(userid_new);
                String sex = newUser.getSex();
                if(sex.equals("boy")){
                    message_new.setAvatar("img/2.jpg");
                }else if(sex.equals("girl")){
                    message_new.setAvatar("img/3.jpg");
                }else if(sex.equals("man")){
                    message_new.setAvatar("img/4.jpg");
                }
            }

            JSONArray json = JSONArray.fromObject(messages);
            response.getWriter().println(json.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
