package main.field;

import main.Game;
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
            Field previousField = movable.getActualField();
            previousField.setActualMovable(null);
            movable.setActualField(this);
            
            Game.getInstance().tabs--;
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