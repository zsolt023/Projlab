package main.field;

import main.Game;
import main.ImagePanel;
import main.Util;
import main.movable.Movable;


public class Hole extends Field {

    /**
     * Ezen attribútum tárolja a lyuk aktiválásának állapotát.
     */
    private boolean isActive = true;

    public Hole() {
        //Default constructor
    }

    /**
     * Visszaadja a lyuk aktuális állapotát, aktív-e vagy sem.
     *
     * @return boolean
     */
    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Lekéri első körben a lyukon szereplő movable-t, Ha ez a movable nem null, ebből kiderül,
     * hogy a lyuk előző állapota daaktív volt. Mivel váltás lesz ezért ezen movable-t egyből kill-elni lehet.
     * Majd negálja az isActive változót. Ha movable nem volt rajta eddig, akkor csak negálni kell az isActive változó értékét tehát,
     * ha eddig igaz volt, akkor hamis lesz és ha hamis volt igazzá változik.
     */
    public void setActive() {
        Movable movable = this.getActualMovable();

        if (movable != null) {
            Game.getInstance().getTable().kill(movable);
        }
        if (isActive) {
            isActive = false;
        } else {
            isActive = true;
        }

    }

    /**
     * Ez a mező befogadó függvénye. Első körben tovább hív a láncolás következő visit hívással és ezen visszatérési érték alapján,
     * ha igazzal tért vissza az említett visit, akkor megvizsgáljuk a lyuk aktív állapotban van-e,
     * ha igen akkor a rá lépni kívánt movable-t kill-eljük. ellenkező esetben ha nem volt aktív,
     * akkor beállítjuk a lyukra a paraméterben kapott movable-t ezen mezőre. És a movable aktuális field-jét lekérjük,
     * ami az a mező ahonnan érkezik a movable, és ezen mező aktuális movable-t nullázzuk.
     * és a movable mezőjét beállítjuk ezen lyukra, és végül vissza térünk igaz értékkel jelezve a lépés sikerességéről.
     * Ha a fentebb említett visit metódus hamis értékkel tért vissza, ebben az esetben egyből visszatérünk hamis értékkel
     * minden változtatás nélkül.
     *
     * @param movable
     * @return boolean
     */
    @Override
    public boolean accept(Movable movable) {
        if (movable.visit(this)) {
            if (isActive) {
                Game.getInstance().getTable().kill(movable);
            } else {
                this.setActualMovable(movable);
            }
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
        ImagePanel imagePanel;
        if (this.isActive) {
            imagePanel = new ImagePanel("code/res/obj/hole1.jpg", Integer.parseInt(idWithKoord[1])* 30, Integer.parseInt(idWithKoord[2]) * 30);
        } else {
            imagePanel = new ImagePanel("code/res/obj/hole2.jpg", Integer.parseInt(idWithKoord[1])* 30, Integer.parseInt(idWithKoord[2]) * 30);
        }
        imagePanel.paintComponents(imagePanel.graphics);
        Util.frame.getContentPane().add(imagePanel);
    }
    
}