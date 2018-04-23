package main;

import main.movable.Worker;

public class Game {

    /**
     * A singleton tervezési mintához szukséges Game változő ezen változőn keresztül érjük el a Game bármely paraméterét,
     * vagy bármely metődusát.
     */
    private static Game instance;

    /**
     * Segédváltozó, a szükséges tabok kiíratásának száma.
     */
    public static int tabs;

    /**
     * Segédváltoző, ami a kiíratást engedélyezi vagy épp tiltja.
     */
    public static boolean printing;

    /**
     * Segédváltozó, boolean érték, ami az utolsó előtti munkást halálakor lesz igaz érték.
     */
    public static boolean penultimateWorker = false;

    /**
     * Segédváltozó, ami az utolsó doboz megsemmisülését jelzi, ekkor vált igaz értékre.
     */
    public static boolean lastBoxKill = false;

    /**
     * Segédváltoző, ami az utolsó doboz beragadását jelzi, ekkor vált igaz értékre.
     */
    public static boolean lastBoxIsCorner = false;
    
    /**
     * Segédváltozó, ami az utolsó doboz cél-helyre kerülését jelzi, ekkor vált igaz értékre.
     */
    public static boolean lastBoxToObj = false;

    private Game() {
        printing = true;
        tabs = 0;
    }

    /**
     * A Singleton tervezési minta eléréséért felelős. Ha még nem volt létrehozva belőle példány, akkor példányosodik.
     * Egyébként pedig ezen példány érétkét adja vissza, minden más esetben.
     * @return Game
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Az aktuális lépés irányát tárolja.
     */
    private Orientation orientation = null;

    /**
     * Az aktuálisan lépő munkás objektum példányát tárolja.
     */
    private Worker actualMovingWorker = null;

    /**
     * Tábla osztály példányát tárolja ezen keresztül érjük el a tábla szükséges metódusait.
     */
    public static Table table;
    
    /**
     * Az aktuálisan tolni kívánt lánc súrlódási összege. Ezen változó értékével vizsgáljuk, hogy az adott lánc eltolható-e.
     */
    private static int actualChainFriction = 0;

    /**
     * Visszaadja az aktuális lépés irányát.
     * @return Orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Beálítja az aktuális lépés kezdetekor a lépés irányát a aparaméterben kapott értéknek megfelelően.
     * @param orientation 
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    
    /**
     * Visszaadja az épen lépő munkás referefnciáját.
     * @return Worker
     */
    public Worker getActualMovingWorker() {
        if (printing) {
            printTabs();
            System.out.println("> Game.getActualMovingWorker()");
        }
        printTabs();
        System.out.println("< " + this.actualMovingWorker.getId());
        return this.actualMovingWorker;
    }

    /**
     * Beállítja az aktuálisan lépő munkás referenciáját a paraméterben kapott munkásra.
     * @param actualMovingWorker 
     */
    public void setActualMovingWorker(Worker actualMovingWorker) {
        this.actualMovingWorker = actualMovingWorker;
    }

    /**
     * Visszaadja a játék tábláját.
     * @return Table
     */
    public Table getTable() {
        if (printing) {
            printTabs();
            System.out.println("> Game.getTable()");

            printTabs();
            System.out.println("< table");
        }
        return this.table;
    }
    
    /**
     * Visszaadja az aktuálisan tolni kívánt lánc eddigi súrlódási összegét.
     * @return 
     */
    public int getActualChainFriction() {
        return actualChainFriction;
    }

    /**
     * Beállítja az aktuálisan tolni kívánt értéket, a paraméterben kapott értéknek megfelelően.
     * @param actualChainFriction 
     */
    public void setActualChainFriction(int actualChainFriction) {
        Game.actualChainFriction = actualChainFriction;
    }
    
    /**
     * Ez a metódus a játék belépési pontja, akkor hívódik meg, ha elindítják a játékot. 
     * Létrehoz egy Table objektumot, és meghívja rajta a loadTable metódust. 
     * Majd ha az sikeresen lefutott, elindítja a game loop-ot.
     */
    public void init() {
                System.out.println("Create new Table...");
        table = new Table();
        System.out.println("Load table elements, with call loadTable()");
        table.loadTable("map9.txt");
        System.out.println("Start game, with call game()");
        table.game();
    }    

    /**
     * Segéd függvény, ami a szkeletonnál használt kiírásoknah a tabulátorozását oldotta meg.
     */
    public static void printTabs() {
        for (int i = 0; i < tabs; i++) {
            System.out.print("\t");
        }
    }
}