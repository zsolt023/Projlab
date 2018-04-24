package main;

import com.sun.org.apache.xpath.internal.operations.Or;
import main.field.*;
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



                    break;
                case 5:
                    table.loadTable("code/res/maps/map5.txt"); //load(5)
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);
                    System.out.println("listBoxes " +table.getBoxes().get(0).getId()+";"); //listBoxes

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if(table.getBoxes().size()==0){System.out.println("listBoxes NONE");}

                    System.out.println("listPoints "+table.getWorkers().get(0).getId()+":"+table.getWorkers().get(0).getScore()+";"+table.getWorkers().get(1).getId()+":"+table.getWorkers().get(1).getScore() );

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

                    System.out.println("listBoxes " +table.getBoxes().get(0).getId()+";"); //listBoxes

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if(table.getBoxes().size()==0){System.out.println("listBoxes NONE");}

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

                    System.out.println("listPoints "+table.getWorkers().get(0).getId()+":"+table.getWorkers().get(0).getScore()+";"+table.getWorkers().get(1).getId()+":"+table.getWorkers().get(1).getScore() );

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

                    if(table.getBoxes().size()==0) {System.out.println("listBoxes NONE");}

                    System.out.println("listPoints "+table.getWorkers().get(0).getId()+":"+table.getWorkers().get(0).getScore()+";"+table.getWorkers().get(1).getId()+":"+table.getWorkers().get(1).getScore() );

                    break;
                case 11:
                    table.loadTable("code/res/maps/map7.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes "+table.getBoxes().get(0).getId()+";");

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if(table.getBoxes().size()==0) {System.out.println("listBoxes NONE");}

                    System.out.println("listPoints "+table.getWorkers().get(0).getId()+":"+table.getWorkers().get(0).getScore()+";"+table.getWorkers().get(1).getId()+":"+table.getWorkers().get(1).getScore() );

                    break;
                case 12:
                    table.loadTable("code/res/maps/map5.txt");
                    table.getBoxes().remove(2);
                    table.getBoxes().remove(1);

                    System.out.println("listBoxes "+table.getBoxes().get(0).getId()+";");

                    setOrientation(Orientation.LEFT);
                    setActualMovingWorker(table.getWorkers().get(1));
                    this.actualMovingWorker.move();

                    if(table.getBoxes().size()==0) {System.out.println("listBoxes NONE");}

                    System.out.println("listPoints "+table.getWorkers().get(0).getId()+":"+table.getWorkers().get(0).getScore()+";"+table.getWorkers().get(1).getId()+":"+table.getWorkers().get(1).getScore() );

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
                    for(int i=0; i<table.getWorkers().size();i++){
                        System.out.print(table.getWorkers().get(i).getId()+";"+"\n");
                    }

                    System.out.println("listPoints "+table.getWorkers().get(0).getId()+":"+table.getWorkers().get(0).getScore()+";"+"w2:"+x);

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
                    hp1.setNeighbour(Orientation.LEFT,temp17.getNeighbour(Orientation.LEFT));
                    hp1.setNeighbour(Orientation.RIGHT, temp17.getNeighbour(Orientation.RIGHT));
                    hp1.setActualMovable(temp17.getActualMovable());
                    table.getBoxes().get(0).setActualField(hp1);
                    table.getFields().remove(1);
                    table.getFields().add(1,hp1);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    temp17 = table.getFields().get(2);
                    HoneyPlain hp2 = new HoneyPlain();
                    hp2.setNeighbour(Orientation.LEFT,temp17.getNeighbour(Orientation.LEFT));
                    hp2.setNeighbour(Orientation.RIGHT, temp17.getNeighbour(Orientation.RIGHT));
                    hp2.setActualMovable(temp17.getActualMovable());
                    table.getBoxes().get(1).setActualField(hp2);
                    table.getFields().remove(2);
                    table.getFields().add(2,hp2);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    table.getWorkers().get(0).move();

                    break;
                case 18:
                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field temp18 = table.getFields().get(1);
                    HoneyPlain hop2 = new HoneyPlain();
                    hop2.setNeighbour(Orientation.LEFT,temp18.getNeighbour(Orientation.LEFT));
                    hop2.setNeighbour(Orientation.RIGHT,temp18.getNeighbour(Orientation.RIGHT));
                    hop2.setActualMovable(temp18.getActualMovable());
                    table.getBoxes().get(0).setActualField(hop2);
                    table.getFields().remove(1);
                    table.getFields().add(1,hop2);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    table.getBoxes().get(0).setFriction(4);
                    table.getBoxes().get(1).setFriction(4);
                    setActualMovingWorker(table.getWorkers().get(0));
                    setOrientation(Orientation.RIGHT);

                    System.out.println(Game.getInstance().getActualChainFriction());
                    table.getWorkers().get(0).move();

                    break;
                case 19:
                    table.loadTable("code/res/maps/map17.txt");
                    table.getWorkers().get(0).setForce(8);

                    Field tmp = table.getFields().get(1);
                    HoneyPlain hop1 = new HoneyPlain();
                    hop1.setNeighbour(Orientation.LEFT,tmp.getNeighbour(Orientation.LEFT));
                    hop1.setNeighbour(Orientation.RIGHT,tmp.getNeighbour(Orientation.RIGHT));
                    hop1.setActualMovable(tmp.getActualMovable());
                    table.getBoxes().get(0).setActualField(hop1);
                    table.getFields().remove(1);
                    table.getFields().add(1,hop1);
                    System.out.println("setPlainFieldType(p2, hon) DONE");

                    Field temp = table.getFields().get(2);
                    OilPlain op = new OilPlain();
                    op.setNeighbour(Orientation.LEFT,temp.getNeighbour(Orientation.LEFT));
                    op.setNeighbour(Orientation.RIGHT,temp.getNeighbour(Orientation.RIGHT));
                    op.setActualMovable(temp.getActualMovable());
                    table.getBoxes().get(1).setActualField(op);
                    table.getFields().remove(2);
                    table.getFields().add(2,op);
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
                    hp.setNeighbour(Orientation.LEFT,temp1.getNeighbour(Orientation.LEFT));
                    hp.setNeighbour(Orientation.RIGHT,temp1.getNeighbour(Orientation.RIGHT));
                    hp.setActualMovable(temp1.getActualMovable());
                    table.getBoxes().get(1).setActualField(hp);
                    table.getFields().remove(2);
                    table.getFields().add(2,hp);
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
                    table.game();
                    table.loadTable("code/res/maps/map1.txt");
                    table.game();
                    table.loadTable("code/res/maps/map1.txt");
                    table.game();
                    table.loadTable("code/res/maps/map4.txt");
                    table.game();
                    table.loadTable("code/res/maps/map5.txt");
                    table.game();
                    table.loadTable("code/res/maps/map1.txt");
                    table.game();
                    table.loadTable("code/res/maps/map7.txt");
                    table.game();
                    table.loadTable("code/res/maps/map8.txt");
                    table.game();
                    table.loadTable("code/res/maps/map9.txt");
                    table.game();
                    table.loadTable("code/res/maps/map1.txt");
                    table.game();
                    table.loadTable("code/res/maps/map7.txt");
                    table.game();
                    table.loadTable("code/res/maps/map5.txt");
                    table.game();
                    table.loadTable("code/res/maps/map1.txt");
                    table.game();
                    table.loadTable("code/res/maps/map7.txt");
                    table.game();
                    table.loadTable("code/res/maps/map1.txt");
                    table.game();
                    table.loadTable("code/res/maps/map1.txt");
                    table.game();
                    table.loadTable("code/res/maps/ap17.txt");
                    table.game();
                    table.loadTable("code/res/maps/map17.txt");
                    table.game();
                    table.loadTable("code/res/maps/map17.txt");
                    table.game();
                    table.loadTable("code/res/maps/map17.txt");
                    table.game();
                    table.loadTable("code/res/maps/map21.txt");

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
}