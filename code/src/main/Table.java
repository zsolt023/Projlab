package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.field.Field;
import main.field.Hole;
import main.field.Objective;
import main.field.Pillar;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;
import main.movable.Box;
import main.movable.Movable;
import main.movable.Worker;

/**
 *
 */
public class Table {

    /**
     * Default constructor
     */
    public Table() {
    }

    /**
     *
     */
    private List<Field> fields = new ArrayList<>();

    /**
     *
     */
    private List<Worker> workers = new ArrayList<>();

    /**
     *
     */
    private List<Box> boxes = new ArrayList<>();

    /**
     *
     */
    public void loadTable() {
        Game.printing = false;
        if (Game.printing)
            System.out.println("Called Class name: " + Table.class.getSimpleName()
                    + " :: Method name: loadTable :: Parameters: :: return: void");

        //elso esethez objektumok es a kezdo inicializalasok:
        Worker worker1_1 = new Worker();
        worker1_1.setId("wo1_1");
        Worker worker1_2 = new Worker();
        worker1_2.setId("wo1_2");
        Box box1_1 = new Box();
        box1_1.setId("bo1_1");

        Plain plain1_1 = new Plain();
        plain1_1.setId("pl1_1");
        Plain plain1_2 = new Plain();
        plain1_2.setId("pl1_2");
        Plain plain1_3 = new Plain();
        plain1_3.setId("pl1_3");
        Wall wall1_1 = new Wall();
        wall1_1.setId("wa1_1");

        worker1_1.setActualField(plain1_1);
        plain1_1.setActualMovable(worker1_1);

        box1_1.setActualField(plain1_2);
        plain1_2.setActualMovable(box1_1);

        worker1_2.setActualField(plain1_3);
        plain1_3.setActualMovable(worker1_2);

        wall1_1.setActualMovable(null);

        workers.add(worker1_1);
        workers.add(worker1_2);
        boxes.add(box1_1);
        fields.add(plain1_1);
        fields.add(plain1_2);
        fields.add(plain1_3);
        fields.add(wall1_1);

        plain1_1.setNeighbour(Orientation.RIGHT, plain1_2);
        plain1_2.setNeighbour(Orientation.RIGHT, plain1_3);
        plain1_3.setNeighbour(Orientation.RIGHT, wall1_1);


        //2. eset        
        Worker worker2_1 = new Worker();
        worker2_1.setId("wo2_1");
        Worker worker2_2 = new Worker();
        worker2_2.setId("wo2_2");

        Plain plain2_1 = new Plain();
        plain2_1.setId("pl2_1");
        Plain plain2_2 = new Plain();
        plain2_2.setId("pl2_2");
        Plain plain2_3 = new Plain();
        plain2_3.setId("pl2_3");

        worker2_1.setActualField(plain2_1);
        plain2_1.setActualMovable(worker2_1);

        worker2_2.setActualField(plain2_2);
        plain2_2.setActualMovable(worker2_2);

        plain2_3.setActualMovable(null);

        workers.add(worker2_1);
        workers.add(worker2_2);
        fields.add(plain2_1);
        fields.add(plain2_2);
        fields.add(plain2_3);

        plain2_1.setNeighbour(Orientation.RIGHT, plain2_2);
        plain2_2.setNeighbour(Orientation.RIGHT, plain2_3);

        //3. tesztesethez
        Worker worker3_1 = new Worker();
        worker3_1.setId("wo3_1");
        Box box3_1 = new Box();
        box3_1.setId("bo3_1");

        Plain plain3_1 = new Plain();
        plain3_1.setId("pl3_1");
        Plain plain3_2 = new Plain();
        plain3_2.setId("pl3_2");
        Plain plain3_3 = new Plain();
        plain3_3.setId("pl3_3");

        worker3_1.setActualField(plain3_1);
        plain3_1.setActualMovable(worker3_1);

        box3_1.setActualField(plain3_2);
        plain3_2.setActualMovable(box3_1);

        plain3_3.setActualMovable(null);

        workers.add(worker3_1);
        boxes.add(box3_1);
        fields.add(plain3_1);
        fields.add(plain3_2);
        fields.add(plain3_3);

        plain3_1.setNeighbour(Orientation.RIGHT, plain3_2);
        plain3_2.setNeighbour(Orientation.RIGHT, plain3_3);

        //4. eset
        Worker worker4_1 = new Worker();
        worker4_1.setId("wo4_1");
        Box box4_1 = new Box();
        box4_1.setId("bo4_1");

        Plain plain4_1 = new Plain();
        plain4_1.setId("pl4_1");
        Plain plain4_2 = new Plain();
        plain4_2.setId("pl4_2");
        Switch switch4_1 = new Switch();
        switch4_1.setId("sw4_1");
        Hole hole4_1 = new Hole();
        hole4_1.setId("ho4_1");

        worker4_1.setActualField(plain4_1);
        plain4_1.setActualMovable(worker4_1);

        box4_1.setActualField(plain4_2);
        plain4_2.setActualMovable(box4_1);

        switch4_1.setActualMovable(null);
        hole4_1.setActualMovable(null);

        workers.add(worker4_1);
        boxes.add(box4_1);
        fields.add(plain4_1);
        fields.add(plain4_2);
        fields.add(switch4_1);
        fields.add(hole4_1);

        plain4_1.setNeighbour(Orientation.RIGHT, plain4_2);
        plain4_2.setNeighbour(Orientation.RIGHT, switch4_1);

        switch4_1.setHole(hole4_1);

        //5. eset
        Worker worker5_1 = new Worker();
        worker5_1.setId("wo5_1");
        Box box5_1 = new Box();
        box5_1.setId("bo5_1");
        Plain plain5_1 = new Plain();
        plain5_1.setId("pl5_1");
        Plain plain5_2 = new Plain();
        plain5_2.setId("pl5_2");
        Objective objective5_1 = new Objective();
        objective5_1.setId("ob5_1");

        worker5_1.setActualField(plain5_1);
        plain5_1.setActualMovable(worker5_1);

        box5_1.setActualField(plain5_2);
        plain5_2.setActualMovable(box5_1);

        objective5_1.setActualMovable(null);

        workers.add(worker5_1);
        boxes.add(box5_1);
        fields.add(plain5_1);
        fields.add(plain5_2);
        fields.add(objective5_1);

        plain5_1.setNeighbour(Orientation.RIGHT, plain5_2);
        plain5_2.setNeighbour(Orientation.RIGHT, objective5_1);

        //6. eset
        Worker worker6_1 = new Worker();
        worker6_1.setId("wo6_1");
        Box box6_1 = new Box();
        box6_1.setId("bo6_1");
        Plain plain6_1 = new Plain();
        plain6_1.setId("pl6_1");
        Plain plain6_2 = new Plain();
        plain6_2.setId("pl6_2");
        Wall wall6_1 = new Wall();
        wall6_1.setId("wa6_1");

        worker6_1.setActualField(plain6_1);
        plain6_1.setActualMovable(worker6_1);

        box6_1.setActualField(plain6_2);
        plain6_2.setActualMovable(box6_1);

        wall6_1.setActualMovable(null);

        workers.add(worker6_1);
        boxes.add(box6_1);
        fields.add(plain6_1);
        fields.add(plain6_2);
        fields.add(wall6_1);

        plain6_1.setNeighbour(Orientation.RIGHT, plain6_2);
        plain6_2.setNeighbour(Orientation.RIGHT, wall6_1);

        //7. eset
        Worker worker7_1 = new Worker();
        worker7_1.setId("wo7_1");
        Box box7_1 = new Box();
        box7_1.setId("bo7_1");
        Plain plain7_1 = new Plain();
        plain7_1.setId("pl7_1");
        Plain plain7_2 = new Plain();
        plain7_2.setId("pl7_2");
        Hole hole7_1 = new Hole();
        hole7_1.setId("ho7_1");

        worker7_1.setActualField(plain7_1);
        plain7_1.setActualMovable(worker7_1);

        box7_1.setActualField(plain7_2);
        plain7_2.setActualMovable(box7_1);

        hole7_1.setActualMovable(null);

        workers.add(worker7_1);
        boxes.add(box7_1);
        fields.add(plain7_1);
        fields.add(plain7_2);
        fields.add(hole7_1);

        plain7_1.setNeighbour(Orientation.RIGHT, plain7_2);
        plain7_2.setNeighbour(Orientation.RIGHT, hole7_1);

        //8. eset
        Worker worker8_1 = new Worker();
        worker8_1.setId("wo8_1");
        Box box8_1 = new Box();
        box8_1.setId("bo8_1");
        Worker worker8_2 = new Worker();
        worker8_2.setId("wo8_2");
        Worker worker8_3 = new Worker();
        worker8_3.setId("wo8_3");
        Plain plain8_1 = new Plain();
        plain8_1.setId("pl8_1");
        Plain plain8_2 = new Plain();
        plain8_2.setId("pl8_2");
        Plain plain8_3 = new Plain();
        plain8_3.setId("pl8_3");
        Plain plain8_4 = new Plain();
        plain8_4.setId("pl8_4");
        Plain plain8_5 = new Plain();
        plain8_5.setId("pl8_5");

        worker8_1.setActualField(plain8_1);
        plain8_1.setActualMovable(worker8_1);

        box8_1.setActualField(plain8_2);
        plain8_2.setActualMovable(box8_1);

        worker8_2.setActualField(plain8_3);
        plain8_3.setActualMovable(worker8_2);

        worker8_3.setActualField(plain8_4);
        plain8_4.setActualMovable(worker8_3);

        plain8_5.setActualMovable(null);

        workers.add(worker8_1);
        workers.add(worker8_2);
        workers.add(worker8_3);
        boxes.add(box8_1);
        fields.add(plain8_1);
        fields.add(plain8_2);
        fields.add(plain8_3);
        fields.add(plain8_4);
        fields.add(plain8_5);

        plain8_1.setNeighbour(Orientation.RIGHT, plain8_2);
        plain8_2.setNeighbour(Orientation.RIGHT, plain8_3);
        plain8_3.setNeighbour(Orientation.RIGHT, plain8_4);
        plain8_4.setNeighbour(Orientation.RIGHT, plain8_5);

        //9. eset
        Worker worker9_1 = new Worker();
        worker9_1.setId("wo9_1");
        Box box9_1 = new Box();
        box9_1.setId("bo9_1");
        Worker worker9_2 = new Worker();
        worker9_2.setId("wo9_2");
        Box box9_2 = new Box();
        box9_2.setId("bo9_2");
        Plain plain9_1 = new Plain();
        plain9_1.setId("pl9_1");
        Plain plain9_2 = new Plain();
        plain9_2.setId("pl9_2");
        Plain plain9_3 = new Plain();
        plain9_3.setId("pl9_3");
        Plain plain9_4 = new Plain();
        plain9_4.setId("pl9_4");
        Objective objective9_1 = new Objective();
        objective9_1.setId("ob9_1");

        worker9_1.setActualField(plain9_1);
        plain9_1.setActualMovable(worker9_1);

        box9_1.setActualField(plain9_2);
        plain9_2.setActualMovable(box9_1);

        worker9_2.setActualField(plain9_3);
        plain9_3.setActualMovable(worker9_2);

        box9_2.setActualField(plain9_4);
        plain9_4.setActualMovable(box9_2);

        objective9_1.setActualMovable(null);

        workers.add(worker9_1);
        workers.add(worker9_2);
        boxes.add(box9_1);
        boxes.add(box9_2);
        fields.add(plain9_1);
        fields.add(plain9_2);
        fields.add(plain9_3);
        fields.add(plain9_4);
        fields.add(objective9_1);

        plain9_1.setNeighbour(Orientation.RIGHT, plain9_2);
        plain9_2.setNeighbour(Orientation.RIGHT, plain9_3);
        plain9_3.setNeighbour(Orientation.RIGHT, plain9_4);
        plain9_4.setNeighbour(Orientation.RIGHT, objective9_1);

        //14 eset
        Worker worker14_1 = new Worker();
        worker14_1.setId("wo14_1");
        Plain plain14_1 = new Plain();
        plain14_1.setId("pl14_1");
        Hole hole14_1 = new Hole();
        hole14_1.setId("ho14_1");

        worker14_1.setActualField(plain14_1);
        plain14_1.setActualMovable(worker14_1);

        hole14_1.setActualMovable(null);

        workers.add(worker14_1);
        fields.add(plain14_1);
        fields.add(hole14_1);

        plain14_1.setNeighbour(Orientation.RIGHT, hole14_1);

        //15 eset
        Worker worker15_1 = new Worker();
        worker15_1.setId("wo15_1");
        Plain plain15_1 = new Plain();
        plain15_1.setId("pl15_1");
        Wall wall15_1 = new Wall();
        wall15_1.setId("wa15_1");

        worker15_1.setActualField(plain15_1);
        plain15_1.setActualMovable(worker15_1);

        wall15_1.setActualMovable(null);

        workers.add(worker15_1);
        fields.add(plain15_1);
        fields.add(wall15_1);

        plain15_1.setNeighbour(Orientation.RIGHT, wall15_1);

        //16 eset
        Worker worker16_1 = new Worker();
        worker16_1.setId("wo16_1");
        Plain plain16_1 = new Plain();
        plain16_1.setId("pl16_1");
        Plain plain16_2 = new Plain();
        plain16_2.setId("pl16_2");

        worker16_1.setActualField(plain16_1);
        plain16_1.setActualMovable(worker16_1);

        plain16_2.setActualMovable(null);

        workers.add(worker16_1);
        fields.add(plain16_1);
        fields.add(plain16_2);

        plain16_1.setNeighbour(Orientation.RIGHT, plain16_2);

        //13. eset (sarok)
        Worker worker13_1 = new Worker();
        worker13_1.setId("wo13_1");
        Box box13_1 = new Box();
        box13_1.setId("bo13_1");
        Plain plain13_1 = new Plain();
        plain13_1.setId("pl13_1");
        Plain plain13_2 = new Plain();
        plain13_2.setId("pl13_2");
        Plain plain13_3 = new Plain();
        plain13_3.setId("pl13_3");
        Pillar pillar13_1 = new Pillar();
        pillar13_1.setId("pi13_1");
        Wall wall13_1 = new Wall();
        wall13_1.setId("wa13_1");

        worker13_1.setActualField(plain13_1);
        plain13_1.setActualMovable(worker13_1);

        box13_1.setActualField(plain13_2);
        plain13_2.setActualMovable(box13_1);

        plain13_3.setActualMovable(null);
        pillar13_1.setActualMovable(null);
        wall13_1.setActualMovable(null);

        workers.add(worker13_1);
        boxes.add(box13_1);
        fields.add(plain13_1);
        fields.add(plain13_2);
        fields.add(plain13_3);
        fields.add(pillar13_1);
        fields.add(wall13_1);

        plain13_1.setNeighbour(Orientation.RIGHT, plain13_2);
        plain13_2.setNeighbour(Orientation.RIGHT, plain13_3);
        plain13_3.setNeighbour(Orientation.RIGHT, pillar13_1);
        plain13_3.setNeighbour(Orientation.DOWN, wall13_1);

        Game.printing = true;

        System.out.println("Load table done.");
    }

