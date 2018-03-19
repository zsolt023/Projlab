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

        public Hole getHole() {
            System.out.println("Called Class name: " + Switch.class.getSimpleName() 
                    + " :: Method name: getHole :: Parameters: :: return: Hole with id:" + hole.getId());
            return hole;
        }

        public void setHole(Hole hole) {
            this.hole = hole;
        }

	/**
	 * 
	 */
	public void switchState() {
            System.out.println("Called Class name: " + Switch.class.getSimpleName() 
                    + " :: Method name: switchState :: Parameters: :: return: void");
            Hole hole = this.getHole();
            hole.setActive();
	}

	/**
	 * @param movable
	 * @return
	 */
	public boolean accept(Movable movable) {
            System.out.println("Called Class name: " + Switch.class.getSimpleName() 
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