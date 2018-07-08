import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import task2.Point;

public class PointTest {

    @DataProvider(name="distances")
    public static Object[][] pointsProvider()
    {
        return new Object[][]{
                {new Point(1,1),new Point(5,5),5.657},
                {new Point(-1,-1),new Point(5,5),8.486},
                {new Point (-10,-10),new Point(-55,-55),63.640}
        };
    }

    @Test(dataProvider = "distances")
    public void distanceTest(Point p1,Point p2,double result)
    {
        Assert.assertEquals(p1.distance(p2),result);
    }
}
