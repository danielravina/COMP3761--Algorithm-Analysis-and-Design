// NOTE: Please run with at least 1GB heap size: i.e 'java -Xmx1g Driver'
// Graph.java
// Assignment 4
// Author: Daniel Ravina
// Date: February 11, 2015

import java.util.HashMap;
import java.util.Arrays;

class Graph {
  private HashMap<Integer, Node> nodes;

  public Graph() {
    nodes = new HashMap<Integer, Node>();
  }
  
  public void clearVisited() {
    for (Object name : nodes.keySet()){
      nodes.get(name).leave();
    }
  }

  public int size() {
    return nodes.size();
  }

  public Node getNode(int key) {
    return nodes.get(key);
  }

  public void addNodes(int root, int adjacent) {
    Node leftNode  = nodes.get(root);
    Node rightNode = nodes.get(adjacent);
    
    if (leftNode == null) {
      this.nodes.put(root, leftNode = new Node(root));
    }
    
    if (rightNode == null) {
      this.nodes.put(adjacent, rightNode = new Node(adjacent));
    }

    if (!leftNode.hasNode(adjacent)) { 
      leftNode.addAdjacent(adjacent);
    }
    
    if (!rightNode.hasNode(root)) {
      rightNode.addAdjacent(root);
    }
  }

}