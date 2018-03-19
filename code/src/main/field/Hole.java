package main.field;

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
	public boolean getIsActive(){
	return isActive;
	}

	public void setIsActive(boolean b){
	isActive=b;
	}
	
	public void setActive() {
		if(this.getIsActive){
			this.setIsActive(false);
		}
		else{
			this.setIsActive(true);
		}
	}

	/**
	 * @param movable
	 * @return
	 */
	public boolean accept(Movable movable) {
		if(m.visit(this)){
			game.getTable().kill(m);
			return true;
		}
		else{
			return false;
		}
	}

}
