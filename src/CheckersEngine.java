

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class CheckersEngine {
    ArrayList<Tile> board_GUI_Tiles = new ArrayList<Tile>();
    //8x8 array
    //0 = empty

    //1 = red
    //2 = black
    //10 = red king
    //20 = black king
    public byte[][] gameBoard = new byte[8][8];


    public CheckersEngine(){
        initiate();
    }

    private void initiate(){
        //fill the board_GUI_Tile ArrayList with Tile objects
        //8x8, alternating green and white
        // for (int i = 0; i < 8; i++){
        //     for (int j = 0; j < 8; j++){
        //         TileColor color;
        //         if ((i + j) % 2 == 0){
        //             color = TileColor.GREEN;
        //         }
        //         else{
        //             color = TileColor.WHITE;
        //         }
        //         Tile tile = new Tile(color);
        //         board_GUI_Tiles.add(tile);
        //     }
        // }
        
        //fill the gameBoard array with 0's
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                gameBoard[i][j] = 1;
                
            }
        }
        // //fill the gameBoard array with pieces
        // for (int i = 0; i < 3; i++){
        //     for (int j = 0; j < 8; j++){
        //         if ((i + j) % 2 == 1){
        //             gameBoard[i][j] = 1;
        //         }
        //     }
        // }
        // for (int i = 5; i < 8; i++){
        //     for (int j = 0; j < 8; j++){
        //         if ((i + j) % 2 == 1){
        //             gameBoard[i][j] = 2;
        //         }
        //     }
        // }

        

    }

    
    public String getPiece(int x, int y){
        switch(gameBoard[x][y]){
            case 0:
                return "empty";
            case 1:
                return "Red_Regular";
            case 2:
                return "Black_Regular";
            case 10:
                return "Red_King";
            case 20:
                return "Black_King";
            default:
                return "error";
        }
    }
}