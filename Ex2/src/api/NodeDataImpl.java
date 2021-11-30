package Ex2.src.api;

import java.util.Objects;

public class NodeDataImpl implements NodeData, Comparable<NodeDataImpl> {
    private int key;
    private GeoLocation location;
    private double Weight;
    private int tag;
    private String info;
    private boolean visit;
    private Integer from;

    public NodeDataImpl(int key, GeoLocation location) {
        this.key = key;
        this.location = location;
        this.Weight = Weight;
    }

    public NodeDataImpl(NodeData other) {
        this.key = other.getKey();
        this.Weight = other.getWeight();
        this.tag = other.getTag();
        this.info = other.getInfo();
        this.location = other.getLocation();
        this.visit = false;
        this.from = null;
    }

    public int getFrom() {
        return this.from;
    }

    public void setFrom(Integer i) {
        this.from = i;
    }

    public boolean getNodeVis() {
        return this.visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }


    @Override
    public int getKey() {

        return this.key;
    }

    @Override
    public GeoLocation getLocation() {

        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;

    }

    @Override
    public double getWeight() {

        return this.Weight;
    }

    @Override
    public void setWeight(double w) {
        this.Weight = w;

    }

    @Override
    public String getInfo() {

        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeDataImpl nodeData = (NodeDataImpl) o;
        return key == nodeData.key && Double.compare(nodeData.Weight, Weight) == 0 && tag == nodeData.tag && Objects.equals(location, nodeData.location) && Objects.equals(info, nodeData.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, location, Weight, tag, info);
    }

    @Override
    public int compareTo(NodeDataImpl o) {
        Double weight = this.getWeight();
        Double otherWeight = o.getWeight();
        return weight.compareTo(otherWeight);
    }
}



