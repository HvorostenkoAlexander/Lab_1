package model.Administrator;

import model.Card.Product;

import java.sql.SQLException;

/**
 * Created by User on 30.08.2016.
 */
public interface IAdministrator {
        Product UnlockCard( int IdCard) throws SQLException;
      void AddCard(int IdUser, String name, int accaontCard, boolean statusCard, String typeCard)throws SQLException;
}
