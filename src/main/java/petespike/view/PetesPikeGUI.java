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
import petespike.model.PetesPikeObserver;
import petespike.model.Position;
import petespike.model.Direction;

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pete's Pike");

        // Board
        GridPane board = makeBoard();
        

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

        // status labels
        statusLabel = new Label(""); // used by pieceMoved
        statusLabel.setMinWidth(200);
        

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

        // Button actions
        newBtn.setOnAction(e -> {
            // Save the filename and create new game
            currentFilename = fileTextBox.getText();
            try {
                this.game = new PetesPike(currentFilename);
                // update row/col and grid
                boardGrid = makeBoard();
                midRow.getChildren().set(0, boardGrid); // replace center with the new boardGrid
                // reset status/moves
                movesLabel.setText("moves:0");
                statusLabel.setText("New game loaded.");
            } catch (PetesPikeException el) {
                statusLabel.setText("Error: " + el.getMessage());
            }
        });

        resetBtn.setOnAction(e -> {
            // Reset by reloading the last filename
            if (currentFilename != null && currentFilename.isEmpty()) {
                try {
                    this.game = new PetesPike(currentFilename);
                    boardGrid = makeBoard();
                    midRow.getChildren().set(0, boardGrid);
                    movesLabel.setText("moves:0");
                    statusLabel.setText("Game reset.");
                } catch (PetesPikeException ex) {
                    statusLabel.setText("Error: " + ex.getMessage());
                }
            } else {
                statusLabel.setText("No filename to reset to. Use New Game first.");
            }
        });

        hintButton.setOnAction(e -> {
            // show a hint on the right side
            if (game == null) {
                statusLabel.setText("Load a game first to get a hint.");
                return;
            }
            showHint(gameStatus, rightSide);
        });



        Scene scene = new Scene(wholeBoard);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void pieceMoved(Position from, Position to) {
        updateBoard();
        int count = game.getMoveCount();
        movesLabel.setText("moves:" + count);
        if (game.hasWon()) {
        statusLabel.setText("Congratulations! You Won!");
        } else {
        statusLabel.setText("Good Move!");
        }
        
    }

    private void updateBoard() {
        if (game == null || cellButtons == null) {
            return;
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            
            // model returns 1 character (like "-", "P", "0", "T")
            String symbol = game.getSymbolAt(new Position(r, c));

            // Put that symbol on the label
            if (symbol == null) symbol = "-";
            cellButtons[r][c].setText(symbol);
        }
    }

    }


    public void newGame(HBox middleRow, TextField fileText, HBox gameStatus){
        try{
        this.game = new PetesPike(fileText.getText());
        makeBoard();
        }catch(PetesPikeException e){
            gameStatus.getChildren().set(0,new Label(e.getMessage()));
        }
    }

    // Create the board grid
    public GridPane makeBoard() {
        GridPane board = new GridPane();
        
        if(this.game == null){
            for(int i = 0; i < 5; i++){
                for (int j = 0; j == 5; j++){
                    Label label = new Label("placeholder");
                    board.add(label, i, j);
                 }
             }
        }
        else {
            for(int i = 0; i < game.getRows(); i++){
                for (int j = 0; j == game.getCols(); j++){
                    Label label = new Label("placeholder");
                    board.add(label, i, j);
                }
            }
        }
        return board;
    }

    public void showHint(HBox gameStatus,VBox rightSide){
        if (game == null) {
            gameStatus.getChildren().set(0, new Label("Load a game first"));
            return;
        }
        List<Move> possibleMoves = game.getPossibleMoves();
        if (possibleMoves.size()>0){
            Move possibleMove = possibleMoves.get(0);
            hintMove = possibleMove;//storing last
            HBox move = new HBox();
            if(possibleMove.getDirection()==Direction.UP){
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),new ImageView(upArrow));
            }else if(possibleMove.getDirection()==Direction.DOWN){
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),new ImageView(downArrow));
            }else if(possibleMove.getDirection()==Direction.LEFT){
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),new ImageView(leftArrow));
            }else{
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),new ImageView(rightArrow));
            }

            if (rightSide.getChildren().size() > 2) {
                rightSide.getChildren().set(2,move);
            }else {
                rightSide.getChildren().add(move);
            }
            gameStatus.getChildren().set(0,new Label(""));
            }else{gameStatus.getChildren().set(0,new Label("No possible moves"));}
    }

    public static void main(String[] args) throws PetesPikeException {
        //PetesPike test = new PetesPike("data\\petes_pike_5_5_4_0.txt");
        launch(args);
    }
    


}
