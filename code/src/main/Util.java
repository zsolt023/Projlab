package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Util extends Application implements EventHandler<WindowEvent> {
    
    public static Stage stg;
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main/fx/View.fxml"));
            Scene scene = new Scene(root);
            scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    switch (ke.getCode()) {
                        case W:
                            Game.getInstance().setOrientation(Orientation.UP);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(0));
                            break;
                        case A:
                            Game.getInstance().setOrientation(Orientation.LEFT);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(0));
                            break;
                        case S:
                            Game.getInstance().setOrientation(Orientation.DOWN);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(0));
                            break;
                        case D:
                            Game.getInstance().setOrientation(Orientation.RIGHT);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(0));
                            break;
                        case UP:
                            Game.getInstance().setOrientation(Orientation.UP);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(1));
                            break;
                        case DOWN:
                            Game.getInstance().setOrientation(Orientation.DOWN);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(1));
                            break;
                        case RIGHT:
                            Game.getInstance().setOrientation(Orientation.RIGHT);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(1));
                            break;
                        case LEFT:
                            Game.getInstance().setOrientation(Orientation.LEFT);
                            Game.getInstance().setActualMovingWorker(Game.getInstance().getTable().getWorkers().get(1));
                            break;
                    }
                    Game.getInstance().getTable().game();
                    System.out.println("Key Pressed: " + ke.getCode());
                    ke.consume();
                }
            });
            scene.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    switch (ke.getCode()) {
                        case W:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                        case A:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                        case S:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                        case D:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                        case UP:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                        case DOWN:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                        case RIGHT:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                        case LEFT:
                            Game.getInstance().setOrientation(null);
                            Game.getInstance().setActualMovingWorker(null);
                            break;
                    }
                    Game.getInstance().getTable().game();
                    System.out.println("Key Released: " + ke.getCode());
                    ke.consume();
                }
            });
            stage.setOnCloseRequest(this);
            stage.setTitle("Game");
            stage.setWidth(1000);
            stage.setHeight(1000);
            stage.setScene(scene);
            
            stage.show();
            stg = stage;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * A játékot ténylegesen elindító main függvény.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void handle(WindowEvent event) {
        if (WindowEvent.WINDOW_CLOSE_REQUEST.equals(event.getEventType())) {
            stg.close();
        }
    }

}


