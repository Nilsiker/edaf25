package uppgift2;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void flip(){
        model.flip();
    }
}
