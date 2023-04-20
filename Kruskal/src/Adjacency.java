import java.util.ArrayList;

public class Adjacency {

    private ArrayList<Node> adjacency;

    public Adjacency(ArrayList<Node> adjacency) {
        this.adjacency = adjacency;
    }

    public void addToAdjacency(Node element){
        adjacency.add(element);
    }
    ArrayList<Node> getAdjacency(){
        return adjacency;
    }
}
