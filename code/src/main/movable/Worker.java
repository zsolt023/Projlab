package main.movable;

import main.Game;
import main.field.Hole;
import main.field.HoneyPlain;
import main.field.Objective;
import main.field.OilPlain;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;


public class Worker extends Movable {

    private int score = 0;

    private int force = 20;
    
    public Worker() {
        
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
    
    public void addPoint() {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".addPoint()");
            Game.getInstance().printTabs();
            System.out.println("< void");
        }
        this.score++;
    }

    @Override
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
        }
        Game.getInstance().tabs--;
        Game.getInstance().printTabs();
        System.out.println("< false");
        return false;
    }

    @Override
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
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    @Override
    public boolean visit(HoneyPlain honeyPlain) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + honeyPlain.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = honeyPlain.getActualMovable();
        
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
            
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }
    
    @Override
    public boolean visit(OilPlain oilPlain) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + oilPlain.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = oilPlain.getActualMovable();
        
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
            
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    @Override
    public boolean visit(Wall wall) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + wall.getId() + ")");
        }
        Game.getInstance().tabs++;
        Worker actualMovingWorker = Game.getInstance().getActualMovingWorker();

        if (!actualMovingWorker.getId().equals(this.getId())) {
            if (actualMovingWorker.getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().getTable().kill(this);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");                
                Game.getInstance().tabs--;
                return true;
            }
        }
        Game.getInstance().setActualChainFriction(0);
        Game.getInstance().printTabs();
        System.out.println("< false");
        return false;
    }

    @Override
    public boolean visit(Objective objective) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + objective.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = objective.getActualMovable();
        
        if (movable != null) {
            if (movable.visit(this)) {
                //TODO valamelyik kell a 2bol
                objective.setActualMovable(this);
                this.setActualField(objective);
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
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                //TODO valamelyik kell a 2bol
                objective.setActualMovable(this);
                this.setActualField(objective);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    @Override
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
                if (!(movable instanceof Box)) {
                    s.switchState();
                }
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
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                s.switchState();
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    @Override
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
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    @Override
    public boolean visit(Worker worker) {
        if (Game.getInstance().printing){
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + worker.getId() + ")");
        }
        
        Game.getInstance().printTabs();
        System.out.println("< false");
        return false;
    }

    @Override
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