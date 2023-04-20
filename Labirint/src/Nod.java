import java.util.ArrayList;
public class Nod {

    private final ArrayList<Pair> adjacency;
    private final Pair coords;
    private boolean drawn;
    private final boolean isExit;
    public Nod(ArrayList<Pair> adjacency, Pair coords, boolean isExit){
        this.adjacency = adjacency;
        this.coords = coords;
        this.isExit = isExit;
    }
    public boolean getisExit(){
        return isExit;
    }
    public Pair getCoords(){
        return coords;
    }
    public ArrayList<Pair> getAdjacency(){
        return adjacency;
    }
    public boolean getDrawn(){
        return drawn;
    }
    public void addEdge(Pair nr){
        adjacency.add(nr);
    }
    public void setDrawn(boolean drawn){
        this.drawn = drawn;
    }
}
