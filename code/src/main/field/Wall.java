package main.field;

import main.ImagePanel;
import main.Util;
import main.movable.Movable;


public class Wall extends Field {

    public Wall() {
        //Default constructor
    }

    /**
     * Ez a mező befogadó függvénye. Első körben meghívja a paraméterben kapott movable objektumon a visit függvényt.
     * Itt a visit függvény csak akkor tér vissza igaz értékkel, ha egy munkást falnak tolnak.
     * Ekkor lekérjük a tolt munkás érkező mezőjét. Ezen mezőt nullázzuk, majd a munkást is beállítjuk erre a mezőre
     * (az utolsó beállításra egyébként nem lenne szükség).
     * És végül vissza térünk igaz értékkel. Az összes többi esetben a visit hamissal tér vissza,
     * ami által ezen függvény is hamissal tér majd vissza.
     *
     * @param movable
     * @return boolean
     */
    @Override
    public boolean accept(Movable movable) {
        if (movable.visit(this)) {
            Field previousField = movable.getActualField();
            previousField.setActualMovable(null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw() {
        String[] idWithKoord = this.getId().split("_");
        ImagePanel imagePanel = new ImagePanel("code/res/obj/wall.jpg", Integer.parseInt(idWithKoord[1])* 30, Integer.parseInt(idWithKoord[2]) * 30);
        imagePanel.paintComponents(imagePanel.graphics);
        Util.frame.getContentPane().add(imagePanel);
    }
    
}