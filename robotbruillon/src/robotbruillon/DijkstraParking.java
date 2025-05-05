package robotbruillon;

import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DijkstraParking {
    static final int WIDTH = 30;
    static final int HEIGHT = 13;
    static final int[][] directions = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    static boolean[][] obstacles = new boolean[HEIGHT][WIDTH];

    public static void main(String[] args) {
        // Exemple d'obstacles
    	obstacles[0][0] = true;
    	obstacles[11][0] = true;
    	obstacles[12][0] = true;
    	obstacles[3][3] = true;
    	obstacles[4][3] = true;
    	obstacles[5][3] = true;
    	obstacles[5][4] = true;
    	obstacles[5][5] = true;
    	obstacles[5][6] = true;
    	obstacles[5][7] = true;
    	obstacles[5][8] = true;
    	obstacles[5][9] = true;
    	obstacles[5][10] = true;
    	obstacles[5][11] = true;
    	obstacles[5][12] = true;
    	obstacles[5][13] = true;
    	obstacles[5][14] = true;
    	obstacles[5][15] = true;
    	obstacles[5][16] = true;
    	obstacles[5][17] = true;
    	obstacles[5][18] = true;
    	obstacles[5][19] = true;
    	obstacles[5][20] = true;
    	obstacles[5][21] = true;
    	obstacles[5][22] = true;
    	obstacles[5][23] = true;
    	obstacles[5][24] = true;
    	obstacles[5][25] = true;
    	obstacles[5][26] = true;
    	obstacles[6][3] = true;
    	obstacles[6][4] = true;
    	obstacles[6][5] = true;
    	obstacles[6][6] = true;
    	obstacles[6][7] = true;
    	obstacles[6][8] = true;
    	obstacles[6][9] = true;
    	obstacles[6][10] = true;
    	obstacles[6][11] = true;
    	obstacles[6][12] = true;
    	obstacles[6][13] = true;
    	obstacles[6][14] = true;
    	obstacles[6][15] = true;
    	obstacles[6][16] = true;
    	obstacles[6][17] = true;
    	obstacles[6][18] = true;
    	obstacles[6][19] = true;
    	obstacles[6][20] = true;
    	obstacles[6][21] = true;
    	obstacles[6][22] = true;
    	obstacles[6][23] = true;
    	obstacles[6][24] = true;
    	obstacles[6][25] = true;
    	obstacles[6][26] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[6][3] = true;
    	obstacles[7][3] = true;
    	obstacles[8][3] = true;
    	obstacles[3][26] = true;
    	obstacles[4][26] = true;
    	obstacles[7][26] = true;
    	obstacles[8][26] = true;
    	obstacles[11][3] = true;
    	obstacles[11][4] = true;
    	obstacles[12][3] = true;
    	obstacles[12][4] = true;
    	obstacles[12][5] = true;
    	obstacles[12][6] = true;
    	obstacles[12][7] = true;
    	obstacles[12][8] = true;
    	obstacles[12][9] = true;
    	obstacles[12][10] = true;
    	obstacles[12][11] = true;
    	obstacles[12][12] = true;
    	obstacles[12][13] = true;
    	obstacles[12][14] = true;
    	obstacles[12][15] = true;
    	obstacles[12][16] = true;
    	obstacles[12][17] = true;
    	obstacles[12][18] = true;
    	obstacles[12][19] = true;
    	obstacles[12][20] = true;
    	obstacles[12][21] = true;
    	obstacles[12][22] = true;
    	obstacles[12][23] = true;
    	obstacles[12][24] = true;
    	obstacles[12][25] = true;
    	obstacles[12][26] = true;
    	obstacles[11][26] = true;
    	obstacles[11][25] = true;
    	obstacles[0][29] = true;
    	obstacles[12][29] = true;
    	obstacles[11][29] = true;
    	obstacles[10][29] = true;
    	obstacles[9][29] = true;
    	
    	
    	

        // Position initiale du robot
        Node start = new Node(28, 12, 0);

        // Liste des places disponibles
        List<Node> placesDisponibles = List.of(
            new Node(1, 0, 0), new Node(2, 0, 0), new Node(3, 0, 0), new Node(4, 0, 0), new Node(5, 0, 0), new Node(6, 0, 0), new Node(7, 0, 0), 
            new Node(8, 0, 0), new Node(9, 0, 0), new Node(10, 0, 0), new Node(11, 0, 0),
            new Node(12, 0, 0), new Node(13, 0, 0), new Node(14, 0, 0), new Node(15, 0, 0), new Node(16, 0, 0), new Node(17, 0, 0), new Node(18, 0, 0),
            new Node(19, 0, 0), new Node(20, 0, 0), new Node(21, 0, 0), new Node(22, 0, 0), new Node(23, 0, 0), new Node(24, 0, 0), new Node(25, 0, 0),
            new Node(26, 0, 0), new Node(27, 0, 0), new Node(28, 0, 0), new Node(0, 1, 0), new Node(0, 2, 0), new Node(0, 3, 0), new Node(0, 4, 0)
            , new Node(0, 5, 0), new Node(0, 6, 0), new Node(0, 7, 0), new Node(0, 8, 0), new Node(0, 9, 0), new Node(0, 10, 0), new Node(29, 1, 0)
            , new Node(29, 2, 0), new Node(29, 3, 0), new Node(29, 4, 0), new Node(29, 5, 0), new Node(29, 6, 0), new Node(29, 7, 0), new Node(29, 8, 0)
            , new Node(4, 4, 0), new Node(5, 4, 0), new Node(6, 4, 0), new Node(7, 4, 0), new Node(8, 4, 0), new Node(9, 4, 0), new Node(10, 4, 0)
            , new Node(11, 4, 0), new Node(12, 4, 0), new Node(13, 4, 0), new Node(14, 4, 0), new Node(15, 4, 0), new Node(16, 4, 0), new Node(17, 4, 0)
            , new Node(18, 4, 0), new Node(19, 4, 0), new Node(20, 4, 0), new Node(21, 4, 0), new Node(22, 4, 0), new Node(23, 4, 0), new Node(24, 4, 0)
            , new Node(25, 4, 0)
            , new Node(4, 7, 0), new Node(5, 7, 0), new Node(6, 7, 0), new Node(7, 7, 0), new Node(8, 7, 0), new Node(9, 7, 0), new Node(10, 7, 0)
            , new Node(11, 7, 0), new Node(12, 7, 0), new Node(13, 7, 0), new Node(14, 7, 0), new Node(15, 7, 0), new Node(16, 7, 0), new Node(17, 7, 0)
            , new Node(18, 7, 0), new Node(19, 7, 0), new Node(20, 7, 0), new Node(21, 7, 0), new Node(22, 7, 0), new Node(23, 7, 0), new Node(24, 7, 0)
            , new Node(25, 7, 0)
            , new Node(5, 11, 0), new Node(6, 11, 0), new Node(7, 11, 0), new Node(8, 11, 0), new Node(9, 11, 0), new Node(10, 11, 0)
            , new Node(11, 11, 0), new Node(12, 11, 0), new Node(13, 11, 0), new Node(14, 11, 0), new Node(15, 11, 0), new Node(16, 11, 0), new Node(17, 11, 0)
            , new Node(18, 11, 0), new Node(19, 11, 0), new Node(20, 11, 0), new Node(21, 11, 0), new Node(22, 11, 0), new Node(23, 11, 0), new Node(24, 11, 0)
    
        );

        // Trouver la meilleure place (plus proche)
        Node bestGoal = trouverPlaceLaPlusProche(start, placesDisponibles);
        List<Node> path = dijkstra(start, bestGoal);

        // Affichage graphique
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Robot Parking - Système de guidage");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Position dynamique du robot
            Node[] robotPosition = { new Node(start.x, start.y, 0) };
            GridPanel panel = new GridPanel(HEIGHT, WIDTH, obstacles, path, robotPosition[0], bestGoal, placesDisponibles);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Timer pour l’animation
            Timer timer = new Timer(500, null);
            ActionListener listener = new ActionListener() {
                int index = 0;

                public void actionPerformed(ActionEvent e) {
                    if (index < path.size()) {
                        Node next = path.get(index);
                        afficherInstruction(robotPosition[0], next);
                        robotPosition[0].x = next.x;
                        robotPosition[0].y = next.y;
                        panel.setStart(robotPosition[0]);
                        panel.repaint();
                        index++;
                    } else {
                        ((Timer) e.getSource()).stop();
                        System.out.println("Arrivé à la place !");
                    }
                }
            };
            timer.addActionListener(listener);
            timer.start();
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
    public static void afficherInstruction(Node from, Node to) {
        int dx = to.x - from.x;
        int dy = to.y - from.y;

        if (dx == 1) System.out.println("Instruction : Aller à DROITE");
        else if (dx == -1) System.out.println("Instruction : Aller à GAUCHE");
        else if (dy == 1) System.out.println("Instruction : Aller en BAS");
        else if (dy == -1) System.out.println("Instruction : Aller en HAUT");
    }
}
