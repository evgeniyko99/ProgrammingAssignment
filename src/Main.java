import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

public class Main {
  
  static final int infinity = Integer.MAX_VALUE;
  
  static int nodes, edges;
  
  static int[] distance;
  
  static List<Edge>[] adjListValues;
  
  public static void constructGraph(Scanner sc){
    adjListValues = new ArrayList[nodes];
      
    for(int i = 0; i < nodes; i++)
      adjListValues[i] = new ArrayList<>();
      
    for(int i = 0; i < nodes && sc.hasNext();i++){
      String edge;
          
      String newValue = sc.nextLine();
          
      while(sc.hasNext() && !(edge = sc.nextLine()).isEmpty()){
        String[] edgeValue = edge.trim().split("");
        int from,to,weight;
              
        to = Integer.parseInt(edgeValue[0]);
        weight = Integer.parseInt(edgeValue[1].trim());
      
        adjListValues[Integer.parseInt(newValue)].add(new Edge(to, weight));
        adjListValues[to].add(new Edge(Integer.parseInt(newValue), weight));
      }
    }
      
  }
  public static void shortestPath(int start){
    distance = new int[nodes];
      
    for(int i = 0; i < nodes; i++)
      distance[i] = infinity;
      
    TreeSet<Node> tree = new TreeSet<>();
      
    distance[start] = 0;

    tree.add(new Node(start,0,-1));

    while (tree.size() > 0) {

      Node currentNode = tree.pollFirst();

      for (Edge edge : adjListValues[currentNode.node]) {
        if (distance[currentNode.node] + edge.weight < distance[edge.to]) {

          tree.remove(new Node(edge.to, distance[edge.to], -1));
          distance[edge.to] = distance[currentNode.node] + edge.weight;

          tree.add(new Node(edge.to, distance[edge.to], currentNode.node));
        }
      }
    }
      
  }

  public static void main(String[] args){
    Date d = new Date();
    long systime1 = d.getTime();
    try{
      Scanner sc = new Scanner(new File("input2.txt"));

      String line = sc.nextLine();
      String[] array = line.split(" ");

      nodes = Integer.parseInt(array[0].split("=")[1]);
      edges = Integer.parseInt(array[1].split("=")[1]);

      constructGraph(sc);

      int val = 0;
          
      shortestPath(val);
          
      int sum = 0;
      for(int i = 0; i < nodes ; i++){
        if(i==val)
          continue;
        if(distance[i] != infinity){
          sum += distance[i];
        }
      }
      System.out.println();
      System.out.println("Sum is: " + sum);
          
    }catch(IOException e){
      System.out.println("From catch ");
    }
      
    Date d1 = new Date();
    long systime2 = d1.getTime();
    
    long time = (systime2-systime1)/100;
    if(time < 10) {
      System.out.println("Output is less than one second " );
    } else {
      System.out.println("time in seconds = " + time/10);
    }
  }
  
}
