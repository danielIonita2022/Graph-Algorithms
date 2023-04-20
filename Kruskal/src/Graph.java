import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import java.util.Scanner;

public class Graph extends JPanel{

    private int nodeNr = 1;
    private int node_diam = 30;
    private Vector<Node> listaNoduri;
    private Vector<Arc> listaArce;
    Point pointStart = null;
    Point pointEnd = null;
    int nrStartNode = 0;
    int nrEndNode = 0;
    boolean isDragging = false;
    HashMap<Node, Adjacency> u_adjacencyList;
    HashMap <Node, Integer> nodeSet;
    public Graph() {
        listaNoduri = new Vector<>();
        listaArce = new Vector<>();
        // borderul panel-ului
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            //evenimentul care se produce la apasarea mouse-ului
            public void mousePressed(MouseEvent e) {
                pointStart = e.getPoint();
            }

            //evenimentul care se produce la eliberarea mouse-ului
            public void mouseReleased(MouseEvent e) {
                if (!isDragging) {
                    addNode(e.getX(), e.getY());
                }
                else {
                    Vector<Node> twoNodes = existArc(pointStart, pointEnd);
                    if(twoNodes.size() == 2) {
                        Node n1 = twoNodes.elementAt(0);
                        Node n2 = twoNodes.elementAt(1);
                        if(n1 != n2){
                            Arc arc = new Arc(n1, n2);
                            listaArce.add(arc);
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
    private Vector<Node> existArc(Point startPoint, Point endPoint)
    {
        nrStartNode = nrEndNode = 0;
        Vector<Node> twoNodes = new Vector<>();
        Node n1 = null,n2 = null;
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
        for(Node nod : listaNoduri){
            if(nod.getNumber() == nrStartNode)
                n1 = nod;
            if(nod.getNumber() == nrEndNode)
                n2 = nod;
        }
        if(n1 != null) {
            if (n1.getNumber() != 0) {
                if (n2 != null) {
                    if (n2.getNumber() != 0) {
                        twoNodes.add(n1);
                        twoNodes.add(n2);
                    }
                }
            }
        }
        return twoNodes;
    }
    //metoda care se apeleaza la eliberarea mouse-ului
    private void addNode(int x, int y) {
        boolean ok = true;
        for(Node i : listaNoduri)
        {
            if(Math.pow(i.getCoordX() - x,2) + Math.pow(i.getCoordY() - y, 2) <= Math.pow(node_diam, 2)){
                ok = false;
                break;
            }
        }
        if(ok) {
            Point center = new Point(x + node_diam, y + node_diam);
            Node node = new Node(x, y, nodeNr, center, Color.red);
            listaNoduri.add(node);
            nodeNr++;
            repaint();
        }
    }

    //se executa atunci cand apelam repaint()
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
        g.drawString("This is my Graph!", 10, 20);
        //deseneaza arcele existente in lista
        for (Arc a : listaArce) {
            a.drawArc(g);
        }
        //deseneaza arcul curent; cel care e in curs de desenare
        if (pointStart != null) {
            g.setColor(Color.RED);
            g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
            repaint();
        }
        //deseneaza lista de noduri
        for (Node nod : listaNoduri) {
            nod.drawNode(g, node_diam, nod.getColor());
        }
    }
    public void makeUnorientedAdjacencyList(){
        u_adjacencyList = new HashMap<>();
        for(Node node : listaNoduri) {
            ArrayList<Node> u_adjacency = new ArrayList<>();
            Adjacency u_adjacencyArray = new Adjacency(u_adjacency);
            u_adjacencyList.put(node, u_adjacencyArray);
        }
        for (Arc arc : listaArce) {
            Adjacency u_adjacencyArray = u_adjacencyList.get(arc.getstartNode());
            u_adjacencyArray.addToAdjacency(arc.getendNode());
            u_adjacencyList.put(arc.getstartNode(), u_adjacencyArray);

            u_adjacencyArray = u_adjacencyList.get(arc.getendNode());
            u_adjacencyArray.addToAdjacency(arc.getstartNode());
            u_adjacencyList.put(arc.getendNode(), u_adjacencyArray);
        }
    }
    static class SortByCost implements Comparator<Arc> {

        public int compare(Arc a, Arc b) {
            return a.getCost() - b.getCost();
        }
    }
    public void setCostArce()
    {
        nodeSet = new HashMap<>();
        System.out.println("Costurile arcelor: ");
        for(Node nod : listaNoduri) {
            nodeSet.put(nod, nod.getNumber());
        }
        Scanner in = new Scanner(System.in);
        for(Arc arc : listaArce){
            System.out.print("Arcul " + arc.getstartNode().getNumber() + "->" + arc.getendNode().getNumber() + ": ");
            int cost = in.nextInt();
            arc.setCost(cost);
        }
        repaint();
    }
    private void Union(Integer set1, Integer set2){
        for(Node nod : nodeSet.keySet()){
            if(Objects.equals(nodeSet.get(nod), set2)){
                nodeSet.put(nod, set1);
            }
        }
    }

    public void Kruskal()
    {
        Vector <Arc> minArce = new Vector<>();

        listaArce.sort(new SortByCost());

        for(Arc arc : listaArce){
            if(!Objects.equals(nodeSet.get(arc.getstartNode()), nodeSet.get(arc.getendNode()))){
                minArce.add(arc);
                Union(nodeSet.get(arc.getstartNode()), nodeSet.get(arc.getendNode()));
            }
        }
        listaArce = minArce;
        repaint();
    }
}