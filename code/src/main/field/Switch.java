package main.field;

import main.movable.Movable;

/**
 * 
 */
public class Switch extends Field {

	/**
	 * Default constructor
	 */
	public Switch() {
	}

	/**
	 * 
	 */
	private Hole hole;

	/**
	 * 
	 */
	public void switchState() {
		hole.setActive();
	}

	/**
	 * @param movable
	 * @return
	 */
	public boolean accept(Movable movable) {
		if (movable.visit(this)) {
			setActualMovable(movable);
			movable.getActualField().setActualMovable(null);
			movable.setActualField(this);
			return true;
		}

		return false;
	}

}