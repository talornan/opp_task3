package Ex2.src.api;

public class GeoLocationImpl implements Ex2.src.api.GeoLocation {
    private double x;
    private double y;
    private double z;

    public GeoLocationImpl(double x, double y, double z ){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        return  Math.sqrt(Math.pow((g.x() - this.x),2) + Math.pow((g.y() - this.y),2) + Math.pow(Math.pow((g.z() - this.z),2),(1/2)));
    }
}
