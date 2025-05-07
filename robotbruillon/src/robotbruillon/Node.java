package robotbruillon;

public class Node {
    public int x, y, cost;
    public boolean disponibilite = true;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.disponibilite = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node n = (Node) o;
            return this.x == n.x && this.y == n.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
