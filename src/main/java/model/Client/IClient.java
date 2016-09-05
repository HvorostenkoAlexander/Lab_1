package model.Client;

import model.Card.Product;
import model.ConnectionMySQL;

import java.sql.SQLException;

/**
 * Created by User on 30.08.2016.
 */
public interface IClient {
    Product FillAaccount( int valueAaccount, int IdCard) throws SQLException, ClassNotFoundException;
    Product PayAaccount( int valueAaccount,int IdCard) throws SQLException, ClassNotFoundException;
    Product BlockAaccount( int IdCard) throws SQLException, ClassNotFoundException;
}
