package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.File;

public class Controller {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField filefield;
    @FXML
     private TextField directoryfield;

    Search search =new Search();

    @FXML
    private void initialize(){
        textArea.setText("");
    }

    @FXML
    private void handleSearch (){
      String directory = directoryfield.getText();
      String file= filefield.getText();
search.searchDirectory(new File(directory),file);
int count = search.getResult().size();

if(count==0){
    textArea.appendText("No results \n");
}else{
    textArea.appendText("Found"+count+ "results!\n");
    for(String matched: search.getResult()){
    textArea.appendText("Found:"+ matched +"\n");
    }
}
}
}
