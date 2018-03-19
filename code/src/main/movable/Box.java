package main.movable;

import main.field.Hole;
import main.field.Objective;
import main.field.Pillar;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;

/**
 * 
 */
public class Box extends Movable {

	/**
	 * Default constructor
	 */
	public Box() {
	}

	/**
	 * 
	 */
	public boolean move() {
            System.out.println("Called Class name: " + Box.class.getSimpleName() 
                + " :: Method name: move :: Parameters: :: return: boolean");
            return this.actualField.getNeigbour().accept(this);
	}

	/**
	 * @param plain
	 * @return
	 */
	public boolean visit(Plain plain) {
            System.out.println("Called Class name: " + Box.class.getSimpleName() 
                    + " :: Method name: visit :: Parameters: Plain with id: " + plain.getId() + ":: return: void");
            Movable movable = plain.getActualMovable();
            if (movable != null) {
                return movable.visit(this);
            } else {
               return true;
            }
	}

	/**
	 * @param pillar
	 * @return
	 */
	public boolean visit(Pillar pillar) {
		// TODO implement here
		return false;
	}

	/**
	 * @param wall
	 * @return
	 */
	public boolean visit(Wall wall) {
		// TODO implement here
		return false;
	}

	/**
	 * @param objective
	 * @return
	 */
	public boolean visit(Objective objective) {
		// TODO implement here
		return false;
	}

	/**
	 * @param switch
	 * @return
	 */
	public boolean visit(Switch s) {
            System.out.println("Called Class name: " + Box.class.getSimpleName() 
                    + " :: Method name: visit :: Parameters: Plain with id: " + s.getId() + ":: return: void");
            Movable movable = s.getActualMovable();
            if (movable != null) {
                return movable.visit(this);
            } else {
                s.switchState();
                return true;
            }
	}

	/**
	 * @param hole
	 * @return
	 */
	public boolean visit(Hole hole) {
		// TODO implement here
		return false;
	}

	/**
	 * @param worker
	 * @return
	 */
	public boolean visit(Worker worker) {
            System.out.println("Called Class name: " + Box.class.getSimpleName() 
                    + " :: Method name: visit :: Parameters: Worker with id: " + worker.getId() + ":: return: void");
            return move();
	}

	/**
	 * @param box
	 * @return
	 */
	public boolean visit(Box box) {
		// TODO implement here
		return false;
	}

}