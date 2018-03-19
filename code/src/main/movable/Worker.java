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
public class Worker extends Movable {

	/**
	 * Default constructor
	 */
	public Worker() {
		score=0;
	}

	/**
	 * 
	 */
	private int score;

	/**
	 * 
	 */
	public void addPoint() {
		score++;
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
		return true;
	}

	/**
	 * @param wall
	 * @return
	 */
	public boolean visit(Wall wall) {
		return true;
	}

	/**
	 * @param objective
	 * @return
	 */
	public boolean visit(Objective objective) {
		if(objective.getActualMovable() != null){
			objective.getActualMovable().visit(this);
		}
		return true;
	}

	/**
	 * @param s
	 * @return
	 */
	public boolean visit(Switch s) {
		if(s.getActualMovable() != null){
			s.getActualMovable().visit(this);
		}
		return false;
	}

	/**
	 * @param hole
	 * @return
	 */
	public boolean visit(Hole hole) {
		if(hole.getActualMovable() != null){
			hole.getActualMovable().visit(this);
		}
		return false;
	}

	/**
	 * @param worker
	 * @return
	 */
	public boolean visit(Worker worker) {
		return false;
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