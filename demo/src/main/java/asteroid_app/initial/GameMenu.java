package asteroid_app.initial;
// Scene is the container for all content
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;

public class GameMenu {
     
    static GameController game; 
    static Text scoreText; 
    static Text PlayerLivesText;
    static Text levelText;
    static long lastAddedTime=0L;

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
        Button highScores = new Button("HIGH SCORES");
        // create a Vbox to manage the nodes on the start menu
        VBox vBox = new VBox(30, headline, playGame, highScores, website);
        vBox.setAlignment(Pos.CENTER);

        // Create all nodes for the Main Game
        Pane gameScreen = new Pane();
        // Create a hbox to display points and level and lives         
        scoreText = new Text("Points: 0");
        levelText = new Text("Level: 1");
        PlayerLivesText = new Text("Lives: 0");
        HBox hBox = new HBox(30, levelText, scoreText, PlayerLivesText);
        hBox.setAlignment(Pos.CENTER);
        // Control box
        Button restartGame = new Button("RESTART");
        restartGame.setFocusTraversable(false);
        Button quitGame = new Button("QUIT");
        quitGame.setFocusTraversable(false);
        HBox controlBox = new HBox(10, quitGame, restartGame);
        controlBox.setAlignment(Pos.CENTER);

        // Create all nodes for the GameOver menu
        GridPane textFieldContainer = new GridPane();
        TextField nameText = new TextField();
        nameText.setPrefWidth(100);
        nameText.setAlignment(Pos.CENTER);
        // nameText.setId("nameText");
        Label headlineover = new Label("GAME OVER");
        headlineover.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
        Label yourScore = new Label("Your score is");
        Label score = new Label("");
        score.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
        Label yourName = new Label("Enter your name");
        Button saveScore = new Button("Save");

        // Create all nodes for the Highscore Menu
        Label headLine = new Label("HIGH SCORES - TOP 5");
        headLine.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
        VBox scoreData = new VBox(20,headLine);
        scoreData.setAlignment(Pos.CENTER);
        
        // Clock to control the game loop
        class Movement extends AnimationTimer{
            @Override
            public void handle(long now){
                if(Main.playerLives.getLives()>0){
                    game.play(gameScreen, mainScene); 
                }else{
                    this.stop();
                    showGameOver(gameScreen, root, score, 
                        headlineover, yourScore, yourName, nameText, saveScore);
                }   
            }
        }
        Movement clock = new Movement();


        // To display Start Screen
        root.setCenter(vBox);

        // To display Game Screen and start the game
        playGame.setOnAction(e -> {
            showGame(gameScreen, root, hBox, controlBox, mainScene);
            game = new GameController(gameScreen, mainScene);
            clock.start();
            game.play(gameScreen, mainScene);
        });
        
        // To restart the game inside Game Screen
        restartGame.setOnAction(e -> {
            clock.stop();
            gameScreen.getChildren().clear();
            game = new GameController(gameScreen, mainScene);
            Main.score.setScore(0);
            Main.playerLives = new PlayerLives();
            scoreText.setText("Points: " + Main.score.getScore());
            clock.start();
            gameScreen.requestFocus();
        });
        
        // To quit the game
        quitGame.setId("quitGame");
        quitGame.setOnAction(e->{
            clock.stop();
            showGameOver(gameScreen, root, score, 
                headlineover, yourScore, yourName, nameText, saveScore);
        });

        // To save the score
        saveScore.setOnAction(e -> {    
            root.getChildren().clear();
            String nameTextContent = nameText.getText();
            try {
                saveHighScore(nameTextContent);
                showHighScore(scoreData,root);
            } catch (IOException e1) {
                root.setCenter(scoreData);
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                root.setCenter(scoreData);
                e1.printStackTrace();
            }            
        });
        
        highScores.setOnAction(e -> {
            try {
                showHighScore(scoreData,root);
            } catch (ClassNotFoundException | IOException e1) {
                root.setCenter(scoreData);
                e1.printStackTrace();
            }
        });
    return mainScene;
    
    }

    public static void saveHighScore(String nameTextContent) throws IOException{
        HighScore highScore = new HighScore(nameTextContent, Main.score.getScore());
        FileOutputStream fileOut = new FileOutputStream("HighScores.ser", true);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(highScore);
        out.close();
        fileOut.close();
        System.out.println("Score saved");
    }

    public static void showHighScore(VBox scoreData, BorderPane root) throws IOException, ClassNotFoundException{
        HighScore highScore = null;
        // Read the HighScores file and show it on the HighScore menu
        FileInputStream fileIn = new FileInputStream("HighScores.ser");
        ArrayList<HighScore> highScoreList = new ArrayList<>();
        boolean cont = true;
        ObjectInputStream in;
        while (cont) {
            try{
                in = new ObjectInputStream(fileIn);
                highScore = (HighScore) in.readObject();
                if (highScore!=null){
                    highScoreList.add(highScore);
                }
            }catch(EOFException e) {
                break;
            }
        }
        fileIn.close();
        ArrayList<HighScore> sortedHighScores = (ArrayList<HighScore>) highScoreList
				.stream().sorted(Comparator.comparing(HighScore::getScore).reversed())
				.collect(Collectors.toList());
        // print top 5 high scores:
        for (int i = 0; i < 5 && i < sortedHighScores.size(); i++){
            Label scoreLabel = new Label(sortedHighScores.get(i).name+"\t\t"+sortedHighScores.get(i).score);
            scoreData.getChildren().add(scoreLabel);
            root.setCenter(scoreData);
        }
    }

    public static void showGameOver(Pane pane, BorderPane root, Label score, 
        Label headlineover, Label yourScore, Label yourName, TextField nameText, Button saveScore){
        pane.getChildren().clear();
        root.getChildren().clear();
        String finalScore = Integer.toString(Main.score.getScore());
        score.setText(finalScore);
        VBox infoBox = new VBox(25, headlineover, yourScore, score, yourName, nameText, saveScore);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPrefWidth(400);     
        root.setCenter(infoBox);
        root.requestFocus();
        pane.requestFocus();
    }

    public static void showGame(Pane gameScreen,BorderPane root, HBox hBox, HBox controlBox, Scene mainScene){
        root.setCenter(gameScreen);
        root.setTop(hBox);
        root.setBottom(controlBox);
        gameScreen.requestFocus();
    }
}