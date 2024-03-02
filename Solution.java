
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws Exception {
        // Test case 1
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println("Actual: " + findCheapestPrice(4, flights1, 0, 3, 1) + ", Expected: 700");

        // Test case 2
        int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println("Actual: " + findCheapestPrice(4, flights2, 0, 3, 0) + ", Expected: -1");

        // Test case 3
        int[][] flights3 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println("Actual: " + findCheapestPrice(3, flights3, 0, 2, 0) + ", Expected: 500");
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    	int cheapestCost = 0;
    	Map<Integer, List<Node>>connectNodeListMap = generateConnectedNodes(flights); 
    	int curSrc = src;
    	int stopCount = 0;
    	while(!connectNodeListMap.get(curSrc).isEmpty()) {
    		List<Node>connectedNodes = connectNodeListMap.get(curSrc);
    		
    		if(connectedNodes.stream().filter(node -> node.getDest() == dst).collect(Collectors.toList()).size() > 0) {
    			connectedNodes =  connectedNodes.stream().filter(node -> node.getDest() == dst).collect(Collectors.toList());
    		}
    		Node nodes = connectedNodes.stream().min((n1,n2) -> n1.getCost() - n2.getCost()).get();
    		if(nodes != null) {
    			int cost = nodes.getCost();
    			cheapestCost += cost;
    			if(nodes.getDest() == dst) {
    				break;
    			}else {
    				curSrc = nodes.getDest();
    				stopCount++;
    				if(stopCount > k) {
    					return -1;
    				}
    			}
    		}
    		
    	}
        return cheapestCost;
    }

	private static Map<Integer, List<Node>> generateConnectedNodes(int[][] flights) {
		Map<Integer, List<Node>>connectNodeListMap = new HashMap<>();
        for(int[] flight : flights) {
        	Node node = new Node(flight[0], flight[1], flight[2]);
   
        	List<Node>currentNodes = connectNodeListMap.containsKey(node.getSrc()) ? connectNodeListMap.get(flight[0]) : new ArrayList<>();
        	currentNodes.add(node);
        	connectNodeListMap.put(node.getSrc(), currentNodes);
        }
        return connectNodeListMap;
	}
}

class Node {
	int src;
	int dest;
	int cost;
	Node (int src, int dest, int cost) {
		this.src = src;
		this.dest = dest;
		this.cost = cost;
	}
	public int getSrc() {
		return src;
	}
	public void setSrc(int src) {
		this.src = src;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
}
