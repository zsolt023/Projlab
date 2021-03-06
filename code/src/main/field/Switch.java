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


public class Switch extends Field {

    /**
     * A kapcsolőhoz tartoző lyuk objectum referenciája.
     */
    private Hole hole;

    public Switch() {
        //Default constructor
    }

    /**
     * Visszaadja a kapcsolőhoz tartoző lyuk objektumot.
     *
     * @return Hole
     */
    public Hole getHole() {
        return hole;
    }

    /**
     * Beállítja a kapcsolóhoz tartoző lyuk objektumot a paraméterben kapott objektumra.
     *
     * @param hole
     */
    public void setHole(Hole hole) {
        this.hole = hole;
    }

    /**
     * Ezen metódus segítségével állíthatja át egy másik osztály az aktuális objektumhoz tartozó lyuk állapotát.
     * Első körben lekéri a kapcsolóhoz tartozó lyuk objektumot. Majd meghívja a lyuk objektumnak a setActive metódusát.
     */
    public void switchState() {
        Hole hole = this.getHole();
        hole.setActive();
    }

    /**
     * Ez a kapcsoló mező befogadó függvénye. Első körben tovább hív a láncolás következő visit hívással
     * és ezen visszatérési érték alapján, ha igazzal tért vissza az említett visit,
     * akkor beállítjuk ezen kapcsoló mezőre a paraméterben kapott movable-t, majd a movable érkezési mezőjét lekérjük,
     * ezen mezőt nullázzuk, és a movable aktuális mezőjét is beállítjuk ezen kapcsoló mezőre. Majd végül visszatérünk igaz értékkel.
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
    public ImageView draw() {
        ImageView ret = new ImageView();
        ret.setImage(Game.getInstance().switchImageView.getImage());
        return ret;
    }
    
}