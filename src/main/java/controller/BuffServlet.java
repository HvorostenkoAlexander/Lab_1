package controller;

import model.AbstractUsers;
import model.Card.ConcreteCard;
import model.Card.Product;
import model.Client.Client;
import model.ConnectionMySQL;
import model.User_Cards;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BuffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        ConnectionMySQL connectionMySQL = null;
        ArrayList<String> userCard= new ArrayList<String>();
        try {
            connectionMySQL = new ConnectionMySQL();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession(true);
        if(request.getAttribute("role").equals("admin"))
        {


            try {
                ArrayList<Product> products = ArrayListProduct(connectionMySQL);
                ArrayList<AbstractUsers> users = ArrayListUsers(connectionMySQL);
                session.setAttribute("products",products);
                session.setAttribute("users", users);
            } catch (SQLException e) {
                e.printStackTrace();
            }


            RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/admin.jsp" );
            dispatcher.forward( request, response );
        }
        else
        {
            try {
                Client client = getUser(connectionMySQL,request.getAttribute("userName").toString());
                session.setAttribute("user",client);
                ArrayList<Product> products = ArrayListProductIDUser(connectionMySQL, client);
                session.setAttribute("products",products);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/users.jsp" );
            dispatcher.forward( request, response );
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        ArrayList<User_Cards> userCard= new ArrayList<User_Cards>();
        try {
        ConnectionMySQL connectionMySQL = new ConnectionMySQL();
        if(session.getAttribute("role").equals("admin"))
        {

                ArrayList<Product> products = ArrayListProduct(connectionMySQL);
                ArrayList<AbstractUsers> users = ArrayListUsers(connectionMySQL);

                session.setAttribute("products",products);
                session.setAttribute("users", users);
            Statement statmtSELECT = connectionMySQL.getConnection().createStatement();
            ResultSet resSet=  statmtSELECT.executeQuery("SELECT * FROM Users_Cards");
            while(resSet.next())
            {
                userCard.add(new User_Cards(resSet.getInt("Id_User"),resSet.getInt("Id_Card")));

            }
            session.setAttribute("userCard",userCard);


            RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/admin.jsp" );
            dispatcher.forward( request, response );
        }

        else
        {
            try {
                Client client = getUser(connectionMySQL,session.getAttribute("userName").toString());
                session.setAttribute("user",client);
                ArrayList<Product> products = ArrayListProductIDUser(connectionMySQL, client);
                session.setAttribute("products",products);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/users.jsp" );
            dispatcher.forward( request, response );
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Product> ArrayListProduct (ConnectionMySQL connectionMySQL) throws SQLException
    {


        Statement statmtSELECT = connectionMySQL.getConnection().createStatement();
        ResultSet resSet=  statmtSELECT.executeQuery("SELECT Id,TypeCard,Name,AccaontCard,StatusCard,\n" +
                "CASE StatusCard\n" +
                "  WHEN 0 THEN 'Заблокирована'\n" +
                "  WHEN 1 THEN 'Разблокирована'\n" +
                "END\n" +
                "FROM Cards");

        ArrayList<Product> products = new ArrayList<Product>();
        while(resSet.next())
        {
            products.add(new ConcreteCard(resSet.getInt("Id"),resSet.getString("Name"), resSet.getInt("AccaontCard"),  resSet.getBoolean("StatusCard"), resSet.getString("TypeCard")));

        }
        return products;
    }

    public ArrayList<Product> ArrayListProductIDUser (ConnectionMySQL connectionMySQL, Client client) throws SQLException
    {

        PreparedStatement statmtSELECTUsers = connectionMySQL.getConnection().prepareStatement("SELECT * FROM Users WHERE Login = ?");
        statmtSELECTUsers.setString(1,client.getLogin());
        ResultSet resSetUsers=  statmtSELECTUsers.executeQuery();
        resSetUsers.next();
        int iid = resSetUsers.getInt("Id");

        PreparedStatement statmtSELECTUsers_Cards = connectionMySQL.getConnection().prepareStatement("SELECT * FROM Users_Cards WHERE Id_User = ?");
        statmtSELECTUsers_Cards.setInt(1,iid);
        ResultSet resSetUsers_Cards=  statmtSELECTUsers_Cards.executeQuery();
        ArrayList<Integer> Id_Card = new ArrayList<Integer>();
        while(resSetUsers_Cards.next())
        {
            Id_Card.add(resSetUsers_Cards.getInt("Id_Card"));
        }

        ArrayList<Product> products = new ArrayList<Product>();
        for (int id: Id_Card)
        {
            PreparedStatement statmtSELECTId = connectionMySQL.getConnection().prepareStatement("SELECT Id,TypeCard,Name,AccaontCard,StatusCard,\n" +
                    "CASE StatusCard\n" +
                    "  WHEN 0 THEN 'Заблокирована'\n" +
                    "  WHEN 1 THEN 'Разблокирована'\n" +
                    "END\n" +
                    "FROM Cards WHERE Id = ?");
            statmtSELECTId.setInt(1,id);
            ResultSet statmtId=  statmtSELECTId.executeQuery();
            while(statmtId.next())
            {
                products.add(new ConcreteCard(statmtId.getInt("Id"),statmtId.getString("Name"), statmtId.getInt("AccaontCard"),  statmtId.getBoolean("StatusCard"), statmtId.getString("TypeCard")));

            }
            statmtId.close();
        }
        return products;
    }

    public Client getUser (ConnectionMySQL connectionMySQL, String Login) throws SQLException
    {


        PreparedStatement statmtSELECT = connectionMySQL.getConnection().prepareStatement("SELECT * FROM Users WHERE Login = ?");
        statmtSELECT.setString(1,Login);
        ResultSet resSet=  statmtSELECT.executeQuery();

        Client user = new Client();
        while(resSet.next())
        {
             user = new Client(resSet.getInt("Id"),resSet.getString("Login"), resSet.getString("Password"));

        }
        return user;
    }

    public ArrayList<AbstractUsers> ArrayListUsers (ConnectionMySQL connectionMySQL) throws SQLException
    {


        Statement statmtSELECT = connectionMySQL.getConnection().createStatement();
        ResultSet resSet=  statmtSELECT.executeQuery("SELECT * FROM Users");

        ArrayList<AbstractUsers> users = new ArrayList<AbstractUsers>();
        while(resSet.next())
        {
            users.add(new Client(resSet.getInt("Id"),resSet.getString("Login"), resSet.getString("Password")));

        }
        return users;
    }




}
