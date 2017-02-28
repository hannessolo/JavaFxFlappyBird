import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Hannes on 18/02/2017.
 */
public class Pillar{

    double x;
    double ygap;

    public Pillar(double x){
        this.x = x;
        ygap = Math.random()*550;
    }

    public void newFrame(GraphicsContext gc){
        x -= 4;
        if(x < -100){
            x = 800;
            ygap = Math.random()*550;
        }
        if (x <= gc.getCanvas().getWidth()/2 && x >= gc.getCanvas().getWidth()/2-3){
            FlappyBird.score++;
        }
        draw(gc);
    }

    private void draw(GraphicsContext gc){
        gc.strokeText(Integer.toString(FlappyBird.score), 20,20);
        gc.setFill(Color.GREEN);
        gc.fillRect(x, 0, 100, gc.getCanvas().getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(x, ygap, 100, 250);

    }

    public boolean checkGameOver(Bird flappy){
        if(flappy.pos.x + 30 > this.x && flappy.pos.x < this.x + 100 &&
                (flappy.pos.y < ygap || flappy.pos.y > ygap + 220)){
            return true;
        }
        return false;
    }

}