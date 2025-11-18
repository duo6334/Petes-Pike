package petespike.view;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import petespike.model.Direction;
import petespike.model.Move;
import petespike.model.PetesPike;
import petespike.model.PetesPikeException;
import petespike.model.PetesPikeObserver;
import petespike.model.Position;

public class PetesPikeGUI extends Application implements PetesPikeObserver {
    private PetesPike game;
    private String currentFilename = "data/petes_pike_5_5_4_0.txt"; // last used filename (used by reset)
    private final String[] list_of_files = {"data/petes_pike_5_5_2_0.txt", "data/petes_pike_5_5_4_0.txt",
                                     "data/petes_pike_5_5_4_1.txt", "data/petes_pike_5_5_5_0.txt",
                                      "data/petes_pike_5_7_4_0.txt", "data/petes_pike_9_9_9_0.txt"};
    private int list_index = 0;
        
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

    //move state
    private Position position = null;
    private Direction direction = null;

    // public PetesPike board = new PetesPike(currentFilename);


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pete's Pike");

        // Board
        GridPane board = makeBoard();
        

        // top controls
        Button newBtn = new Button("New Game");
        newBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Button resetBtn = new Button("Reset");
        resetBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Button hintButton = new Button("Get Hint");
        hintButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // newBtn.setOnAction(e -> newGame(primaryStage));
        // resetBtn.setOnAction(e -> resetGame())

        

        TextField fileTextBox = new TextField("Enter file: ");
        fileTextBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fileTextBox.setMinWidth(300);
        fileTextBox.setOnAction((ActionEvent event) -> {
            String filename = fileTextBox.getText();
            
            try {
                game = new PetesPike(filename);
                setCurrentFilename(filename);
                // update row/col and grid
                board.getChildren().clear();
                board.getChildren().addAll(boardGrid.getChildren());
                // reset moves
                movesLabel.setText("moves:0");
                statusLabel.setText("New game loaded.");
            } catch (PetesPikeException e) {
                statusLabel.setText("Error: " + e.getMessage());
            }
        });

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

        //button event handing

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
        Label statusLabel = new Label(""); // used by pieceMoved
        statusLabel.setMinWidth(200);
        

        Label newGame = new Label("New Game!");
        newGame.setMinWidth(370);
        newGame.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Label validMove = new Label("Good Move!");
        validMove.setMinWidth(370);
        validMove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Label invalidMove = new Label("Illegal move: Piece will fall off mountain!");
        invalidMove.setMinWidth(370);
        invalidMove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Label winLabel = new Label("Congratulations! You Won!");
        winLabel.setMinWidth(370);
        winLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


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
        wholeBoard.getChildren().addAll(topRow,midRow,gameStatus);
        
        BorderPane wholeWindow = new BorderPane();
        wholeWindow.setTop(topRow);
        wholeWindow.setCenter(midRow);
        wholeWindow.setBottom(gameStatus);


        //hintButton.setOnAction(e -> showHint(gameStatus,rightSide));
        //newBtn.setOnAction(e -> newGame(midRow,fileTextBox,gameStatus));

        // Button actions
        newBtn.setOnAction(e -> {
            //      try {
            //     this.game = new PetesPike(currentFilename);
            //     // update row/col and grid
            //     boardGrid = makeBoard();
            //     midRow.getChildren().set(0, boardGrid); // replace center with the new boardGrid
            //     // reset status/moves
            //     movesLabel.setText("moves:0");
            //     statusLabel.setText("New game loaded.");
            // } catch (PetesPikeException el) {
            //     statusLabel.setText("Error: " + el.getMessage());
            // }
                try {
                    this.game = new PetesPike(list_of_files[list_index]);
                    boardGrid = makeBoard();
                    midRow.getChildren().set(0, boardGrid);
                    movesLabel.setText("moves:0");
                    statusLabel.setText("New Game.");
                    if ( list_index == 5) {
                        setListIndex();
                    }
                    list_index++;
                } catch (PetesPikeException ex) {
                    statusLabel.setText("Error: " + ex.getMessage());
                }
        });

