/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.field;

import main.ImagePanel;
import main.Util;
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
    public void draw() {
        String[] idWithKoord = this.getId().split("_");
        ImagePanel imagePanel = new ImagePanel("code/res/obj/honey.jpg", Integer.parseInt(idWithKoord[1])* 30, Integer.parseInt(idWithKoord[2]) * 30);
        imagePanel.paintComponents(imagePanel.graphics);
        Util.frame.getContentPane().add(imagePanel);
    }
}
