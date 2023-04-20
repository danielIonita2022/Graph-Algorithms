import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Graph {

    private HashMap<Pair, Nod> allNodes;
    private final int startX, startY, n, m;
    private final Pair startNod;
    private ArrayList<Pair> allPairs;

    public Graph() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/input.txt"));
        startX = in.nextInt();
        startY = in.nextInt();
        startNod = new Pair(startX,startY);
        n = in.nextInt();
        m = in.nextInt();
        int[][] matrix;

        matrix = readLabyrinth(in);
        transformIntoGraph(matrix);
    }
    public Pair getstartNod(){
        return startNod;
    }
    public int[][] readLabyrinth(Scanner in) {

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                matrix[i][j] = in.nextInt();
            }
        return matrix;
    }
    public Pair newPair(Integer i, Integer j){
        Pair newpair = new Pair(i,j);
        for(Pair pair : allPairs){
            if(newpair.isEqualTo(pair))
                return pair;
        }
        allPairs.add(newpair);
        return newpair;
    }
    public void transformIntoGraph(int[][] matrix) {
        allNodes = new HashMap<>();
        allPairs = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (matrix[i][j] == 0) {
                    ArrayList<Pair> adjacency = new ArrayList<>();
                    if (i > 0) {
                        int up = matrix[i - 1][j];
                        if (up == 0) adjacency.add(newPair(i-1,j));
                    }
                    if (i < n - 1) {
                        int down = matrix[i + 1][j];
                        if (down == 0) adjacency.add(newPair(i+1,j));
                    }
                    if (j > 0) {
                        int left = matrix[i][j - 1];
                        if (left == 0) adjacency.add(newPair(i,j-1));
                    }
                    if (j < m - 1) {
                        int right = matrix[i][j + 1];
                        if (right == 0) adjacency.add(newPair(i,j+1));
                    }
                    Pair currentPair = newPair(i,j);
                    Nod nod;
                    if(i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        nod = new Nod(adjacency, currentPair, true);
                    }
                    else {
                        nod = new Nod(adjacency, currentPair, false);
                    }
                    allNodes.put(currentPair, nod);
                }
            }
        }
    }
    public HashMap<Pair, Pair> BFS() {
        HashMap<Pair, Boolean> visited = new HashMap<>();
        HashMap<Pair, Pair> previousNode = new HashMap<>();
        ArrayList<Pair> futureNodes = new ArrayList<>();
        Pair currentNod = null;
        for(Nod nod : allNodes.values()){
            if(nod.getCoords().first() == startX && nod.getCoords().second() == startY) {
                currentNod = nod.getCoords();
            }
        }
        int index = 0;
        visited.put(currentNod, true);
        Pair empty = new Pair(-1,-1);
        previousNode.put(currentNod, empty);

        while (visited.size() != allNodes.size()) {
            for (Pair nod : allNodes.get(currentNod).getAdjacency()) {
                if (!visited.containsKey(nod)) {
                    visited.put(nod, true);
                    previousNode.put(nod, currentNod);
                    futureNodes.add(nod);
                }
            }
            currentNod = futureNodes.get(index);
            ++index;
        }
        return previousNode;

//        PrintShortestPath(53, previousNode);
    }
    public HashMap<Pair, Nod> getAllNodes(){
        return allNodes;
    }
    public int getN(){
        return n;
    }
    public int getM(){
        return m;
    }
}
