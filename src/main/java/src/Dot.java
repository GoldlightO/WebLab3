package src;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Dot implements Serializable{
    private int x;
    private double y;
    private double r;
    private boolean inside;
    private long executionTime;
    private Integer id;

    public Dot(int x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Dot() {
        this.x = 0;
        this.y = 0;
        this.r = 0;
    }

    public void check() {
        long now = System.nanoTime();

        if (x <= 0 && y <= 0 && -x <= (r +y)) inside = true;
        else if (x >= 0 && y >= 0 && x <= r/2.0 && y <= r) inside = true;
        else inside = x >= 0 && y <= 0 && x<=Math.sqrt(r*r-y*y);

        executionTime = System.nanoTime() - now;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setR(double r) {
        this.r = r;
    }

    public int getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getR() {
        return r;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }
    public boolean getInside() {
        return inside;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }
}
