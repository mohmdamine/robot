package robotbruillon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GridPanel extends JPanel {
    private final int cellSize = 50;
    private final int rows, cols;
    private boolean[][] obstacles;
    private List<Node> path;
    private Node start, goal;
    private List<Node> placesDisponibles;

    public GridPanel(int rows, int cols, boolean[][] obstacles, List<Node> path, Node start, Node goal, List<Node> placesDisponibles) {
        this.rows = rows;
        this.cols = cols;
        this.obstacles = obstacles;
        this.path = path;
        this.start = start;
        this.goal = goal;
        this.placesDisponibles = placesDisponibles;
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (obstacles[y][x]) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        // Chemin
        if (path != null) {
            for (Node node : path) {
                g.setColor(Color.CYAN);
                g.fillRect(node.x * cellSize, node.y * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(node.x * cellSize, node.y * cellSize, cellSize, cellSize);
            }
        }

        // Départ
        g.setColor(Color.GREEN);
        g.fillRect(start.x * cellSize, start.y * cellSize, cellSize, cellSize);

        // Destination
        g.setColor(Color.RED);
        g.fillRect(goal.x * cellSize, goal.y * cellSize, cellSize, cellSize);
        
     // Places disponibles (autres que goal)
        if (placesDisponibles != null) {
            for (Node place : placesDisponibles) {
                if (!place.equals(goal)) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(place.x * cellSize, place.y * cellSize, cellSize, cellSize);
                    g.setColor(Color.GRAY);
                    g.drawRect(place.x * cellSize, place.y * cellSize, cellSize, cellSize);
                }
            }
        }
    }
    public void setStart(Node newStart) {
        this.start = newStart;
    }
}
