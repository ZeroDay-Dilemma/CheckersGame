

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

    CheckersEngine game;
    Container gamePane;

    JPanel bottomPanel;
    JPanel boardPanelBase;
    JLayeredPane[][] boardTiles = new JLayeredPane[8][8]; //64 tiles
    


    JMenuBar menuBar;
    JMenu menu;
    JMenuItem newGame, exit;

    private final int SIZE_PIXEL = 80;
    private final int SCREEN_WIDTH = 840, SCREEN_HEIGHT = 640;
    private final int BOARD_WIDTH = 640, BOARD_HEIGHT = 640;
    private final int BOTTOM_PANEL_WIDTH = 200, BOTTOM_PANEL_HEIGHT = 640;

    
    public GUI(CheckersEngine game){
        this.game= game;

        setTitle("Checkers");
        setSize(SCREEN_WIDTH+100, SCREEN_HEIGHT+100); //SIZE_PIXEL x 8 = 800, + 200 for misc height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePane = getContentPane();
        gamePane.setLayout(new BorderLayout(0,0));
        //gamePane.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        
        boardPanelBase = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
            }
        };
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        boardPanelBase.setLayout(new GridLayout(8,8, 0,0));
        boardPanelBase.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        boardPanelBase.setMinimumSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        boardPanelBase.setMaximumSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        //boardPanelBase.setBorder(new LineBorder(Color.BLACK, 1));
        
        
                        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                
                boardTiles[i][j] = drawTile(i,j);
                //genPiece(i,j);
                //boardTiles[i*8+j] = new JLayeredPane();
                //boardTiles[i*8+j].add(genPiece(i,j));
                //boardTiles[i*8+j].setLayout(new GridLayout(1,1, 0,0));

                //boardTiles[i][j].setName("tileStack " + i + "," + j);
                
                boardPanelBase.add(boardTiles[i][j]);
            }
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(boardPanelBase);



        // boardPanelBase = new JPanel();
        // boardPanelBase.setLayout(new GridLayout(8,8, 0,0));
        // boardPanelBase.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        // boardPanelBase.setMinimumSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        // boardPanelBase.setMaximumSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        // //boardPanelBase.setBorder(new LineBorder(Color.BLACK, 5));
        
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2,1,0,0));
        bottomPanel.setPreferredSize(new Dimension(BOTTOM_PANEL_WIDTH,BOTTOM_PANEL_HEIGHT));
        bottomPanel.setMinimumSize(new Dimension(BOTTOM_PANEL_WIDTH,BOTTOM_PANEL_HEIGHT));
        bottomPanel.setMaximumSize(new Dimension(BOTTOM_PANEL_WIDTH,BOTTOM_PANEL_HEIGHT));
        bottomPanel.setBorder(new LineBorder(Color.RED, 1));

        


        //boardPanelBase.addMouseListener(this);
        bottomPanel.addMouseListener(this);

        
        
        // boardTiles=drawBoardTiles();
        // boardTiles[3].add(new JLabel(new ImageIcon(".\\lib\\img\\red.png")),new Integer(2),0);
        // for (int i = 0; i < 64; i++){
            
        //    //boardPanelBase.add(boardTiles[i]);
        //    panel.add(boardTiles[i]);
           
        // }
        
        

        //Menu Bar  
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);

        //Menu Items
            //New Game  
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);
        menu.add(newGame);
            
            //Exit
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        menu.add(exit);
        

        menu.getAccessibleContext().setAccessibleDescription(
            "The menu stuff. I just wanted to use this line of code tbh");
        
        //setJMenuBar(menuBar);
        

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER,0,0);
        flowLayout.setAlignOnBaseline(true);
        //boardPanelBase.setLayout(flowLayout);
        

        JPanel contentPanel = new JPanel(new BorderLayout());

        // bottomPanel.setBackground(Color.RED);

        contentPanel.add(menuBar, BorderLayout.NORTH);
        contentPanel.add(bottomPanel, BorderLayout.EAST);
        contentPanel.add(centerPanel);



        this.setContentPane(contentPanel);
        //this.setSize(SCREEN_WIDTH+100, SCREEN_HEIGHT+100);

        
        //gamePane.add(boardPanelBase, "Center");
        //gamePane.add(bottomPanel, "East");

        
  

        this.setVisible(true);
        //System.out.println(menu.getSize());
        //System.out.println(boardTiles[0].getSize());
        //System.out.println(boardPanelBase.getSize());
        //System.out.println(bottomPanel.getSize());
        System.out.println(gamePane.getSize());
        
    }

    //draws a single tile given the position
    //refers to the gameBoard array
    //input: position of tile
    //output: JLayeredPane with the tile
    

    

    private JLayeredPane drawTile(int x,int y){
        
        JLayeredPane tile = new JLayeredPane();
        // tile.setPreferredSize(new Dimension(SIZE_PIXEL,SIZE_PIXEL));
        // tile.setMinimumSize(new Dimension(SIZE_PIXEL,SIZE_PIXEL));
        // tile.setMaximumSize(new Dimension(SIZE_PIXEL,SIZE_PIXEL));
        //tile.setBorder(new LineBorder(Color.BLACK, 1));
        Tile greenTile = new Tile(TileColor.GREEN, x, y);
        Tile whiteTile = new Tile(TileColor.WHITE, x, y);

        greenTile.setName("Tile_Green "+ x +"-"+ y);
        whiteTile.setName("Tile_White "+ x +"-"+ y);

        greenTile.setBounds(0,0,SIZE_PIXEL,SIZE_PIXEL);
        whiteTile.setBounds(0,0,SIZE_PIXEL,SIZE_PIXEL);

        greenTile.addMouseListener(this);
        whiteTile.addMouseListener(this);
        if(y%2==0){ //lines 1,3,5,7
            if(x%2==0){ //columns 1,3,5,7
                tile.add(greenTile,new Integer(-1));
                //tile.add(new Tile(TileColor.GREEN), new Integer(-1));
            }
            else{ //columns 0,4,6
                tile.add(whiteTile,new Integer(-1));
                //tile.add(new Tile(TileColor.WHITE), new Integer(-1));
            }
        }
        else{ //lines 0,2,4,6
            if(x%2==0){ //columns 1,3,5,7
                tile.add(whiteTile,new Integer(-1));
                //tile.add(new Tile(TileColor.WHITE), new Integer(-1));
            }
            else{ //columns 0,4,6
                tile.add(greenTile,new Integer(-1));
                //tile.add(new Tile(TileColor.GREEN), new Integer(-1));
            }

        }
        
        tile.addMouseListener(this);
        //1 = red
        //2 = black
        //10 = red king
        //20 = black king
        if(game.gameBoard[x][y]!=0){
            tile.add(genPiece(x,y), new Integer(1));
        }
        return tile;
    }


    //TODO it keeps shifting a few pixels every placement?? it is never adjusted like that, it should be static
    private Piece genPiece(int x, int y){
        
        if(game.gameBoard[x][y]!=0){
            PIECE_COLOR color;
            boolean isKing=false;
            if(game.gameBoard[x][y]==1){
                color=PIECE_COLOR.RED;
            }
            else if(game.gameBoard[x][y]==2){
                color=PIECE_COLOR.BLACK;
            }
            else if(game.gameBoard[x][y]==10){
                color=PIECE_COLOR.RED;
                isKing=true;
            }
            else{
                color=PIECE_COLOR.BLACK;
                isKing=true;
            }
            Piece piece = new Piece(color, isKing,x,y);
            piece.setName("Piece_"+color.toString()+" "+ x +"-"+ y);
            piece.setBorder(new LineBorder(Color.BLACK, 1));
            piece.setBounds(8,8, SIZE_PIXEL-16,SIZE_PIXEL-16);
            piece.addMouseListener(this);

            //JPanel 64x64 black square
            // THIS WORKS PERFECTLY SO LIKE WHATS UP??

            // JPanel square = new JPanel();
            // square.setBounds(8,8,SIZE_PIXEL-16,SIZE_PIXEL-16);
            //square.add(piece);
            //square.addMouseListener(this);
            //return square;

            return piece;
            
        };

        return null;
        
    }

    public void updateTile(int x, int y){

        JLayeredPane tile = drawTile(x,y);

        //never need to remove base tile
        if(boardTiles[x][y].getComponent(0) instanceof Piece){
            boardTiles[x][y].remove(0);
        }
        
        //boardTiles[x*8+y].removeAll();
        if(game.gameBoard[x][y]!=0){ //if there is a piece on the tile
            boardTiles[x][y].add(genPiece(x,y),new Integer(1));
        }
        boardTiles[x][y].revalidate();
        boardTiles[x][y].repaint();
    }


    public void redrawBoard(){
        boardPanelBase.removeAll();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                boardPanelBase.add(drawTile(i,j));
            }
        }
        boardPanelBase.revalidate();
        boardPanelBase.repaint();
    }
    // private JLayeredPane[] drawBoardTiles(){
    //     JLayeredPane[] tiles = new JLayeredPane[64];
    //     for (int i = 0; i < 64; i++){
    //         tiles[i] = new JLayeredPane();
            
    //         if((i/8)%2 == 0){
    //             if(i%2 == 0){
    //                 tiles[i].add(new Tile(TileColor.GREEN), new Integer(2), -1);
    //                 //tiles[i].add(new JPanel((LayoutManager) new Tile(TileColor.GREEN)));
    //             }
    //             else{
    //                 tiles[i].add(new Tile(TileColor.WHITE), new Integer(2), -1);
    //             }
    //         }
    //         else{
    //             if(i%2 == 0){
    //                 tiles[i].add(new Tile(TileColor.WHITE), new Integer(2), -1);
    //             }
    //             else{
    //                 tiles[i].add(new Tile(TileColor.GREEN), new Integer(2), -1);
    //             }
    //         }
    //         tiles[i].setPreferredSize(new Dimension(SIZE_PIXEL,SIZE_PIXEL));
    //         tiles[i].setMinimumSize(new Dimension(SIZE_PIXEL,SIZE_PIXEL));
    //         tiles[i].setMaximumSize(new Dimension(SIZE_PIXEL,SIZE_PIXEL));
    //         tiles[i].setBorder(new LineBorder(Color.BLACK, 1));
    //         tiles[i].addMouseListener(this);
            
    //     }   
        
    //     return tiles;
    // }

    //SHIT CODE
    //just store the info in the tile or the piece
    //left for reference
    //TODO clean up all name stuff, not useful anymore

    private String[] nameToVals(String name){ 
        //name format: [PieceType]_[Color] [x]-[y]
        //ex "Piece_RED 0-0"

        String[] vals = new String[4];
        
        String[] split = name.split(" ");
        //split[0] = "Piece_RED"
        //split[1] = "0-0"

        //firstHalf[0] = "Piece"
        //firstHalf[1] = "RED"
        String[] firstHalf = split[0].split("_");

        String[] posHalf = split[1].split("-");
        //int x = Integer.parseInt(pos[0]);
        //int y = Integer.parseInt(pos[1]);

        
        return new String[]{firstHalf[0], firstHalf[1], posHalf[0], posHalf[1]};
    }
    // implements....
    // ActionListener, MouseListener, MouseMotionListener
    Piece placeHolder=null;
    @Override
    public void mousePressed(MouseEvent arg0) {


        
        if(arg0.getComponent().getName().contains("Tile")){
            String name = arg0.getComponent().getName();


            System.out.println(arg0.getComponent().getName());
            //print out type

            System.out.println(arg0.getComponent().getClass());
            if(placeHolder!=null){

                Tile currTile = (Tile) arg0.getComponent();
                game.gameBoard[currTile.getTileX()][currTile.getTileY()]=placeHolder.valueForGrid();

                
                int x = placeHolder.getX();
                int y = placeHolder.getY();
                
                game.gameBoard[x][y]=0;
                updateTile(x,y);

                updateTile(currTile.getTileX(),currTile.getTileY());


                placeHolder=null;
            }
        }

        else if(placeHolder!=null){
            //clear border

        }
        else if(arg0.getComponent() instanceof Piece){
            //TODO DRAW BORDER AROUND ACTIVE PIECE
            


            //setBorder(new LineBorder(Color.BLACK, 1));

            placeHolder = (Piece)arg0.getComponent();

            //print getX and getY
            System.out.println("Piece: "+placeHolder.getX()+"-"+placeHolder.getY());

            int x = placeHolder.getX();
            int y = placeHolder.getY();
            
            //game.gameBoard[x][y]=0;
            
            updateTile(placeHolder.getX(),placeHolder.getY());

            //redrawBoard();
            System.out.println(arg0.getComponent().getName());
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        
    }


}
