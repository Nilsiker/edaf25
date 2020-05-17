package ovn7.uppg1;

public class Factorial extends Program {
    public Factorial(WordFactory wFactory) {
        WordFactory factory = wFactory;
        Address n = new Address(0),
                fac = new Address(1);
        add(new Copy(factory.createWord("5"),n));
        // omissions
    }
}
