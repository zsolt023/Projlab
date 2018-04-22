package main.field;

import main.Game;
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
            Game.getInstance().tabs++;
            Field previousField = movable.getActualField();
            previousField.setActualMovable(null);
            
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