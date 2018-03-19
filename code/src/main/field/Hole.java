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
		if (isActive){
			isActive = false;}
		else{
			if (actualMovable != null)
				Game.getTable().kill(actualMovable);
			isActive = true;
		}
	}

	/**
	 * @param movable
	 * @return
	 */
	public boolean accept(Movable movable) {
		if (movable.visit(this)){
			if (isActive) {
				Game.getTable().kill(movable);
				movable.getActualField().setActualMovable(null);
				return true;
			}else{
				setActualMovable(movable);
				movable.getActualField().setActualMovable(null);
				movable.setActualField(this);
				return true;
			}
		}
		return false;
	}

}