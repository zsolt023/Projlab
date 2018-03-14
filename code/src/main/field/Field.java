package main.field;

import java.util.Map;

import main.Orientation;
import main.movable.Movable;

/**
 * 
 */
public abstract class Field {

	/**
	 * Default constructor
	 */
	public Field() {
	}

	/**
	 * 
	 */
	private Field neighbour;

	/**
	 * 
	 */
	protected Map<Orientation, Field> neighbours;

	/**
	 * Azt a movable objektumot t�rolja, ami �ppen mozg�sban van, ha van ilyen.
	 * Egy�bk�nt null.
	 */
	protected Movable actualMovable;

	/**
	 * @return
	 */
	public Movable getActualMovable() {
		// TODO implement here
		return null;
	}

	/**
	 * @param movable
	 */
	public void setActualMovable(Movable movable) {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public Field getNeigbour() {
		// TODO implement here
		return null;
	}

	/**
	 * @param movable
	 * @return
	 */
	public abstract boolean accept(Movable movable);

}