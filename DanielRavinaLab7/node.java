// Assignment 7
// Author: Daniel Ravina
// Date: March 18, 2015

import java.util.HashMap;
import java.util.Collection;

class Node {
  public static final int INFINITY = 1000000;
  int label, predecessor, distance;
  private HashMap<Integer, Integer> edges;


  public Node(int label) {
    this.label   = label;
    this.edges   = new HashMap<Integer, Integer>();
    // This is not the best way to do it because if we want to change the source we have to build the graph again
    this.distance = INFINITY;
  }

  public void setPredecessor(int label) {
    this.predecessor = label;
  }

  public void setDistance(int dist) {
    this.distance = dist;
  }

  public void addEdge (int nodeRef, int cost) {
    this.edges.put(nodeRef, cost);
  }

  public boolean hasAdjacents() {
    return edges != null;
  }


  public int getCost(int key) {
    return edges.get(key);
  }

  public int getDistance() {
    return distance;
  }

  public int getPredecessor() {
    return predecessor;
  }

  public Collection<Integer> getAdjacents(){
    return edges.keySet();
  }

  public int getLabel(){
    return label;
  }

}
