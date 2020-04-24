package decorator;

public abstract class BaseDrink implements Drink{
    String desc;
    double cost;

    public BaseDrink(String desc, double cost){
        this.desc = desc;
        this.cost = cost;
    }

    @Override
    public String toString(){
        return desc;
    }

    @Override
    public double cost() {
        return cost;
    }
}