    public void kill(Movable movable) {
        Game.printTabs();
        System.out.println("> Table.kill(" + movable.getId() + ")");
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

        System.out.println("    The penultimate worker pushed to wall? (I/N)");
        Scanner reader = new Scanner(System.in);
        String question = reader.nextLine();
        if (question.startsWith("i") || question.startsWith("I")) {

        }
        Game.printTabs();
        System.out.println("< void");
    }

    public void game() {
        while (!gameOver()) {
            System.out.println("Called Class name: " + Table.class.getSimpleName()
                    + " :: Method name: game :: Parameters: :: return: void");
            System.out.println("");
            System.out.println("1.  Push box -> worker to wall or pillar");
            System.out.println("2.  Trying to push worker");
            System.out.println("3.  Push box to plain");
            System.out.println("4.  Push box to switch");
            System.out.println("5.  Push box to objective");
            System.out.println("6.  Push box to wall or pillar");
            System.out.println("7.  Push box to hole");
            System.out.println("8.  Push box -> worker -> worker");
            System.out.println("9.  Push box -> worker -> box to objective");
            System.out.println("10. Last box stucks");
            System.out.println("11. Last box has being killed");
            System.out.println("12. Last box pushed to objective");
            System.out.println("13. Last but one worker dies");
            System.out.println("14. Step to hole");
            System.out.println("15. Step to wall or pillar");
            System.out.println("16. Step to plain, switch or objective");

            System.out.println("The game was stared, please select one alternative:");

            Worker actualMovingWorker = null;
            Scanner reader = new Scanner(System.in);
            int alternatives = reader.nextInt();
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
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    for (Worker w : workers) {
                        if (w.getId().equals("wo13_1")) {
                            actualMovingWorker = w;
                            break;
                        }
                    }
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
     * @return
     */
    public boolean gameOver() {
        System.out.println("Called Class name: " + Table.class.getSimpleName()
                + " :: Method name: gameOver :: Parameters: :: return: boolean");
        return false;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

}