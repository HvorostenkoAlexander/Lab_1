package controller;

import model.ConnectionMySQL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthServlet extends HttpServlet {
    @Override


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        try {
            login(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();


        } catch (SQLException e) {
            e.printStackTrace();


        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        try {
            login(request, response)

            ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void login(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        request.setCharacterEncoding("UTF-8");
        ConnectionMySQL connectionMySQL = new ConnectionMySQL();
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        PreparedStatement statement = connectionMySQL.getConnection().prepareStatement("SELECT * FROM Users WHERE Login like ?");
        statement.setString(1,login);
        ResultSet rs = statement.executeQuery();
        if(rs.next()) {
            String lgn = rs.getString(2);
            String psw = rs.getString(3);
            if (lgn.equals(login) && psw.equals(password)) {
                if (login.equals("admin")) {
                    if(remember!=null && remember.equals("Remember"))
                    {
                        request.setAttribute("role", "admin");
                        request.setAttribute("isAuthorized", true);
                    }
                    else {
                        session.setAttribute("role", "admin");
                        request.getRequestDispatcher("/BuffServlet").forward(request, response);
                    }

                } else {
                    if(remember!=null && remember.equals("Remember"))
                    {
                        session.setAttribute("role", "user");
                        session.setAttribute("isAuthorized", true);
                    }
                    else {
                        session.setAttribute("role", "user");
                        session.setAttribute("userName",login );
                        request.getRequestDispatcher("/BuffServlet").forward(request, response);

                    }
                }

            }
        }

    }
}