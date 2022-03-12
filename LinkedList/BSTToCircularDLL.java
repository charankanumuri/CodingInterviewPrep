package LinkedList;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

public class BSTToCircularDLL {

    // leetcode question Number : 426
    // Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in
    // place.

    // You can think of the left and right pointers as synonymous to the predecessor
    // and successor pointers in a doubly-linked list.
    // For a circular doubly linked list, the predecessor of the first element is
    // the last element, and the successor of the last element is the first element.

    // We want to do the transformation in place. After the transformation, the left
    // pointer of the tree node should point to its predecessor, and the right
    // pointer should point to its successor.
    // You should return the pointer to the smallest element of the linked list.

    // check this image for ref:
    // https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png

    // Input: root = [4,2,5,1,3]

    // check this image for ref:
    // https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png

    // Explanation: The figure below shows the transformed BST. The solid line
    // indicates the successor relationship,
    // while the dashed line means the predecessor relationship.

    // check this image for ref:
    // https://assets.leetcode.com/uploads/2018/10/12/bstdllreturnbst.png

    Node head = null;
    Node prev = null;

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return root;
        convert(root);
        // To point the last node with the first node so that it becomes circular DLL.
        head.left = prev;
        prev.right = head;
        return head;
    }

    // Just like Inorder traversal : Left Root Right
    public void convert(Node root) {
        if (root == null)
            return;
        // first go to the left
        convert(root.left);
        // making the extreme left node as the first node of DLL
        if (prev == null) {
            head = root;
        } else {
            // point the right of prev to root
            prev.right = root;
            // point the left of root to prev
            root.left = prev;
        }
        // For each recursive call make the current root as prev
        prev = root;
        // then call Right
        convert(root.right);
    }

}
