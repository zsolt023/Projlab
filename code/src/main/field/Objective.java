package main.field;

import main.Game;
import main.movable.Movable;


public class Objective extends Field {

    public Objective() {
    }

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