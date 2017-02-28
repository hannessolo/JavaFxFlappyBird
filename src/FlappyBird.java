import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Hannes on 18/02/2017.
 */
public class FlappyBird extends Application{

    private Stage window;
    static int score;
    public static boolean gameOver;
    Button restart;
    Canvas gameCanvas;

    private Bird flappy;
    private Pillar[] pillars = new Pillar[3];

    AnimationTimer timer;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage mainStage){
        window = mainStage;
        score = 0;

        gameCanvas = new Canvas(450, 700);
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        Pane root = new Pane();
        Scene gameScene = new Scene(root,450, 700);

        root.getChildren().add(gameCanvas);

        window.setScene(gameScene);

        initGame();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0,0,gameCanvas.getWidth(), gameCanvas.getHeight());
                flappy.applyForce(new PVector(0, 1.5f));
                for(Pillar p : pillars ){
                    p.newFrame(gc);
                }
                flappy.newFrame(gc, gameCanvas);
                for(Pillar p : pillars){
                    if(gameOver)
                        break;
                    gameOver = p.checkGameOver(flappy);
                }
                if(gameOver){
                    timer.stop();
                    restart = new Button("Restart");
                    root.getChildren().add(restart);
                    restart.setOnAction(event -> {
                        restart.setVisible(false);
                        score = 0;
                        gameCanvas.toFront();
                        root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                        initGame();
                        timer.start();
                    });
                }
            }
        };

        gameCanvas.setOnMousePressed(event -> {
            flappy.vel.add(new PVector(0, -1*flappy.vel.mag() - 8));
        });


        timer.start();

        window.setTitle("Flappy Bird");
        window.show();
    }

    private void initGame(){
        flappy = new Bird((float)gameCanvas.getWidth()/3-1.5f, (float)gameCanvas.getHeight()/2-1.5f,3);
        pillars[0] = new Pillar(700);
        pillars[1] = new Pillar(1000);
        pillars[2] = new Pillar(1300);
    }

}
