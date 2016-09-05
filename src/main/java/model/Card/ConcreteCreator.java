package model.Card;

/**
 * Created by User on 30.08.2016.
 */
public class ConcreteCreator extends Creator  {


    @Override
    public Product factoryMethod(int id,String name, int accaontCard, boolean statusCard, String typeCard)
    {
        return new ConcreteCard( id, name,  accaontCard,  statusCard,  typeCard);
    }


}
