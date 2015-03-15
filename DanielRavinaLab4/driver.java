// NOTE: Please run with at least 1GB heap size: i.e 'java -Xmx1g Driver'
// Driver.java
// Assignment 4
// Author: Daniel Ravina
// Date: February 11, 2015

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;


class Driver {
  public static String FILE_NAME              = "graph.txt";
  public static Graph graph                   = new Graph();;
  public static int graphSize                 = 0;
  public static ArrayList<Integer> components = new ArrayList<Integer>();;
  public static final int DISPLAY_THRESHHOLD  = 5;

  public static void main(String[] args) {
    try {
      buildGraph();
      bfs();
      displayResult();
      graph.clearVisited();
      components.clear();
      dfs();
      displayResult();
    } catch (IOException e) {
      System.out.println("Error Reading file");
    }
  }

  public static void displayResult()  {
    Collections.sort(components);
    Collections.reverse(components);
    System.out.println("Number of components: " + components.size());
    System.out.println("The 5 Biggest are: ");
    System.out.print("[");
    for(int i = 0; i < DISPLAY_THRESHHOLD; i++) {
      System.out.print(components.get(i));
      if (i != 4) { System.out.print(", "); }
    }
    System.out.print("]");
    System.out.println("");
  }

  public static void dfs() {
    System.out.println("Starting DFS...");
    Stack<Node> stack = new Stack<Node>();
    for (int i = 1; i <= graphSize; i++) {
      Node currentNode = graph.getNode(i);
      if(!currentNode.wasVisited()) {
        stack.push(currentNode);
        currentNode.visit();
        int count = 0;
        while(!stack.isEmpty()) {
          for (int adjacend : currentNode.getAdjacentNodes()) {
            Node adj = graph.getNode(adjacend);
            if(!adj.wasVisited()) {
              adj.visit();
              stack.push(adj);
            }
          }
          count++;
          currentNode = stack.pop();
        }
        components.add(count);
      }
    }
  }

  public static void bfs() {
    System.out.println("Starting BFS...");
    LinkedList<Node> queue = new LinkedList<Node>();
    for (int i = 1; i <= graphSize; i++) {
      Node currentNode = graph.getNode(i);
      if(!currentNode.wasVisited()) {
        currentNode.visit();
        int count = 0;
        do {
          for (int adjacend : currentNode.getAdjacentNodes()) {
            Node adj = graph.getNode(adjacend);
            if(!adj.wasVisited()) {
              adj.visit();
              queue.add(adj);
            }
          }
          currentNode = queue.poll();
          count++;
        } while(currentNode != null);
        components.add(count);
      }
    }
  }

  public static void buildGraph() throws IOException {
    System.out.println("Building Graph...");
    RandomAccessFile aFile = new RandomAccessFile(FILE_NAME, "r");
    FileChannel inChannel = aFile.getChannel();
    MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
    buffer.load();
    String tmp = "";
    for (int i = 0; i < buffer.limit(); i++)
    {
      char ch = (char) buffer.get();
      if ((char) ch == '\n') {
        String nodes[] = tmp.split("\\s");
        graph.addNodes(
          Integer.parseInt(nodes[0]),
          Integer.parseInt(nodes[1])
        );
        tmp = "";
      } else {
        tmp += ch;
      }
    }
    graphSize = graph.size();
    buffer.clear();
    inChannel.close();
    aFile.close();
  }
  
}