package main.field;

import main.movable.Movable;

/**
 * 
 */
public class Objective extends Field {

	/**
	 * Default constructor
	 */
	public Objective() {
	}

	/**
	 * @param movable
	 * @return
	 */
	public boolean accept(Movable movable) {
		if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".accept(" + movable.getId() + ")");
        }
		return false;
	}

}
