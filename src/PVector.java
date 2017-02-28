/**
 * Created by Hannes on 16/02/2017.
 */
public class PVector {
    public float x;
    public float y;

    public PVector(){
        x = 0;
        y = 0;
    }

    public PVector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public PVector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public PVector(PVector vec){
        this.x = vec.x;
        this.y = vec.y;
    }

    public static PVector add(PVector vec1, PVector vec2){
        PVector added = new PVector();
        added.x = vec1.x + vec2.x;
        added.y = vec1.y + vec2.y;
        return added;
    }

    public static PVector sub(PVector vec1, PVector vec2){
        PVector subtr = new PVector();
        subtr.x = vec1.x - vec2.x;
        subtr.y = vec1.y - vec2.y;
        return subtr;
    }

    public static PVector div(PVector vec1, float mass){
        vec1.x = vec1.x / mass;
        vec1.y = vec1.y / mass;
        return vec1;
    }

    public void add(PVector vec){
        this.x = vec.x + this.x;
        this.y = vec.y + this.y;
    }

    public void sub(PVector vec){
        this.x -= vec.x;
        this.y -= vec.y;
    }

    public float mag(){
        float magnitude = (float)Math.sqrt(Math.abs(this.x * this.x + this.y * this.y));
        return magnitude;
    }

    public void mult(float m){
        this.x *= m;
        this.y *= m;
    }

    public void div(float d){
        this.x /= d;
        this.y /= d;
    }

    public static PVector normalize(PVector vec){
        vec = PVector.div(vec, vec.mag());
        return vec;
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }
}
