package edu.miracosta.cs113;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  public void add(int index, E obj) {
	  ListIterator<E> iter = listIterator(index);
	  iter.add(obj);
	  
   }
  public void addFirst(E obj) { 
	  add(0, obj);
	  
  }
  public void addLast(E obj) { 
	  add(size, obj);
  }

  public E get(int index) { 	
	  ListIterator<E> iter = listIterator(index); 
      return iter.next();
  }  
  public E getFirst() { 
	  return head.data;  
  }
  public E getLast() { 
	  return tail.data;  
  }

  public int size() {  
	  if (head == null)
		  return 0;

	  int count = 1;
	  
	  Node current = head;
	  
	  while (current.next != null) {
		  count++;
		  current = current.next;
	  }
	  
	  return count;
	  
  } // Fill Here

  public E remove(int index)
  {     E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
  }

  public Iterator iterator() { return new ListIter(0);  }
  public ListIterator listIterator() { return new ListIter(0);  }
  public ListIterator listIterator(int index){return new ListIter(index);}
  public ListIterator listIterator(ListIterator iter)
  {     return new ListIter( (ListIter) iter);  }

  // Inner Classes
  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        {   data = dataItem;   }
  }  // end class Node

  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 

    public ListIter(int i)  // constructor for ListIter class
    {   if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
        lastItemReturned = null;
 
        if (i == size)     // Special case of last item
        {     index = size;     nextItem = null;      }
        else          // start at the beginning
        {   nextItem = head;
            for (index = 0; index < i; index++)  nextItem = nextItem.next;   
        }// end else
    }  // end constructor

    public ListIter(ListIter other) {   
    	nextItem = other.nextItem;
        index = other.index;    
    }

    public boolean hasNext() {   
    	    return nextItem != null;
    } 
    
    public boolean hasPrevious() {   
    	return (nextItem == null && size != 0)
    			|| nextItem.prev != null;
    } 
    
    public int previousIndex() {  
    	if (!hasPrevious())
    		return -1;
    	else 
    		return index;
    } 
    
    public int nextIndex() {  
    	if (!hasNext())
    		return size;
    	else
    		return index;
    	
    } 
    
    public void set(E o)  { 
    	
    }  // not implemented
    
    public void remove(){
    	
    }      // not implemented

    public E next() {  
    	
    	if (!hasNext())
    		throw new NoSuchElementException();
    	lastItemReturned = nextItem;
    	
    	nextItem = nextItem.next;
    	
    	index++;
    	
        return lastItemReturned.data; 
    }

    public E previous() {  
    	if (!hasPrevious()) 
    		throw new NoSuchElementException();
    	
    	if (nextItem == null)
    		nextItem = tail;
    	else
    		nextItem = nextItem.prev;
    	
    	lastItemReturned = nextItem;
    	
    	index--;
    	
    	return lastItemReturned.data;  
    }

    public void add(E obj) {
    	/* 
    	 * When adding, there are four cases to address:
    	 * 		–Add to an empty list
    	 * 		–Add to the head of the list
    	 * 		–Add to the tail of the list
    	 * 		–Add to the middle of the list
    	 */
    	
    	//add to an empty list
    	if (head == null) {
    		head = new Node<E> (obj);
    	}
    	
    	//adding to the head of the list
    	if (nextItem == head) {
    		Node<E> newNode = new Node<E>(obj);
    		newNode.next = nextItem;
    		nextItem.prev = newNode;
    		head = newNode;
    	}
    	
    	//adding to the tail of the list
    	else if (nextItem == null) {
    		Node<E> newNode = new Node<E>(obj);
    		tail.next = newNode;
    		newNode.prev = tail;
    		tail = newNode;
    	}
    	
    	//adding to the middle of the list
    	else {
    		Node <E> newNode = new Node<E>(obj);
    		newNode.prev = nextItem.prev;
    		nextItem.prev.next = newNode;
    		newNode.next = nextItem;
    		nextItem.prev = newNode;
    				
    	}
    	
    	size++;
    	index++;
    	lastItemReturned = null;
    }
  }// end of inner class ListIter
}// end of class DoubleLinkedList
