package asteroid_app.initial;

// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.animation.AnimationTimer;

public class GameMenu {
     
    static GameController game; 
    static Text pointText; 
    // static int currentLevel = 1;
    static Text levelText;

    public static Scene newGameMenu() {

        // Create a root as a border pane
        BorderPane root = new BorderPane();
        root.setPrefSize(Main.WIDTH, Main.HEIGHT);
        Scene mainScene = new Scene(root);

        // Create all nodes for the StartMenu
        Label headline = new Label("ASTEROIDS");
        headline.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        Label website = new Label("www.freevideogamesonline.com");
        Button playGame = new Button("PLAY GAME");
        // playGame.setId("playGame");
        Button highScores = new Button("HIGH SCORES");
        // highScores.setId("highScores");
        // create a Vbox to manage the nodes on the start menu
        VBox vBox = new VBox(30, headline, playGame, highScores, website);
        vBox.setAlignment(Pos.CENTER);
        // add vBox into root

        // create all nodes for the Main Game
        Pane gameScreen = new Pane();
        // Create a hbox to display points and level
        HBox hBox = new HBox(400);
        hBox.setAlignment(Pos.CENTER);         
        pointText = new Text(Main.pointX, Main.pointY, "Points: 0");
        levelText = new Text(Main.pointX, Main.pointY, "Level: 1");
        hBox.getChildren().addAll(levelText, pointText);
        // Control box
        Button restartGame = new Button("RESTART");
        Button quitGame = new Button("QUIT");
        HBox controlBox = new HBox(10, quitGame, restartGame);
        controlBox.setAlignment(Pos.CENTER);

        // Clock to control Timeline of the game
        class Movement extends AnimationTimer{
            @Override
            public void handle(long now){
                game.play(gameScreen, mainScene);    
            }
        }
        Movement clock = new Movement();


        // To display Start Screen
        root.setCenter(vBox);

        // To display Game Screen and start the game
        playGame.setOnAction(e -> {
            root.setCenter(gameScreen);
            root.setTop(hBox);
            root.setBottom(controlBox);
            game = new GameController(gameScreen, mainScene);
            clock.start();
            game.play(gameScreen, mainScene);
            gameScreen.requestFocus();
        });
        
        // To restart the game
        restartGame.setOnAction(e -> {
            clock.stop();
            gameScreen.getChildren().clear();
            game = new GameController(gameScreen, mainScene);
            clock.start();
            gameScreen.requestFocus();
        });
        
        // QuitGame button: Display Start Scene again
        quitGame.setId("quitGame");
        quitGame.setOnAction(e->{
            clock.stop();
            gameScreen.getChildren().clear();
            root.getChildren().clear();
            root.setCenter(vBox);
            root.requestFocus();
            gameScreen.requestFocus();
        });

        return mainScene;
    }
}