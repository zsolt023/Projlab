package main.movable;

import main.Game;
import main.field.Hole;
import main.field.Objective;
import main.field.Pillar;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;


public class Box extends Movable {

    public Box() {
    }

    public boolean move() {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".move()");
        }
        Game.getInstance().tabs++;
        if (this.actualField.getNeigbour().accept(this)) {
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

    public boolean visit(Plain plain) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + plain.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = plain.getActualMovable();
        
        if (movable != null) {
            if (movable.visit(this)) {
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
        } else {
            if (Game.getInstance().table.alternatives == 3) {
                Game.getInstance().printing = false;
                Game.getInstance().getTable().kill(this);
                Game.getInstance().printing = true;
            }
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        }
    }

    public boolean visit(Pillar pillar) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + pillar.getId() + ")"); 
            Game.getInstance().printTabs();
            System.out.println("< false");
        }
        return false;
    }

    public boolean visit(Wall wall) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + wall.getId() + ")");
            Game.getInstance().printTabs();
            System.out.println("< false");
        }
        return false;
    }

    public boolean visit(Objective objective) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + objective.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = objective.getActualMovable();
        
        if (movable != null) {
            if (movable.visit(this)) {
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
        } else {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        }
    }

    public boolean visit(Switch s) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + s.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = s.getActualMovable();
        Game.getInstance().tabs--;

        if (movable != null) {
            if (movable.visit(this)) {
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            Game.getInstance().tabs++;
            s.switchState();
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        }
    }

    public boolean visit(Hole hole) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + hole.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = hole.getActualMovable();
        
        if (movable != null) {
            if (movable.visit(this)) {
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
        } else {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        }
    }

    public boolean visit(Worker worker) {
        if (Game.getInstance().printing){
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + worker.getId() + ")");
        }
        Game.getInstance().tabs++;
        
        if (move()) {
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

    public boolean visit(Box box) {
        if (Game.getInstance().printing){
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + box.getId() + ")");
        }
        Game.getInstance().tabs++;
        
        if (move()) {
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