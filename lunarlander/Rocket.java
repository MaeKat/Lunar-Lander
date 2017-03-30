

package lunarlander;


import basicgraphics.BasicFrame;
import basicgraphics.CollisionEventType;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 *
 * @author sbrandt + vhoffm2/Vanessa Hoffmann
 */
public class Rocket extends Sprite {
    
    public Picture initialPic;
    /**
     * Initializes the sprite, setting its picture,
     * position, and speed. It also adds it to the
     * SpriteComponent.
     * 
     * @param sc
     * @throws IOException 
     */
    
    public static Picture makeRect(Color color,int width,int height) {
        
        Image im = BasicFrame.createImage(width, height);
        Graphics g = im.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
     
        return new Picture(im);
    }
    
    public void init(SpriteComponent sc) throws IOException {
        initialPic = makeRect(Color.red,20,30);
        setPicture(initialPic);
        Dimension d = sc.getSize();
        setX(10);
        setY(10);
        setVelX(0);
        setVelY(0);
        this.sc = sc;
        sc.addSprite(this);
    }
   
    public void upDown(double incr) {
      
        setVelY((incr+ getVelY()));
        setVelX(getVelX());
      
    }
      public void leftRight(double incr) {
        setVelY(getVelY());
        setVelX((incr+ getVelX()));
    }
    
    SpriteComponent sc;
    /**
     * Here we update the velocity and picture
     * if they need updating.
     */
    @Override
    public void preMove() {
        setVelY(getVelY()+.002);
    }
    /**
     * This sprite only reacts to collisions with the
     * borders of the display region. When it does, it
     * wraps to the other side.
     * @param se 
     */
      @Override
    public void processEvent(SpriteCollisionEvent se) {
      /*   if (se.eventType == CollisionEventType.WALL_INVISIBLE) {
            if(se.sprite2 != null) {
        } else {
            setActive(false);
        }
                JOptionPane.showMessageDialog(sc, "LOSER");
                System.exit(0);
        }
        */
      if(se.eventType == CollisionEventType.WALL) {
            if (se.xlo) {
                
                setX(sc.getSize().width - getWidth());
            }
            if (se.xhi) {
                
                setX(0);
            }
            if (se.ylo) {
                JOptionPane.showMessageDialog(sc, "You Crashed!): \n Your X-Velocity: "+getVelX()+"\n Your Y-Velocity: "+getVelY());
                System.exit(0);
            }
            if (se.yhi) {
               JOptionPane.showMessageDialog(sc, "You Crashed!    ): \n Your X-Velocity: "+getVelX()+"\n Your Y-Velocity: "+getVelY());
                System.exit(0);
            }
        }
        
        
         if (se.eventType == CollisionEventType.SPRITE) {
            if (se.sprite2 instanceof Lander) {
              if ((Math.abs(getVelX())>.1) ||(Math.abs(getVelY())>.4))
              {
               se.sprite2.setActive(false);
                JOptionPane.showMessageDialog(sc, "You Crashed!    ): \n Your X-Velocity: "+getVelX()+"\n Your Y-Velocity: "+getVelY());
                System.exit(0);
              }
              else
                se.sprite2.setActive(false);
                JOptionPane.showMessageDialog(sc, "You win!    :) \n Your X-Velocity: "+getVelX()+"\n Your Y-Velocity: "+getVelY());
                System.exit(0);
            }
        
        }
    }
}
