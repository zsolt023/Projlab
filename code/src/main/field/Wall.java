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
		if(m.visit(this)){
			return true;
		}
		else{
			return false;
		}
	}

}
