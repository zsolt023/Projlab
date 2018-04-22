package main.field;

import main.Game;
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
     * @return int
     */
    public int getFrictionMultiplier() {
        return frictionMultiplier;
    }

    /**
     * Beállítja a súrlódási szorző értékét a paraméterben kapott értékre.
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
     * @param movable
     * @return boolean
     */
    @Override
    public boolean accept(Movable movable) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".accept(" + movable.getId() + ")");
        }

        Game.getInstance().tabs++;
        if (movable.visit(this)) {
            if (Game.getInstance().table.alternatives == 10) {
                Game.getInstance().getTable().kill(movable);
            }
            this.setActualMovable(movable);
            Field previousField = movable.getActualField();
            previousField.setActualMovable(null);
            movable.setActualField(this);

            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        } else {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< false");
            return false;
        }
    }

}