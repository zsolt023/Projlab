package main;

import main.field.Field;
import main.movable.Box;
import main.movable.Worker;


public class Game {

    /**
     * A singleton tervezési mintához szukséges Game változő ezen változőn keresztül érjük el a Game bármely paraméterét,
     * vagy bármely metódusát.
     */
    private static Game instance;

    private Game() {

    }

    /**
     * A Singleton tervezési minta eléréséért felelős. Ha még nem volt létrehozva belőle példány, akkor példányosodik.
     * Egyébként pedig ezen példány érétkét adja vissza, minden más esetben.
     *
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
     *
     * @return Orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Beálítja az aktuális lépés kezdetekor a lépés irányát a aparaméterben kapott értéknek megfelelően.
     *
     * @param orientation
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Visszaadja az épen lépő munkás referefnciáját.
     *
     * @return Worker
     */
    public Worker getActualMovingWorker() {

        return this.actualMovingWorker;
    }

    /**
     * Beállítja az aktuálisan lépő munkás referenciáját a paraméterben kapott munkásra.
     *
     * @param actualMovingWorker
     */
    public void setActualMovingWorker(Worker actualMovingWorker) {
        this.actualMovingWorker = actualMovingWorker;
    }

    /**
     * Visszaadja a játék tábláját.
     *
     * @return Table
     */
    public Table getTable() {
        return this.table;
    }

    /**
     * Visszaadja az aktuálisan tolni kívánt lánc eddigi súrlódási összegét.
     *
     * @return
     */
    public int getActualChainFriction() {
        return actualChainFriction;
    }

    /**
     * Beállítja az aktuálisan tolni kívánt értéket, a paraméterben kapott értéknek megfelelően.
     *
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
        table = new Table();
        table.loadTable("code/res/maps/map3.txt");
        table.game();
    }
    
    public void drawAll() {
        for (Field field : table.getFields()) {
            field.draw();
        }
        
        for (Box box : table.getBoxes()) {
            box.draw();
        }
        
        for (Worker worker : table.getWorkers()) {
            worker.draw();
        }
        
        
    }
}