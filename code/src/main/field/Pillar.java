package main.field;

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
		if(m.visit(this)){
			return true;
		}
		else{
			return false;
		}
	}

}
