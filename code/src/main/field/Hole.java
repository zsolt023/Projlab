package main.field;

import main.Game;
import main.movable.Movable;

/**
 * 
 */
public class Hole extends Field {

	/**
	 * Default constructor
	 */
	public Hole() {
	}

	/**
	 * 
	 */
	private boolean isActive;

	/**
	 * 
	 */
	private Switch s;

	/**
	 * 
	 */
	public void setActive() {
		if (Game.printing)
            System.out.println("Called Class name: " + Hole.class.getSimpleName() 
                    + " :: Method name: setActive :: Parameters: :: return: void");
            Movable movable = this.getActualMovable();
            if (movable != null) {
                Game.getInstance().getTable().kill(movable);
            }
            if (isActive) {
                isActive = false;
            } else {
                isActive = true;
            }
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