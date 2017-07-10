package service;

import dao.DaoDemo;
import domain.Message;
import domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/3.
 */
public class ServiceDemo {
    public void addMessage(String message, String username, String time, String ip, String userid) throws SQLException {
        DaoDemo dd = new DaoDemo();
        dd.addMessage(message, username, time, ip, userid);
    }

    public List<Message> findMessage() throws SQLException {
        DaoDemo dd = new DaoDemo();
        return dd.findMessage();
    }

    public List<Message> findMyMessage(int userid) throws SQLException {
        DaoDemo dd = new DaoDemo();
        return dd.findMyMessage(userid);
    }
    public void delMessage(String rowID) throws SQLException {
        DaoDemo dd = new DaoDemo();
        dd.delMessage(rowID);
    }

    public void signUp(String username, String password,String sex) throws SQLException {
        DaoDemo dd = new DaoDemo();
        dd.signUp(username, password,sex);
    }

    public User signIn(String username) throws SQLException {
        DaoDemo dd = new DaoDemo();
        return dd.signIn(username);
    }

    public User findId(int userid) throws SQLException {
        DaoDemo dd = new DaoDemo();
        return dd.findId(userid);
    }
}
