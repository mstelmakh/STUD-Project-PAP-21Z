package z11.libraryapp.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import z11.libraryapp.DbHandler;
import z11.libraryapp.errors.DdlQueryError;
import z11.libraryapp.errors.UnavailableDB;
import z11.libraryapp.model.Book;
import z11.libraryapp.model.BookInstance;
import z11.libraryapp.model.User;

public class UserBookReservedController {

    private User userObject;

    private Book bookObject;

    @FXML
    private Label bookAuthor;

    @FXML
    private Label bookId;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookTitle;

    public void setData(BookInstance bookInstance, User user){
        try {
            userObject = user;
            DbHandler dbManager = new DbHandler();
            bookObject = dbManager.getBook(bookInstance.getBook_id());
            Image image = new Image(getClass().getResourceAsStream("/z11/libraryapp/img/covers/" +
                                                                    bookObject.getCoverSrc()));
            bookImage.setImage(image);
            bookTitle.setText(bookObject.getTitle());
            bookTitle.setWrapText(true);
            bookAuthor.setWrapText(true);
            bookAuthor.setText(bookObject.getAuthorsNames());
            bookId.setText(Integer.toString(bookInstance.getId()));
        } catch (DdlQueryError | UnavailableDB | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bookOnMouseClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = MainWindowController.changeScene(event, "/z11/libraryapp/fxml/BookView.fxml");
            BookViewController bookViewController = fxmlLoader.getController();
            bookViewController.setData(bookObject, userObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnavailableDB e) {
            e.printStackTrace();
        }
    }

}
