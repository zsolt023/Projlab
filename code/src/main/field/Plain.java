package main.field;

import main.Game;
import main.movable.Movable;


public class Plain extends Field {

    public final int DEFAULT_FRICTION = 2;
    protected int frictionMultiplier;
    
    public Plain() {
        frictionMultiplier = DEFAULT_FRICTION;
    }

    public int getFrictionMultiplier() {
        return frictionMultiplier;
    }

    public void setFrictionMultiplier(int frictionMultiplier) {
        this.frictionMultiplier = frictionMultiplier;
    }

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