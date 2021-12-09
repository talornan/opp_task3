package Ex2.src.api;

import java.util.Objects;

public class EdgeDataImpl implements Ex2.src.api.EdgeData {
    private int Src;
    private int Dest;
    private double Weight;
    private int tag;
    private String info;



    public EdgeDataImpl(int Src, int Dest, double Weight) {
        this.Src = Src;
        this.Dest = Dest;
        this.Weight = Weight;
    }

    public EdgeDataImpl(EdgeData other){
        this.Src = other.getSrc();
        this.Dest = other.getDest();
        this.Weight = other.getWeight();
        this.tag = other.getTag();
        this.info = other.getInfo();

    }



    // check if we visit in current edge
        public boolean EdgeVis(EdgeData edge){
            if(edge.getTag() == 1)
                return true;
            else
                return false;
        }
        @Override
        public int getSrc () {
            return this.Src;
        }

        @Override
        public int getDest () {
            return this.Dest;
        }

        @Override
        public double getWeight () {
            return this.Weight;
        }

        @Override
        public String getInfo () {
            return this.info;
        }

        @Override
        public void setInfo (String s){
            this.info = s;
        }

        @Override
        public int getTag () {
            return this.tag;
        }

        @Override
        public void setTag ( int t){
            this.tag = t;

        }

        public void setWeight ( double w){

        Weight = w;
        }
        @Override
        public boolean equals (Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EdgeDataImpl edgeData = (EdgeDataImpl) o;
            return Src == edgeData.Src && Dest == edgeData.Dest && Double.compare(edgeData.Weight, Weight) == 0 && tag == edgeData.tag && Objects.equals(info, edgeData.info);
        }

        @Override
        public int hashCode () {
            return Objects.hash(Src, Dest, Weight, tag, info);
        }

    }


