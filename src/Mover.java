import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Hannes on 16/02/2017.
 */
public class Mover {

    PVector accel;
    PVector vel;
    PVector pos;
    public float mass;
    public float bouncyness;
    public float friction;

    public Mover(float x, float y, float m){
        accel = new PVector(0,0);
        vel = new PVector(0,0);
        pos = new PVector(x,y);
        mass = m;
        bouncyness = 0.6f;
        friction = 0.001f;
    }

    private void draw(GraphicsContext gc, float xPosition, float yPosition, float width, float height){
        gc.setFill(Color.MEDIUMPURPLE);
        gc.fillOval(xPosition, yPosition,width,height);
    }

    public void applyForce(PVector force){

        PVector f = PVector.div(force, mass);
        accel.add(f);

    }

    private void update(){
        vel.add(accel);
        pos.add(vel);
        accel.mult(0);
    }

    public void newFrame(GraphicsContext gc, Canvas canvas){
        update();
        checkEdges(canvas);
        draw(gc, pos.x, pos.y, mass*10, mass*10);
    }

    public void checkEdges(Canvas canvas){
        if(pos.x < 0){
            pos.x = 0;
            vel.x *= -bouncyness;
        }else if(pos.x > (float)canvas.getWidth() - mass/0.1f) {
            pos.x = (float)canvas.getWidth() - mass/0.1f;
            vel.x *= -bouncyness;
        }

        if(pos.y < 0){
            pos.y = 0;
            vel.y *= -bouncyness;
        }else if(pos.y > (float)canvas.getHeight() - mass/0.1f) {
            pos.y = (float)canvas.getHeight() - mass/0.1f;
            vel.y *= -bouncyness;
        }
    }

}
