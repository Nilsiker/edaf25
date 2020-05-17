package ovn7.uppg1;

public class VeryLongFactory implements WordFactory{
    @Override
    public Word createWord(String s) {
        return new VeryLongWord(Integer.parseInt(s));
    }
}
