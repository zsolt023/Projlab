package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.field.Field;
import main.field.Hole;
import main.field.Objective;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;
import main.movable.Box;
import main.movable.Movable;
import main.movable.Worker;

public class Table {

    public static int alternatives = 0;

    /**
     * A táblán szereplő összes mezőt tároljuk ebben a listában.
     */
    private List<Field> fields = new ArrayList<>();

    /**
     * A táblán lévő összes munkást tároljuk ebben a listában, és kivesszük belőle azon elemet,
     * ami megsemmisült a játék végének ellenőrzése céljábol.
     */
    private List<Worker> workers = new ArrayList<>();

    /**
     * A táblán lévő összes dobozt tároljok ebben a listában, és kivesszük belőle azon elemet,
     * ami megsemmisült, vagy cél-helyre került, vagy beragadt a játék végének ellenőrzése céljából.
     */
    private List<Box> boxes = new ArrayList<>();

    private Hole hole4_1 = new Hole();
    private Hole hole7_1 = new Hole();
    private Box box11_1 = new Box();
    private Box box12_1 = new Box();
    private Worker worker13_1 = new Worker();

    public Table() {
        //Default constructor
    }

    /**
     * Visszaadja a mezők listáját.
     * hhh
     *
     * @return List<Field>
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * Visszaadja a munkások listáját.
     *
     * @return List<Worker>
     */
    public List<Worker> getWorkers() {
        return workers;
    }

    /**
     * Visszaadja a dobozok listáját.
     *
     * @return List<Box>
     */
    public List<Box> getBoxes() {
        return boxes;
    }

    /**
     * Ez a metódus hozza létre a pályafájlból az összes mezőt, az összes ládát,
     * és legenerál random helyekre random számú (legalább 2 darab) munkást.
     * Ezeket az objektumokat konzisztens módon eltárolja a table megfelelő változóiban.
     * Beállítja a mezők szomszédait, “ráteszi” a mezőkre a munkásokat, és a dobozokat.
     * Összeköti a kapcsolókat egy lyuk objektummal.
     */

    public void loadTable(String filename) {
        BufferedReader br = null;
        int lineNumber;

        Box b1 = new Box();
        b1.setId("b1");
        Box b2 = new Box();
        b2.setId("b2");
        Box b3 = new Box();
        b3.setId("b3");

        boxes.add(b1);
        boxes.add(b2);
        boxes.add(b3);

        Worker w1 = new Worker();
        w1.setId("w1");
        Worker w2 = new Worker();
        w2.setId("w2");
        Worker w3 = new Worker();
        w3.setId("w3");

        workers.add(w1);
        workers.add(w2);
        workers.add(w3);


        try {
            br = new BufferedReader(new FileReader(filename));

            String s;

            while ((s = br.readLine()) != null) {
                //0:mező sorszám 1:mező típusa 2:mezőn lévő moveable azonosítója 3:mező azonosítója 4:felső szomszéd 5:jobb szomszéd6: alsó szomszéd 7:bal szomszéd
                String line[] = s.split(";");

                switch (line[1]) {
                    case "wall":
                        Wall wall = new Wall();
                        wall.setId(line[3]);

                        if (line[2] != null) {
                            for (Worker w : workers) {
                                if (w.getId().equals(line[2]))
                                    wall.setActualMovable(w);
                            }

                            for (Box b : boxes) {
                                if (b.getId().equals(line[2]))
                                    wall.setActualMovable(b);
                            }
                        }

                        if (!line[4].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[4]))
                                    wall.setNeighbour(Orientation.UP, f);
                        if (!line[5].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[5]))
                                    wall.setNeighbour(Orientation.RIGHT, f);
                        if (!line[6].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[6]))
                                    wall.setNeighbour(Orientation.DOWN, f);
                        if (!line[7].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[7]))
                                    wall.setNeighbour(Orientation.LEFT, f);
                        if (wall.getActualMovable() != null)
                            wall.getActualMovable().setActualField(wall);
                        fields.add(wall);


