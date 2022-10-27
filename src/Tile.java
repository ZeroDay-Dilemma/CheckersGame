
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Tile extends JPanel {


    private TileColor color;
    private BufferedImage image;
    private Point positionOffset;

    private int tileX = 0;
    private int tileY = 0;
    
    private int sizePixel = 80;

    public TileColor getTileColor() {
        return color;
    }
    public String getTileFileName(){
        if (color == TileColor.GREEN){
            return "Tile_Green.png";
        }
        else{
            return "Tile_White.png";
        }

    }

    //getters
    public int getTileX(){
        return tileX;
    }
    public int getTileY(){
        return tileY;
    }


    public Tile(TileColor color, int x, int y) {
        this.tileX=x;
        this.tileY=y;
        this.color = color;
        try {
            // Load the image for the current file
            File img = new File(".\\lib\\img\\" + getTileFileName());
            image = ImageIO.read(img); 

            setBounds(0, 0, sizePixel, sizePixel);
        } catch(IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(sizePixel, sizePixel));
        setMinimumSize(new Dimension(sizePixel, sizePixel));
		setOpaque(false);
    }


    public Tile(TileColor color) {
        this.color = color;
        try {
            // Load the image for the current file
            File img = new File(".\\lib\\img\\" + getTileFileName());
            image = ImageIO.read(img); 

            setBounds(0, 0, sizePixel, sizePixel);
        } catch(IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(sizePixel, sizePixel));
        setMinimumSize(new Dimension(sizePixel, sizePixel));
		setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        BufferedImage img = image;

        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }	
}
