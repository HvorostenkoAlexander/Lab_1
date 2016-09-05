package model.Client;

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
public class Client extends AbstractUsers implements IClient {

    public  Product FillAaccount( int valueAaccount,int IdCard) throws SQLException, ClassNotFoundException {
        ConnectionMySQL connectionMySQL=new ConnectionMySQL();
        PreparedStatement statmtUPDATE = connectionMySQL.getConnection().prepareStatement("UPDATE `Card`.`Cards` SET `AccaontCard`= `AccaontCard`+ ? WHERE `Id`=?");
        statmtUPDATE.setInt(1,valueAaccount);
        statmtUPDATE.setInt(2,IdCard);
        statmtUPDATE.execute();

        PreparedStatement statmtSELECT = connectionMySQL.getConnection().prepareStatement("SELECT * FROM `Card`.`Cards`  WHERE `Id`=?;");
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


    public Product PayAaccount( int valueAaccount,int IdCard) throws SQLException, ClassNotFoundException {
        ConnectionMySQL connectionMySQL=new ConnectionMySQL();
        PreparedStatement statmtUPDATE = connectionMySQL.getConnection().prepareStatement("UPDATE `Card`.`Cards` SET `AccaontCard`= `AccaontCard`- ? WHERE `Id`=?");
        statmtUPDATE.setInt(1,valueAaccount);
        statmtUPDATE.setInt(2,IdCard);
        statmtUPDATE.execute();

        PreparedStatement statmtSELECT = connectionMySQL.getConnection().prepareStatement("SELECT * FROM `Card`.`Cards`  WHERE `Id`=?;");
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
    public Product BlockAaccount(int IdCard) throws SQLException, ClassNotFoundException {
        ConnectionMySQL connectionMySQL=new ConnectionMySQL();
        PreparedStatement statmtUPDATE = connectionMySQL.getConnection().prepareStatement("UPDATE `Card`.`Cards` SET `StatusCard`= 0 WHERE `Id`=?");
        statmtUPDATE.setInt(1,IdCard);
        statmtUPDATE.execute();

        PreparedStatement statmtSELECT = connectionMySQL.getConnection().prepareStatement("SELECT * FROM `Card`.`Cards`  WHERE `Id`=?;");
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

    public Client(int id, String Login, String Password){super(id,Login, Password);}

    public  Client(){}

}
