package robotbruillon;

import javax.swing.*;
import java.util.*;

public class DijkstraParking {
    static final int WIDTH = 10;
    static final int HEIGHT = 8;
    static final int[][] directions = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    static boolean[][] obstacles = new boolean[HEIGHT][WIDTH];

    public static void main(String[] args) {
        // Exemple d'obstacles
    	obstacles[1][3] = true;
    	obstacles[0][3] = true;
    	obstacles[2][3] = true;
        obstacles[3][3] = true;
        obstacles[3][4] = true;
        obstacles[3][5] = true;
        obstacles[4][5] = true;

        // Position initiale du robot
        Node start = new Node(0, 0, 0);

        // Liste des places disponibles
        List<Node> placesDisponibles = List.of(
            new Node(8, 1, 0),
            new Node(9, 3, 0),
            new Node(7, 7, 0)
        );

        // Trouver la meilleure place (plus proche)
        Node bestGoal = trouverPlaceLaPlusProche(start, placesDisponibles);
        List<Node> path = dijkstra(start, bestGoal);

        // Affichage graphique
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Robot Parking - Place la plus proche");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GridPanel(HEIGHT, WIDTH, obstacles, path, start, bestGoal));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static Node trouverPlaceLaPlusProche(Node start, List<Node> places) {
        Node meilleure = null;
        int meilleurCout = Integer.MAX_VALUE;

        for (Node goal : places) {
            List<Node> chemin = dijkstra(start, goal);
            if (!chemin.isEmpty() && chemin.size() < meilleurCout) {
                meilleurCout = chemin.size();
                meilleure = goal;
            }
        }

        return meilleure;
    }

    public static List<Node> dijkstra(Node start, Node goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<String, Integer> costMap = new HashMap<>();
        Map<String, Node> cameFrom = new HashMap<>();

        openSet.add(start);
        costMap.put(getKey(start), 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            for (int[] dir : directions) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < WIDTH && ny < HEIGHT && !obstacles[ny][nx]) {
                    Node neighbor = new Node(nx, ny, current.cost + 1);
                    String key = getKey(neighbor);
                    int newCost = current.cost + 1;

                    if (!costMap.containsKey(key) || newCost < costMap.get(key)) {
                        costMap.put(key, newCost);
                        neighbor.cost = newCost;
                        neighbor.previous = current;
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    static List<Node> reconstructPath(Node end) {
        List<Node> path = new ArrayList<>();
        Node current = end;
        while (current != null) {
            path.add(current);
            current = current.previous;
        }
        Collections.reverse(path);
        return path;
    }

    static String getKey(Node node) {
        return node.x + "," + node.y;
    }
}
