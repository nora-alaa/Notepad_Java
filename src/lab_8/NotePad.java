/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_8;

import javafx.application.Application;
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
import javafx.stage.Stage;

/**
 *
 * @author nora
 */
public class NotePad extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Notepad");

        MenuBar bar = new MenuBar();
        Menu file = new Menu("File");
        // new
        MenuItem newFile = new MenuItem("New");
        newFile.setAccelerator(KeyCombination.keyCombination("ALT+N"));
        // open
        MenuItem openFile = new MenuItem("Open");
        openFile.setAccelerator(KeyCombination.keyCombination("ALT+O"));
        openFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));

        // save
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setAccelerator(KeyCombination.keyCombination("ALT+S"));
        saveFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("bookmark.png"))));

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

        file.getItems().addAll(newFile, openFile, saveFile, new SeparatorMenuItem(), exitFile);

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
        });

        openFile.setOnAction(e -> {
            txtArea.clear();
        });


        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(txtArea);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
