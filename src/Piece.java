
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//asking for one piece jokes
//its just begging for some
//but i have some self restraint
//and dignaty
public class Piece extends JPanel{

    public boolean isKing=false;
    private PIECE_COLOR color;
    private BufferedImage image;
    private int pieceX; //used to store position, solely for tracking if movement error
    private int pieceY; //used to store position, solely for tracking if movement error

    private int sizePixel = 80;


    public String getFileName(){
        if(color==PIECE_COLOR.RED){
            if(isKing){
                return "Red_King.png";
            }
            else{
                return "Red_Regular.png";
            }
        }
        else{
            if(isKing){
                return "Black_King.png";
            }
            else{
                return "Black_Regular.png";
            }
        }
    }

    public Piece(PIECE_COLOR color, boolean isKing) {
        this.color = color;
        this.isKing = isKing;
        try {
            // Load the image for the current file
            File img = new File(".\\lib\\img\\" + getFileName());
            image = ImageIO.read(img); 

            setBounds(0, 0, sizePixel, sizePixel);
        } catch(IOException e) {
            e.printStackTrace();
        }



        setPreferredSize(new Dimension(sizePixel, sizePixel));
        setMinimumSize(new Dimension(sizePixel, sizePixel));
		setOpaque(false);
    }

    public Piece(PIECE_COLOR color, boolean isKing, int x, int y) {
        this.color = color;
        this.isKing = isKing;

        this.pieceX = x;
        this.pieceY = y;
        try {
            // Load the image for the current file
            File img = new File(".\\lib\\img\\" + getFileName());
            image = ImageIO.read(img); 

            setBounds(0, 0, sizePixel, sizePixel);
        } catch(IOException e) {
            e.printStackTrace();
        }



        setPreferredSize(new Dimension(sizePixel, sizePixel));
        setMinimumSize(new Dimension(sizePixel, sizePixel));
		setOpaque(false);

    }
    //1 = red
    //2 = black
    //10 = red king
    //20 = black king
    public byte valueForGrid(){
        if(color==PIECE_COLOR.RED){
            if(isKing){
                return 10;
            }
            else{
                return 1;
            }
        }
        else{
            if(isKing){
                return 20;
            }
            else{
                return 2;
            }
        }
    }
    //getters
    public PIECE_COLOR getColor() {
        return color;
    }
    public boolean getIsKing(){
        return isKing;
    }
    public int getX(){
        return pieceX;
    }
    public int getY(){
        return pieceY;
    }

    //setters
    public void setIsKing(boolean isKing){
        this.isKing = isKing;
    }
    public void pSetX(int x){
        this.pieceX = x;
    }
    public void pSetY(int y){
        this.pieceY = y;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        BufferedImage img = image;


        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }	

    
}
