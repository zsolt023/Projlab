/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Game;
import main.Util;

/**
 *
 * @author schnorb
 */
public class FXMLDocumentController implements Initializable {
    
    public static TabPane tabPaneStatic;
    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Label newGameLabel;
    
    @FXML
    private Label gameInfoLabel;
    
    @FXML
    private Label infosLabel;
    
    @FXML
    private Label statLabel;
    
    @FXML
    private Button newGameButton;
    
    @FXML
    private Button newGameStartButton;
    
    @FXML
    private Button gameInfoButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button back2Button;
    
    @FXML
    private void handleGameAction() {
        if (newGameButton.isArmed()) {
            tabPaneStatic = tabPane;
            tabPane.getSelectionModel().select(3);
        }
    }
    
    @FXML
    private void handleGameStartAction() {
        if (newGameStartButton.isArmed()) {
            Game.getInstance().init();
        }
    }
    
    @FXML
    private void handleInfoAction() {
        if (gameInfoButton.isArmed()) {
            tabPane.getSelectionModel().select(1);
        }
    }
    
    @FXML
    private void handleExitAction() {
        if (exitButton.isArmed()) {
            Util.stg.close();
        }
    }
    
    @FXML
    private void handleBackAction() {
        if (backButton.isArmed() || back2Button.isArmed()) {
            tabPane.getSelectionModel().select(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newGameLabel.setText("1. A játék indításához hazsnáld az Új játék gombot.");
        gameInfoLabel.setText("2. A játékkal kapcsolatos információkat a Súgó gomb megnyomásával éred el.");
        infosLabel.setText("Ez egy táblán mászkálós játék...");
        handleGameAction();
        handleInfoAction();
        handleExitAction();
        handleBackAction();
        handleGameStartAction();
    }    
    
}
