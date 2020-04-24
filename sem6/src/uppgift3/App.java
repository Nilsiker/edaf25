package uppgift3;

public class App {
    public static void main(String[] args) {
        Warner w = new Warner(0.6);
        Portfolio p = new Portfolio();
        w.add(p);

        Stock s1 = new Stock();
        Stock s2 = new Stock();
        s1.update(100);
        s2.update(100);

        p.add(s1);
        p.add(s2);

        s1.update(90);
        s2.update(0);
    }
}
