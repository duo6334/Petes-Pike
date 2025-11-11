package petespike.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import petespike.model.Move;
import petespike.model.PetesPike;
import petespike.model.Position;

public class PetesPikeGUI extends Application{
    private PetesPike game;
    private String currentFilename = ""; // last used filename (used by reset)

    // UI
    private GridPane boardGrid;
    private Button[][] cellButtons;      // up to 9x9
    private Label statusLabel;
    private Label movesLabel;
    private Button hintButton;
    private int rows = 0;
    private int cols = 0;

    // selection / hint state
    private Position selected = null;
    private Move hintMove = null;
    private char[][] prevBoard = null;

    // size of the board max of 9
    private static final int MAX_SIZE = 9;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pete's Pike");

        // top controls
        Button newBtn = new Button("New Game");
        Button resetBtn = new Button("Reset");
        Button quitBtn = new Button("Quit");
        hintButton = new Button("Hint");

        newBtn.setOnAction(e -> NewGame(primaryStage));
        resetBtn.setOnAction(e -> resetGame());
        quitBtn.setOnAction(e -> Platform.exit());
        hintButton.setOnAction(e -> showHint());
    }


}
