import Ex2.src.api.GeoLocation;
import Ex2.src.api.GeoLocationImpl;
import Ex2.src.api.NodeData;
import Ex2.src.api.NodeDataImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NodeDataTest {
    GeoLocation location = new GeoLocationImpl(3.0,1.0,5.0);
    GeoLocation location1 = new GeoLocationImpl(0.0,1.0,5.0);
    NodeData node = new NodeDataImpl(1,location);

    @Test
    public void getIdTest(){
        Assertions.assertEquals(node.getKey(),1);
    }

    @Test
    public void getLocationTest(){
        Assertions.assertEquals(node.getLocation(),location);
    }
    @Test
    public void getWeightTest(){
        Assertions.assertEquals(node.getWeight(),0.0);
    }
    @Test
    public void getTagTest(){
        Assertions.assertEquals(node.getTag(),0);
    }
    @Test
    public void getInfoTest(){
        Assertions.assertEquals(node.getInfo(),null);
    }
    @Test
    public void setLocationTest(){
        node.setLocation(location1);
        Assertions.assertEquals(node.getLocation(),location1);
    }
    @Test
    public void setWeightTest(){
        node.setWeight(30.10152879327731);
        Assertions.assertEquals(node.getWeight(),30.10152879327731);
    }
    @Test
    public void setTagTest(){
        node.setTag(16);
        Assertions.assertEquals(node.getTag(),16);
    }
    @Test
    public void setInfoTest(){
        node.setInfo("1234");
        Assertions.assertEquals(node.getInfo(),"1234");
    }





}
