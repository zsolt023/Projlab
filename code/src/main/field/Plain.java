package main.field;

import main.ImagePanel;
import main.Util;
import main.movable.Movable;


public class Plain extends Field {

    /**
     * Segéd konstans.
     */
    public final int DEFAULT_FRICTION = 2;

    /**
     * A sürlódási együttható szorzőja sima mező esetén: 2, olajos mező eseten: 1, mézes mező esetén: 3
     */
    protected int frictionMultiplier;

    public Plain() {
        frictionMultiplier = DEFAULT_FRICTION;
    }

    /**
     * Visszaadja a súrlódási szorző értékét.
     *
     * @return int
     */
    public int getFrictionMultiplier() {
        return frictionMultiplier;
    }

    /**
     * Beállítja a súrlódási szorző értékét a paraméterben kapott értékre.
     *
     * @param frictionMultiplier
     */
    public void setFrictionMultiplier(int frictionMultiplier) {
        this.frictionMultiplier = frictionMultiplier;
    }

    /**
     * Ez a sima mező befogadó függvénye. Első körben tovább hív a láncolás következő visit hívással és ezen visszatérési érték alapján,
     * ha igazzal tért vissza az említett visit, akkor beállítjuk ezen sima mezőre a paraméterben kapott movable-t,
     * majd a movable érkezési mezőjét lekérjük, ezen mezőt nullázzuk, és a movable aktuális mezőjét is beállítjuk ezen sima mezőre.
     * Majd végül visszatérünk igaz értékkel.
     * Ellenkező esetben csak szimplán visszatérünk a visit visszatérési értéknek megfelelően hamis értékkel,
     * bármiféle átállítás nélkül.
     *
     * @param movable
     * @return boolean
     */
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
        ImagePanel imagePanel = new ImagePanel("code/res/obj/plain.jpg", Integer.parseInt(idWithKoord[1])* 30, Integer.parseInt(idWithKoord[2]) * 30);
        imagePanel.paintComponents(imagePanel.graphics);
        Util.frame.getContentPane().add(imagePanel);
    }
    
}