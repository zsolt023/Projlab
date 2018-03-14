package main.field;

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
		// TODO implement here
		return false;
	}

}