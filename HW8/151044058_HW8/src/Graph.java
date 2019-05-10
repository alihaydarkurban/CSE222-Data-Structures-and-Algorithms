public interface Graph
{
    int getNumV();

    boolean isDirected();

    void insert(Edge edge);

    MyArrayList<Integer> DFS(int v);
}