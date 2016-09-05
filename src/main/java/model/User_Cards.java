package model;

/**
 * Created by User on 01.09.2016.
 */
public class User_Cards {
    int idUser;
    int idCard;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdCard() {
        return idCard;
    }

    public User_Cards(int id_User, int id_Card) {
        this.idUser = id_User;
        this.idCard = id_Card;
    }

    public User_Cards() {

    }
}
