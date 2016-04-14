import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ScrollBar;
import javafx.geometry.Orientation;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
public class Twitter extends Application {
    TwitterServer tsv = new TwitterServer();
    BorderPane pane = new BorderPane();
    HBox toolbar = new HBox();   
    VBox vb1 = new VBox();
    User mainUser;
    TextField textField = new TextField();
    static ObservableList<Tweet> feed = FXCollections.observableArrayList();
    static ListView<Tweet> list = new ListView<>(feed);
    Button deleteBtn;
    @Override
    public void start(Stage primaryStage) {
        //Toolbar - Refresh button
        Image img = new Image("TwitterSymbol.jpg");
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(30);
        imgView.setFitWidth(30);
        Button refreshBtn = new Button("Refresh");
        Refresh b1 = new Refresh();
        refreshBtn.setGraphic(imgView);
        refreshBtn.setOnAction(b1);
        refreshBtn.setStyle("-fx-background-color: #FFFFFF");
        toolbar.getChildren().add(refreshBtn);
        toolbar.setAlignment(Pos.CENTER);
        toolbar.setStyle("-fx-background-color: #FFFFFF");
        pane.setTop(toolbar);
        //VBox left
        mainUser = new User("Cool Puppy", "@ngailapdi", "mainUser.jpg", "CS 1331 Student");
        //Profile picture of the user
        Image img1 = new Image(mainUser.getImage());
        ImageView img1View = new ImageView(img1);
        img1View.setFitHeight(200);
        img1View.setFitWidth(300);
        vb1.getChildren().add(img1View);
        //Tweet button
        Image img2 = new Image("tweetBtn.jpg");
        ImageView img2View = new ImageView(img2);
        img2View.setFitHeight(30);
        img2View.setFitWidth(80);
        Tweeting b2 = new Tweeting();
        Button tweetBtn = new Button();
        tweetBtn.setGraphic(img2View);
        tweetBtn.setStyle("-fx-background-color: #FFFFFF");
        tweetBtn.setOnAction(b2);
        //Place to tweet for user
        textField.setPromptText("What's happening?");
        textField.setPrefHeight(200);
        textField.setAlignment(Pos.TOP_LEFT);
        vb1.setPadding(new Insets(0, 10, 10, 10));
        Text mainUserName = new Text(mainUser.getName());
        mainUserName.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
        mainUserName.setFill(Color.BLUE);
        vb1.setSpacing(10);
        vb1.getChildren().add(mainUserName);
        vb1.getChildren().add(new Text(mainUser.getTwitterHandle()));
        vb1.getChildren().add(new Text(mainUser.getDescription()));
        vb1.getChildren().add(textField);
        vb1.getChildren().add(tweetBtn);
        vb1.setAlignment(Pos.TOP_CENTER);
        vb1.setStyle("-fx-background-color: #FFFFFF");
        pane.setLeft(vb1);  
        
        pane.setCenter(list);
                
        
        
        


        
        Scene scene = new Scene(pane, 800, 800);
        primaryStage.setTitle("Twitter");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    private class Refresh implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Refresh();
            

        }

    }
    private class Tweeting implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (!textField.getText().isEmpty()) {
                Tweet mainTweet = new Tweet(mainUser, textField.getText());
                feed.add(mainTweet);
                deleteBtn = mainTweet.getDeleteButton();
                deleteBtn.setOnAction((ActionEvent ae) -> {
                    feed.remove(mainTweet);
                    });
            } else {
                Alert fail = new Alert(AlertType.INFORMATION);
                fail.setTitle("Alert!");
                fail.setContentText("You haven't typed in anything!");
                fail.showAndWait();
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void Refresh() {
        int numTweet = 0;
        Random rand = new Random();
        int gen = rand.nextInt(3) + 2;
        for (int i = numTweet; i < gen + numTweet; i++) {
            Tweet newTweet = tsv.randTweet();
            feed.add(newTweet);
        }
        numTweet += gen;
    }
    public static ObservableList<Tweet> getFeed() {
        return feed;
    }
    public static ListView<Tweet> getList() {
        return list;
    }            
}