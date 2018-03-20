package main.field;

import main.Game;
import main.movable.Movable;


public class Hole extends Field {

    public Hole() {
    }

    private boolean isActive = true;

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setActive() {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".setActive()");
        }
        
        if (Game.getInstance().table.alternatives == 4) {
            Game.getInstance().printing = false;
            Game.getInstance().getTable().kill(null);
            Game.getInstance().printing = true;
        }
        
        Game.getInstance().tabs++;
        Movable movable = this.getActualMovable();
        Game.getInstance().tabs--;
        
        
        if (movable != null) {
            Game.getInstance().tabs++;
            Game.getInstance().getTable().kill(movable);
            Game.getInstance().tabs--;
        }
        if (isActive) {
            isActive = false;
        } else {
            isActive = true;
        }
        Game.getInstance().printTabs();
        System.out.println("< void");
    }

    public boolean accept(Movable movable) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".accept(" + movable.getId() + ")");
        }
        Game.getInstance().tabs++;
        if (movable.visit(this)) {
            if (Game.getInstance().table.alternatives == 7) {
                Game.getInstance().printing = false;
                Game.getInstance().getTable().kill(null);
                Game.getInstance().printing = true;
            }
            if (isActive) {
                Game.getInstance().getTable().kill(movable);
            } else {
                this.setActualMovable(movable);
            }

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