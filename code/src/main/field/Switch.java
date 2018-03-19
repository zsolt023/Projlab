package main.field;

import main.Game;
import main.movable.Movable;

/**
 *
 */
public class Switch extends Field {

    /**
     * Default constructor
     */
    public Switch() {
    }

    /**
     *
     */
    private Hole hole;

    public Hole getHole() {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".getHole()");
        }
        Game.printTabs();
        System.out.println("< " + hole.getId());
        return hole;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }

    /**
     *
     */
    public void switchState() {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".switchState()");
        }
        Hole hole = this.getHole();
        hole.setActive();
        Game.printTabs();
        System.out.println("< void");
    }

    /**
     * @param movable
     * @return
     */
    public boolean accept(Movable movable) {
        if (Game.printing)
            System.out.println("> " + this.getId() + ".accept(" + movable.getId() + ")");
        Game.tabs++;

        if (movable.visit(this)) {
            Game.tabs++;
            this.setActualMovable(movable);
            Game.tabs--;
            Game.tabs++;
            Field previousField = movable.getActualField();
            Game.tabs--;
            Game.tabs++;
            previousField.setActualMovable(null);
            Game.tabs--;
            Game.tabs++;
            movable.setActualField(this);
            Game.tabs--;

            Game.tabs--;
            Game.printTabs();
            System.out.println("< true");
            return true;
        } else {
            Game.tabs--;
            Game.printTabs();
            System.out.println("< false");
            return false;
        }
    }

}