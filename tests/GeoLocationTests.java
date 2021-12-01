import Ex2.src.api.GeoLocation;
import Ex2.src.api.GeoLocationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GeoLocationTests {
    GeoLocation location = new GeoLocationImpl(3.0,1.0,5.0);
    GeoLocation location1 = new GeoLocationImpl(0.0,0.0,0.0);
    @Test
    public void testX(){
        Assertions.assertEquals(location.x(),3.0);

    }
    @Test
    public void testY(){
        Assertions.assertEquals(location.y(),1.0);
    }

    @Test
    public void testZ(){
        Assertions.assertEquals(location.z(),5.0);
    }

    @Test
    public void testDist(){
        Assertions.assertEquals(location.distance(location1),Math.sqrt(Math.pow((3 - 0),2) + Math.pow((1 - 0),2) + Math.pow(Math.pow((5 - 0),2),(1/2))));

    }




}
