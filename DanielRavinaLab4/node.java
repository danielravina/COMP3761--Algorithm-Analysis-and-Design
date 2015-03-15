// NOTE: Please run with at least 1GB heap size: i.e 'java -Xmx1g Driver'
// Node.java (AKA Vertex)
// Assignment 4
// Author: Daniel Ravina
// Date: February 11, 2015

import java.util.ArrayList;

class Node {
  
  int name;
  private boolean visited;
  private ArrayList<Integer> adjacentNodes;
  
  public Node(int name) {
    this.visited       = false;
    this.name          = name;
    this.adjacentNodes = new ArrayList<Integer>();
  }

  public void addAdjacent (int node) {
    this.adjacentNodes.add(node);
  }
  
  public boolean hasNode (int name) {
    return adjacentNodes.contains(name);
  }
  
  public boolean hasAdjacents() {
    return adjacentNodes != null;
  }
  
  public void visit() {
    this.visited = true;
  }

  public void leave() {
    this.visited = false; 
  }

  public boolean wasVisited() {
    return visited;
  }

  public ArrayList<Integer> getAdjacentNodes(){
    return adjacentNodes;
  }

  public int getName(){
    return name;
  }

  public void freeMemory() {
    this.adjacentNodes = null;
  }

}
