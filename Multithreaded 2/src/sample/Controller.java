package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;

public class Controller {
    @FXML
    TextArea text;
    @FXML
    TextField directoryfield;

    Encryption encryption = new Encryption();

    /*WARNING: If you decide to run my program you will notice that I copied my GUI from TASK 1 cuz
    I'm that lazy. Enter your folder in directory field and press Find( tbh should be named encrypt) */

@FXML
    private void handlesearch(){
    Thread thread = new Thread() {
        @Override
        public void run() {
            String directory = directoryfield.getText();
            try {
                encryption.search(new File(directory));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    };
    thread.start();
    text.appendText("Encryption success");//This text will still pop out even you have expception so don't be confused.

}

}