                    case "plain":
                        Plain plain = new Plain();
                        plain.setId(line[3]);

                        if (line[2] != null) {
                            for (Worker w : workers) {
                                if (w.getId().equals(line[2]))
                                    plain.setActualMovable(w);
                            }

                            for (Box b : boxes) {
                                if (b.getId().equals(line[2]))
                                    plain.setActualMovable(b);
                            }
                        }

                        if (!line[4].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[4]))
                                    plain.setNeighbour(Orientation.UP, f);
                        if (!line[5].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[5]))
                                    plain.setNeighbour(Orientation.RIGHT, f);
                        if (!line[6].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[6]))
                                    plain.setNeighbour(Orientation.DOWN, f);
                        if (!line[7].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[7]))
                                    plain.setNeighbour(Orientation.LEFT, f);
                        if (plain.getActualMovable() != null)
                            plain.getActualMovable().setActualField(plain);
                        fields.add(plain);
                    case "hole":
                        Hole hole = new Hole();
                        hole.setId(line[3]);

                        if (line[2] != null) {
                            for (Worker w : workers) {
                                if (w.getId().equals(line[2]))
                                    hole.setActualMovable(w);
                            }

                            for (Box b : boxes) {
                                if (b.getId().equals(line[2]))
                                    hole.setActualMovable(b);
                            }
                        }

                        if (!line[4].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[4]))
                                    hole.setNeighbour(Orientation.UP, f);
                        if (!line[5].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[5]))
                                    hole.setNeighbour(Orientation.RIGHT, f);
                        if (!line[6].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[6]))
                                    hole.setNeighbour(Orientation.DOWN, f);
                        if (!line[7].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[7]))
                                    hole.setNeighbour(Orientation.LEFT, f);
                        if (hole.getActualMovable() != null)
                            hole.getActualMovable().setActualField(hole);
                        fields.add(hole);
                    case "switch":
                        Switch sw = new Switch();
                        sw.setId(line[3]);

                        if (line[2] != null) {
                            for (Worker w : workers) {
                                if (w.getId().equals(line[2]))
                                    sw.setActualMovable(w);
                            }

                            for (Box b : boxes) {
                                if (b.getId().equals(line[2]))
                                    sw.setActualMovable(b);
                            }
                        }

                        if (!line[4].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[4]))
                                    sw.setNeighbour(Orientation.UP, f);
                        if (!line[5].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[5]))
                                    sw.setNeighbour(Orientation.RIGHT, f);
                        if (!line[6].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[6]))
                                    sw.setNeighbour(Orientation.DOWN, f);
                        if (!line[7].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[7]))
                                    sw.setNeighbour(Orientation.LEFT, f);
                        if (sw.getActualMovable() != null)
                            sw.getActualMovable().setActualField(sw);
                        fields.add(sw);
                    case "objective":
                        Objective obj = new Objective();
                        obj.setId(line[3]);

                        if (line[2] != null) {
                            for (Worker w : workers) {
                                if (w.getId().equals(line[2]))
                                    obj.setActualMovable(w);
                            }

                            for (Box b : boxes) {
                                if (b.getId().equals(line[2]))
                                    obj.setActualMovable(b);
                            }
                        }

                        if (!line[4].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[4]))
                                    obj.setNeighbour(Orientation.UP, f);
                        if (!line[5].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[5]))
                                    obj.setNeighbour(Orientation.RIGHT, f);
                        if (!line[6].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[6]))
                                    obj.setNeighbour(Orientation.DOWN, f);
                        if (!line[7].equals("null"))
                            for (Field f : fields)
                                if (f.getId().equals(line[7]))
                                    obj.setNeighbour(Orientation.LEFT, f);
                        if (obj.getActualMovable() != null)
                            obj.getActualMovable().setActualField(obj);
                        fields.add(obj);
                }


            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();


            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }
    

    /**
     * Leveszi a pályáról a paraméterben kapott movable objektumot legyen az doboz, vagy munkás.
     * Tehát kiveszi a boxes listából, vagy kiveszi a workers listából a paraméterben kapott elemet.
     *
     * @param movable
     */
    public void kill(Movable movable) {

        if (movable != null) {
            for (Worker w : workers) {
                if (movable.getId().equals(w.getId())) {
                    workers.remove(w);
                    break;
                }
            }

            for (Box b : boxes) {
                if (movable.getId().equals(b.getId())) {
                    boxes.remove(b);
                    break;
                }
            }
        }

        Scanner reader = new Scanner(System.in);
        String question;
        switch (alternatives) {
            case 1:
                System.out.println("The penultimate worker killed by wall? (I/N)");
                question = reader.nextLine();
                if (question.startsWith("i") || question.startsWith("I")) {
                    Game.getInstance().penultimateWorker = true;
                }
                break;
            case 3:
                Game.getInstance().printing = true;
                System.out.println("The box is moved in the corner? (I/N)");
                question = reader.nextLine();
                if (question.startsWith("i") || question.startsWith("I")) {
                    System.out.println("This box was last on the table? (I/N)");
                    question = reader.nextLine();
                    if (question.startsWith("i") || question.startsWith("I")) {
                        Game.getInstance().lastBoxIsCorner = true;
                    }
                }
                Game.getInstance().printing = false;
                break;
            case 4:
                if (!Game.getInstance().printing) {
                    System.out.println("On the switched hole, is movable? (I/N)");
                    question = reader.nextLine();
                    if (question.startsWith("i") || question.startsWith("I")) {
                        Box box4_2 = new Box();
                        box4_2.setId("bo4_2");
                        boxes.add(box4_2);
                        hole4_1.setActualMovable(box4_2);
                        box4_2.setActualField(hole4_1);
                        System.out.println("This box was last on the hole, or penultimate worker was on the hole? (I/N)");
                        question = reader.nextLine();
                        if (question.startsWith("i") || question.startsWith("I")) {
                            Game.getInstance().lastBoxKill = true;
                        } else {
                            System.out.println("The box is moved in the corner? (I/N)");
                            question = reader.nextLine();
                            if (question.startsWith("i") || question.startsWith("I")) {
                                System.out.println("This box was last on the table? (I/N)");
                                question = reader.nextLine();
                                if (question.startsWith("i") || question.startsWith("I")) {
                                    Game.getInstance().lastBoxIsCorner = true;
                                }
                            }
                        }
                    }
                }
                break;
            case 5:
                System.out.println("This box was last on the table what pushed on the objective? (I/N)");
                question = reader.nextLine();
                if (question.startsWith("i") || question.startsWith("I")) {
                    Game.getInstance().lastBoxToObj = true;
                }
                break;
            case 7:
                if (!Game.getInstance().printing) {
                    System.out.println("Hole is aktive now (want to kill box)? (I/N)");
                    question = reader.nextLine();
                    if (question.startsWith("i") || question.startsWith("I")) {
                        hole7_1.setIsActive(true);
                        System.out.println("This box was last on the table? (I/N)");
                        question = reader.nextLine();
                        if (question.startsWith("i") || question.startsWith("I")) {
                            Game.getInstance().lastBoxKill = true;
                        }
                    } else {
                        hole7_1.setIsActive(false);
                        System.out.println("The box is moved in the corner? (I/N)");
                        question = reader.nextLine();
                        if (question.startsWith("i") || question.startsWith("I")) {
                            System.out.println("This box was last on the table? (I/N)");
                            question = reader.nextLine();
                            if (question.startsWith("i") || question.startsWith("I")) {
                                Game.getInstance().lastBoxIsCorner = true;
                            }
                        }
                    }
                }
                break;
            case 9:
                System.out.println("This box was last on the table what pushed on the objective? (I/N)");
                question = reader.nextLine();
                if (question.startsWith("i") || question.startsWith("I")) {
                    Game.getInstance().lastBoxToObj = true;
                }
                break;
            case 10:
                Game.getInstance().lastBoxIsCorner = true;
                break;
            case 11:
                Game.getInstance().lastBoxKill = true;
                break;
            case 12:
                Game.getInstance().lastBoxToObj = true;
                break;
            case 13:
                Game.getInstance().penultimateWorker = true;
                break;
            case 14:
                System.out.println("Hole is aktive now (want to kill worker)? (I/N)");
                question = reader.nextLine();
                if (question.startsWith("i") || question.startsWith("I")) {
                    System.out.println("This worker was penultimate on the table? (I/N)");
                    question = reader.nextLine();
                    if (question.startsWith("i") || question.startsWith("I")) {
                        Game.getInstance().penultimateWorker = true;
                    }
                }
                break;
            default:
                System.out.println("Your choose is bad, try again!");
                break;
        }
    }

    /**
     * Egy ciklusban vizsgálja, hogy nincs-e vége a játéknak, a gameOver függvény visszatérési értékét használva.
     * Első körben a cikluson belül figyeli történt-e billentyű lenyomás, ha igen,
     * akkor beállítja az Game osztály actuálisan lépő munkását, arra a munkásra, aki kezdeményezte a lépést,
     * majd beállítja a billentyű lenyomásának megfelelő irányt a szintén Game osztály orientation változójába.
     * Majd legvégül ezen munkás move() függvénye kerül meghívásra, ami a rekurziót hivatott elindítani.
     */
    public void game() {
        while (!gameOver()) {
            System.out.println("Called Class name: " + Table.class.getSimpleName()
                    + " :: Method name: game :: Parameters: :: return: void");
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
            alternatives = reader.nextInt();
            switch (alternatives) {
                case 1:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo1_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 2:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo2_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 3:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo3_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 4:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo4_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 5:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo5_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 6:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo6_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 7:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo7_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 8:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo8_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 9:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo9_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 10:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo10_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 11:
                    kill(box11_1);
                    break;
                case 12:
                    kill(box12_1);
                    break;
                case 13:
                    kill(worker13_1);
                    break;
                case 14:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo14_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 15:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo15_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                case 16:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo16_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Your choose is bad, try again!");
                    break;
            }

            if (actualMovingWorker != null) {
                Game.getInstance().setOrientation(Orientation.RIGHT);
                Game.getInstance().setActualMovingWorker(actualMovingWorker);
                actualMovingWorker.move();
            }
        }
    }

    /**
     * Ez a függvény csak akkor tér vissza igazzal, ha csak egy munkás maradt életben.
     * Vagy a raktárban az utolsó doboz, cél-helyre került, vagy beszorult, vagy lyukba esett.
     * Egyébként pedig mindig hamissal tér vissza.
     *
     * @return boolean
     */
    public boolean gameOver() {
        System.out.println("> table.gameOver()");
        if (Game.getInstance().penultimateWorker) {
            System.out.println("    Game over, because the penultimate worker was killed.");
        }
        if (Game.getInstance().lastBoxKill) {
            System.out.println("    Game over, because the last box was killed.");
        }
        if (Game.getInstance().lastBoxToObj) {
            System.out.println("    Game over, because the last box was pushed on objective.");
        }
        if (Game.getInstance().lastBoxIsCorner) {
            System.out.println("    Game over, because the last box was pushed in the corner.");
        }
        if (Game.getInstance().penultimateWorker || Game.getInstance().lastBoxKill || Game.getInstance().lastBoxIsCorner || Game.getInstance().lastBoxToObj) {
            System.out.println("< true");
        } else {
            System.out.println("< false");
        }
        Game.getInstance().penultimateWorker = false;
        Game.getInstance().lastBoxKill = false;
        Game.getInstance().lastBoxIsCorner = false;
        Game.getInstance().lastBoxToObj = false;

        Game.getInstance().tabs = 0;
        return false;
    }

}