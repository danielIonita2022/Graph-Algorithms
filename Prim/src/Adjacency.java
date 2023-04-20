import java.util.ArrayList;
import java.util.HashMap;

public class Adjacency {

    private HashMap<Node, Integer> adjacency;

    public Adjacency(HashMap<Node, Integer> adjacency) {
        this.adjacency = adjacency;
    }

    public void addToAdjacency(Node element, Integer cost){
        adjacency.put(element, cost);
    }
    public Integer getCostVecin(Node vecin){return adjacency.get(vecin);}
    HashMap<Node, Integer> getAdjacency(){
        return adjacency;
    }
}
