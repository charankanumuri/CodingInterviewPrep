class Node{
    int key;
    int val;
    Node next;
    
    public Node(int key, int value){
        this.key = key;
        this.val = value;
    }
}

class MyHashMap {

    Node head;
    public MyHashMap() {
        head = null;
    }
    
    public void put(int key, int value) {
        if(head == null){
            head = new Node(key, value);
            return;
        }
        
        if(!doesExist(key, value)){
            Node newNode = new Node(key, value);
            
            Node current = head;
            
            while(current.next!=null){
                current = current.next;
            }
            
            current.next = newNode;
        }
    }
    
    public boolean doesExist(int key, int value){
        
        if(head == null)
            return false;
        
        if(head.key == key)
        {
            head.val = value;
            return true;
        }
        
        Node current = head;
        
        while(current.next!=null){
            if(current.next.key == key){
                current.next.val = value;
                return true;
            }
            else
                current = current.next;
        }
        
        return false;
    }
    
    public int get(int key) {
        
        if(head == null)
            return -1;
        
        if(head.key == key)
            return head.val;
        
        Node current = head;
        
        while(current.next!=null){
            if(current.next.key == key)
                return current.next.val;
            
            current = current.next;
        }
    
        return -1;
    }
    
    public void remove(int key) {
        if(head == null)
            return;
        
        if(head.key == key)
        {
            head = head.next;
            return;
        }
        
        Node current = head;
        
        while(current.next!=null){
            if(current.next.key == key){
                current.next = current.next.next;
            }
            else
                current = current.next;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */