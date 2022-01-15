/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_8;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author nora
 */
public class NotePad extends Application {

    File fileToOpen = new File("");

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Untitled");

        MenuBar bar = new MenuBar();
        Menu file = new Menu("File");

        // new
        MenuItem newFile = new MenuItem("New");
        newFile.setAccelerator(KeyCombination.keyCombination("ALT+N"));
        // open
        MenuItem openFile = new MenuItem("Open");
        openFile.setAccelerator(KeyCombination.keyCombination("ALT+O"));
        openFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));

        // save ad
        MenuItem saveAsFile = new MenuItem("Save As");
        saveAsFile.setAccelerator(KeyCombination.keyCombination("ALT+Shift+S"));
        saveAsFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("bookmark.png"))));

        // save
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setAccelerator(KeyCombination.keyCombination("ALT+S"));

        // exit
        MenuItem exitFile = new MenuItem("Exit");
        exitFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

        Menu edit = new Menu("Edit");
        // undo
        MenuItem undoEdit = new MenuItem("Undo");
        undoEdit.setAccelerator(KeyCombination.keyCombination("ALT+Z"));
        // Cut
        MenuItem cutEdit = new MenuItem("Cut");
        cutEdit.setAccelerator(KeyCombination.keyCombination("ALT+X"));
        // Copy
        MenuItem copyEdit = new MenuItem("Copy");
        copyEdit.setAccelerator(KeyCombination.keyCombination("ALT+C"));
        // paste
        MenuItem pasteEdit = new MenuItem("Paste");
        pasteEdit.setAccelerator(KeyCombination.keyCombination("ALT+V"));
        // delete
        MenuItem deleteEdit = new MenuItem("Delete");
        deleteEdit.setAccelerator(KeyCombination.keyCombination("Del"));
        // selectAll
        MenuItem selectAllEdit = new MenuItem("Select All");

        Menu help = new Menu("Help");
        // aboutHelp
        MenuItem aboutHelp = new MenuItem("About Notepad");
        // dialog 
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("About Notepad");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("Nora alaa eldin salama");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        //Showing the dialog on clicking the button
        aboutHelp.setOnAction(e -> {
            dialog.showAndWait();
        });

        file.getItems().addAll(newFile, openFile, saveFile, saveAsFile, new SeparatorMenuItem(), exitFile);

        edit.getItems().addAll(undoEdit, new SeparatorMenuItem(), copyEdit, cutEdit, pasteEdit, deleteEdit, new SeparatorMenuItem(), selectAllEdit);

        help.getItems().addAll(aboutHelp);

        bar.getMenus().addAll(file, edit, help);

        // textarea
        TextArea txtArea = new TextArea();

//        TextInputControl;
        copyEdit.setOnAction(e -> {
            txtArea.copy();
        });

        pasteEdit.setOnAction(e -> {
            txtArea.paste();
        });

        cutEdit.setOnAction(e -> {
            txtArea.cut();
        });

        undoEdit.setOnAction(e -> {
            txtArea.undo();
        });

        deleteEdit.setOnAction(e -> {
            txtArea.deleteText(txtArea.getSelection());
        });

        selectAllEdit.setOnAction(e -> {
            txtArea.selectAll();
        });

        exitFile.setOnAction(e -> {
            primaryStage.close();
        });

        newFile.setOnAction(e -> {
            txtArea.clear();
            primaryStage.setTitle("Untitled");
            fileToOpen = new File("");
        });

//        openFile.setOnAction(e -> {
//            txtArea.clear();
//        });

        FileChooser fileChoose = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Text Documents(*.txt)", "*.txt");

        openFile.setOnAction(e -> {
            txtArea.clear();

            fileChoose.setTitle("Open File");

            fileChoose.getExtensionFilters().add(extFilter);

            fileToOpen = fileChoose.showOpenDialog(primaryStage);
            char dataInFile[] = new char[(int) fileToOpen.length()];
            try {
                System.out.println(fileToOpen.isFile());

                FileReader fileReader = new FileReader(fileToOpen);
                fileReader.read(dataInFile);
//                System.out.println(fileToOpen.getName().split("."));

                primaryStage.setTitle(fileToOpen.getName() + " Notepad");

                txtArea.appendText(String.valueOf(dataInFile));
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        saveFile.setOnAction(e -> {
            try {
                if (fileToOpen.exists()) {
                    FileWriter fileWriter = new FileWriter(fileToOpen);

                    fileWriter.write(txtArea.getText());

                    fileWriter.close();

                } else {

                    fileChoose.setTitle("Save As File");

                    fileChoose.getExtensionFilters().add(extFilter);

                    File fileToSave = fileChoose.showSaveDialog(primaryStage);
                    fileToOpen = fileToSave;
//                fileToSave.createNewFile();
                    FileWriter fileWriter = new FileWriter(fileToSave);

                    fileWriter.write(txtArea.getText());
                    System.out.println(txtArea.getText());

                    primaryStage.setTitle(fileToSave.getName() + " Notepad");
                    fileWriter.close();

                }

                System.out.println(fileToOpen.exists());

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        saveAsFile.setOnAction((ActionEvent e) -> {
            fileChoose.setTitle("Save As File");

            fileChoose.getExtensionFilters().add(extFilter);

            File fileToSave = fileChoose.showSaveDialog(primaryStage);
            fileToOpen = fileToSave;

            try {
//                fileToSave.createNewFile();

                FileWriter fileWriter = new FileWriter(fileToSave);

                fileWriter.write(txtArea.getText());
                System.out.println(txtArea.getText());

                primaryStage.setTitle(fileToSave.getName() + " Notepad");
                fileWriter.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

//            System.out.println(fileToSave);
        });

        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(txtArea);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
