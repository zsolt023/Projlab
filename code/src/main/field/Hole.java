package main.field;

import main.Game;
import main.movable.Movable;

import java.util.Scanner;

/**
 *
 */
public class Hole extends Field {

    /**
     * Default constructor
     */
    public Hole() {
    }

    /**
     *
     */
    private boolean isActive;

    /**
     *
     */
    private Switch s;

    /**
     *
     */
    public void setActive() {
        if (Game.printing) {
            Game.printTabs();
            System.out.println(">" + this.getId() + ".setActive()");
        }
        Game.tabs++;
        Movable movable = this.getActualMovable();
        Game.tabs--;
        if (movable != null) {
            Game.tabs++;
            Game.getInstance().getTable().kill(movable);
            Game.tabs--;
        }
        if (isActive) {
            isActive = false;
        } else {
            isActive = true;
        }
        Game.printTabs();
        System.out.println("< void");
    }

    /**
     * @param movable
     * @return
     */
    public boolean accept(Movable movable) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".accept(" + movable.getId() + ")");
        }
        Game.tabs++;
        if (movable.visit(this)) {
            System.out.println("Nyitva van a lyuk?");
            Scanner reader = new Scanner(System.in);
            String question = reader.nextLine();
            if (question.startsWith("i") || question.startsWith("I")) {
                isActive = true;
            } else {
                isActive = false;
            }

            if (isActive) {
                Game.tabs--;
                Game.tabs++;
                Game.getInstance().getTable().kill(movable);
                Game.tabs--;
            } else {
                Game.tabs--;
                Game.tabs++;
                this.setActualMovable(movable);
                Game.tabs--;
            }

            Game.tabs++;
            Field previousField = movable.getActualField();
            Game.tabs--;

            Game.tabs++;
            previousField.setActualMovable(null);
            Game.tabs--;

            Game.tabs++;
            movable.setActualField(this);
            Game.tabs--;

            Game.tabs--;
            Game.printTabs();
            System.out.println("< true");
            return true;
        } else {
            Game.tabs--;
            Game.printTabs();
            System.out.println("< false");
            return false;
        }

    }

}