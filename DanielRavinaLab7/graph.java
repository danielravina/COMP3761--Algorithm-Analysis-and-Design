// Assignment 7
// Author: Daniel Ravina
// Date: March 18, 2015

import java.util.HashMap;
import java.util.Arrays;

class Graph {
  private HashMap<Integer, Node> nodes;

  public Graph() {
    nodes = new HashMap<Integer, Node>();
  }

  public void clearVisited() {

  }

  public int size() {
    return nodes.size();
  }

  public Node getNode(int key) {

    return nodes.get(key);
  }

  public Node addNode(int nodeName) {
    Node new_node = new Node(nodeName);
    nodes.put(nodeName, new_node);
    return new_node;
  }

  public void debug(){
    System.out.println(nodes.keySet().size());
    for(int i : nodes.keySet()) {
      System.out.print(i);
      Node node = nodes.get(i);

      for(int j : node.getEdges().keySet()) {
        System.out.print("--> ");
        System.out.print(j);
        System.out.print("(" + node.getEdgeCost(j) +")");
      }
      System.out.println();
      break;
    }
  }
}