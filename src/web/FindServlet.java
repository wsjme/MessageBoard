package web;

import domain.Message;
import domain.User;
import net.sf.json.JSONArray;
import service.ServiceDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/3.
 */
@WebServlet(name = "FindServlet", urlPatterns = "/fs")
public class FindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            response.setContentType("text/html; charset=utf-8");
            ServiceDemo sd = new ServiceDemo();
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
