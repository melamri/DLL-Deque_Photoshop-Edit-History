/*
 *	Ikbel Amri
 *	Data Structures
 *	Photoshop Edit History
*/

public  class EditManager {

	protected int size;	 				// number of elements
	protected DNode header, trailer;	// sentinels

	/** Constructor that creates an empty Deque */
	public EditManager() {
		size = 0; 
		header =  new DNode("", null,  null);		// create header
		trailer =  new DNode("", null,  null);  // create trailer
		header.setNext(trailer);	
		trailer.setPrev(header); 				// make header and trailer point to each other
	}

	/** Returns the number of elements in the deque */
	public int size() { return size; }

	/** Returns whether the deque is empty */
	public boolean isEmpty() { 
	
		return (size == 0); 
	}
	
	/** Returns the first node	in the deque */
	public DNode getFirst(){
		if (isEmpty()){
			System.out.println("Deque is Empty.");
			return null;
		}
		return header.getNext();
	}

	/** Returns the last node of the deque */
	public DNode getLast(){
		if (isEmpty()){
			System.out.println("Deque is  empty");
			return null;
		}
		return trailer.getPrev();
	}

	/** Returns the node before the given node v. An error occurs if v
	* is the header */
	public DNode getPrev(DNode v) {
		if (v == header) {
			System.out.println("Cannot move  back	 past  the	header of  the	deque");
			return null;
		}
		return v.getPrev();
	}

	/** Returns the node after the given node v. An error occurs if v
	* is the trailer */
	public DNode getNext(DNode v){
		if (v == trailer){
			System.out.println("Cannot move  forward past	 the  trailer of  the  deque");
			return null;
		}
		return v.getNext();
	}

	// adds a Node v to the end of the deque
	public void addFirst(DNode v) {
		if (isEmpty()) {
			header.setNext(v);
			v.setPrev(header);
			v.setNext(trailer);
			trailer.setPrev(v);
			size++;
		}
		else {
			v.setNext(header.getNext());
			header.getNext().setPrev(v);
			header.setNext(v);
			v.setPrev(header);
			size++;
		}

	}
	
	// adds a Node v to the beginning of the deque
	public void addLast(DNode v) {
		if (isEmpty()) {
			trailer.setPrev(v);
			v.setNext(trailer);
			v.setPrev(header);
			header.setPrev(v);
			size++;
		}
		else {
			v.setPrev(trailer.getPrev());
			trailer.getPrev().setNext(v);
			trailer.setPrev(v);
			v.setNext(trailer);
			size++;
		}
	}

	// removes the Node at the beginning of deque
	public String removeLast() {
		if (isEmpty()) {
			System.out.println("Deque is empty.");
			return null;
		}
		else {
			DNode tempLast=trailer.getPrev();
			String o = tempLast.getElement(); 
			DNode tempSecondtoLast=tempLast.getPrev();
			trailer.setPrev(tempSecondtoLast);
			tempSecondtoLast.setNext(trailer);
			size--;
			return o;
		}
	}
	
	// removes the Node at the end of the deque
	public String removeFirst() {
		if (isEmpty()) {
			System.out.println("Deque is empty.");
			return null;
		}
		else {
			DNode tempFirst=header.getNext();
			String o = tempFirst.getElement();
			if (size==1){
				tempFirst.setNext(null);
				tempFirst.setPrev(null);
				header.setNext(trailer);	
				trailer.setPrev(header);
			}
			else {
				DNode tempSecondtoFirst= tempFirst.getNext();
				tempSecondtoFirst.setPrev(header);
				header.setNext(tempSecondtoFirst);
				tempFirst.setNext(null);
				tempFirst.setPrev(null);
			}
			size--;
			return o;
		}
	}
	
	// this method returns string including values of all elements in the deque
	public String toString() {
		String s = "[";
		DNode v = header.getNext();
		while (v!=trailer){
			s += v.getElement();
			v = v.getNext();
			if (v != trailer){
				s += ",";
			}
		}
		s += "]";
		System.out.println(s);
		return s;
	}
	
}