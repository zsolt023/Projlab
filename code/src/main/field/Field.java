package main.field;

import java.util.EnumMap;
import java.util.Map;

import main.Game;

import main.Orientation;
import main.movable.Movable;

/**
 *
 */
public abstract class Field {

    protected Map<Orientation, Field> neighbours = new EnumMap<>(Orientation.class);

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Field() {

    }

    /**
     * Azt a movable objektumot t�rolja, ami �ppen mozg�sban van, ha van ilyen.
     * Egy�bk�nt null.
     */
    protected Movable actualMovable;

    public Movable getActualMovable() {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId()+ ".getActualMovable()");
        }
        Game.printTabs();
        System.out.println("< " + ((actualMovable != null) ? actualMovable.getId() : null));
        return this.actualMovable;
    }

    public void setActualMovable(Movable movable) {
        if (Game.printing){
            Game.printTabs();
            System.out.println("> " + this.getId()+ ".setActualMovable("
                    + ((movable != null) ? movable.getId() : null) + ")");}
        Game.printTabs();
        System.out.println("< void");
        this.actualMovable = movable;
    }

    public Field getNeigbour() {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId()+ ".getNeighbour()");
        }

        Game.printTabs();
        System.out.println("< " + neighbours.get(Game.getInstance().getOrientation()).getId());
        return neighbours.get(Game.getInstance().getOrientation());
    }

    public void setNeighbour(Orientation orientation, Field neighbour) {
        neighbours.put(orientation, neighbour);
    }

    public abstract boolean accept(Movable movable);

}