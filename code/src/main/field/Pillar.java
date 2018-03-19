package main.field;

import main.Game;
import main.movable.Movable;

/**
 * 
 */
public class Pillar extends Field {

	/**
	 * Default constructor
	 */
	public Pillar() {
	}

	/**
	 * @param movable
	 * @return
	 */
	public boolean accept(Movable movable) {
		if (movable.visit(this)){
			Game.getTable().kill(movable);
			movable.getActualField().setActualMovable(null);
			return true;
		}
		return false;
	}



}