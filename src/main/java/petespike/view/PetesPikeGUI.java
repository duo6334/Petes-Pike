package petespike.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import petespike.model.Move;
import petespike.model.PetesPike;
import petespike.model.PetesPikeObserver;
import petespike.model.Position;

public class PetesPikeGUI extends Application implements PetesPikeObserver{
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

    public void NewGame(Stage primaryStage){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pete's Pike");

        
        // top controls
        Button newBtn = new Button("New Game");
        Button resetBtn = new Button("Reset");
        Button quitBtn = new Button("Quit");
        hintButton = new Button("Hint");

        // newBtn.setOnAction(e -> newGame(primaryStage));
        // resetBtn.setOnAction(e -> resetGame());
        // quitBtn.setOnAction(e -> Platform.exit());
        // hintButton.setOnAction(e -> showHint());

        // movement controls
        Button upBtn = new Button();
        Button downBtn = new Button();
        Button leftBtn = new Button();
        Button rightBtn = new Button();

        // Load arrows
        Image upArrow = new Image("file:data/25637.png");
        Image downArrow = new Image("file:data/61932.png");
        Image leftArrow = new Image("file:data/959160.png");
        Image rightArrow = new Image("file:data/right-arrow.png");

        // View arrows and attach to buttons
        ImageView upView = new ImageView(upArrow);
        upView.setFitWidth(30);
        upBtn.setGraphic(upView);
        ImageView downView = new ImageView(downArrow);
        downView.setFitWidth(30);
        downBtn.setGraphic(downView);
        ImageView leftView = new ImageView(leftArrow);
        leftView.setFitWidth(30);
        leftBtn.setGraphic(leftView);
        ImageView rightView = new ImageView(rightArrow);
        rightView.setFitWidth(30);
        rightBtn.setGraphic(rightView);

        GridPane movementCtrl = new GridPane();
        // middle of top row
        movementCtrl.add(upBtn, 1, 0);
        // middle of bottom row
        movementCtrl.add(downBtn, 1, 2);
        // left of middle row
        movementCtrl.add(leftBtn, 0, 1);
        // right of middle row
        movementCtrl.add(rightBtn, 2, 1);


        Label newGame = new Label("New Game!");
        Label validMove = new Label("Good Move!");
        Label invalidMove = new Label("Illegal move: Piece will fall off mountain!");
        Label winLabel = new Label("Congratulations! You Won!");

        // this will show the state (labels ^) of the game  
        HBox gameStatus = new HBox();
        gameStatus.getChildren().addAll(newGame,movesLabel);

        VBox wholeBoard = new VBox();

        //wholeBoard.getChildren().addAll(movementCtrl);

        Scene scene = new Scene(movementCtrl);
        primaryStage.setTitle("Petes Pike");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void pieceMoved(Position from, Position to) {
        // TODO Auto-generated method stub
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    


}
