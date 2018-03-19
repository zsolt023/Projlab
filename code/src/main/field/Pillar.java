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
		if (Game.printing){
			Game.printTabs();
			System.out.println("> " + this.getId() + ".accept(" + movable.getId() + ")");}

		Game.tabs++;
		if (movable.visit(this)) {
			Game.tabs++;
			Game.getInstance().getTable().kill(movable);
			Game.tabs--;

			Game.tabs++;
			this.setActualMovable(movable);
			Game.tabs--;

			Game.tabs++;
			Field previousField = movable.getActualField();
			Game.tabs--;

			Game.tabs++;
			previousField.setActualMovable(null);
			Game.tabs--;

			Game.tabs++;
			movable.setActualField(this);
			Game.tabs--;

			Game.tabs--;
			Game.printTabs();
			System.out.println("< true");
			return true;
		} else {
			Game.tabs--;
			Game.printTabs();
			System.out.println("< false");
			return false;
		}
	}

}