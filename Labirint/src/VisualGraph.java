import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class VisualGraph extends JPanel {

    private final int squareHeight;
    private final int squareWidth;
    private final int n;
    private final int m;
    private final Pair startNode;
    private final Graph graph;
    private final HashMap<Pair, Pair> previous;
    public VisualGraph() throws FileNotFoundException {
        setBorder(BorderFactory.createLineBorder(Color.black));
        graph = new Graph();
        squareHeight = 80;
        squareWidth = 80;
        n = graph.getN();
        m = graph.getM();
        startNode = graph.getstartNod();
        previous = graph.BFS();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                Color fillColor;
                Pair ij = graph.newPair(i,j);
                if(i == startNode.first() && j == startNode.second()) {
                    fillColor = Color.CYAN;
                }
                else if(graph.getAllNodes().containsKey(ij)) {
                    fillColor = Color.WHITE;
                }
                else fillColor = Color.GRAY;
                draw(g, j*this.squareWidth, i*this.squareHeight, fillColor);
            }
        }
        //Pair empty = new Pair(-1,-1);
        for(Nod nod : graph.getAllNodes().values()){
            if(nod.getisExit() && !nod.getDrawn()) {
                nod.setDrawn(true);
                Pair pair = nod.getCoords();
                while(previous.get(pair).first() != -1){
                    drawPath(g, pair, previous.get(pair));
                    pair = previous.get(pair);
                }
            }
        }
    }
    public void drawPath(Graphics g, Pair current, Pair prev){
        g.setColor(Color.RED);
        int halfHeight = squareHeight/2;
        int halfWidth = squareWidth/2;
        g.drawLine(current.second()*squareHeight + halfHeight,
                current.first()*squareWidth + halfWidth,
                prev.second()*squareHeight + halfHeight,
                prev.first()*squareWidth + halfWidth);
    }
    public void draw(Graphics g, int x, int y, Color fill){
        g.setColor(Color.black);
        g.drawRect(x, y, squareWidth, squareHeight);
        g.setColor(fill);
        g.fillRect(x + 1, y + 1, squareWidth - 1, squareHeight - 1);
    }
}
