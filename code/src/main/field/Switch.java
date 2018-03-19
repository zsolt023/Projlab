package main.field;

import main.Game;
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
            if (Game.printing)
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
        if (Game.printing)
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
        if (Game.printing)
            System.out.println("Called Class name: " + Switch.class.getSimpleName() 
                    + " :: Method name: accept :: Parameters: Movable with id: " + movable.getId() + ":: return: boolean");
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