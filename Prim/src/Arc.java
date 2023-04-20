import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.math.*;
public class Arc {
    private Node startNode;
    private Node endNode;
    private int cost;

    public Arc(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.cost = -1;
    }
    public Arc(Node startNode, Node endNode, int cost){
        this.startNode = startNode;
        this.endNode = endNode;
        this.cost = cost;
    }

    public Node getstartNode() {
        return startNode;
    }

    public Node getendNode() {
        return endNode;
    }
    public int getCost() {return cost;}
    public void setCost(int cost){
        this.cost = cost;
    }

    public void drawArc(Graphics g) {
        g.setColor(Color.RED);
        g.drawLine(startNode.getCoordX() + 15, startNode.getCoordY() + 15, endNode.getCoordX() + 15, endNode.getCoordY() + 15);
        if(cost != -1)
            g.drawString(((Integer)cost).toString(), ((startNode.getCoordX() + endNode.getCoordX() + 30)/2),
                    (startNode.getCoordY() + endNode.getCoordY() + 30)/2);
    }
}
