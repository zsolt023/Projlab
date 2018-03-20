package main;

import main.movable.Worker;

public class Game {

    private static Game instance;

    public static int tabs;

    public static boolean printing;

    public static boolean penultimateWorker = false;

    public static boolean lastBoxKill = false;

    public static boolean lastBoxIsCorner = false;
    
    public static boolean lastBoxToObj = false;

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

    public static Table table;

    public void init() {
        printing = false;
        if (printing)
            System.out.println("Called Class name: " + Game.class.getSimpleName()
                + " :: Method name: init() :: Parameters: :: return: void");
        printing = true;
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

            printTabs();
            System.out.println("< table");
        }
        return this.table;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Worker getActualMovingWorker() {
        if (printing) {
            printTabs();
            System.out.println("> Game.getActualMovingWorker()");
        }
        printTabs();
        System.out.println("< " + this.actualMovingWorker.getId());
        return this.actualMovingWorker;
    }

    public void setActualMovingWorker(Worker actualMovingWorker) {
        this.actualMovingWorker = actualMovingWorker;
    }

    public static void printTabs() {
        for (int i = 0; i < tabs; i++) {
            System.out.print("\t");
        }
    }
}