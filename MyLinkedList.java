/* defining node class */	
  class Node<T> {
       private	T element;///////////declaring the type of the element to be T
       private Node<T> next;
       private Node<T> prev;
       public Node(T a){
       element=a;
       next=null;
       prev=null;
       }
       public Node(T a,Node<T> prev){
        this.prev=prev;
        next=null;
        element=a;
       }
       public T getElement(){
   	    return element;
   	   }
       public Node<T> getNext(){
   	    return next;
   	   }
       public Node<T> getPrev(){
   	    return prev;
       }
       public void setElement(T element){
        this.element=element;
       }
       public void setNext(Node<T> next){
   	    this.next=next;
       }
       public void setPrev(Node<T> prev){
   	     this.prev=prev;
       }
 }
public class MyLinkedList<T>{
  private Node<T> head;
  private Node<T> tail;
  private int size;
  public MyLinkedList(){
  	head=null;
  	tail=null;
  	size=0;
  }
  public Boolean isEmpty(){
  	return size==0;
  } 
  public Node<T> head(){
  	if(isEmpty()){
  		return null;
  	}
  	return head;
  }
  public Node<T> tail(){
  	if(isEmpty()){
  		return null;
  	}
  	return tail;
  }
  public int size(){
  	return size;
  }
  public void addFirst(T element){
    Node<T> a= new Node<>(element);
    if(head==null){
    	head=a;
    	tail=head;
    	size++;
    	return;
    }
    head.setPrev(a);
    a.setNext(head);
    a.setPrev(null);
    head=a;
    size++;
  }
  public void addLast(T element){
  Node<T> a= new Node<>(element);
    if(head==null){
  	head=a;
  	tail=a;
  	size++;
  	return;
   }
   tail.setNext(a);
   a.setNext(null);
   a.setPrev(tail);
   tail=a;
   size++;
}
  public Node<T> removeFirst(){
   if(isEmpty()){
   	return null;
   }
   Node<T>temp=head;
   head=head.getNext();
   head.setPrev(null);
   size--;
   return temp;
  }
  public Boolean isMember(T element){
  	if(isEmpty()){
  		return false;
  	}
  	Node<T> temp=head;
    while(temp!=null){
    	if(temp.getElement()==element){
    		return true;
    	}
    	temp=temp.getNext();
    }
    return false;
  }

}