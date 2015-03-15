import java.util.Stack;

class BinaryTree {
  private Node _rootNode;

  public BinaryTree(int[] inOrder, int[] postOrder) {
    int inStart   = 0;
    int inEnd     = inOrder.length-1;
    int postStart = 0;
    int postEnd   = postOrder.length-1;


    _rootNode = build(inOrder, inStart, inEnd, postOrder, postStart, postEnd);
  }

  public Node build(int[] inOrder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {
      if(inStart > inEnd || postStart > postEnd) {
        return null;
      }
      int rootValue = postOrder[postEnd];
      Node root = new Node(rootValue);

      int k = 0;
      for(int i=0; i< inOrder.length; i++){
        if( inOrder[i] == rootValue ) {
            k = i;
            break;
        }
      }
      root.setLeft  ( build(inOrder, inStart, k-1, postOrder, postStart, postStart+k-(inStart+1)) );
      root.setRight ( build(inOrder, k+1, inEnd, postOrder, postStart+k-inStart, postEnd-1) );

      return root;
  }

  public void displayPreorderList() {
    System.out.println("PreOrder is: ");
    System.out.print("[");
    dfs(this._rootNode);
    System.out.print("]");
    System.out.println();
  }

  public void dfs(Node rootNode) {
    rootNode.visit();

    System.out.print(" " + rootNode.getData() + " ");

    if(rootNode.getLeft() != null &&  !rootNode.getLeft().wasVisited()) {
      dfs(rootNode.getLeft());
    }
    if(rootNode.getRight() != null && !rootNode.getRight().wasVisited()) {
      dfs(rootNode.getRight());
    }

  }

  public void display(Node rootNode) {

  }



}