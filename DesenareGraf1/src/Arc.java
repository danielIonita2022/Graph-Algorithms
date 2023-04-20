import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Arc
{
	private Node startNode;
	private Node endNode;
	public Arc(Node startNode, Node endNode)
	{
		this.startNode = startNode;
		this.endNode = endNode;
	}
	public Node getstartNode(){return startNode;}
	public Node getendNode(){return endNode;}
	public void drawArc(Graphics g, boolean oriented)
	{
		if (this.startNode != null)
		{
            g.setColor(Color.RED);

			int xA = startNode.getCoordX();
			int yA = startNode.getCoordY();
			int xB = endNode.getCoordX();
			int yB = endNode.getCoordY();
			double xC = endNode.getCenter().getX();
			double yC = endNode.getCenter().getY();

            g.drawLine(xA, yA, xB, yB);
			if(oriented){

				if(yB > yA && xB > xA)
				{
					g.drawLine(xB,yB,xB + 10,yB - 15);
					g.drawLine(xB,yB,xB - 20,yB - 10);
				}
				else if(yB > yA && xB < xA)
				{
					g.drawLine(xB,yB,xB + 10,yB - 20);
					g.drawLine(xB,yB,xB + 20,yB - 10);
				}
				else if(yB < yA && xB > xA)
				{
					g.drawLine(xB,yB,xB - 10,yB + 20);
					g.drawLine(xB,yB,xB - 20,yB + 10);
				}
				else if(yB < yA && xB < xA){

					g.drawLine(xB,yB,xB + 10,yB + 20);
					g.drawLine(xB,yB,xB + 20,yB + 10);
				}
			}
        }
	}
}
