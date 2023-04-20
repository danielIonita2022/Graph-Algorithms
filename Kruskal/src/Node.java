import java.awt.*;

public class Node
{
    private int coordX;
    private int coordY;
    private int number;
    private Point center;
    private Color color;

    public Node(int coordX, int coordY, int number, Point center, Color color)
    {
        this.coordX = coordX;
        this.coordY = coordY;
        this.number = number;
        this.center = center;
        this.color = color;
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
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }

    public void drawNode(Graphics g, int node_diam, Color color)
    {
        g.setColor(color);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX, coordY, node_diam, node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(coordX, coordY, node_diam, node_diam);
        if(number < 10)
            g.drawString(((Integer)number).toString(), coordX+13, coordY+20);
        else
            g.drawString(((Integer)number).toString(), coordX+8, coordY+20);
    }
}