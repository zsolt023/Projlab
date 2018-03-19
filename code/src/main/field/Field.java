package main.field;

import java.util.EnumMap;
import java.util.Map;

import main.Game;
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
		 neighbours = new EnumMap<>(Orientation.class);
	}


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

		return actualMovable;
	}

	/**
	 * @param movable
	 */
	public void setActualMovable
	(Movable movable) {

		actualMovable = movable;
	}

	/**
	 * @return
	 */
	public Field getNeigbour() {
		return neighbours.get(Game.orientation);
	}

	/**
	 * @param movable
	 * @return
	 */
	public abstract boolean accept(Movable movable);

}