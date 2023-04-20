import java.awt.*;

public class Node implements Comparable<Node>
{
    private int coordX;
    private int coordY;
    private int number;
    private Point center;
    private Node parent;
    private int minCost;
    private Long timestamp = System.nanoTime();

    public Node()
    {
        this.coordX = 0;
        this.coordY = 0;
        this.number = 0;
    }
    public Node(int coordX, int coordY, int number, Point center)
    {
        this.coordX = coordX;
        this.coordY = coordY;
        this.number = number;
        this.center = center;
    }
    public Node(Node n)
    {
        this.coordX = n.coordX;
        this.coordY = n.coordY;
        this.number = n.number;
        this.center = n.center;
    }
    public int getCoordX() {
        return coordX;
    }
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }
    public int getCoordY() {
        return coordY;
    }
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Point getCenter(){return center;}
    public Node getParent(){return parent;}
    public void setParent(Node parent)
    {
        this.parent = parent;
    }
    public int getMinCost(){return minCost;}
    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }
    public void setTimestamp(Long timestamp){
        this.timestamp = timestamp;
    }
    public Long getTimestamp(){return this.timestamp;}
    public void drawNode(Graphics g, int node_diam)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX, coordY, node_diam, node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(coordX, coordY, node_diam, node_diam);
        if(number < 10)
            g.drawString(((Integer)number).toString(), coordX+13, coordY+20);
        else
            g.drawString(((Integer)number).toString(), coordX+8, coordY+20);
    }

    @Override
    public int compareTo(Node another) {
        if (minCost == another.minCost) {
            return timestamp.compareTo(another.timestamp);
        } else {
            return this.minCost - another.minCost;
        }
    }
}