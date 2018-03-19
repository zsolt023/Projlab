package main.field;

import main.Game;
import main.movable.Movable;

/**
 * 
 */
public class Wall extends Field {

	/**
	 * Default constructor
	 */
	public Wall() {
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