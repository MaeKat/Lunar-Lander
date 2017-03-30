/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunarlander;


import lunarlander.Rocket;
import basicgraphics.BasicFrame;
import basicgraphics.SpriteComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author vhoffm2
 */
public class MyLunarLander {
    public static void main(String[] args) throws IOException {
        BasicFrame bf = new BasicFrame("Rocket");
        final SpriteComponent sc = new SpriteComponent() {
            @Override
            public void paintBackground(Graphics g) {
                Dimension d = getSize();
                d.width =800;
                d.height =400;
                g.setColor(Color.black);
                g.fillRect(0, 0, d.width, d.height);
                final int NUM_STARS = 30;
                Random rand = new Random();
                rand.setSeed(0);
                g.setColor(Color.white);
                for(int i=0;i<NUM_STARS;i++) {
                    int diameter = rand.nextInt(5)+1;
                    int xpos = rand.nextInt(d.width);
                    int ypos = rand.nextInt(d.height);
                    g.fillOval(xpos, ypos, diameter, diameter);
                }
            }
        };
         sc.setPreferredSize(new Dimension(800,400));
        bf.add("Rocket",sc,0,0,1,1);
        bf.show();
        final Rocket r = new Rocket();
         final Lander l = new Lander();
        final double INCR = .02;
        bf.addKeyListener(new KeyAdapter() {   
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    r.leftRight( INCR);
                } else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    r.leftRight(-INCR);}
                    else if(ke.getKeyCode() == KeyEvent.VK_UP) {
                    r.upDown(-INCR*2);}
                   else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    r.upDown(INCR);}
                 
                  
                
            }
        });
        l.init(sc);
        r.init(sc);
        sc.start(0,10);
    }
}





