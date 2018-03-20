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
		if(movable.visit(this)){
			Game.tabs--;
			Game.tabs++;
			Game.getInstance().getTable().kill(moveable);
			Game.getInstance().getActualMovingWorker().addPoint();
			this.setActualMoveable(null);
			Game.tabs--;
		}
		else {
            		Game.tabs--;
            		Game.printTabs();
            		System.out.println("< false");
            		return false;
		}

}
