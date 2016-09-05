package model.Card;

/**
 * Created by User on 30.08.2016.
 */
public abstract  class Creator {
    public abstract Product factoryMethod(int id, String name, int accaontCard, boolean statusCard, String typeCard);
}
