class Node {

  private int data;
  private Node left;
  private Node right;
  private boolean visited;

  public Node(int data) {
    this.data = data;
    this.visited = false;
  }

  public void setLeft(Node left) {
    this.left = left;
  }
  public void setRight(Node right) {
    this.right = right;
  }

  public Node getLeft() {
    return this.left;
  }
  public Node getRight() {
    return this.right;
  }

  public void visit(){
    this.visited = true;
  }

  public boolean wasVisited() {
    return this.visited;
  }

  public int getData() {
    return data;
  }

}