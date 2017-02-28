import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Hannes on 18/02/2017.
 */
public class Bird extends Mover{
    public Bird(float x, float y, float m) {
        super(x, y, m);
        bouncyness = 0;
    }

    public boolean checkFallen(Canvas canvas){

        if(pos.y > canvas.getHeight())
            return true;

        return false;

    }

    @Override
    public void newFrame(GraphicsContext gc, Canvas canvas){
        FlappyBird.gameOver = checkFallen(canvas);
        super.newFrame(gc, canvas);
    }
}
