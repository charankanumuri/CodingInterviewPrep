class Node{
    int val;
    Node next;
    
    public Node(int value){
        this.val = value;
        this.next = null;
    }
}

class MyHashSet {

    Node head;
    public MyHashSet() {
        head = null;
    }
    
    public void add(int key) {
        if(head == null){
            head = new Node(key);
            return;
        }
        
        if(!doesExist(key)){
             Node current = head;
        
            Node newNode = new Node(key);
            while(current.next!=null){
                current = current.next;
            }

            current.next = newNode;
        }
        
    }
    
    public boolean doesExist(int key){
        if(head == null)
            return false;
        
        if(head.val == key)
            return true;
        
        Node current = head;
        while(current.next!=null){
            if(current.next.val == key)
                return true;
            
            current = current.next;
        }
        
        return false;
    }
    
    public void remove(int key) {
        if(head == null)
            return;
        
        if(head.val == key){
            head = head.next;
            return;
        }
        
        Node current = head;
        while(current.next!=null){
            if(current.next.val == key){
                current.next = current.next.next; 
                return;
            }
            else
                current = current.next;
        }
    }
    
    public boolean contains(int key) {
        return doesExist(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */