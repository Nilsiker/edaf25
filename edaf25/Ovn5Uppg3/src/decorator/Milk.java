package decorator;

public class Milk extends DrinkDecorator {

    public Milk(Drink drink){
        super(drink);
        desc = "milk";
        cost = 1;
    }
}
