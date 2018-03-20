package main.field;

import main.Game;
import main.movable.Movable;


public class Switch extends Field {

    public Switch() {
    }

    private Hole hole;

    public Hole getHole() {
        if (Game.getInstance().printing) {
            Game.getInstance().tabs++;
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".getHole()");
        }
        Game.getInstance().printTabs();
        System.out.println("< " + hole.getId());
        return hole;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }

    public void switchState() {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".switchState()");
        }
        Hole hole = this.getHole();
        hole.setActive();
        Game.getInstance().tabs--;
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