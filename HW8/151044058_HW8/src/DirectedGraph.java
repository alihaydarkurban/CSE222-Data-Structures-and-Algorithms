import java.util.Iterator;

public class DirectedGraph implements Graph
{
    private boolean directed;

    private int numV;

    private MyLinkedList<Edge> [] edges;

    public DirectedGraph(int numV, boolean directed)
    {
        this.numV = numV;
        this.directed = directed;

        edges = new MyLinkedList[numV];

        for(int i = 0; i < numV; ++i)
            edges[i] = new MyLinkedList<>();

    }

    @Override
    public int getNumV()
    {
        return numV;
    }

    @Override
    public boolean isDirected()
    {
        return directed;
    }

    @Override
    public void insert(Edge edge)
    {
        edges[edge.getSource() - 1].add(edge);

        if(!isDirected())
            edges[edge.getSource()].add(new Edge(edge.getSource(),edge.getDest()));

    }


    private MyArrayList<Integer> HelperDFS(int v,boolean [] visited, MyArrayList<Integer> temp)
    {

        visited[v - 1] = true;

        temp.add(v);

        Iterator iter = edges[v - 1].iterator();


        while(iter.hasNext())
        {
            MyLinkedList.Node node = (MyLinkedList.Node) iter.next();

            Edge e = (Edge) node.getData();

            if(!visited[e.getDest() - 1])
                HelperDFS(e.getDest(), visited, temp);
        }

        return temp;
    }

    @Override
    public MyArrayList<Integer> DFS(int v)
    {
        boolean visited[] = new boolean[numV];

        MyArrayList<Integer> temp = new MyArrayList<>();

        MyArrayList<Integer> result = HelperDFS(v, visited,temp);

        return result;
    }
}