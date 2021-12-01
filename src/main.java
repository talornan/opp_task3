package Ex2.src;

import Ex2.src.api.GeoLocation;
import Ex2.src.api.NodeData;
import Ex2.src.api.GeoLocationImpl;
import Ex2.src.api.NodeDataImpl;

public class main {
    public static void main(String[] args) {
        GeoLocation location = new GeoLocationImpl(3.0,1.0,5.0);
        double Weight = 32.10378225882353;
        int tag = 0;
        String info = "pos:"+location+","+Weight+"," + tag + ","+ "id:"+1;
        NodeData ver = new NodeDataImpl(1,location);
        System.out.println(ver.getKey());
        System.out.println(ver.getLocation().x());
        System.out.println(ver.getLocation().y());
        System.out.println(ver.getLocation().z());
        System.out.println(ver.getWeight());
        System.out.println(ver.getLocation().distance(ver.getLocation()));
    }
}
