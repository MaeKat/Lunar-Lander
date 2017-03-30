
package lunarlander;



import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;


/**
 *
 * @author sbrandt + vhoffm2/Vanessa Hoffmann
 */
public class Lander extends Sprite {
    
    public Picture initialPic;
    /**
     * Initializes the sprite, setting its picture,
     * position, and speed. It also adds it to the
     * SpriteComponent.
     * 
     * @param sc
     * @throws IOException 
     */
    
    public static Picture makeRect(Color color,int w, int h) {
        Image im = BasicFrame.createImage(w, h);
        Graphics g = im.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        return new Picture(im);
    }
    
    public void init(SpriteComponent sc) throws IOException {
        
        setPicture(makeRect(Color.red,100,20));
        Dimension d = sc.getSize();
        setX(350);
        setY(380);
        setVelX(0);
        setVelY(0);
        this.sc = sc;
        sc.addSprite(this);
    }
    
   
    
    SpriteComponent sc;
    /**
     * Here we update the velocity and picture
     * if they need updating.
     */
    @Override
    public void preMove() {
       // setVelY(-.002);
    }
    /**
     * This sprite only reacts to collisions with the
     * borders of the display region. When it does, it
     * wraps to the other side.
     * @param se 
     */
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        if (se.xlo) {
            setX(sc.getSize().width-getWidth());
        }
        if (se.xhi) {
            setX(0);
        }
        if (se.ylo) {
            setY(sc.getSize().height-getHeight());
        }
        if (se.yhi) {
            setY(0);
        }
    }
}
