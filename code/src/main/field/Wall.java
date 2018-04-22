package main.field;

import main.Game;
import main.movable.Movable;


public class Wall extends Field {

    public Wall() {
    }

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