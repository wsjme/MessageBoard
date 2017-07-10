package web;

import service.ServiceDemo;

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
@WebServlet(name = "DeleteServlet", urlPatterns = "/ds")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String rowID = request.getParameter("rowID");
            ServiceDemo sd = new ServiceDemo();
            sd.delMessage(rowID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
