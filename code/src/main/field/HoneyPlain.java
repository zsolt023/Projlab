/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.field;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

import main.Game;
import main.movable.Movable;

/**
 * @author schnorb
 */
public class HoneyPlain extends Plain {

    public HoneyPlain() {
        this.frictionMultiplier = DEFAULT_FRICTION + 1;
    }

    @Override
    public boolean accept(Movable movable) {
        if (movable.visit(this)) {
            this.setActualMovable(movable);
            Field previousField = movable.getActualField();
            previousField.setActualMovable(null);
            movable.setActualField(this);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public ImageView draw() {
        ImageView ret = new ImageView();
        ret.setImage(Game.getInstance().honeyImageView.getImage());
        return ret;
    }
}
