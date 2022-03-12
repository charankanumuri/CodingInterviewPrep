package LinkedList;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
 
public class ReOrderList {
    
    // You are given the head of a singly linked-list. The list can be represented as:

    // L0 → L1 → … → Ln - 1 → Ln
    // Reorder the list to be on the following form:

    // L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    // You may not modify the values in the list's nodes. Only nodes themselves may be changed.

    // check this image for ref: https://assets.leetcode.com/uploads/2021/03/04/reorder1linked-list.jpg

    // Input: head = [1,2,3,4]
    // Output: [1,4,2,3]
    public void reorderList(ListNode head) {
        if (head == null) return;
        
        // fast & slow pointer to check the middle of the list
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // middle of the list is in "slow"


        // To reverse the list starting from "slow" as head
        ListNode prev = null;
        ListNode temp = slow;
        while(temp != null) {
            ListNode next_node = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next_node;
        }
        
        // "prev" has the reversed list 
        ListNode list1 = head;
        ListNode list2 = prev;

        // head has first half of the original list
        // prev has reversed second half of the original list
        // merging 2 lists i.e. head and prev;
        while(list2.next != null) {
            ListNode node = list1.next;
            list1.next = list2;
            list1 = node;
            
            ListNode node2 = list2.next;
            list2.next = list1;
            list2 = node2;
        }
    }
}
