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
                {new Point(5,5),new Point(1,1),5.657},

                {new Point (-10,-10),new Point(55,55),91.924},
                {new Point (10,10),new Point(-55,-55),91.924},

                {new Point (5,10),new Point(120,0),115.434}


        };
    }

    @Test(dataProvider = "distances")
    public void distanceTest(Point p1,Point p2,double result)
    {
        Assert.assertEquals(p1.distance(p2),result);
    }
}
