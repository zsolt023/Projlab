package main;

import main.movable.Worker;

/**
 * 
 */
public class Game {

        private static Game instance;

        public static int tabs;

        public static boolean printing;
        
        private Game() {
            printing = true;
            tabs = 0;
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
            printing = false;
            if (printing)
                System.out.println("Called Class name: " + Game.class.getSimpleName()
                    + " :: Method name: init() :: Parameters: :: return: void");
            printing=true;
            System.out.println("Create new Table...");
            table = new Table();
            System.out.println("Load table elements, with call loadTable()");
            table.loadTable();
            System.out.println("Start game, with call game()");
            table.game();
        }
        
	public Table getTable() {
            if (printing) {
                printTabs();
                System.out.println("> Game.getTable()");
            }

            printTabs();
        System.out.println("< Table");
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
            if (printing) {
                printTabs();
                System.out.println("> Game.getActualMovingWorker()");
            }
            printTabs();
            System.out.println("< " + this.actualMovingWorker);
            return this.actualMovingWorker;
	}
        
        public void setActualMovingWorker(Worker actualMovingWorker) {
//            System.out.println("Called Class name: " + Game.class.getSimpleName() 
//                    + " :: Method name: setActualMovingWorker() :: Parameters: Worker actualMovingWorker :: return: void");
            this.actualMovingWorker = actualMovingWorker;
        }

        public static void printTabs(){
            for (int i = 0; i < tabs; i++) {
                System.out.print("\t");
            }
        }
}