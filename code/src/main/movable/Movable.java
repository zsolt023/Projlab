package main.movable;

import main.field.Field;
import main.field.Hole;
import main.field.Objective;
import main.field.Pillar;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;

/**
 * 
 */
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
            System.out.println("Called Class name: " + Movable.class.getSimpleName() 
                    + " :: Method name: getActualField :: Parameters: :: return: Field with id: " + actualField);
            return this.actualField;
	}

	public void setActualField(Field field) {
            System.out.println("Called Class name: " + Movable.class.getSimpleName() 
                    + " :: Method name: setActualField :: Parameters: Field with id: " + field.getId() + " :: return: void");
            this.actualField = field;
	}

	public abstract boolean move();

	public abstract boolean visit(Plain plain);

	public abstract boolean visit(Pillar pillar);

	public abstract boolean visit(Wall wall);

	public abstract boolean visit(Objective objective);

	public abstract boolean visit(Switch s);

	public abstract boolean visit(Hole hole);

	public abstract boolean visit(Worker worker);

	public abstract boolean visit(Box box);

}