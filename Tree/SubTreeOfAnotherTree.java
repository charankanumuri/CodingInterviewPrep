package Tree;

public class SubTreeOfAnotherTree {
    
    // leetcode question Number : 572
    //  Given the roots of two binary trees root and subRoot, 
    //  return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

    //  A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. 
    //  The tree tree could also be considered as a subtree of itself.
    // check this image for ref: https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null) return true;
        if(root == null || subRoot == null) return false;

        // if root values of both root and subRoot are same
        // then proceed to check for left and right values.
        if(root.val == subRoot.val) {
            if(compareTrees(root.left, subRoot.left) && compareTrees(root.right, subRoot.right))
                return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    
    public boolean compareTrees(TreeNode s, TreeNode t) {
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.val != t.val) return false;
        // if root.values of 2 trees are equal then check for left and right values
        return compareTrees(s.left, t.left) && compareTrees(s.right, t.right);
    }
}
