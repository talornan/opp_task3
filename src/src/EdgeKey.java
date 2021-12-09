package Ex2.src;

import java.util.Objects;

public class EdgeKey {
    public int src;
    public int dst;


    public EdgeKey(int src,int dst){
        this.src = src;
        this.dst = dst;
    }


    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDst() {
        return dst;
    }

    public void setDst(int dst) {
        this.dst = dst;
    }

    public void copyKey(EdgeKey other){
        this.src = other.getSrc();
        this.dst = other.getDst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeKey edgeKey = (EdgeKey) o;
        return src == edgeKey.src && dst == edgeKey.dst;
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dst);
    }

}
