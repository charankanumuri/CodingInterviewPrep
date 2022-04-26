/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        
        HashMap<Node,Node> map = new HashMap<>();
        return cloneGraphRecursive(node, map);
    }
    
    public Node cloneGraphRecursive(Node node, Map<Node, Node> map){
        
        //if node is null, there is nothing we need to do
        if(node == null)
            return null;
        
        //if we already cloned this node, lets return it from map
        if(map.containsKey(node))
            return map.get(node);
        
        //create a new Node and add it to map
        Node cloneNode = new Node(node.val);
        map.put(node, cloneNode);
        
        //lets explore its neighbors
        for(Node n: node.neighbors){
            
            //while exploring neighbors we also need to make sure we clone the neighbor node
            //so recursively call that neighbor node and then add it to the cloned neighbor list
            cloneNode.neighbors.add(cloneGraphRecursive(n, map));
        }
        
        return cloneNode;
    }
}