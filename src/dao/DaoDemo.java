package dao;

import domain.Message;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/3.
 */
public class DaoDemo {
    public void addMessage(String message, String username, String time, String ip, String userid) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into message values(?,?,?,?,?,?,?)";
        qr.update(sql, null, null,username, message, time, ip, userid);
    }

    public List<Message> findMessage() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from message";
        return qr.query(sql, new BeanListHandler<>(Message.class));
    }

    public List<Message> findMyMessage(int userid) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from message where userid = ?";
        return qr.query(sql, new BeanListHandler<>(Message.class), userid);

    }

    public void delMessage(String rowID) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from message where id=?";
        qr.update(sql, rowID);
    }

    public void signUp(String username, String password,String sex) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?)";
        qr.update(sql, null, username, password,sex,0);
    }

    public User signIn(String username) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where username = ?";
        return qr.query(sql, new BeanHandler<>(User.class), username);
    }

    public User findId(int userid) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where id = ?";
        return qr.query(sql, new BeanHandler<>(User.class), userid);
    }

}
