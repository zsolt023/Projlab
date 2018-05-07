/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author schnorb
 */
public class ImagePanel extends JLabel {
    
    private BufferedImage image;
    
    public Graphics graphics;
    
    private int x;
    
    private int y;

    public ImagePanel(String path, int x, int y) {
       try {                
          image = ImageIO.read(new File(path));
          this.x = x;
          this.y = y;
          this.graphics = image.getGraphics();
       } catch (IOException ex) {
            ex.printStackTrace();
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
}
