package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.google.common.base.Stopwatch;

import java.io.File;

public class Controller implements Runnable{
    @FXML
    private TextArea textArea;
    @FXML
    private TextField filefield;
    @FXML
     private TextField directoryfield;
    @FXML
    private Label timelabel;

    Search search =new Search();
    Stopwatch stopwach = Stopwatch.createUnstarted();

    @FXML
    private void initialize(){
        textArea.setText("");
        timelabel.setText("");
    }

    @FXML
    private void handleSearch (){
 new Thread (() -> {


     stopwach.start();
     String directory = directoryfield.getText();
     String file = filefield.getText();
     search.searchDirectory(new File(directory), file);
     int count = search.getResult().size();

     if (count == 0) {
         textArea.appendText("No results \n");
     } else {
         textArea.appendText("Found" + count + "results!\n");
         for (String matched : search.getResult()) {
             textArea.appendText("Found:" + matched + "\n");
         }

     }
     stopwach.stop();



 }).start();
 timelabel.setText(stopwach.toString());
}
    @Override
    public void run() {

    }
}
