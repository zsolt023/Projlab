package main.field;

import java.util.EnumMap;
import java.util.Map;

import main.Game;

import main.Orientation;
import main.movable.Movable;

public abstract class Field {

    private String id;

    /**
     * EnumMap ami az összes szomszédot tárolja az irányoknak megfelelően.
     */
    protected Map<Orientation, Field> neighbours = new EnumMap<>(Orientation.class);

    /**
     * Azt a movable objektumot tárolja, ami éppen ezen mezőn áll, ha van ilyen.
     * Egyébként null.
     */
    protected Movable actualMovable;

    public Field() {
        //Default constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Visszaadja azon movable objektumot, ami éppen rajta áll, ha van ilyen, ha nincs akkor nullal tér vissza,
     * azaz ezen mezőn épp nem áll semmi.
     *
     * @return Movable
     */
    public Movable getActualMovable() {
        return this.actualMovable;
    }

    /**
     * Beállítja ezen mezőre a paraméterben kapott movable objektumot.
     *
     * @param movable
     */
    public void setActualMovable(Movable movable) {
        this.actualMovable = movable;
    }

    /**
     * Visszaadja az aktuális lépés irányában lévő szomszédos mezőt.
     *
     * @return Field
     */
    public Field getNeigbour() {
        return neighbours.get(Game.getInstance().getOrientation());
    }

    /**
     * Beállítja ennek az objektumank a paraméterben kapott irány és mezőnek megfelelő szomszédokat.
     *
     * @param orientation
     * @param neighbour
     */
    public void setNeighbour(Orientation orientation, Field neighbour) {
        neighbours.put(orientation, neighbour);
    }

    public abstract boolean accept(Movable movable);

}