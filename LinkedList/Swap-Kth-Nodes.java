/*
Swapping Nodes in a Linked List
Medium

You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]
Example 2:

Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]
 

Constraints:

The number of nodes in the list is n.
1 <= k <= n <= 105
0 <= Node.val <= 100
*/ 

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        int length=0;
        
        ListNode current = head;
        ListNode frontNode=null;
        
        
        while(current!=null){
            length++;
            
            //assign the kth node from front
            if(length==k)
                frontNode=current;
            
            current = current.next;
        }
        
        current = head;
        ListNode endNode=null;
        for(int i=0;i<length-k;i++){
            current = current.next;
        }
        
        //kth node from behind
        endNode = current;
        
        //swap the values
        int temp=frontNode.val;
        frontNode.val = endNode.val;
        endNode.val = temp;
        
        return head;
    }
}