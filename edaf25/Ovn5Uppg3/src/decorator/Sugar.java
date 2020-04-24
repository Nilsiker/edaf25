package decorator;

public class Sugar extends DrinkDecorator {

    public Sugar(Drink drink){
        super(drink);
        desc = "sugar";
        cost = 0;
    }
}
