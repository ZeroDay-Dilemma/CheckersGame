public class Main {

    CheckersEngine game;
	GUI gui;

    public Main(){
        game = new CheckersEngine();
        gui = new GUI(game);
    }

    public static void main(String[] args) {
        Main try1 = new Main();
    }
}
