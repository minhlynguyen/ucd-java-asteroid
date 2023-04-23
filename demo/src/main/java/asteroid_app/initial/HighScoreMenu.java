package asteroid_app.initial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HighScoreMenu {

    // public static Scene highScoreScene(int WIDTH, int HEIGHT){

        // BorderPane borderPane = new BorderPane();

        // Label headLine = new Label("Name\t\tScore");

        // create a Vbox to manage the nodes on the high score menu
        // VBox vBox = new VBox(20, headLine);
        // vBox.setId("highScoreVbox");

        // read high scores from file
        // try {
        //     BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"));

        //     String line;
        //     while ((line = reader.readLine()) != null) {
        //         Label score = new Label(line);
        //         vBox.getChildren().add(score);
        //     }

        //     reader.close();
        // } catch (IOException e) {
        //     Label noScores = new Label("There are no high scores.");
        //     vBox.getChildren().add(noScores);
        // }

        // add vBox into root
        // borderPane.setCenter(vBox);

        // Scene highScoreScene = new Scene(borderPane, WIDTH, HEIGHT);

        // return highScoreScene;
    // }
}
