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


/*
i/p:    1 -> 1-> 1-> 2-> 3-> null
o/p:    2->3->null
*/
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;
        
        //creating a node before head
        ListNode preHead = new ListNode(0);
        preHead.next = head; //point its next to head
        
        //Add one more pointer to preHead to track the movement
        ListNode helper = preHead;
        
        while(head!=null){
            
            //First base case is, is my very first node value a duplicate?
            //to check that, we need to know whether there is a next node, if yes, are the values same?
            if(head.next!=null && head.val == head.next.val){
                
                //when values are same, keep moving the head until the condition fails
                while(head.next!=null && head.val == head.next.val){
                    head = head.next;
                }
                
                //when the condition fails, head will point to last duplicate of current iteration
                //point the helper.next to head.next as head is a duplicate
                helper.next = head.next;
            }
            else
                helper = helper.next; //If current node and next node aren't duplicates, move helper by one node
            
            //after all this, make sure we move head by one point
            head = head.next;
        }
        
        //Our preHead was point to head and helper, so prehead.next will point to helper
        return preHead.next;
    }
}