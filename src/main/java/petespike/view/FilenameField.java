package petespike.view;

import java.awt.TextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import petespike.model.PetesPike;
import petespike.model.PetesPikeException;

public class FilenameField implements EventHandler<ActionEvent>{

     TextField filenameField;
     PetesPike game;

    // public FilenameField(TextField filename, PetesPike game) {
    //     this.filenameField = filename;
    //     this.game = game;
    // }

    @Override
    public void handle(ActionEvent event) {
        String filename = filenameField.getText();

            try {
                this.game = new PetesPike(filename);
            } catch (PetesPikeException e) {
                System.out.println("File not found");
            }
        
    }

}
