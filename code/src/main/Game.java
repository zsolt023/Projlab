package main;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.field.Field;
import main.movable.Box;
import main.movable.Worker;
import main.fx.FXMLDocumentController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class Game {

    /**
     * A singleton tervezési mintához szukséges Game változő ezen változőn keresztül érjük el a Game bármely paraméterét,
     * vagy bármely metódusát.
     */
    private static Game instance;
    public ImageView hole1ImageView;
    public ImageView hole2ImageView;
    public ImageView honeyImageView;
    public ImageView objectiveImageView;
    public ImageView oilImageView;
    public ImageView plainImageView;
    public ImageView switchImageView;
    public ImageView wallImageView;
    public ImageView boxImageView;
    public ImageView worker1ImageView;
    public ImageView worker2ImageView;

    private Game() {
        InputStream inputStream;
        BufferedImage bufferedImage;
        javafx.scene.image.Image newImage;
        try {
            inputStream = new FileInputStream("code/res/obj/hole1.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            hole1ImageView = new ImageView(newImage);
            hole1ImageView.setFitHeight(30);
            hole1ImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/hole2.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            hole2ImageView = new ImageView(newImage);
            hole2ImageView.setFitHeight(30);
            hole2ImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/honey.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            honeyImageView = new ImageView(newImage);
            honeyImageView.setFitHeight(30);
            honeyImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/objective.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            objectiveImageView = new ImageView(newImage);
            objectiveImageView.setFitHeight(30);
            objectiveImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/oil.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            oilImageView = new ImageView(newImage);
            oilImageView.setFitHeight(30);
            oilImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/plain.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            plainImageView = new ImageView(newImage);
            plainImageView.setFitHeight(30);
            plainImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/switch.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            switchImageView = new ImageView(newImage);
            switchImageView.setFitHeight(30);
            switchImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/wall.jpg");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            wallImageView = new ImageView(newImage);
            wallImageView.setFitHeight(30);
            wallImageView.setFitWidth(30);
             inputStream = new FileInputStream("code/res/obj/box.png");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            boxImageView = new ImageView(newImage);
            boxImageView.setFitHeight(30);
            boxImageView.setFitWidth(30);
            inputStream = new FileInputStream("code/res/obj/worker1.png");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            worker1ImageView = new ImageView(newImage);
            worker1ImageView.setFitHeight(30);
            worker1ImageView.setFitWidth(30);
            inputStream = new FileInputStream("code/res/obj/worker2.png");
            bufferedImage = ImageIO.read(inputStream);
            newImage = SwingFXUtils.toFXImage(bufferedImage, null);
            worker2ImageView = new ImageView(newImage);
            worker2ImageView.setFitHeight(30);
            worker2ImageView.setFitWidth(30);

        } catch (IOException e) {
            e.printStackTrace();
        }




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
    public Table table;

    /**
     * Az aktuálisan tolni kívánt lánc súrlódási összege. Ezen változó értékével vizsgáljuk, hogy az adott lánc eltolható-e.
     */
    private int actualChainFriction = 0;

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
        this.actualChainFriction = actualChainFriction;
    }

    /**
     * Ez a metódus a játék belépési pontja, akkor hívódik meg, ha elindítják a játékot.
     * Létrehoz egy Table objektumot, és meghívja rajta a loadTable metódust.
     * Majd ha az sikeresen lefutott, elindítja a game loop-ot.
     */
    public void init() {
        table = new Table();
        table.loadTable("code/res/maps/map3_2.txt");
        table.game();
    }

    public void drawAll() {
        StackPane gameStackPane = new StackPane();
        gameStackPane.setAlignment(Pos.CENTER);
        VBox gameVBox = new VBox();
        gameStackPane.getChildren().add(gameVBox);
        for (int i = 0; i < Math.sqrt(table.getFields().size()); i++) {
            HBox newHBox = new HBox();
            gameVBox.getChildren().add(newHBox);
            for (int j = 0; j < Math.sqrt(table.getFields().size()); j++) {
                StackPane newStackPane = new StackPane();
                newHBox.getChildren().add(newStackPane);
            }
        }

        for (Field field : table.getFields()) {
            String[] idWithKoord = field.getId().split("_");
            ImageView fieldImageView = field.draw();
            HBox actualHBox = (HBox) gameVBox.getChildren().get(Integer.parseInt(idWithKoord[2]));
            StackPane actualStackPane = (StackPane) actualHBox.getChildren().get(Integer.parseInt(idWithKoord[1]));
            actualStackPane.getChildren().add(fieldImageView);
        }

        for (Box box : table.getBoxes()) {
            String[] idWithKoord = box.getActualField().getId().split("_");
            ImageView boxImageView = box.draw();
            HBox actualHBox = (HBox) gameVBox.getChildren().get(Integer.parseInt(idWithKoord[2]));
            StackPane actualStackPane = (StackPane) actualHBox.getChildren().get(Integer.parseInt(idWithKoord[1]));
            actualStackPane.getChildren().add(boxImageView);
        }

        for (Worker worker : table.getWorkers()) {
            String[] idWithKoord = worker.getActualField().getId().split("_");
            ImageView workerImageView = worker.draw();
            HBox actualHBox = (HBox) gameVBox.getChildren().get(Integer.parseInt(idWithKoord[2]));
            StackPane actualStackPane = (StackPane) actualHBox.getChildren().get(Integer.parseInt(idWithKoord[1]));
            actualStackPane.getChildren().add(workerImageView);
        }

        Tab gametab = FXMLDocumentController.tabPaneStatic.getSelectionModel().getSelectedItem();
        gametab.setContent(gameStackPane);
    }
}