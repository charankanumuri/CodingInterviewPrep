/**
 * Swap Nodes in Pairs
Medium
Given a linked list, swap every two adjacent nodes and return its head. 
You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

 

Example 1:


Input: head = [1,2,3,4]
Output: [2,1,4,3]
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [1]
Output: [1]
 

Constraints:

The number of nodes in the list is in the range [0, 100].
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
    public ListNode swapPairs(ListNode head) {
        
        //additional node that is useful for returning head
        ListNode helper = new ListNode();
        helper.next=head;
        
        //node that is used for swapping purpose
        ListNode prev = helper;
        
        while(head!=null && head.next!=null){
            
            //take 1st two nodes
            ListNode first = head;
            ListNode second = head.next;
            
            //we know helper node is pointing to head (first) initially,
            //we need to switch that to the second node
            prev.next=second;
            
            //we should not lose 3rd node after the pair, so point 1st to 3rd node
            first.next = second.next; 
            
            //point second to 1st node
            second.next = first;
            
            //swapping is done, lets assign head
            //head should be the 3rd node.. after swapping 1 & 2 -> it is 2->1->3->4->null
            //point head to one.next which is 3
            //mark previous as one because thats the node we need for next swap
            prev = first;
            head = first.next;
        }
        
        return helper.next;
    }
}