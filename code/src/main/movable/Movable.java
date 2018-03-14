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

	/**
	 * Default constructor
	 */
	public Movable() {
	}

	/**
	 * 
	 */
	protected Field actualField;

	/**
	 * @return
	 */
	public Field getActualField() {
		// TODO implement here
		return null;
	}

	/**
	 * @param field
	 */
	public void setActualField(Field field) {
		// TODO implement here
	}

	/**
	 * 
	 */
	public abstract void move();

	/**
	 * @param plain
	 * @return
	 */
	public abstract boolean visit(Plain plain);

	/**
	 * @param pillar
	 * @return
	 */
	public abstract boolean visit(Pillar pillar);

	/**
	 * @param wall
	 * @return
	 */
	public abstract boolean visit(Wall wall);

	/**
	 * @param objective
	 * @return
	 */
	public abstract boolean visit(Objective objective);

	/**
	 * @param switch
	 * @return
	 */
	public abstract boolean visit(Switch s);

	/**
	 * @param hole
	 * @return
	 */
	public abstract boolean visit(Hole hole);

	/**
	 * @param worker
	 * @return
	 */
	public abstract boolean visit(Worker worker);

	/**
	 * @param box
	 * @return
	 */
	public abstract boolean visit(Box box);

}