import Ex2.src.api.EdgeData;
import Ex2.src.api.EdgeDataImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EdgeDataTest {
    EdgeData edge = new EdgeDataImpl( 0,16,1.3118716362419698);
    EdgeData edge1 = new EdgeDataImpl( 3,5,2.3118716362419698);

    @Test
    public void TestgetSrc(){
        Assertions.assertEquals(edge.getSrc(),0);
        Assertions.assertEquals(edge1.getSrc(),3);
    }
    @Test
    public void TestgetDst(){
        Assertions.assertEquals(edge.getDest(),16);
        Assertions.assertEquals(edge1.getDest(),5);
    }
    @Test
    public void TestgetWight(){
        Assertions.assertEquals(edge.getWeight(),1.3118716362419698);
        Assertions.assertEquals(edge1.getWeight(),2.3118716362419698);
    }

    @Test
    public void TestSetWight(){
        edge.setTag(1);
        edge1.setTag(2);
        Assertions.assertEquals(edge.getTag(),1);
        Assertions.assertEquals(edge1.getTag(),2);
    }
    @Test
    public void TestSetInfo(){
        edge.setInfo("tal");
        edge1.setInfo("ariel");
        Assertions.assertEquals(edge.getInfo(),"tal");
        Assertions.assertEquals(edge1.getInfo(),"ariel");

    }



}
