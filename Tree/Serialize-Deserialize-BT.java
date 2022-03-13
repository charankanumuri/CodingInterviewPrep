/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * Serialize means kind of encryption or encoding
 * Deserialize means decode the encryption
 * 
 * 
 * T(N)=O(n)
 * S(N)=O(n)
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return encode(root, "");
    }
    
    //Following DFS technique to form a encoded string from the Tree
    public String encode(TreeNode root, String s){

        //base case when we reach null, encode the string with NULL and return the string
        if(root == null){
            s = s+"null,";
            return s;
        }
        
        //when we see the root value, add it to the string
        s= s+s.valueOf(root.val)+",";

        //call left and right nodes
        s = encode(root.left, s);
        s = encode(root.right,s);
        
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        String[] data_array = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(data_array));
        return De_serialize(list);
        
    }
    
    //contructing tree from list, we use list because it is easy to remove, and list gets rearranged automatically
    public TreeNode De_serialize(List<String> l){

        //if list contains null, return NULL to the tree
        if(l.get(0).equals("null"))
        {
            l.remove(0);
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0); //after adding to tree, remove that value from the list

        root.left = De_serialize(l);
        root.right= De_serialize(l);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));