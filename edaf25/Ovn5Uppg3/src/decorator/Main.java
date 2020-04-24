package decorator;

public class Main {

    public static void main(String[] args) {
        Drink drink = new Milk(new Milk(new Sugar(new Coffee())));
        System.out.println(drink);
        System.out.println(drink.cost());
    }
}

