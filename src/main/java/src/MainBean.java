package src;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class MainBean implements Serializable{
    private int x;
    private double y;
    private boolean r1;
    private boolean r2;
    private boolean r3;
    private boolean r4;
    private boolean r5;
    private double r;
    private ArrayList<Dot> list = new ArrayList<>();
    private Connector connector=new Connector();

    public void setX(int x) {this.x = x;}
    public void setY(double y) {
        this.y = ((Long)Math.round(y * 1000)).doubleValue()/1000;
    }
    public void setR(double r) {
        this.r = r;
    }


    public String check() {
        checkR();
        Dot point = new Dot(x, y, r);
        point.check();
        connector.sendOne(point);
        list.add(point);
        if (list == null) {
            list = new ArrayList<>();
        }
        return "update";
    }

    public void reset() {
        list.clear();
        connector.clearAll();
    }

    public void checkR() {
        if(r1){
            r=1;
        }else if(r2){
            r=1.5;
        }else if(r3){
            r=2;
        }else if(r4){
            r=2.5;
        }else if(r5){
            r=3;
        }
    }

    public String normalCheck() {
        Dot point = new Dot(x, y, r);
        point.check();
        connector.sendOne(point);
        list.add(point);
        if (list == null) {
            list = new ArrayList<>();
        }
        return "update";
    }

    @PostConstruct
    public void init() {
        list= connector.getAll();
    }


    public int getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public ArrayList<Dot> getList() {
        return list;
    }
    public void setList(ArrayList<Dot> list) {
        this.list = list;
    }

    public void setR1(Boolean r1) {
        this.r1 = r1;
    }
    public void setR2(Boolean r2) {
        this.r2 = r2;
    }
    public void setR3(Boolean r3) {
        this.r3 = r3;
    }
    public void setR4(Boolean r4) {
        this.r4 = r4;
    }
    public void setR5(Boolean r5) {
        this.r5 = r5;
    }

    public double getR() {
        return r;
    }

    public Boolean getR1() {
        return r1;
    }

    public Boolean getR2() {
        return r2;
    }

    public Boolean getR3() {
        return r3;
    }

    public Boolean getR4() {
        return r4;
    }

    public Boolean getR5() {
        return r5;
    }
}
