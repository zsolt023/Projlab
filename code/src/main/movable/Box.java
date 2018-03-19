package main.movable;

import main.Game;
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
		if(this.actualField.getNeigbour().accept(this))
			return true;
		return false;
	}

	/**
	 * @param plain
	 * @return
	 */
	public boolean visit(Plain plain) {
		if(plain.getActualMovable() != null){
			plain.getActualMovable().visit(this);
		}
		return true;
	}

	/**
	 * @param pillar
	 * @return
	 */
	public boolean visit(Pillar pillar) {

		return false;
	}

	/**
	 * @param wall
	 * @return
	 */
	public boolean visit(Wall wall) {

		return false;
	}

	/**
	 * @param objective
	 * @return
	 */
	public boolean visit(Objective objective) {
		if(objective.getActualMovable() != null){
			objective.getActualMovable().visit(this);
		}
		Game.getTable().kill(this);
		Game.getActualMovingWorker().addPoint();
		return true;
	}

	/**
	 * @param switch
	 * @return
	 */
	public boolean visit(Switch s) {
		if(s.getActualMovable() != null){
			s.getActualMovable().visit(this);
		}
		s.switchState();
		return true;
	}

	/**
	 * @param hole
	 * @return
	 */
	public boolean visit(Hole hole) {
		if(hole.getActualMovable() != null){
			hole.getActualMovable().visit(this);
		}
		// TODO implement here
		return true;
	}

	/**
	 * @param worker
	 * @return
	 */
	public boolean visit(Worker worker) {
		this.move();
		return true;
	}

	/**
	 * @param box
	 * @return
	 */
	public boolean visit(Box box) {
		this.move();
		return true;
	}

}