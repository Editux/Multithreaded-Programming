package sample;


import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Controller {
    @FXML
    TextArea text;
    @FXML
    TextField directoryfield;
    @FXML
    ProgressBar pb;

    Encryption encryption = new Encryption();
    Thread thread1;
    Thread thread2;

    /*WARNING: If you decide to run my program you will notice that I copied my GUI from TASK 1 cuz
    I'm that lazy. Enter your folder in directory field and press Find( tbh should be named encrypt) */

    @FXML
    private void handlesearchToEncrypt() {


        thread1 = new Thread() {
            @Override
            public void run() {
                pb.setProgress(10);
                String directory = directoryfield.getText();


                try {
                    encryption.searchToEncrypt(new File(directory));
                    pb.setProgress(50);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                text.appendText("Encryption success\n");
                pb.setProgress(100);
            }

        };
        try {
            pb.setProgress(0.0F);
            thread1.sleep(3000);
        } catch (InterruptedException e) {

        }
        thread1.start();


    }

    @FXML
    private void handlesearchToDecrypt() {

        thread2 = new Thread() {
            @Override
            public void run() {
                pb.setProgress(10);
                String directory = directoryfield.getText();

                // do stuff

                try {

                    encryption.searchtoDecrypt(new File(directory));
                    pb.setProgress(50);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                text.appendText("Decryption success\n");
                pb.setProgress(100);
            }

        };
        try {
            pb.setProgress(0.0F);
            thread2.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();


    }

    @FXML
    private void handleStopThread() {
        try {
            thread1.suspend();
            thread2.suspend();

        } catch (NullPointerException e) {
        }
        text.appendText("Thread suspended\n");
    }

    @FXML
    private void handleResumeThread() {
        try {
            thread1.resume();
            thread2.resume();

        } catch (NullPointerException e) {

        }
        text.appendText("Thread resumed \n");
    }

    @FXML
    private void handleZip() throws IOException {

        String directory = directoryfield.getText();

        FileOutputStream fos = new FileOutputStream(directory+".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(directory);

        encryption.zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
        text.appendText("The folder is zipped");

    }
}
