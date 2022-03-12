package Tree;

import java.util.*;

public class BTLevelOrderTraversal {

    // leetcode question Number : 102 && 103
    // Given the root of a binary tree, return the level order traversal of its
    // nodes' values. (i.e., from left to right, level by level).
    // check this image for ref:
    // https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg

    // Input: root = [3,9,20,null,null,15,7]
    // Output: [[3],[9,20],[15,7]]

    // ZigZag traversal 
    // Output: [[3],[20,9],[15,7]]

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        // boolean zigzag = false;
        while (!q.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.peek();
                q.poll();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
                temp.add(node.val);
            }
            // if(zigzag)
            // Collections.reverse(temp);
            // zigzag = !zigzag;
            res.add(temp);
        }
        return res;
    }
}
