/*
 * The CellDeque class is a Double ended queue of Cell objects.
 * We require this deque list to contain Cell objects: we add a cell when it is potentially part of the solution or remove it during the
   backtracking if it leads to a dead-end.
 * Adding and removing a cell can only be done from the first or the last position of the list. 
 */


public class CellDeque{
        CellNode head;
        CellNode tail;
	
	    //Constructor that creates an empty deque
	    public CellDeque(){
		    head = null;
	    }
	
		//Inserts into the front of the deque
		public void insertFirst(Cell p){
			CellNode toAdd = new CellNode(p);
			// if the deque is empty, then the cell inserting is the first and last element of the deque
			if(isEmpty()){
				head = toAdd;
				tail = toAdd;
		    }
			else{
				head.prev = toAdd;
				toAdd.next = head;
				head = toAdd;
			}
	    }
	
		//Inserts into the end of the deque
			public void insertLast(Cell p){
			CellNode toAdd = new CellNode(p);
			// if the deque is empty, then the cell inserting is the first and last element of the deque
			if (isEmpty()) {
				head = toAdd;
				tail = toAdd;
			} else {
				tail.next = toAdd;
				toAdd.prev = tail;
				tail = toAdd;
			}			
		}
	
		//Removes and returns the cell item at the front of the deque
		public Cell removeFirst(){
			//we cannot remove a cell from the empty deque
			if(isEmpty()){
				throw new DequeEmptyException("The deque is empty");
			}
			else{
				CellNode temp = head;
				//if there is more than one element in the deque
				if(head.next != null){
					head = head.next;
					head.prev = null;
				}
				//if there is only one element in the deque
				else{
					head = null;
				}
				//returns the removed cell
				return temp.item;
			}
			
		}
	
		//Removes the cell item at the end of the deque
		public Cell removeLast(){
			//if there is more than one element in the deque
			if(isEmpty()){
				throw new DequeEmptyException("The deque is empty");
			}
			else{
				CellNode temp = tail;
				//if there is more than one element in the deque
				if(tail.prev != null){
					tail = tail.prev;
					tail.next = null;
				}
				//if there is only one cell in the deque
				else{
					tail = null;
					head = null;
				}
				//returns the last cell of the deque
				return temp.item;
			}
		}
	
		//Accesses the first cell in the list without removing it
		public Cell first(){
			if(isEmpty()){
				throw new DequeEmptyException("The deque is empty");
			}
			else{
				//returns the first cell from the deque
				return head.item;
			}
		}
	
		//Accesses the last cell in the list without removing it
		public Cell last(){
			if(isEmpty()){
				throw new DequeEmptyException("The deque is empty");
			}
			else{
				//returns the last cell from the deque
				return tail.item;
			}
		}
	
		//Returns true if the deque is empty, false if it is not
		public boolean isEmpty(){
			//deque is empty only when head is null
			return head == null;
		}
	
		//Empties all the items from the deque
		public void makeEmpty(){
			//deque is empty only when head is null
			head = null;
		}
	
		//to print deque
		public String toString() {
			String details = "The contents of the list are: ";
			CellNode current = head;
			while(current!= null){
				details = details+current.item+"\n";
				current = current.next;
			}
			return details;
		}
	
		//Internal test harness for this class
		public static void main(String[] args){
			//creating an object 
			CellDeque list = new CellDeque();
			Cell a = new Cell(1,0);
			Cell b = new Cell(2,0);
			Cell c = new Cell(3,0);
			Cell d = new Cell(1,5);
			Cell e = new Cell(2,3);
			//adding various cells to the deque
			list.insertFirst(a);
			list.insertFirst(b);
			list.insertLast(c);
			list.insertLast(d);
			list.insertFirst(e);
			//checking the insertFirst() and insertLast() method
			System.out.println("The list should be {(2,3),(2,0),(1,0),(3,0),(1,5)}");
			System.out.println(list);
			//checking first() and last() method
			System.out.println("The first cell in the list is "+list.first());
			System.out.println("The last cell of the list is "+list.last());
			//checking the isEmpty() method
			if(list.isEmpty()){
				System.out.println("The list is empty");
			}
			else{
				System.out.println("The list is not empty");
				System.out.println(list);
			}
		    // removing first cell from the list	
			System.out.println("After removing first cell: "+list.removeFirst());
			System.out.println(list);
			// removing last cell from the list	
			System.out.println("After removing last cell: "+list.removeLast());
			System.out.println(list);
			//checking the makeEmpty() method
			list.makeEmpty();
			System.out.println("After removing all the elements:");
			System.out.println(list);
			if(list.isEmpty()){
				System.out.println("The list is empty");
			}
			else{
				System.out.println("The list is not empty");
				System.out.println(list);
			}
			list.insertLast(b);
			System.out.println("inserting a cell in the list "+list);
			//checking first() and last() method
			System.out.println("The first cell in the list is "+list.first());
			System.out.println("The last cell of the list is "+list.last());
			list.removeLast();	
			System.out.println(list);
			// removing last cell from the list	
			System.out.println("checking the exception handling: "+list.removeLast());
			System.out.println(list);
		}
	}