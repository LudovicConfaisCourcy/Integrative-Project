package edu.vanier.template.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/*
 * @authors Anton Lisunov
 */
public class HelpWindowController {

    @FXML
    private TextArea txtHelp;

    public void initialize() {
       
            txtHelp.setText(readTextFileAsString("Help.txt"));

       
    }
    
     public static String readTextFileAsString(String relativeFilePath) {
        StringBuilder content = new StringBuilder();
        Path filePath = Paths.get(System.getProperty("user.dir"), relativeFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

}
