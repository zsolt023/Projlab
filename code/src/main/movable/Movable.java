package main.movable;

import main.Drawable;
import main.field.Field;
import main.field.Hole;
import main.field.HoneyPlain;
import main.field.Objective;
import main.field.OilPlain;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;


public abstract class Movable implements Drawable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movable() {
        //Default constructor
    }

    protected Field actualField;

    /**
     * Visszaadja azon fieldet, amin épp áll.
     * @return Field
     */
    public Field getActualField() {
        return this.actualField;
    }
    
    /**
     * Beállítja a paraméterében kapot field objectumra magát.
     * @param field 
     */
    public void setActualField(Field field) {
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