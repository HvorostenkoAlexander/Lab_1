package model.Card;

/**
 * Created by User on 30.08.2016.
 */
public class Product {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String      name;
    int  accaontCard;
    boolean     statusCard;

    public Product(int id,String name, int accaontCard, boolean statusCard) {
        this.id = id;
        this.name = name;
        this.accaontCard = accaontCard;
        this.statusCard = statusCard;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccaontCard() {
        return accaontCard;
    }

    public void setAccaontCard(int accaontCard) {
        this.accaontCard = accaontCard;
    }

    public boolean isStatusCard() {
        return statusCard;
    }

    public void setStatusCard(boolean statusCard) {
        this.statusCard = statusCard;
    }
}
