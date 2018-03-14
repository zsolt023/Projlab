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
	}

	/**
	 * 
	 */
	private int score;

	/**
	 * 
	 */
	public void addPoint() {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void move() {
		// TODO implement here
	}

	/**
	 * @param plain
	 * @return
	 */
	public boolean visit(Plain plain) {
		// TODO implement here
		return false;
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
		// TODO implement here
		return false;
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
		// TODO implement here
		return false;
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