package z11.libraryapp.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import z11.libraryapp.model.Book;
import z11.libraryapp.model.Genre;
import z11.libraryapp.model.User;

public class GenreViewController {

    private User userObject;

    @FXML
    private Button authorsButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private GridPane genreBooksContainer;

    @FXML
    private Button genresButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button readingButton;

    @FXML
    private Label usernameLabel;

    @FXML
    void authorsButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = MainWindowController.changeScene(event, "/z11/libraryapp/fxml/Authors.fxml");
        AuthorsController controller = fxmlLoader.getController();
        controller.setData(userObject);
    }

    @FXML
    void genresButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = MainWindowController.changeScene(event, "/z11/libraryapp/fxml/Genres.fxml");
        GenresController controller = fxmlLoader.getController();
        controller.setData(userObject);
    }

    @FXML
    void dashboardButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = MainWindowController.changeScene(event, "/z11/libraryapp/fxml/MainWindow.fxml");
        MainWindowController controller = fxmlLoader.getController();
        controller.setData(userObject);
    }

    @FXML
    void historyButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = MainWindowController.changeScene(event, "/z11/libraryapp/fxml/History.fxml");
        HistoryController controller = fxmlLoader.getController();
        controller.setData(userObject);
    }

    @FXML
    void readingButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = MainWindowController.changeScene(event, "/z11/libraryapp/fxml/Reading.fxml");
        ReadingController controller = fxmlLoader.getController();
        controller.setData(userObject);
    }

    @FXML
    void logOutButtonOnAction(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = MainWindowController.changeScene(event, "/z11/libraryapp/fxml/SignIn.fxml");
        SignInController signInController = fxmlLoader.getController();
        signInController.setData(userObject);
    }

    private void setGenreNameLabel(Genre genre){
        Label genreName = new Label(genre.getName());
        genreName.setFont(Font.font("Berlin Sans FB", 24));
        genreName.setText(genre.getName());
        genreName.setPadding(new Insets(0, 0, 0, 10));
        genreBooksContainer.add(genreName, 0, 1);
    }

    private void setGenreBooks(Genre genre, ArrayList<Book> books) throws IOException{
        int col = 0;
        int row = 2;
        for (Book book : books){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(BookController.class.getResource("/z11/libraryapp/fxml/Book.fxml"));
            VBox bookBox = fxmlLoader.load();
            BookController bookController = fxmlLoader.getController();
            bookController.setData(book, userObject);

            if (col==6){
                col = 0;
                ++row;
            }
            genreBooksContainer.add(bookBox, col++, row);
            GridPane.setMargin(bookBox, new Insets(10));
        }
    }

    public void setData(Genre genre, ArrayList<Book> books, User user) throws IOException{
        userObject = user;
        usernameLabel.setText(userObject.getLogin());
        setGenreNameLabel(genre);
        setGenreBooks(genre, books);
    }
}
