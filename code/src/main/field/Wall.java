package main.field;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import main.movable.Movable;


public class Wall extends Field {

    public Wall() {
        //Default constructor
    }

    /**
     * Ez a mező befogadó függvénye. Első körben meghívja a paraméterben kapott movable objektumon a visit függvényt.
     * Itt a visit függvény csak akkor tér vissza igaz értékkel, ha egy munkást falnak tolnak.
     * Ekkor lekérjük a tolt munkás érkező mezőjét. Ezen mezőt nullázzuk, majd a munkást is beállítjuk erre a mezőre
     * (az utolsó beállításra egyébként nem lenne szükség).
     * És végül vissza térünk igaz értékkel. Az összes többi esetben a visit hamissal tér vissza,
     * ami által ezen függvény is hamissal tér majd vissza.
     *
     * @param movable
     * @return boolean
     */
    @Override
    public boolean accept(Movable movable) {
        if (movable.visit(this)) {
            Field previousField = movable.getActualField();
            previousField.setActualMovable(null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ImageView draw() {
        InputStream wallInputStream;
        BufferedImage wallBufferedImage;
        try {
            wallInputStream = new FileInputStream("code/res/obj/wall.jpg");
           
            wallBufferedImage = ImageIO.read(wallInputStream);
            javafx.scene.image.Image newWallImage = SwingFXUtils.toFXImage(wallBufferedImage, null);
            ImageView wallImageView = new ImageView(newWallImage);
            wallImageView.setFitHeight(30);
            wallImageView.setFitWidth(30);
            return wallImageView;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}