import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.io.FileWriter;
import java.io.IOException;

public class MyPanel extends JPanel {
	private int nodeNr = 1;
	private int node_diam = 30;
	private Vector<Node> listaNoduri;
	private Vector<Arc> listaArce;
	Point pointStart = null;
	Point pointEnd = null;
	int nrStartNode = 0;
	int nrEndNode = 0;
	boolean isDragging = false;
	int[][] adjacencyMatrix = new int[100][100];
	boolean oriented = false;
	public MyPanel(boolean oriented) {
		this.oriented = oriented;
		adjacencyMatrix[0][0] = 0;
		listaNoduri = new Vector<>();
		listaArce = new Vector<>();
		// borderul panel-ului
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(new MouseAdapter() {
			//evenimentul care se produce la apasarea mousse-ului
			public void mousePressed(MouseEvent e) {
				pointStart = e.getPoint();
			}
			
			//evenimentul care se produce la eliberarea mousse-ului
			public void mouseReleased(MouseEvent e) {
				if (!isDragging) {
					addNode(e.getX(), e.getY());
					try {
						writeFile();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
				else {
					Vector<Node> twoNodes = existArc(pointStart, pointEnd);
					if(twoNodes.size() == 2) {
						Node n1 = twoNodes.elementAt(0);
						Node n2 = twoNodes.elementAt(1);
						if(n1 != n2){

							Arc arc = new Arc(n1, n2);
							listaArce.add(arc);
							makeMatrix();
							try {
								writeFile();
							} catch (IOException ex) {
								throw new RuntimeException(ex);
							}

						}
						else repaint();
					}
					else repaint();
				}
				pointStart = null;
				isDragging = false;
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			//evenimentul care se produce la drag&drop pe mousse
			public void mouseDragged(MouseEvent e) {
				pointEnd = e.getPoint();
				isDragging = true;
				repaint();
			}
		});
	}

	public void writeFile() throws IOException {
		FileWriter output;
		try {
			output = new FileWriter("output.txt");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		output.write(String.valueOf(listaNoduri.size()));
		output.write('\n');
		for(int i = 0;i < listaNoduri.size(); i++) {
			for (int j = 0; j < listaNoduri.size(); j++) {
				output.write(String.valueOf(adjacencyMatrix[i][j]));
				output.write(" ");
			}
			output.write('\n');
		}
		try {
			output.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

	}
	public void makeMatrix()
	{
		for (int k = 0; k < listaArce.size(); k++) {

			int i = listaArce.elementAt(k).getstartNode().getNumber();
			int j = listaArce.elementAt(k).getendNode().getNumber();
			adjacencyMatrix[i-1][j-1] = adjacencyMatrix[j-1][i-1] = 1;
		}
	}
	Vector<Node> existArc(Point startPoint, Point endPoint)
	{
		nrStartNode = nrEndNode = 0;
		Vector<Node> twoNodes = new Vector<>();
		Node n1 ,n2;
		Point center1 = new Point(0,0);
		Point center2 = new Point(0,0);
		for(int i = 0; i < listaNoduri.size(); i++) {
			if (startPoint.x >= listaNoduri.elementAt(i).getCoordX() &&
					startPoint.x <= listaNoduri.elementAt(i).getCoordX() + node_diam &&
					startPoint.y >= listaNoduri.elementAt(i).getCoordY() &&
					startPoint.y <= listaNoduri.elementAt(i).getCoordY() + node_diam) {

				nrStartNode = listaNoduri.elementAt(i).getNumber();
				center1 = listaNoduri.elementAt(i).getCenter();

			}
			if (endPoint.x >= listaNoduri.elementAt(i).getCoordX() &&
					endPoint.x <= listaNoduri.elementAt(i).getCoordX() + node_diam &&
					endPoint.y >= listaNoduri.elementAt(i).getCoordY() &&
					endPoint.y <= listaNoduri.elementAt(i).getCoordY() + node_diam) {

				nrEndNode = listaNoduri.elementAt(i).getNumber();
				center2 = listaNoduri.elementAt(i).getCenter();
			}
		}
			if((oriented == true) && (nrStartNode > nrEndNode)) {

				int temp = nrStartNode;
				nrStartNode = nrEndNode;
				nrEndNode = temp;
		}
		n1 = new Node(startPoint.x, startPoint.y, nrStartNode, center1);
		n2 = new Node(endPoint.x, endPoint.y, nrEndNode, center2);
		if(n1.getNumber() != 0 && n2.getNumber() != 0) {
			twoNodes.add(n1);
			twoNodes.add(n2);
		}
		return twoNodes;
	}
	//metoda care se apeleaza la eliberarea mouse-ului
	private void addNode(int x, int y) {

		//MODIFICAT
		boolean ok = true;
		for(int i=0; i<listaNoduri.size(); i++)
		{
		    if((listaNoduri.elementAt(i).getCoordX() - node_diam <= x && listaNoduri.elementAt(i).getCoordX() + node_diam >= x) &&
					(listaNoduri.elementAt(i).getCoordY() - node_diam <= y && listaNoduri.elementAt(i).getCoordY() + node_diam >= y)){
				ok = false;
				break;
			}

		}
		if(ok) {
			Point center = new Point(x + node_diam, y + node_diam);
			Node node = new Node(x, y, nodeNr, center);
			listaNoduri.add(node);
			nodeNr++;
			repaint();
		}
	}
	
	//se executa atunci cand apelam repaint()
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
		g.drawString("This is my Graph!", 10, 20);
		//deseneaza arcele existente in lista
		for (Arc a : listaArce)
		{
			a.drawArc(g, oriented);
		}
		//deseneaza arcul curent; cel care e in curs de desenare
		if (pointStart != null)
		{
			g.setColor(Color.RED);
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
			repaint();
		}
		//deseneaza lista de noduri
		for(int i=0; i<listaNoduri.size(); i++)
		{
			listaNoduri.elementAt(i).drawNode(g, node_diam);
		}
	}
}
