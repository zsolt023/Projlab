package main;

import java.io.*;
import java.util.*;

import main.field.Field;
import main.field.Hole;
import main.field.Objective;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;
import main.movable.Box;
import main.movable.Movable;
import main.movable.Worker;
import main.fx.FXMLDocumentController;

public class Table {
    
    /**
     * A táblán szereplő összes mezőt tároljuk ebben a listában.
     */
    private List<Field> fields = new ArrayList<>();

    /**
     * A táblán lévő összes munkást tároljuk ebben a listában, és kivesszük belőle azon elemet,
     * ami megsemmisült a játék végének ellenőrzése céljábol.
     */
    private List<Worker> workers = new ArrayList<>();
    public List<Worker> workers2 = new ArrayList<>();
    
    /**
     * A táblán lévő összes dobozt tároljok ebben a listában, és kivesszük belőle azon elemet,
     * ami megsemmisült, vagy cél-helyre került, vagy beragadt a játék végének ellenőrzése céljából.
     */
    private List<Box> boxes = new ArrayList<>();


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
     * @param filename String
     */
    public void loadTable(String filename) {
        BufferedReader br = null;

        boxes.clear();
        fields.clear();

        try {
            br = new BufferedReader(new FileReader(filename));

            String s;

            Map<Field, String[]> neighboursMap = new HashMap<>();

            while ((s = br.readLine()) != null) {
                //0:mező sorszám 1:mező típusa 2:mezőn lévő moveable azonosítója 3:mező azonosítója 4:felső szomszéd 5:jobb szomszéd6: alsó szomszéd 7:bal szomszéd
                String line[] = s.split(";");

                if (!"null".equals(line[2])) {
                    if (line[2].startsWith("b")) {
                        Box box = new Box();
                        box.setId(line[2]);
                        boxes.add(box);
                    }
                    if (line[2].startsWith("wo")) {
                        Worker worker = new Worker();
                        worker.setId(line[2]);
                        workers.add(worker);
                    }
                }
                
                switch (line[1]) {
                    case "wall":
                        Wall wall = new Wall();
                        wall.setId(line[3]);
                        String[] wallNeighbours = new String[4];
                        for (int i = 0; i < 4; i++) {
                            wallNeighbours[i] = line[i+4];
                        }
                        neighboursMap.put(wall, wallNeighbours);
                        fields.add(wall);
                        break;
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

                        if (plain.getActualMovable() != null) {
                            plain.getActualMovable().setActualField(plain);
                        }
                        
                        String[] plainNeighbours = new String[4];
                        for (int i = 0; i < 4; i++) {
                            plainNeighbours[i] = line[i+4];
                        }
                        neighboursMap.put(plain, plainNeighbours);
                        fields.add(plain);
                        break;
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
                        
                        if (hole.getActualMovable() != null) {
                            hole.getActualMovable().setActualField(hole);
                        }
                        
                        String[] holeNeighbours = new String[4];
                        for (int i = 0; i < 4; i++) {
                            holeNeighbours[i] = line[i+4];
                        }
                        neighboursMap.put(hole, holeNeighbours);
                        fields.add(hole);
                        break;
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

                        if (sw.getActualMovable() != null) {
                            sw.getActualMovable().setActualField(sw);
                        }
                        
                        String[] switchNeighbours = new String[4];
                        for (int i = 0; i < 4; i++) {
                            switchNeighbours[i] = line[i+4];
                        }
                        neighboursMap.put(sw, switchNeighbours);
                        fields.add(sw);
                        
                        String[] ids = sw.getId().split("-");
                        for (Field field : fields) {
                            if (ids[1].equals(field.getId())) {
                                Hole holeToSwitch = (Hole) field;
                                sw.setHole(holeToSwitch);
                                sw.getHole().setIsActive(false);
                            }
                        }
                        break;
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
                        
                        if (obj.getActualMovable() != null) {
                            obj.getActualMovable().setActualField(obj);
                        }
                        
                        String[] objectiveNeighbours = new String[4];
                        for (int i = 0; i < 4; i++) {
                            objectiveNeighbours[i] = line[i+4];
                        }
                        neighboursMap.put(obj, objectiveNeighbours);
                        fields.add(obj);
                        break;
                }
            }

            for (Map.Entry<Field, String[]> neighs : neighboursMap.entrySet()) {
                for (int i = 0; i < neighs.getValue().length; i++) {
                    if (!"null".equals(neighs.getValue()[i])) {
                        for (Field field : fields) {
                            if (neighs.getValue()[i].equals(field.getId())) {
                                switch (i) {
                                    case 0:
                                        neighs.getKey().setNeighbour(Orientation.UP, field);
                                        break;
                                    case 1:
                                        neighs.getKey().setNeighbour(Orientation.RIGHT, field);
                                        break;
                                    case 2:
                                        neighs.getKey().setNeighbour(Orientation.DOWN, field);
                                        break;
                                    case 3:
                                        neighs.getKey().setNeighbour(Orientation.LEFT, field);
                                        break;
                                }
                            }
                        }
                    }
                }
            }
            
            workers2.add(workers.get(0));
            workers2.add(workers.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println("load(" + filename + ") DONE");
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
    }

    /**
     * Egy ciklusban vizsgálja, hogy nincs-e vége a játéknak, a gameOver függvény visszatérési értékét használva.
     * Első körben a cikluson belül figyeli történt-e billentyű lenyomás, ha igen,
     * akkor beállítja az Game osztály actuálisan lépő munkását, arra a munkásra, aki kezdeményezte a lépést,
     * majd beállítja a billentyű lenyomásának megfelelő irányt a szintén Game osztály orientation változójába.
     * Majd legvégül ezen munkás move() függvénye kerül meghívásra, ami a rekurziót hivatott elindítani.
     */
    public void game() {
        if (!gameOver()) {            
            if (null != Game.getInstance().getActualMovingWorker()) {
                Game.getInstance().getActualMovingWorker().move();
            }
            Game.getInstance().drawAll();
        }
        
    }

    /**
     * Ez a függvény csak akkor tér vissza igazzal, ha csak egy munkás maradt életben.
     * Vagy a raktárban az utolsó doboz, cél-helyre került, vagy beszorult, vagy lyukba esett.
     * Egyébként pedig mindig hamissal tér vissza.
     * @return boolean
     */
    public boolean gameOver() {
        if (boxes.isEmpty() || workers.size() <= 1) {
            FXMLDocumentController.tabPaneStatic.getSelectionModel().select(2);
            return true;
        } else {
            return false;
        }
    }
    
    
    public void listWorkers(){
        System.out.print("listWorkers ");
        for (Worker w:workers) {
            System.out.print(w.getId()+ ";");
        }
        System.out.println();
    }

    public void listBoxes(){
        System.out.print("listBoxes ");
        for (Box b:boxes) {
            System.out.print(b.getId()+ ";");
        }
        System.out.println();
    }

    public void listFields(){
        System.out.print("listFields ");
        for (Field f:fields) {
            System.out.print(f.getId()+ ";");
        }
        System.out.println();
    }
    public void listPoints(){
        System.out.print("listWorkers ");
        for (Worker w:workers) {
            System.out.print(w.getId()+ ":" + w.getScore()+ "; ");
        }
        System.out.println();
    }
    
}