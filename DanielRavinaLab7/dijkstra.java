import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Dijkstra {

  public static void main(String[] args) {
    Graph graph = buildGraph(new Graph());
    graph.setShortestPathsFrom(1); // by looking at dijkstraData.txt, the first vertex is 1
    int[] destinations = {7, 37, 59, 82, 99,115,133,165,188,197};
    graph.displayShortestPathTo(destinations);
    // graph.displayAllPaths();
  }

  public static Graph buildGraph(Graph graph) {
      final int VERTEX_INDEX = 0;

      try {
        Scanner file = new Scanner(new File("dijkstraData.txt"));
        // Scanner file = new Scanner(new File("test.txt"));

        while(file.hasNextLine()) {
          String line = file.nextLine();
          String[] verticies = line.split("\\s+");
          int label = Integer.parseInt(verticies[VERTEX_INDEX]);
          Node node = graph.addNode(label);

          for(int i = VERTEX_INDEX+1; i < verticies.length; ++i) {
            String node_meta[] = verticies[i].split(",");
            int adjacent = Integer.parseInt(node_meta[0]);
            int cost     = Integer.parseInt(node_meta[1]);
            node.addEdge(adjacent, cost);
          }
        }
        file.close();

      } catch(FileNotFoundException e) {
        System.out.println("Error: File Not Found...Exiting");
        System.exit(-1);
      }
      return graph;
    }
}