package main.field;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import main.movable.Movable;


public class Objective extends Field {

    public Objective() {
        //Default constructor
    }

    /**
     * Ez a cél mező befogadó függvénye. Első körben tovább hív a láncolás következő visit hívással és ezen visszatérési érték alapján,
     * ha igazzal tért vissza az említett visit, akkor a movable érkezési mezőjét lekérjük, ezen mezőt nullázzuk,
     * és a movable aktuális mezőjét is beállítjuk ezen cél mezőre. Majd végül visszatérünk igaz értékkel.
     * Ellenkező esetben csak szimplán visszatérünk a visit visszatérési értéknek megfelelően hamis értékkel,
     * bármiféle átállítás nélkül.
     *
     * @param movable
     * @return boolean
     */
    @Override
    public boolean accept(Movable movable) {
        if (movable.visit(this)) {
            Field previousField = movable.getActualField();
            previousField.setActualMovable(null);
            movable.setActualField(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ImageView draw() {
        InputStream objectiveInputStream;
        BufferedImage objectiveBufferedImage;
        try {
            objectiveInputStream = new FileInputStream("code/res/obj/objective.jpg");
           
            objectiveBufferedImage = ImageIO.read(objectiveInputStream);
            javafx.scene.image.Image newObjectiveImage = SwingFXUtils.toFXImage(objectiveBufferedImage, null);
            ImageView objectiveImageView = new ImageView(newObjectiveImage);
            objectiveImageView.setFitHeight(30);
            objectiveImageView.setFitWidth(30);
            return objectiveImageView;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}