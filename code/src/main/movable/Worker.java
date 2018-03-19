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
            this.score++;
	}

	/**
	 * 
         * @return 
	 */
	public boolean move() {
            System.out.println("Called Class name: " + Worker.class.getSimpleName() 
                + " :: Method name: move :: Parameters: :: return: boolean");
            return this.actualField.getNeigbour().accept(this);
	}

	/**
	 * @param plain
	 * @return
	 */
	public boolean visit(Plain plain) {
            System.out.println("Called Class name: " + Worker.class.getSimpleName() 
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
            System.out.println("Called Class name: " + Worker.class.getSimpleName() 
                    + " :: Method name: visit :: Parameters: Wall with id: " + wall.getId() + ":: return: void");
            Worker actualMovingWorker = Game.getInstance().getActualMovingWorker();
            return !actualMovingWorker.getId().equals(this.getId());
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
            System.out.println("Called Class name: " + Worker.class.getSimpleName() 
                    + " :: Method name: visit :: Parameters: Worker with id: " + worker.getId() + ":: return: void");
            return false;
	}

	/**
	 * @param box
	 * @return
	 */
	public boolean visit(Box box) {
            System.out.println("Called Class name: " + Worker.class.getSimpleName() 
                    + " :: Method name: visit :: Parameters: Box with id: " + box.getId() + ":: return: void");
            return move();
	}

}