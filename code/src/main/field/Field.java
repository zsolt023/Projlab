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
	 * Azt a movable objektumot tárolja, ami éppen mozgásban van, ha van ilyen.
	 * Egyébként null.
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