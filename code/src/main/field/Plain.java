package main.field;

import main.movable.Movable;

/**
 * 
 */
public class Plain extends Field {

	/**
	 * Default constructor
	 */
	public Plain() {
	}

	/**
	 * @param movable
	 * @return
	 */
	public boolean accept(Movable movable) {
            System.out.println("Called Class name: " + Plain.class.getSimpleName() 
                    + " :: Method name: accept :: Parameters: Movable with id: " + movable.getId() + ":: return: void");
            if (movable.visit(this)) {                
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