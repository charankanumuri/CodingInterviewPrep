package Tree;

import java.util.Stack;

public class KthSmallestNodeInBST {

    // leetcode question Number : 230
    // Given the root of a binary search tree, and an integer k,
    // return the kth smallest value (1-indexed) of all the values of the nodes in
    // the tree.

    // check this image for ref:
    // https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg

    // Input: root = [5,3,6,2,4,null,null,1], k = 3
    // Output: 3

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        int count = 0;
        TreeNode node = root;
        while (true) {
            // traverse till the bottom left of the tree
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                if (st.isEmpty())
                    break;
                // pop the elements from the stack till k times to get the kth smallest number
                // as in BST all the smaller elements are stored in left side.
                node = st.peek();
                st.pop();
                count++;
                if (count == k)
                    return node.val;
                node = node.right;
            }
        }
        return -1;
    }
}
