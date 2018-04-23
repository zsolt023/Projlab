package main;

import main.movable.Worker;

import java.util.Scanner;

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
        boolean end = false;

        while(!end) {

            table = new Table();

            System.out.println("");
            System.out.println("1.  Push box -> worker to wall");
            System.out.println("2.  Trying to push worker");
            System.out.println("3.  Push box to plain");
            System.out.println("4.  Push box to switch");
            System.out.println("5.  Push box to objective");
            System.out.println("6.  Push box to wall");
            System.out.println("7.  Push box to hole");
            System.out.println("8.  Push box -> worker -> worker");
            System.out.println("9.  Push box -> worker -> box to objective");
            System.out.println("10. Last box stucks");
            System.out.println("11. Last box has being killed");
            System.out.println("12. Last box pushed to objective");
            System.out.println("13. Penultimate worker dies");
            System.out.println("14. Step to hole");
            System.out.println("15. Step to wall");
            System.out.println("16. Step to plain, switch or objective");
            System.out.println("17. Push Box -> Box chain through 2 HoneyField");
            System.out.println("18. Push Box -> Box chain through 1 HoneyField 1 Plain");
            System.out.println("19. Push Box -> Box chain through 1 HoneyField 1 OilyField");
            System.out.println("20. Push Box -> Box chain through 1 Plain 1 OilyField");
            System.out.println("21. Push Box -> Worker -> Box -> Box chain");
            System.out.println("22. Run every test");
            System.out.println("23. Create your own test");

            System.out.println("The game was stared, please select one alternative:");

            Worker actualMovingWorker = null;
            Scanner reader = new Scanner(System.in);
            int alternatives = reader.nextInt();
            switch (alternatives) {
                case 1:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 2:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 3:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 4:
                    table.loadTable("map4.txt");
                    table.game();
                    break;
                case 5:
                    table.loadTable("map5.txt");
                    table.game();
                    break;
                case 6:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 7:
                    table.loadTable("map7.txt");
                    table.game();
                    break;
                case 8:
                    table.loadTable("map8.txt");
                    table.game();
                    break;
                case 9:
                    table.loadTable("map9.txt");
                    table.game();
                    break;
                case 10:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 11:
                    table.loadTable("map7.txt");
                    table.game();
                    break;
                case 12:
                    table.loadTable("map5.txt");
                    table.game();
                    break;
                case 13:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 14:
                    table.loadTable("map7.txt");
                    table.game();
                    break;
                case 15:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 16:
                    table.loadTable("map1.txt");
                    table.game();
                    break;
                case 17:
                    table.loadTable("map17.txt");
                    table.game();
                    break;
                case 18:
                    table.loadTable("map17.txt");
                    table.game();
                    break;
                case 19:
                    table.loadTable("map17.txt");
                    table.game();
                    break;
                case 20:
                    table.loadTable("map17.txt");
                    table.game();
                    break;
                case 21:
                    table.loadTable("map21.txt");
                    table.game();
                    break;
                case 22:
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map4.txt");
                    table.game();
                    table.loadTable("map5.txt");
                    table.game();
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map7.txt");
                    table.game();
                    table.loadTable("map8.txt");
                    table.game();
                    table.loadTable("map9.txt");
                    table.game();
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map7.txt");
                    table.game();
                    table.loadTable("map5.txt");
                    table.game();
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map7.txt");
                    table.game();
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map1.txt");
                    table.game();
                    table.loadTable("map17.txt");
                    table.game();
                    table.loadTable("map17.txt");
                    table.game();
                    table.loadTable("map17.txt");
                    table.game();
                    table.loadTable("map17.txt");
                    table.game();
                    table.loadTable("map21.txt");

                    break;
                case 23:
                    //Custom
                    break;
                case 24:
                    System.out.println("Shutting down");
                    end = true;
                default:
                    System.out.println("Your choose is bad, try again!");
                    break;
            }
        }


        //table.loadTable("map9.txt");


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