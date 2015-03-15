// Assignment 7
// Author: Daniel Ravina
// Date: March 18, 2015

import java.util.HashMap;

class Node {

  int label;
  private boolean visited;
                //adjacent, cost
  private HashMap<Integer, Integer> edges;

  public Node(int name) {
    this.visited = false;
    this.label   = label;
    this.edges   = new HashMap<Integer, Integer>();
  }

  public void addEdge (int nodeRef, int cost) {
    this.edges.put(nodeRef, cost);
  }

  public boolean hasAdjacents() {
    return edges != null;
  }

  public void visit() {
    this.visited = true;
  }

  public int getEdgeCost(int key) {
    return edges.get(key);
  }

  public boolean wasVisited() {
    return visited;
  }


  public HashMap<Integer, Integer> getEdges(){
    return edges;
  }

  public int getLabel(){
    return label;
  }

}
