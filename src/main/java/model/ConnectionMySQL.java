package model; /**
 * Created by User on 30.08.2016.
 */

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public  class ConnectionMySQL {

    final static Logger log = Logger.getLogger("model.ConnectionMySQL");


    static final String URL = "jdbc:mysql://localhost:3306/Card";
    static final String USERNAME = "root";
    static final String PASSWORD = "";


    Connection  connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public   ConnectionMySQL() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        setConnection( DriverManager.getConnection(URL, USERNAME,PASSWORD));
        System.out.println(getConnection());
    }

}
