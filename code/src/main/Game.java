package main;

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


public class Game {

    /**
     * A singleton tervezési mintához szukséges Game változő ezen változőn keresztül érjük el a Game bármely paraméterét,
     * vagy bármely metódusát.
     */
    private static Game instance;

    private Game() {

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
        table.loadTable("code/res/maps/map3.txt");
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
            ImageView boxImageView =  box.draw();
            HBox actualHBox = (HBox) gameVBox.getChildren().get(Integer.parseInt(idWithKoord[2]));
            StackPane actualStackPane = (StackPane) actualHBox.getChildren().get(Integer.parseInt(idWithKoord[1]));
            actualStackPane.getChildren().add(boxImageView);
        }
        
        for (Worker worker : table.getWorkers()) {
            String[] idWithKoord = worker.getActualField().getId().split("_");
            ImageView workerImageView =  worker.draw();
            HBox actualHBox = (HBox) gameVBox.getChildren().get(Integer.parseInt(idWithKoord[2]));
            StackPane actualStackPane = (StackPane) actualHBox.getChildren().get(Integer.parseInt(idWithKoord[1]));
            actualStackPane.getChildren().add(workerImageView);
        }
        
        Tab gametab = FXMLDocumentController.tabPaneStatic.getSelectionModel().getSelectedItem();
        gametab.setContent(gameStackPane);
    }
}