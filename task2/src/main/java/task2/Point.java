package task2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Point {

    private int x;
    private int y;

    public Point(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public double distance(Point p)
    {
        double height=p.y-y;
        double width=p.x-y;
        double temp = Math.sqrt(height*height+width*width);
        return new BigDecimal(temp).setScale(3, RoundingMode.UP).doubleValue();
    }
}
