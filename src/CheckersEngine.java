

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
    public PIECE_COLOR turn =  PIECE_COLOR.BLACK;
    public Piece pieceHeld = null;
    public boolean gameOver = false;
    public PIECE_COLOR winner = null;

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
                gameBoard[i][j] = 0;
            }
        }
        //fill the gameBoard array with pieces
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 8; j++){
                if ((i + j) % 2 == 1){
                    gameBoard[i][j] = 1;
                }
            }
        }
        for (int i = 5; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ((i + j) % 2 == 1){
                    gameBoard[i][j] = 2;
                }
            }
        }

        

    }

    public PIECE_COLOR getTurn(){
        return turn;
    }
    public String getGameStatus(){
        if (gameOver){
            return "Game Over!";
        }
        else{
            return "Game: In Progress";
        }
    }
    public String getPiece(int x, int y){

        //(gameBoard[x][y]<0) ? (-gameBoard[x][y]) : (gameBoard[x][y])
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

    //recursive function to check if a move is valid
    //returns true if the move is valid
    //returns false if the move is invalid


    // public boolean isValidMove(int x, int y, int x2, int y2, boolean isJump, boolean isKing, PIECE_COLOR color){
    //     //if the move is out of bounds
    //     if (x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7){
    //         return false;
    //     }
    //     //if the move is to the same spot
    //     if (x == x2 && y == y2){
    //         return false;
    //     }
    //     //if the move is to a spot that is not empty
    //     if (gameBoard[x2][y2] != 0){
    //         return false;
    //     }
    //     //if the move is not a jump
    //     if (!isJump){
    //         //if the move is not a king
    //         if (!isKing){
    //             //if the move is not in the right direction
    //             if (color == PIECE_COLOR.RED){
    //                 if (x2 < x){
    //                     return false;
    //                 }
    //             }
    //             else{
    //                 if (x2 > x){
    //                     return false;
    //                 }
    //             }
    //         }
    //         //if the move is a king
    //         else{
    //             //if the move is not in the right direction
    //             if (color == PIECE_COLOR.RED){
    //                 if (x2 < x && y2 < y){
    //                     return false;
    //                 }
    //             }
    //             else{
    //                 if (x2 > x && y2 > y){
    //                     return false;
    //                 }
    //             }
    //         }
    //     }
    //     //if the move is a jump
    //     else{
    //         //if the move is not a king
    //         if (!isKing){
    //             //if the move is not in the right direction
    //             if (color == PIECE_COLOR.RED){
    //                 if (x2 < x){
    //                     return false;
    //                 }
    //             }
    //             else{
    //                 if (x2 > x){
    //                     return false;
    //                 }
    //             }
    //         }
    //         //if the move is a king
    //         else{
    //             //if the move is not in the right direction
    //             if (color == PIECE_COLOR.RED){
    //                 if (x2 < x && y2 < y){
    //                     return false;
    //                 }
    //             }
    //             else{
    //                 if (x2 > x && y2 > y){
    //                     return false;
    //                 }
    //             }
    //         }
    //         //if the move is a jump, check if there is a piece to jump
    //         if (color == PIECE_COLOR.RED){
    //             if (gameBoard[(x + x2) / 2][(y + y2) / 2] != 2 && gameBoard[(x + x2) / 2][(y + y2) / 2] != 20){
    //                 return false;
    //             }
    //         }
    //         else{
    //             if (gameBoard[(x + x2) / 2][(y + y2) / 2] != 1 && gameBoard[(x + x2) / 2][(y + y2) / 2] != 10){
    //                 return false;
    //             }
    //         }

    //     }
    //     return true;
    // }

    // public int[][] legalMoves(Tile tile){
    //     //check if digonally adjacent tiles are empty, if not, check if they are occupied by an enemy piece, and if so, check if the tile behind it is empty, if so, return that tile as a legal move
        
    //     for(int i = 0; i < 8; i++){
    //         for(int j = 0; j < 8; j++){
    //             if(gameBoard[i][j] == 1){
    //                 //red piece
    //                 if(gameBoard[i+1][j+1] == 0){
    //                     //empty tile
    //                 }
    //                 else if(gameBoard[i+1][j+1] == 2){
    //                     //black piece
    //                     if(gameBoard[i+2][j+2] == 0){
    //                         //empty tile
    //                     }
    //                 }
    //                 else if(gameBoard[i+1][j-1] == 0){
    //                     //empty tile
    //                 }
    //                 else if(gameBoard[i+1][j-1] == 2){
    //                     //black piece
    //                     if(gameBoard[i+2][j-2] == 0){
    //                         //empty tile
    //                     }
    //                 }
    //             }
    //             else if(gameBoard[i][j] == 2){
    //                 //black piece
    //                 if(gameBoard[i-1][j+1] == 0){
    //                     //empty tile
    //                 }
    //                 else if(gameBoard[i-1][j+1] == 1){
    //                     //red piece
    //                     if(gameBoard[i-2][j+2] == 0){
    //                         //empty tile
    //                     }
    //                 }
    //                 else if(gameBoard[i-1][j-1] == 0){
    //                     //empty tile
    //                 }
    //                 else if(gameBoard[i-1][j-1] == 1){
    //                     //red piece
    //                     if(gameBoard[i-2][j-2] == 0){
    //                         //empty tile
    //                     }
    //                 }
    //             }
    //         }
    //     }

    //     return new int[][]{{0,0}};
    // }
//if(gameBoard[tile[0]][tile[1]] == color.toByte() && gameBoard[tile[0]][tile[1]] == color.toKingByte()){ //same color


    //TODO  none of this works
    public int[] legalTileJumpHelper(int[] tile, PIECE_COLOR color, boolean isKing, int iter){
        int result = legalTilesHelper(tile, color);
        if(result==-1){ //possible jump
            //(+1, +1),(+1, -1),(-1, +1),(-1, -1)
            switch(iter){
                case 0:
                    if(gameBoard[tile[0]+1][tile[1]+1] == color.toByte() && gameBoard[tile[0]+1][tile[1]+1] == color.toKingByte()){ //same color
                        return new int[]{-99};
                    }
                    else if(gameBoard[tile[0]+1][tile[1]+1] == color.opposite().toByte() || gameBoard[tile[0]+1][tile[1]+1] == color.opposite().toKingByte()){ //opposite color
                        if(gameBoard[tile[0]+2][tile[1]+2] == 0){ //empty tile
                            return new int[]{tile[0]+2,tile[1]+2};
                        }
                        else{
                            return new int[]{-99};
                        }
                    }
                    else{
                        return new int[]{-99};
                    }
                case 1:
                    if(gameBoard[tile[0]+1][tile[1]-1] == color.toByte() && gameBoard[tile[0]+1][tile[1]-1] == color.toKingByte()){ //same color
                        return new int[]{-99};
                    }
                    else if(gameBoard[tile[0]+1][tile[1]-1] == color.opposite().toByte() || gameBoard[tile[0]+1][tile[1]-1] == color.opposite().toKingByte()){ //opposite color
                        if(gameBoard[tile[0]+2][tile[1]-2] == 0){ //empty tile
                            return new int[]{tile[0]+2,tile[1]-2};
                        }
                        else{
                            return new int[]{-99};
                        }
                    }
                    else{
                        return new int[]{-99};
                    }
                case 2:
                    if(gameBoard[tile[0]-1][tile[1]+1] == color.toByte() && gameBoard[tile[0]-1][tile[1]+1] == color.toKingByte()){ //same color
                        return new int[]{-99};
                    }
                    else if(gameBoard[tile[0]-1][tile[1]+1] == color.opposite().toByte() || gameBoard[tile[0]-1][tile[1]+1] == color.opposite().toKingByte()){ //opposite color
                        if(gameBoard[tile[0]-2][tile[1]+2] == 0){ //empty tile
                            return new int[]{tile[0]-2,tile[1]+2};
                        }
                        else{
                            return new int[]{-99};
                        }
                    }
                    else{
                        return new int[]{-99};
                    }
                case 3:
                    if(gameBoard[tile[0]-1][tile[1]-1] == color.toByte() && gameBoard[tile[0]-1][tile[1]-1] == color.toKingByte()){ //same color
                        return new int[]{-99};
                    }
                    else if(gameBoard[tile[0]-1][tile[1]-1] == color.opposite().toByte() || gameBoard[tile[0]-1][tile[1]-1] == color.opposite().toKingByte()){ //opposite color
                        if(gameBoard[tile[0]-2][tile[1]-2] == 0){ //empty tile
                            return new int[]{tile[0]-2,tile[1]-2};
                        }
                        else{
                            return new int[]{-99};
                        }
                    }
                    else{
                        return new int[]{-99};
                    }
                default:
                    return new int[]{-99};




            }


        }
        if(result==1){
            switch(iter){
                case 0:
                    return new int[]{tile[0]+1,tile[1]+1};
                case 1:
                    return new int[]{tile[0]+1,tile[1]-1};
                case 2:
                    return new int[]{tile[0]-1,tile[1]+1};
                case 3:
                    return new int[]{tile[0]-1,tile[1]-1};
                default:
                    return new int[]{-99};
            }
        }
        return new int[]{-99};
    }
    public int legalTilesHelper(int[] tile, PIECE_COLOR color){
        //+1, +1, +1, -1, -1, +1, -1, -1 
        // /
        // int[][] toReturn = new int[][]{{x+1, y+1}, {x+1, y-1}, {x-1, y+1}, {x-1, y-1}};
        if (tile[0] < 0 || tile[0] > 7 || tile[1] < 0 || tile[1] > 7){
            return 0;
        }
        if(gameBoard[tile[0]][tile[1]] != 0){ 
            if(gameBoard[tile[0]][tile[1]] == color.toByte() || gameBoard[tile[0]][tile[1]] == color.toKingByte()){ //same color
                return 0;
            }
            else{ //different color
                return -1;
            }
        }

        return 1;

    }
    public int[][] legalTiles(int x, int y, PIECE_COLOR color, boolean isKing){
        int[][] toReturn;
        if(isKing){
            toReturn = new int[][]{{x+1, y+1}, {x+1, y-1}, {x-1, y+1}, {x-1, y-1}};
        }
        else{
            if(color == PIECE_COLOR.RED){ //red moves down
                toReturn = new int[][]{{x+1, y+1}, {x+1, y-1}};
            }
            else{ //black moves up
                toReturn = new int[][]{{x-1, y+1}, {x-1, y-1}};
            }
        }
        
        int iter = 0;
        for (int i=0; i<toReturn.length;i++){
            int[] returnedTile = legalTileJumpHelper(toReturn[i], color, isKing, iter);
            if(returnedTile[0] == -99){
                toReturn[i] = new int[]{-99};
            }
            else{
                toReturn[i] = returnedTile;
            }
            iter++;
            // if(legalTilesHelper(tile, color)==0){
            //     toReturn=removeElement(toReturn, tile);
            // }
        }



        return toReturn;

        // if (tile[0] < 0 || tile[0] > 7 || tile[1] < 0 || tile[1] > 7){
        //     toReturn = removeElement(toReturn, tile);
        // }
        // if(gameBoard[tile[0]][tile[1]] != color.toByte() && gameBoard[tile[0]][tile[1]] != color.toKingByte()){
        //     toReturn = removeElement(toReturn, tile);
        // }
        //return new int[][]{{0,0}};
    }


    private int[][] removeElement(int[][] toReturn, int[] tile) {
        int[][] toReturn2 = new int[toReturn.length - 1][2];
        int index = 0;
        for (int[] tile2 : toReturn){
            if (tile2 != tile){
                toReturn2[index] = tile2;
                index++;
            }
        }
        return toReturn2;
    }
    public int[][] addElement(int[][] toReturn, int[] tile){
        int[][] toReturn2 = new int[toReturn.length + 1][2];
        int index = 0;
        for (int[] tile2 : toReturn){
            toReturn2[index] = tile2;
            index++;
        }
        toReturn2[index] = tile;
        return toReturn2;
    }

    public int isLegalMove(int tileX,int tileY){
        //0 not legal move
        //1 legal move
        //2 legal jump

        

        //check if
        //  space is not occupied by a piece
        //  within 1x1 of the piece
        //  is regular, only move forwards
        //  is king, can move forwards or backwards
        //  is a jump

        //if the tile is diagonally adjacent to the pieceHeld

        //x and y are backwards....
        //TODO FIX, for now I just swapped them for this
        //if tile is within a 1 range of motion
        if (Math.abs(tileX - pieceHeld.pGetX()) == 1 && Math.abs(tileY - pieceHeld.pGetY()) == 1){
            //if the tile is empty
            if (gameBoard[tileX][tileY] == 0){
                //if the pieceHeld is a regular piece
                if (pieceHeld.isKing == false){
                    //if the pieceHeld is red
                    if (pieceHeld.getColor() == PIECE_COLOR.RED){ //-> red moves down, black moves up
                        //if the tile is below the pieceHeld
                        if (tileX > pieceHeld.pGetX()){
                            return 1;
                        }
                    }
                    //if the pieceHeld is black
                    else{
                        //if the tile is above the pieceHeld
                        if (tileX < pieceHeld.pGetX()){
                            return 1;
                        }
                    }
                }
                //if the pieceHeld is a king
                //were not concerned with direction
                //and we already know the tile is empty
                else{
                    
                    return 1;
                }
            }
            
        }
        //if its a jump
        if(Math.abs(tileX - pieceHeld.pGetX()) == 2 && Math.abs(tileY - pieceHeld.pGetY()) == 2){
            //if the tile is empty
            if (gameBoard[tileX][tileY] == 0){
                int pieceAtTile=gameBoard[(tileX + pieceHeld.pGetX()) / 2][(tileY + pieceHeld.pGetY()) / 2];
                //if the pieceHeld is a regular piece
                if (pieceHeld.isKing == false){
                    //if the pieceHeld is red
                    if (pieceHeld.getColor() == PIECE_COLOR.RED){ //-> red moves down, black moves up
                        //if the tile is below the pieceHeld
                        if (tileX > pieceHeld.pGetX()){
                            //if the tile in between is occupied by a black piece
                            if (pieceAtTile == 2 || pieceAtTile == 20){
                                gameBoard[(tileX + pieceHeld.pGetX()) / 2][(tileY + pieceHeld.pGetY()) / 2]=0;
                                return 2;
                            }
                        }
                    }
                    //if the pieceHeld is black
                    else{
                        //if the tile is above the pieceHeld
                        if (tileX < pieceHeld.pGetX()){
                            //if the tile in between is occupied by a red piece
                            if (pieceAtTile == 1 || pieceAtTile == 10){
                                gameBoard[(tileX + pieceHeld.pGetX()) / 2][(tileY + pieceHeld.pGetY()) / 2]=0;
                                return 2;
                            }
                        }
                    }
                }
                //if the pieceHeld is a king
                else{
                    //if the tile in between is occupied by a red piece
                    if (pieceAtTile == 1 || pieceAtTile == 10){
                        gameBoard[(tileX + pieceHeld.pGetX()) / 2][(tileY + pieceHeld.pGetY()) / 2]=0;
                        return 2;
                    }
                    //if the tile in between is occupied by a black piece
                    if (pieceAtTile == 2 || pieceAtTile == 20){
                        gameBoard[(tileX + pieceHeld.pGetX()) / 2][(tileY + pieceHeld.pGetY()) / 2]=0;
                        return 2;
                    }
                }
            }
        }
        


        return 0;
    }

    public int[] tilesAffected(int tileX, int tileY){
        //TODO
        //find all tiles between the pieceHeld and the tile
        //if the pieceHeld is a regular piece
        System.out.println();
        int directionX = ((tileX - pieceHeld.pGetX()) < 0) ? -1 : 1;
        int directionY = ((tileY - pieceHeld.pGetY()) < 0) ? -1 : 1;
        if(Math.abs(tileX - pieceHeld.pGetX()) == 2){
            return new int[]{pieceHeld.pGetX()+directionX, pieceHeld.pGetY()+directionY};
        }   




        return null;
    }

    public boolean isLegalJump(){
        return true;
    }
    public boolean tryMove(Tile tile){
        //assert:
        // trySelect() == true

        //should never be the case, but just in case
        if(pieceHeld == null) return false;

        //TODO 
        // if pieceColor == turn
        // select the piece
        // if next click is an open tile
        // check if move is legal
        // if legal, move the piece
        // if not legal, deselect the piece

        //king can move backwards, regular can only move forwards
        //if a piece can jump, it can choose to jump
        //if a piece reaches the end of the board, it becomes a king
        
        int legalMove = isLegalMove(tile.getTileX(),tile.getTileY());
        if(legalMove>0){

            gameBoard[tile.getTileX()][tile.getTileY()]=pieceHeld.valueForGrid();
            //if reach the end of the board, make king
            if (pieceHeld.getColor() == PIECE_COLOR.RED && tile.getTileX() == 7 && pieceHeld.isKing == false){
                System.out.println("Promoted to king");
                gameBoard[tile.getTileX()][tile.getTileY()]*=10;
            }
            if (pieceHeld.getColor() == PIECE_COLOR.BLACK && tile.getTileX() == 0 && pieceHeld.isKing == false){
                System.out.println("Promoted to king");
                gameBoard[tile.getTileX()][tile.getTileY()]*=10;
            }

            //move the piece
            //update the gameBoard array
            //update the turn
            //update the pieceHeld
            
            //update the gameBoard array with the moved tile
            

            //clear the old tile
            gameBoard[pieceHeld.pGetX()][pieceHeld.pGetY()]=0;



            //TODO this
            //update the turn
            //on a jump, if there is another valid jump you can go again. 
            //if(legalMove==1){
                turn = (turn == PIECE_COLOR.RED) ? PIECE_COLOR.BLACK : PIECE_COLOR.RED;
            //}
            
            if(legalMove==2){ //chance on capture for game to end
                //check if any pieces of the opposite color left on board
                //if not, end game
                //if so, continue
                if(anyLeft(turn)==false){
                    gameOver=true;
                    winner = (turn == PIECE_COLOR.RED) ? PIECE_COLOR.BLACK : PIECE_COLOR.RED;
                }
                
            }

            //updating board drawing done in GUI.java
            //resetting pieceHeld done in GUI.java, as it needs the values to update board drawing

            return true;
        }
        else{
            //deselect the piece
            resetPH();
            return false;
        }


        // game.gameBoard[currtileX][currtileY]=game.pieceHeld.valueForGrid();
                
        // int x = game.pieceHeld.pGetX();
        // int y = game.pieceHeld.pGetY();
        
        // game.gameBoard[x][y]=0;
    }

    public boolean anyLeft(PIECE_COLOR color){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(gameBoard[i][j]==color.toByte() || gameBoard[i][j]==color.toByte()*10){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean trySelect(Piece piece){
        if(piece.getColor() == turn){
            return true;
        }
        else{
            return false;
        }
    }


    public void resetPH(){
        pieceHeld.setBorder(null);
        pieceHeld=null;
    }
}