public class Edge
{
    private int dest;

    private int source;

    public Edge(int source, int dest)
    {
        this.source = source;
        this.dest = dest;
    }

    public int getDest()
    {
        return dest;
    }

    public int getSource()
    {
        return source;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[(");
        sb.append(source);
        sb.append(", ");
        sb.append(dest);
        sb.append(")]");
        return sb.toString();
    }
}