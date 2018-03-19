package main.field;

import main.movable.Movable;

/**
 *
 */
public class Plain extends Field {

    /**
     * Default constructor
     */
    public Plain() {
    }

    /**
     * @param movable
     * @return
     */
    public boolean accept(Movable movable) {
        if (movable.visit(this)) {
            setActualMovable(movable);
            movable.getActualField().setActualMovable(null);
            movable.setActualField(this);
            return true;
        }

        return false;
    }

}