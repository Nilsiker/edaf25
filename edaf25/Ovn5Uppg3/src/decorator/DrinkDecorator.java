package decorator;

public abstract class DrinkDecorator implements Drink {
    protected Drink drink;
    protected String desc;
    protected double cost;

    public DrinkDecorator(Drink drink){
        this.drink = drink;
    }

    public String toString(){
        return drink.toString() + " " + desc;
    }

    public double cost(){
        return drink.cost() + cost;
    }
}