        resetBtn.setOnAction(e -> {
            // Reset by reloading the last filename
            if (currentFilename != null && !currentFilename.isEmpty()) {
                // try {
                    // this.game = new PetesPike(currentFilename);
                    boardGrid = makeBoard();
                    midRow.getChildren().set(list_index, boardGrid);
                    movesLabel.setText("moves:0");
                    statusLabel.setText("Game reset.");
                // } catch (PetesPikeException ex) {
                // }
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

        upBtn.setOnMousePressed(e -> {
            direction = Direction.UP;
            if(direction != null && position != null){
                Move move = new Move(position, direction);
                try {
                    game.makeMove(move);
                    updateBoard();
                } catch (PetesPikeException e1) {
                     // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            position = null; 
            direction = null;
        });

        downBtn.setOnMousePressed(e ->{
            direction = Direction.DOWN;
            if(direction != null && position != null){
                Move move = new Move(position, direction);
                try {
                    game.makeMove(move);
                    updateBoard();
                } catch (PetesPikeException e1) {
                     // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            position = null; 
            direction = null;
        });

        rightBtn.setOnMousePressed(e ->{
            direction = Direction.RIGHT;
            if(direction != null && position != null){
                Move move = new Move(position, direction);
                try {
                    game.makeMove(move);
                    updateBoard();
                } catch (PetesPikeException e1) {
                     // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            position = null; 
            direction = null;
        });

        leftBtn.setOnMousePressed(e ->{
            direction = Direction.LEFT;
            if(direction != null && position != null){
                Move move = new Move(position, direction);
                try {
                    game.makeMove(move);
                    updateBoard();
                } catch (PetesPikeException e1) {
                     // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            position = null; 
            direction = null;
        });


        Scene scene = new Scene(wholeWindow);
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
        middleRow.getChildren().set(0,makeBoard());
        }catch(PetesPikeException e){
            gameStatus.getChildren().set(0,new Label(e.getMessage()));
        }
    }

    // Create the board grid
    public GridPane makeBoard() {
        GridPane board = new GridPane();
        
        if(this.game == null){
            for(int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    Button square = new Button("");
                    square.setMinWidth(50);
                    square.setMinHeight(50);
                    board.add(square, i, j);
                 }
             }
        }
        else {
            for(int i = 0; i < game.getRows(); i++){
                for (int j = 0; j < game.getCols(); j++){
                    if(game.getSymbolAt(new Position(i, j)).equals("-")){
                        Button square = new Button("");
                        square.setMinWidth(30);
                        square.setMinHeight(30);
                        board.add(square, j, i);
                    }else if(game.getSymbolAt(new Position(i, j)).equals("\u001b[38;5;220mP\u001b[0m")){
                        final int k = i;
                        final int l = j;
                        Button square = new Button("P");
                        square.setTextFill(Color.GOLD);
                        square.setMinWidth(30);
                        square.setMinHeight(30);
                        board.add(square, j, i);
                        square.setOnMousePressed(e ->{
                            position = new Position(k, l);
                        });
                    }else if(game.getSymbolAt(new Position(i, j)).equals("T")){
                        final int k = i;
                        final int l = j;
                        Button square = new Button("T");
                        square.setMinWidth(30);
                        square.setMinHeight(30);
                        board.add(square, j, i);
                        square.setOnMousePressed(e ->{
                            position = new Position(k, l);
                        });
                    }else{
                        final int k = i;
                        final int l = j;
                        Button square = new Button("G");
                        square.setMinWidth(30);
                        square.setMinHeight(30);
                        board.add(square, j, i);
                        square.setOnMousePressed(e ->{
                            position = new Position(k, l);
                        });
                    }
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
                ImageView up = new ImageView(upArrow);
                up.setFitWidth(20);
                up.setFitHeight(20);
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),up);
            }else if(possibleMove.getDirection()==Direction.DOWN){
                ImageView down = new ImageView(downArrow);
                down.setFitWidth(20);
                down.setFitHeight(20);
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),down);
            }else if(possibleMove.getDirection()==Direction.LEFT){
                ImageView left = new ImageView(leftArrow);
                left.setFitWidth(20);
                left.setFitHeight(20);
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),left);
            }else{
                ImageView right = new ImageView(rightArrow);
                right.setFitWidth(20);
                right.setFitHeight(20);
                move.getChildren().addAll(new Label(game.getSymbolAt(possibleMove.getPosition())),right);
            }

            if (rightSide.getChildren().size() > 2) {
                rightSide.getChildren().set(2,move);
            }else {
                rightSide.getChildren().add(move);
            }
            gameStatus.getChildren().set(0,new Label(""));
            }else{gameStatus.getChildren().set(0,new Label("No possible moves"));}
    }

    public void setCurrentFilename(String filename){
        this.currentFilename = "file:" + filename;
    }
    public void setListIndex(){
        this.list_index = 0;
    }
    

    public static void main(String[] args) throws PetesPikeException {
        //PetesPike test = new PetesPike("data\\petes_pike_5_5_4_0.txt");
        launch(args);
    }
    


}
