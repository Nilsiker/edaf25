package ovn7.uppg1;

public class LongFactory implements WordFactory {

    @Override
    public Word createWord(String s) {
        return new LongWord(Integer.parseInt(s));
    }
}
