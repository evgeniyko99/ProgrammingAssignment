public class Node implements Comparable<Node>{

  int distance, node, parent;

  public Node(int distance,int node,int parent){
    this.distance = distance;
    this.node = node;
    this.parent = parent;
  }
  
  public int compareTo(Node nodeObect) {
    if (distance == nodeObect.distance){
      return Integer.compare(node, nodeObect.node);
          
    }
    return Integer.compare(distance, nodeObect.distance);
   }

}
