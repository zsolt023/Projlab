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
            System.out.println("Called Class name: " + Wall.class.getSimpleName() 
                    + " :: Method name: accept :: Parameters: Movable with id: " + movable.getId() + ":: return: void");
            if (movable.visit(this)) {
                Game.getInstance().getTable().kill(movable);
                this.setActualMovable(movable);
                Field previousField = movable.getActualField();
                previousField.setActualMovable(null);
                movable.setActualField(this);
                return true;
            } else {
                return false;
            }
	}

}