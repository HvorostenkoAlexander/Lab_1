package model.Card;

/**
 * Created by User on 30.08.2016.
 */
public class ConcreteCard  extends Product   {
    public String getTypeCard() {
        return typeCard;
    }

    String typeCard;

    public ConcreteCard(int id, String name, int accaontCard, boolean statusCard, String typeCard) {
        super(id, name, accaontCard, statusCard);
        this.typeCard = typeCard;
    }
}
