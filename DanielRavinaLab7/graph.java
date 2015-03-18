// Assignment 7
// Author: Daniel Ravina
// Date: March 18, 2015
import java.util.HashMap;
import java.util.Collection;
import java.util.PriorityQueue;


class Graph {
  public static final int INFINITY = 1000000;

  private HashMap<Integer, Node> nodes;

  public Graph() {
    nodes = new HashMap<Integer, Node>();
  }


  public void setShortestPathsFrom(int source) {
    PriorityQueue<Node> pQueue     = new PriorityQueue<Node>(size(), new NodeDistanceComparator());
    Node                sourceNode = getNode(source);
    sourceNode.setDistance(0);
    for(int v : sourceNode.getAdjacents()) {
      Node adjNode = getNode(v);
      adjNode.setDistance(sourceNode.getCost(v));
      adjNode.setPredecessor(sourceNode.getLabel());
      pQueue.add(adjNode);
    }

    while(!pQueue.isEmpty()) {
      Node min = pQueue.poll();
      for(int w : min.getAdjacents()) {

        Node adjacent   = getNode(w);
        int adjDistance = adjacent.getDistance();
        int costFromMin = min.getCost(w);
        if (adjDistance == INFINITY) {
          adjacent.setDistance(min.getDistance() + costFromMin);
          adjacent.setPredecessor(min.getLabel());
          pQueue.add(adjacent);}

        else if (costFromMin + min.getDistance() < adjacent.getDistance() ) {
          adjacent.setDistance(costFromMin + min.getDistance());
          adjacent.setPredecessor(min.getLabel());
        }
      }
    }
  }
  public void displayShortestPathTo(int[] destinations){
    for (int i = 0; i < destinations.length; i++ ){
      Node dest = getNode(destinations[i]);
      System.out.println("distance of 1 -> " + destinations[i] + "   is " + dest.getDistance() + ", Predecessor:  " + dest.getPredecessor());
    }
  }

  public void displayAllPaths(){
    for(int label : nodes.keySet()) {
      Node dest = getNode(label);
      System.out.println("Distance of 1 -> " + label + " is " + dest.getDistance() + ", Predecessor: " + dest.getPredecessor());
    }
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

}