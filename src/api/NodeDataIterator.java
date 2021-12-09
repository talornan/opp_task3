package Ex2.src.api;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class NodeDataIterator implements Iterator<NodeData> {

    Iterator<NodeData> iterator ;
    public NodeDataIterator(Iterator<NodeData> iterator){
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        try {

        }
        catch (ConcurrentModificationException exception){
            
        }
        return false;
    }

    @Override
    public NodeData next() {
        return null;
    }
}
