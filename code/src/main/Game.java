package main;

import main.movable.Worker;

/**
 * 
 */
public class Game {

        private static Game instance;
        
        private Game() {
            
        }
        
        public static Game getInstance() {
            if (instance == null) {
                instance = new Game();
            }
            return instance;
        }
        
	private Orientation orientation = null;

	private Worker actualMovingWorker = null;

	private static Table table;
        
        public void init() {
            System.out.println("Called Class name: " + Game.class.getSimpleName() 
                    + " :: Method name: init() :: Parameters: :: return: void");
            System.out.println("Create new Table...");
            table = new Table();
            System.out.println("Load table elements, with call loadTable()");
            table.loadTable();
            System.out.println("Start game, with call game()");
            table.game();
        }
        
	public Table getTable() {
            System.out.println("Called Class name: " + Game.class.getSimpleName() 
                    + " :: Method name: getTable() :: Parameters: :: return: Table");
            return this.table;
	}

        public Orientation getOrientation() {
//            System.out.println("Called Class name: " + Game.class.getSimpleName() 
//                    + " :: Method name: getOrientation() :: Parameters: :: return: Orientation");
            return orientation;
        }
        
        public void setOrientation(Orientation orientation) {
//            System.out.println("Called Class name: " + Game.class.getSimpleName() 
//                    + " :: Method name: setOrientation() :: Parameters: Orientation orientation :: return: void");
            this.orientation = orientation;
        }

        public Worker getActualMovingWorker() {
            System.out.println("Called Class name: " + Game.class.getSimpleName() 
                    + " :: Method name: getActualMovingWorker() :: Parameters: :: return: Worker with id: " + this.actualMovingWorker);
            return this.actualMovingWorker;
	}
        
        public void setActualMovingWorker(Worker actualMovingWorker) {
//            System.out.println("Called Class name: " + Game.class.getSimpleName() 
//                    + " :: Method name: setActualMovingWorker() :: Parameters: Worker actualMovingWorker :: return: void");
            this.actualMovingWorker = actualMovingWorker;
        }
}