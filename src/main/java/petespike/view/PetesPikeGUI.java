package petespike.view;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import petespike.model.Move;
import petespike.model.PetesPike;
import petespike.model.PetesPikeException;
import petespike.model.PetesPikeException;
import petespike.model.PetesPikeObserver;
import petespike.model.Position;

public class PetesPikeGUI extends Application implements PetesPikeObserver{
    private PetesPike game;
    private String currentFilename = ""; // last used filename (used by reset)

    // UI
    private GridPane boardGrid;
    private Button[][] cellButtons;      // up to 9x9
    private Label statusLabel;
    private Label movesLabel = new Label("moves:0");
    private Button hintButton;
    private int rows = 0;
    private int cols = 0;
    private Image upArrow = new Image("file:data/25637.png");
    private Image downArrow = new Image("file:data/61932.png");
    private Image leftArrow = new Image("file:data/959160.png");
    private Image rightArrow = new Image("file:data/right-arrow.png");


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

        // Board
        GridPane board = new GridPane();
        

        // top controls
        Button newBtn = new Button("New Game");
        Button resetBtn = new Button("Reset");
        hintButton = new Button("Get Hint");

        // newBtn.setOnAction(e -> newGame(primaryStage));
        // resetBtn.setOnAction(e -> resetGame())
        

        TextField fileTextBox = new TextField();
        fileTextBox.setPromptText("Enter file");

        // movement controls
        Button upBtn = new Button();
        Button downBtn = new Button();
        Button leftBtn = new Button();
        Button rightBtn = new Button();

        // Load arrows
        

        // View arrows and attach to buttons
        ImageView upView = new ImageView(upArrow);
        upView.setFitWidth(50);
        upView.setFitHeight(30);
        upView.setFitWidth(50);
        upView.setFitHeight(30);
        upBtn.setGraphic(upView);
        ImageView downView = new ImageView(downArrow);
        downView.setFitWidth(50);
        downView.setFitHeight(30);
        downView.setFitWidth(50);
        downView.setFitHeight(30);
        downBtn.setGraphic(downView);
        ImageView leftView = new ImageView(leftArrow);
        leftView.setFitWidth(30);
        leftView.setFitHeight(30);
        leftView.setFitHeight(30);
        leftBtn.setGraphic(leftView);
        ImageView rightView = new ImageView(rightArrow);
        rightView.setFitWidth(30);
        rightView.setFitHeight(30);
        rightView.setFitHeight(30);
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
        newGame.setMinWidth(400);
        Label validMove = new Label("Good Move!");
        validMove.setMinWidth(400);
        Label invalidMove = new Label("Illegal move: Piece will fall off mountain!");
        invalidMove.setMinWidth(400);
        Label winLabel = new Label("Congratulations! You Won!");
        winLabel.setMinWidth(400);

        // this will show the state (labels ^) of the game  
        HBox gameStatus = new HBox();
        gameStatus.getChildren().addAll(invalidMove,movesLabel);

        HBox topRow = new HBox();
        topRow.getChildren().addAll(resetBtn,fileTextBox,newBtn);

        VBox rightSide = new VBox();
        rightSide.getChildren().addAll(movementCtrl,hintButton);

        HBox midRow = new HBox();
        midRow.getChildren().addAll(board,rightSide);

        VBox wholeBoard = new VBox();

        wholeBoard.getChildren().addAll(topRow,rightSide,gameStatus);

        //hintButton.setOnAction(e -> showHint(gameStatus,rightSide));
        //newBtn.setOnAction(e -> newGame(midRow,fileTextBox,gameStatus));

        Scene scene = new Scene(wholeBoard);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void pieceMoved(Position from, Position to) {
        
        
    }

    public void newGame(HBox middleRow, TextField fileText, HBox gameStatus){
        try{
        this.game = new PetesPike(fileText.getText());
        }catch(PetesPikeException e){
            gameStatus.getChildren().set(0,new Label(e.getMessage()));
        }
    }

    // Create the board grid
    public GridPane makeBoard() {
        GridPane board = new GridPane();
        for(int i = 0; i == game.getRows(); i++){
            for (int j = 0; j == game.getCols(); j++){
                Label label = new Label("placeholder");
                board.add(label, i, j);
            }
        }
        return board;
    }

    public void showHint(HBox gameStatus,VBox rightSide){
        List<Move> possibleMoves = game.getPossibleMoves();
        if (possibleMoves.size()>0){
            Move possibleMove = possibleMoves.get(0);
            HBox move = new HBox();
            move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),new ImageView(upArrow));
            rightSide.getChildren().set(2,move);
            gameStatus.getChildren().set(0,new Label(""));
            }
        else{gameStatus.getChildren().set(0,new Label("No possible moves"));}
    }

    public static void main(String[] args) throws PetesPikeException {
        //PetesPike test = new PetesPike("data\\petes_pike_5_5_4_0.txt");
        launch(args);
    }
    


}
