package asteroid_app.initial;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HighScoreMenu {

    public static Scene highScoreScene(int WIDTH, int HEIGHT){

        BorderPane borderPane = new BorderPane();

        Label headLine = new Label("Name\tScore");

        // create a Vbox to manage the nodes on the high score menu
        VBox vBox = new VBox(20, headLine);
        vBox.setId("highScoreVbox");

        // add vBox into root
        borderPane.setCenter(vBox);

        Scene highScoreScene = new Scene(borderPane, WIDTH, HEIGHT);

        return highScoreScene;
    }
}
