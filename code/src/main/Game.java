package main;

import com.sun.org.apache.xpath.internal.operations.Or;
import main.field.*;
import main.movable.Box;
import main.movable.Worker;

import java.util.List;
import java.util.Scanner;

public class Game {

    /**
     * A singleton tervezési mintához szukséges Game változő ezen változőn keresztül érjük el a Game bármely paraméterét,
     * vagy bármely metódusát.
     */
    private static Game instance;

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
        boolean end = false;

        while (!end) {

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
            //int alternatives = 23;
            switch (alternatives) {
                case 1:
                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.RIGHT);
                    this.actualMovingWorker.move();

                    break;
                case 2:
                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.UP);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.LEFT);
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.LEFT);
                    this.actualMovingWorker.move();
                    break;
                case 3:
                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();
                    break;
                case 4:
                    table.loadTable("code/res/maps/map4.txt");
                    Switch s1 = new Switch();
                    Hole h1 = new Hole();
                    s1.setId("s1");
                    h1.setId("h1");

                    s1.setNeighbour(Orientation.UP, table.getFields().get(17).getNeighbour(Orientation.UP));
                    s1.setNeighbour(Orientation.DOWN, table.getFields().get(17).getNeighbour(Orientation.DOWN));
                    s1.setNeighbour(Orientation.LEFT, table.getFields().get(17).getNeighbour(Orientation.LEFT));
                    s1.setNeighbour(Orientation.RIGHT, table.getFields().get(17).getNeighbour(Orientation.RIGHT));
                    s1.setHole(h1);
                    table.getFields().get(12).setNeighbour(Orientation.DOWN, s1);
                    table.getFields().get(16).setNeighbour(Orientation.RIGHT, s1);
                    table.getFields().get(18).setNeighbour(Orientation.LEFT, s1);
                    table.getFields().get(22).setNeighbour(Orientation.UP, s1);

                    h1.setNeighbour(Orientation.UP, table.getFields().get(7).getNeighbour(Orientation.UP));
                    h1.setNeighbour(Orientation.DOWN, table.getFields().get(7).getNeighbour(Orientation.DOWN));
                    h1.setNeighbour(Orientation.LEFT, table.getFields().get(7).getNeighbour(Orientation.LEFT));
                    h1.setNeighbour(Orientation.RIGHT, table.getFields().get(7).getNeighbour(Orientation.RIGHT));
                    h1.setIsActive(false);
                    table.getFields().get(2).setNeighbour(Orientation.DOWN, h1);
                    table.getFields().get(6).setNeighbour(Orientation.RIGHT, h1);
                    table.getFields().get(8).setNeighbour(Orientation.LEFT, h1);
                    table.getFields().get(12).setNeighbour(Orientation.UP, h1);

                    table.getFields().remove(17);
                    table.getFields().remove(7);

                    table.getFields().add(17, s1);
                    table.getFields().add(7, h1);

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();
                    h1.setActualMovable(this.actualMovingWorker);
                    this.actualMovingWorker.setActualField(h1);

                    System.out.println("listWorkers " + table.getWorkers().get(0).getId() + ";" + table.getWorkers().get(1).getId());

                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    setOrientation(Orientation.UP);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.println("listWorkers " + table.getWorkers().get(0).getId());

                    break;
                case 5:
                    table.loadTable("code/res/maps/map5.txt"); //load(5)
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);
                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";"); //listBoxes

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());

                    break;
                case 6:
                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();
                    break;
                case 7:
                    table.loadTable("code/res/maps/map7.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";"); //listBoxes

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    break;
                case 8:
                    table.loadTable("code/res/maps/map8.txt");

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    break;
                case 9:
                    table.loadTable("code/res/maps/map9.txt");

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());

                    break;
                case 10:
                    table.loadTable("code/res/maps/map1.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    table.getBoxes().remove(0);
                    System.out.println("stuck(b1) DONE");

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());

                    break;
                case 11:
                    table.loadTable("code/res/maps/map7.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";");

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());

                    break;
                case 12:
                    table.loadTable("code/res/maps/map5.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";");

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());

                    break;
                case 13:
                    table.loadTable("code/res/maps/map1.txt");
                    table.getWorkers().remove(2);

                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    int x = table.getWorkers().get(1).getScore();

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.print("listWorkers ");
                    for (int i = 0; i < table.getWorkers().size(); i++) {
                        System.out.print(table.getWorkers().get(i).getId() + ";" + "\n");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + "w2:" + x);

                    break;
                case 14:
                    table.loadTable("code/res/maps/map7.txt");
                    table.getWorkers().remove(2);
                    table.listWorkers();
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.DOWN);
                    table.getWorkers().get(0).move();
                    table.listWorkers();

                    break;
                case 15:
                    table.loadTable("code/res/maps/map1.txt");
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.UP);
                    table.getWorkers().get(0).move();
                    setOrientation(Orientation.LEFT);
                    table.getWorkers().get(0).move();
                    setActualMovingWorker(table.getWorkers().get(1));
                    setOrientation(Orientation.RIGHT);
                    table.getWorkers().get(1).move();
                    break;
                case 16:
                    table.loadTable("code/res/maps/map1.txt");
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.DOWN);
                    table.getWorkers().get(0).move();
                    setActualMovingWorker(table.getWorkers().get(1));
                    setOrientation(Orientation.UP);
                    table.getWorkers().get(1).move();
                    break;
                case 17:
                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field temp17 = table.getFields().get(1);
                    HoneyPlain hp1 = new HoneyPlain();
                    hp1.setNeighbour(Orientation.LEFT, temp17.getNeighbour(Orientation.LEFT));
                    hp1.setNeighbour(Orientation.RIGHT, temp17.getNeighbour(Orientation.RIGHT));
                    hp1.setActualMovable(temp17.getActualMovable());
                    table.getBoxes().get(0).setActualField(hp1);
                    table.getFields().remove(1);
                    table.getFields().add(1, hp1);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    temp17 = table.getFields().get(2);
                    HoneyPlain hp2 = new HoneyPlain();
                    hp2.setNeighbour(Orientation.LEFT, temp17.getNeighbour(Orientation.LEFT));
                    hp2.setNeighbour(Orientation.RIGHT, temp17.getNeighbour(Orientation.RIGHT));
                    hp2.setActualMovable(temp17.getActualMovable());
                    table.getBoxes().get(1).setActualField(hp2);
                    table.getFields().remove(2);
                    table.getFields().add(2, hp2);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    Game.getInstance().setActualChainFriction(10);

                    table.getWorkers().get(0).move();

                    break;
                case 18:
                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field temp18 = table.getFields().get(1);
                    HoneyPlain hop2 = new HoneyPlain();
                    hop2.setNeighbour(Orientation.LEFT, temp18.getNeighbour(Orientation.LEFT));
                    hop2.setNeighbour(Orientation.RIGHT, temp18.getNeighbour(Orientation.RIGHT));
                    hop2.setActualMovable(temp18.getActualMovable());
                    table.getBoxes().get(0).setActualField(hop2);
                    table.getFields().remove(1);
                    table.getFields().add(1, hop2);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    Game.getInstance().setActualChainFriction(8);

                    table.getWorkers().get(0).move();

                    break;
                case 19:
                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field tmp = table.getFields().get(1);
                    HoneyPlain hop1 = new HoneyPlain();
                    hop1.setNeighbour(Orientation.LEFT, tmp.getNeighbour(Orientation.LEFT));
                    hop1.setNeighbour(Orientation.RIGHT, tmp.getNeighbour(Orientation.RIGHT));
                    hop1.setActualMovable(tmp.getActualMovable());
                    table.getBoxes().get(0).setActualField(hop1);
                    table.getFields().remove(1);
                    table.getFields().add(1, hop1);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    Field temp = table.getFields().get(2);
                    OilPlain op = new OilPlain();
                    op.setNeighbour(Orientation.LEFT, temp.getNeighbour(Orientation.LEFT));
                    op.setNeighbour(Orientation.RIGHT, temp.getNeighbour(Orientation.RIGHT));
                    op.setActualMovable(temp.getActualMovable());
                    table.getBoxes().get(1).setActualField(op);
                    table.getFields().remove(2);
                    table.getFields().add(2, op);
                    System.out.println("setPlainFieldType(p3, oil) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    table.getWorkers().get(0).move();

                    break;
                case 20:
                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field temp1 = table.getFields().get(2);
                    OilPlain hp = new OilPlain();
                    hp.setNeighbour(Orientation.LEFT, temp1.getNeighbour(Orientation.LEFT));
                    hp.setNeighbour(Orientation.RIGHT, temp1.getNeighbour(Orientation.RIGHT));
                    hp.setActualMovable(temp1.getActualMovable());
                    table.getBoxes().get(1).setActualField(hp);
                    table.getFields().remove(2);
                    table.getFields().add(2, hp);
                    System.out.println("setPlainFieldType(p3, oil) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    table.getWorkers().get(0).move();

                    break;
                case 21:
                    table.loadTable("code/res/maps/map21.txt");
                    table.getWorkers().get(0).setForce(8);
                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    table.getBoxes().get(2).setFriction(4);
                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    table.getWorkers().get(0).move();
                    break;
                case 22:


                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.RIGHT);
                    this.actualMovingWorker.move();

                    System.out.println();

                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.UP);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.LEFT);
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.LEFT);
                    this.actualMovingWorker.move();

                    System.out.println();

                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.println();

                    table.loadTable("code/res/maps/map4.txt");
                    Switch ss1 = new Switch();
                    Hole hh1 = new Hole();
                    ss1.setId("s1");
                    hh1.setId("h1");

                    ss1.setNeighbour(Orientation.UP, table.getFields().get(17).getNeighbour(Orientation.UP));
                    ss1.setNeighbour(Orientation.DOWN, table.getFields().get(17).getNeighbour(Orientation.DOWN));
                    ss1.setNeighbour(Orientation.LEFT, table.getFields().get(17).getNeighbour(Orientation.LEFT));
                    ss1.setNeighbour(Orientation.RIGHT, table.getFields().get(17).getNeighbour(Orientation.RIGHT));
                    ss1.setHole(hh1);
                    table.getFields().get(12).setNeighbour(Orientation.DOWN, ss1);
                    table.getFields().get(16).setNeighbour(Orientation.RIGHT, ss1);
                    table.getFields().get(18).setNeighbour(Orientation.LEFT, ss1);
                    table.getFields().get(22).setNeighbour(Orientation.UP, ss1);

                    hh1.setNeighbour(Orientation.UP, table.getFields().get(7).getNeighbour(Orientation.UP));
                    hh1.setNeighbour(Orientation.DOWN, table.getFields().get(7).getNeighbour(Orientation.DOWN));
                    hh1.setNeighbour(Orientation.LEFT, table.getFields().get(7).getNeighbour(Orientation.LEFT));
                    hh1.setNeighbour(Orientation.RIGHT, table.getFields().get(7).getNeighbour(Orientation.RIGHT));
                    hh1.setIsActive(false);
                    table.getFields().get(2).setNeighbour(Orientation.DOWN, hh1);
                    table.getFields().get(6).setNeighbour(Orientation.RIGHT, hh1);
                    table.getFields().get(8).setNeighbour(Orientation.LEFT, hh1);
                    table.getFields().get(12).setNeighbour(Orientation.UP, hh1);

                    table.getFields().remove(17);
                    table.getFields().remove(7);

                    table.getFields().add(17, ss1);
                    table.getFields().add(7, hh1);

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();
                    hh1.setActualMovable(this.actualMovingWorker);
                    this.actualMovingWorker.setActualField(hh1);

                    System.out.println("listWorkers " + table.getWorkers().get(0).getId() + ";" + table.getWorkers().get(1).getId());

                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    setOrientation(Orientation.UP);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.println("listWorkers " + table.getWorkers().get(0).getId());

                    System.out.println();


                    table.loadTable("code/res/maps/map5.txt"); //load(5)
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);
                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";"); //listBoxes

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());


                    System.out.println();

                    table.loadTable("code/res/maps/map1.txt");
                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();
                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    System.out.println();

                    table.loadTable("code/res/maps/map7.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";"); //listBoxes

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println();


                    table.loadTable("code/res/maps/map8.txt");

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.println();


                    table.loadTable("code/res/maps/map9.txt");

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());


                    System.out.println();

                    table.loadTable("code/res/maps/map1.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    table.getBoxes().remove(0);
                    System.out.println("stuck(b1) DONE");

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());


                    System.out.println();

                    table.loadTable("code/res/maps/map7.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";");

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());

                    System.out.println();

                    table.loadTable("code/res/maps/map5.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes " + table.getBoxes().get(0).getId() + ";");

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if (table.getBoxes().size() == 0) {
                        System.out.println("listBoxes NONE");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + table.getWorkers().get(1).getId() + ":" + table.getWorkers().get(1).getScore());

                    System.out.println();

                    table.loadTable("code/res/maps/map1.txt");
                    table.getWorkers().remove(2);

                    setOrientation(Orientation.DOWN);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    int xx = table.getWorkers().get(1).getScore();

                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    this.actualMovingWorker.move();

                    System.out.print("listWorkers ");
                    for (int i = 0; i < table.getWorkers().size(); i++) {
                        System.out.print(table.getWorkers().get(i).getId() + ";" + "\n");
                    }

                    System.out.println("listPoints " + table.getWorkers().get(0).getId() + ":" + table.getWorkers().get(0).getScore() + ";" + "w2:" + xx);

                    System.out.println();

                    table.loadTable("code/res/maps/map7.txt");
                    table.getWorkers().remove(2);
                    table.listWorkers();
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.DOWN);
                    table.getWorkers().get(0).move();
                    table.listWorkers();

                    System.out.println();

                    table.loadTable("code/res/maps/map1.txt");
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.UP);
                    table.getWorkers().get(0).move();
                    setOrientation(Orientation.LEFT);
                    table.getWorkers().get(0).move();
                    setActualMovingWorker(table.getWorkers().get(1));
                    setOrientation(Orientation.RIGHT);
                    table.getWorkers().get(1).move();

                    System.out.println();

                    table.loadTable("code/res/maps/map1.txt");
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.DOWN);
                    table.getWorkers().get(0).move();
                    setActualMovingWorker(table.getWorkers().get(1));
                    setOrientation(Orientation.UP);
                    table.getWorkers().get(1).move();

                    System.out.println();

                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field ttemp17 = table.getFields().get(1);
                    HoneyPlain hhp1 = new HoneyPlain();
                    hhp1.setNeighbour(Orientation.LEFT, ttemp17.getNeighbour(Orientation.LEFT));
                    hhp1.setNeighbour(Orientation.RIGHT, ttemp17.getNeighbour(Orientation.RIGHT));
                    hhp1.setActualMovable(ttemp17.getActualMovable());
                    table.getBoxes().get(0).setActualField(hhp1);
                    table.getFields().remove(1);
                    table.getFields().add(1, hhp1);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    ttemp17 = table.getFields().get(2);
                    HoneyPlain hhp2 = new HoneyPlain();
                    hhp2.setNeighbour(Orientation.LEFT, ttemp17.getNeighbour(Orientation.LEFT));
                    hhp2.setNeighbour(Orientation.RIGHT, ttemp17.getNeighbour(Orientation.RIGHT));
                    hhp2.setActualMovable(ttemp17.getActualMovable());
                    table.getBoxes().get(1).setActualField(hhp2);
                    table.getFields().remove(2);
                    table.getFields().add(2, hhp2);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    Game.getInstance().setActualChainFriction(10);

                    table.getWorkers().get(0).move();

                    System.out.println();

                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field ttemp18 = table.getFields().get(1);
                    HoneyPlain hhop2 = new HoneyPlain();
                    hhop2.setNeighbour(Orientation.LEFT, ttemp18.getNeighbour(Orientation.LEFT));
                    hhop2.setNeighbour(Orientation.RIGHT, ttemp18.getNeighbour(Orientation.RIGHT));
                    hhop2.setActualMovable(ttemp18.getActualMovable());
                    table.getBoxes().get(0).setActualField(hhop2);
                    table.getFields().remove(1);
                    table.getFields().add(1, hhop2);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    Game.getInstance().setActualChainFriction(8);

                    table.getWorkers().get(0).move();

                    System.out.println();

                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field ttmp = table.getFields().get(1);
                    HoneyPlain hhop1 = new HoneyPlain();
                    hhop1.setNeighbour(Orientation.LEFT, ttmp.getNeighbour(Orientation.LEFT));
                    hhop1.setNeighbour(Orientation.RIGHT, ttmp.getNeighbour(Orientation.RIGHT));
                    hhop1.setActualMovable(ttmp.getActualMovable());
                    table.getBoxes().get(0).setActualField(hhop1);
                    table.getFields().remove(1);
                    table.getFields().add(1, hhop1);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    Field ttemp = table.getFields().get(2);
                    OilPlain oop = new OilPlain();
                    oop.setNeighbour(Orientation.LEFT, ttemp.getNeighbour(Orientation.LEFT));
                    oop.setNeighbour(Orientation.RIGHT, ttemp.getNeighbour(Orientation.RIGHT));
                    oop.setActualMovable(ttemp.getActualMovable());
                    table.getBoxes().get(1).setActualField(oop);
                    table.getFields().remove(2);
                    table.getFields().add(2, oop);
                    System.out.println("setPlainFieldType(p3, oil) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    table.getWorkers().get(0).move();

                    System.out.println();

                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field ttemp1 = table.getFields().get(2);
                    OilPlain hhp = new OilPlain();
                    hhp.setNeighbour(Orientation.LEFT, ttemp1.getNeighbour(Orientation.LEFT));
                    hhp.setNeighbour(Orientation.RIGHT, ttemp1.getNeighbour(Orientation.RIGHT));
                    hhp.setActualMovable(ttemp1.getActualMovable());
                    table.getBoxes().get(1).setActualField(hhp);
                    table.getFields().remove(2);
                    table.getFields().add(2, hhp);
                    System.out.println("setPlainFieldType(p3, oil) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    table.getWorkers().get(0).move();

                    System.out.println();

                    table.loadTable("code/res/maps/map21.txt");
                    table.getWorkers().get(0).setForce(8);
                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    table.getBoxes().get(2).setFriction(4);
                    setOrientation(Orientation.RIGHT);
                    setActualMovingWorker(table.getWorkers().get(0));
                    table.getWorkers().get(0).move();


                    break;
                case 23:
                    //Custom
                    reader = new Scanner(System.in);
                    String cmd = "";

                    while (!cmd.equals("exit")) {
                        cmd = reader.next();
                        System.out.println(cmd);

                        if (cmd.startsWith("load")) {
                            table.loadTable(cmd.substring(5, cmd.length() - 1));

                        }

                        if (cmd.startsWith("step")) {
                            String wId = cmd.substring(5, 7);
                            String ori = cmd.substring(8, cmd.length() - 1);
                            for (Worker w : table.getWorkers()) {
                                if (w.getId().equals(wId)) {
                                    setActualMovingWorker(w);

                                    switch (ori) {
                                        case "left":
                                            setOrientation(Orientation.LEFT);
                                            break;
                                        case "right":
                                            setOrientation(Orientation.RIGHT);
                                            break;
                                        case "up":
                                            setOrientation(Orientation.UP);
                                            break;
                                        case "down":
                                            setOrientation(Orientation.DOWN);
                                            break;
                                        default:
                                            System.out.println("Wrong orientation. Try up, down, left, right with lowercase.");
                                    }
                                    w.move();
                                    break;
                                }
                            }

                        }

                        if (cmd.startsWith("placeWorker")) {
                            String wId = cmd.substring(12, 14);
                            String fId = cmd.substring(15, cmd.length() - 1);

                            for (Field f : table.getFields()) {
                                if (f.getId().equals(fId)) {
                                    Worker w = new Worker();
                                    w.setId(wId);
                                    w.setActualField(f);
                                    f.setActualMovable(w);
                                    table.getWorkers().add(w);
                                    break;
                                }
                            }


                        }

                        if (cmd.startsWith("placeBox")) {
                            String bId = cmd.substring(9, 11);
                            String fId = cmd.substring(12, cmd.length() - 1);

                            for (Field f : table.getFields()) {
                                if (f.getId().equals(fId)) {
                                    Box b = new Box();
                                    b.setId(bId);
                                    b.setActualField(f);
                                    f.setActualMovable(b);
                                    table.getBoxes().add(b);
                                    break;
                                }
                            }
                        }

                        if (cmd.startsWith("killWorker")) {

                            for (Worker w : table.getWorkers()) {
                                if (w.getId().equals(cmd.substring(11, 13)))
                                    table.kill(w);
                            }
                        }

                        if (cmd.startsWith("killBox")) {

                            for (Box b : table.getBoxes()) {
                                if (b.getId().equals(cmd.substring(8, 10)))
                                    table.kill(b);
                            }
                        }

                        if (cmd.startsWith("stuck")) {
                            for (Box b : table.getBoxes()) {
                                if (b.getId().equals(cmd.substring(6, 8)))
                                    table.kill(b);
                            }
                        }

                        if (cmd.startsWith("addPoint")) {

                            for (Worker w : table.getWorkers()) {
                                if (w.getId().equals(cmd.substring(9, 11)))
                                    w.addPoint();
                            }
                        }

                        if (cmd.startsWith("listBoxes")) {
                            table.listBoxes();
                        }
                        if (cmd.startsWith("listWorkers")) {
                            table.listWorkers();
                        }
                        if (cmd.startsWith("listPoints")) {
                            table.listPoints();
                        }
                        if (cmd.startsWith("listFields")) {
                            table.listFields();
                        }


                        if (cmd.startsWith("setFriction")) {
                            for (Box b : table.getBoxes()) {
                                if (b.getId().equals(cmd.substring(12, 13)))
                                    b.setFriction(Integer.getInteger(cmd.substring(14, cmd.length() - 1)));
                            }

                        }

                        if (cmd.startsWith("setForce")) {
                            for (Worker b : table.getWorkers()) {
                                if (b.getId().equals(cmd.substring(9, 11)))
                                    b.setForce(Integer.getInteger(cmd.substring(12, cmd.length() - 1)));
                            }

                        }

                        if (cmd.startsWith("setSwitch")) {
                            for (Field f : table.getFields()) {
                                if (f.getId().equals(cmd.substring(10, cmd.length() - 1)))
                                    f.switchState();
                            }

                        }

                        if (cmd.startsWith("setPlainFieldType")) {
                            System.out.println(cmd.substring(18, 20));
                            System.out.println();

                            switch (cmd.substring(21, cmd.length())) {
                                case "hon":
                                    Field tempHon = null;
                                    for (Field f : table.getFields()) {
                                        if (f.getId().equals(cmd.substring(18, 20)))
                                            tempHon = f;
                                    }
                                    HoneyPlain newH = new HoneyPlain();
                                    newH.setNeighbour(Orientation.LEFT, tempHon.getNeighbour(Orientation.LEFT));
                                    newH.setNeighbour(Orientation.RIGHT, tempHon.getNeighbour(Orientation.RIGHT));
                                    newH.setActualMovable(tempHon.getActualMovable());
                                    if (tempHon.getActualMovable() != null)
                                        tempHon.getActualMovable().setActualField(newH);
                                    table.getFields().remove(tempHon);
                                    table.getFields().add(newH);
                                    System.out.println("setPlainFieldType(" + cmd.substring(18, 20) + ", hon) DONE");
                                    break;
                                case "oil":

                                    Field tempOil = null;
                                    for (Field f : table.getFields()) {
                                        if (f.getId().equals(cmd.substring(18, 20)))
                                            tempOil = f;
                                    }
                                    OilPlain newO = new OilPlain();
                                    newO.setNeighbour(Orientation.LEFT, tempOil.getNeighbour(Orientation.LEFT));
                                    newO.setNeighbour(Orientation.RIGHT, tempOil.getNeighbour(Orientation.RIGHT));
                                    newO.setActualMovable(tempOil.getActualMovable());
                                    if (tempOil.getActualMovable() != null)
                                        tempOil.getActualMovable().setActualField(newO);
                                    table.getFields().remove(tempOil);
                                    table.getFields().add(newO);
                                    System.out.println("setPlainFieldType(" + cmd.substring(18, 20) + ", oil) DONE");
                                    break;
                            }

                        }


                    }


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
}