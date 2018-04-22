package main.movable;

import main.Game;
import main.field.Field;
import main.field.Hole;
import main.field.HoneyPlain;
import main.field.Objective;
import main.field.OilPlain;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;


public abstract class Movable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movable() {
    }

    protected Field actualField;

    public Field getActualField() {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".getActualField()");
            Game.getInstance().printTabs();
            System.out.println("< " + actualField.getId());
        }
        return this.actualField;
    }

    public void setActualField(Field field) {
        if (Game.getInstance().printing){
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".setActualField(" + field.getId() + ")");
            Game.getInstance().printTabs();
            Game.getInstance().tabs--;
            System.out.println("< void");
        }
        this.actualField = field;
    }

    public abstract boolean move();

    public abstract boolean visit(Plain plain);
    
    public abstract boolean visit(HoneyPlain honeyPlain);
    
    public abstract boolean visit(OilPlain oilPlain);

    public abstract boolean visit(Wall wall);

    public abstract boolean visit(Objective objective);

    public abstract boolean visit(Switch s);

    public abstract boolean visit(Hole hole);

    public abstract boolean visit(Worker worker);

    public abstract boolean visit(Box box);

}