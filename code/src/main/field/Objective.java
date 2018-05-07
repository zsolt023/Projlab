package main.field;

import main.ImagePanel;
import main.Util;
import main.movable.Movable;


public class Objective extends Field {

    public Objective() {
        //Default constructor
    }

    /**
     * Ez a cél mező befogadó függvénye. Első körben tovább hív a láncolás következő visit hívással és ezen visszatérési érték alapján,
     * ha igazzal tért vissza az említett visit, akkor a movable érkezési mezőjét lekérjük, ezen mezőt nullázzuk,
     * és a movable aktuális mezőjét is beállítjuk ezen cél mezőre. Majd végül visszatérünk igaz értékkel.
     * Ellenkező esetben csak szimplán visszatérünk a visit visszatérési értéknek megfelelően hamis értékkel,
     * bármiféle átállítás nélkül.
     *
     * @param movable
     * @return boolean
     */
    @Override
    public boolean accept(Movable movable) {
        if (movable.visit(this)) {
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
        ImagePanel imagePanel = new ImagePanel("code/res/obj/objective.jpg", Integer.parseInt(idWithKoord[1])* 30, Integer.parseInt(idWithKoord[2]) * 30);
        imagePanel.paintComponents(imagePanel.graphics);
        Util.frame.getContentPane().add(imagePanel);
    }
    
}