package model.Administrator;

import model.AbstractUsers;
import model.Card.ConcreteCreator;
import model.Card.Creator;
import model.Card.Product;
import model.ConnectionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 30.08.2016.
 */
public class Administrator extends  AbstractUsers implements IAdministrator {


    public Administrator() {
    }

    public Product UnlockCard( int IdCard) throws SQLException
    {
        ConnectionMySQL connectionMySQL = null;
        try {
            connectionMySQL = new ConnectionMySQL();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement statmtUPDATE = connectionMySQL.getConnection().prepareStatement("UPDATE Cards SET StatusCard= 1 WHERE Id=?");
        statmtUPDATE.setInt(1,IdCard);
        statmtUPDATE.execute();

        PreparedStatement statmtSELECT = connectionMySQL.getConnection().prepareStatement("SELECT * FROM Cards  WHERE Id=?;");
        statmtSELECT.setInt(1,IdCard);
        ResultSet resSet=  statmtSELECT.executeQuery( );

        ArrayList<Creator> creators = new ArrayList<Creator>();
        Product product = new  Product();
        while(resSet.next())
        {
            creators.add( new ConcreteCreator())  ;
            for (Creator creator: creators) {
                product = creator.factoryMethod(resSet.getInt("Id"),resSet.getString("Name"), resSet.getInt("AccaontCard"),  resSet.getBoolean("StatusCard"), resSet.getString("TypeCard"));
            }
        }
        return product;
    }


    public  void AddCard(int IdUser, String name, int accaontCard, boolean statusCard, String typeCard)throws SQLException
    {
        ConnectionMySQL connectionMySQL = null;
        try {
            connectionMySQL = new ConnectionMySQL();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement statmt = connectionMySQL.getConnection().prepareStatement("INSERT INTO Cards (TypeCard, Name,AccaontCard,StatusCard)  VALUES (?, ? ,?,?);");

        statmt.setString(1,typeCard);
        statmt.setString(2,name);
        statmt.setInt(3,accaontCard);
        statmt.setBoolean(4,statusCard);
        statmt.execute();

        PreparedStatement statmtSELECT = connectionMySQL.getConnection().prepareStatement("SELECT * FROM Cards WHERE id=(SELECT max(id) FROM Cards)");
        ResultSet resSet=  statmtSELECT.executeQuery();
        resSet.next();
        PreparedStatement statmtINSERT = connectionMySQL.getConnection().prepareStatement("INSERT INTO Users_Cards (Id_User, Id_Card)  VALUES (?, ?);");

        statmtINSERT.setInt(1,IdUser);
        statmtINSERT.setInt(2, resSet.getInt("Id"));
        statmtINSERT.execute();

    }

    public Administrator(int id, String Login, String Password){super(id, Login, Password);}


}
