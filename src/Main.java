public class Main {

    CheckersEngine game;
	GUI gui;

    public Main(){
        game = new CheckersEngine();
        gui = new GUI(game);
    }

    public static void main(String[] args) {
        //it all starts here
        Main try1 = new Main();
    }
}
