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
public class Worker extends Movable {

    /**
     * Default constructor
     */
    public Worker() {
    }

    /**
     *
     */
    private int score;

    /**
     *
     */
    public void addPoint() {
        this.score++;
    }

    /**
     * @return
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
        }
        Game.tabs--;
        Game.printTabs();
        System.out.println("< false");
        return false;
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
            Game.tabs--;
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
        // TODO implement here
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
        Game.tabs++;
        Worker actualMovingWorker = Game.getInstance().getActualMovingWorker();
        Game.tabs--;

        if (!actualMovingWorker.getId().equals(this.getId())) {

            Game.printTabs();
            System.out.println("< true");
            return true;
        }

        Game.printTabs();
        System.out.println("< false");
        return false;
    }


    /**
     * @param objective
     * @return
     */
    public boolean visit(Objective objective) {
        // TODO implement here
        return false;
    }

    /**
     * @param s
     * @return
     */
    public boolean visit(Switch s) {
        // TODO implement here
        return false;
    }

    /**
     * @param hole
     * @return
     */
    public boolean visit(Hole hole) {
        // TODO implement here
        return false;
    }

    /**
     * @param worker
     * @return
     */
    public boolean visit(Worker worker) {
        if (Game.printing){
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + worker.getId() + ")");}

        Game.printTabs();
        System.out.println("< false");
        return false;
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