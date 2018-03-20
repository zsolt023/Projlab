package main.movable;

import main.Game;
import main.field.Hole;
import main.field.Objective;
import main.field.Pillar;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;

/**
 *
 */
public class Box extends Movable {

    /**
     * Default constructor
     */
    public Box() {
    }

    /**
     *
     */
    public boolean move() {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".move()");
        }
        Game.tabs++;
        if (this.actualField.getNeigbour().accept(this)) {
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

    /**
     * @param plain
     * @return
     */
    public boolean visit(Plain plain) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + plain.getId() + ")");
        }
        Game.tabs++;
        Movable movable = plain.getActualMovable();
        Game.tabs--;

        Game.tabs++;
        if (movable != null) {
            if (movable.visit(this)) {
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
        } else {
            Game.printTabs();
            System.out.println("< true");
            return true;
        }
    }

    /**
     * @param pillar
     * @return
     */
    public boolean visit(Pillar pillar) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + pillar.getId() + ")");
        }
        return false;
    }

    /**
     * @param wall
     * @return
     */
    public boolean visit(Wall wall) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + wall.getId() + ")");
        }
        return false;
    }

    /**
     * @param objective
     * @return
     */
    public boolean visit(Objective objective) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + objective.getId() + ")");
        }
        Game.tabs++;
        Movable movable = objective.getActualMovable();
        Game.tabs--;

        Game.tabs++;
        if (movable != null) {
            if (movable.visit(this)) {
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
        } else {
            Game.tabs--;
            Game.printTabs();
            System.out.println("< true");
            return true;
        }
    }
    }

    /**
     * @param s
     * @return
     */
    public boolean visit(Switch s) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + s.getId() + ")");
        }
        Game.tabs++;
        Movable movable = s.getActualMovable();
        Game.tabs--;

        if (movable != null) {
            if (movable.visit(this)) {
                Game.printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            Game.tabs++;
            s.switchState();
            Game.tabs--;
            Game.printTabs();
            System.out.println("< true");
            return true;
        }
    }

    /**
     * @param hole
     * @return
     */
    public boolean visit(Hole hole) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + hole.getId() + ")");
        }
        Game.tabs++;
        Movable movable = hole.getActualMovable();
        Game.tabs--;

        Game.tabs++;
        if (movable != null) {
            if (movable.visit(this)) {
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
        } else {
            Game.tabs--;
            Game.printTabs();
            System.out.println("< true");
            return true;
        }
    }

    /**
     * @param worker
     * @return
     */
    public boolean visit(Worker worker) {
        if (Game.printing){
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + worker.getId() + ")");}
        Game.tabs++;
        if (move()) {
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

    /**
     * @param box
     * @return
     */
    public boolean visit(Box box) {
        if (Game.printing){
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + box.getId() + ")");}
        Game.tabs++;
        if (move()) {
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
