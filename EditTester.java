/*
 *	Ikbel Amri
 *	Data Structures
 *	Photoshop Edit History
*/

import java.util.Scanner;

public class EditTester {

	public static void main (String [] args) { 
		int maxSize = 10; // set max size to 10
		EditManager myDeque = new EditManager(); // create an instance to create the first Deque to store the elements
		EditManager myDeque2 = new EditManager(); // create an instance for the redo deque

		// create scanner object to take user input
		Scanner kbd = new Scanner(System.in); 
	
		// will run infinity loop & take user input until 'quit' command
		boolean running=true; 
		System.out.println("*** user input");
		System.out.println("Write a command to be added to the deque.");
		System.out.println("You may also type 'undo' to undo the command, 'redo' to redo it, 'print' to print the contents of the deque, or 'quit' to exit the program.");
		
		while(running) {
			System.out.print("Type expression > ");	//text before input
			String inStr = kbd.nextLine();			//keyboard scanner input
			
			// in case the user wants to quit the program
			// if it returns 0, it means the string is identical to the given one
			if (inStr.compareTo("quit")==0) {
				running = false;	// this will terminate while loop
				System.out.println("Good-Bye!");	// output
			}
			else if (inStr.compareTo("undo")==0){ // if user selects to "undo"
				if (myDeque.isEmpty()) // if deque is empty
					System.out.println("Cannot undo last command. Deque is empty."); // output message
				else {
					System.out.println("result > undone " + myDeque.getLast() + " command"); // output message
					String element = myDeque.getLast().getElement(); // get the last element in the deque
					myDeque2.addLast(new DNode (element, null, null)); 
					// create a new node with that element and add it to the redo deque
					myDeque.removeLast(); // remove the last node from the first deque
					
					// System.out.println(myDeque.size); // size check
				}
			}
			else if (inStr.compareTo("redo")==0){ // if user selects to "redo"
				if (myDeque2.isEmpty()) // if redo deque is empty
					System.out.println("Cannot redo last command. There is nothing to redo."); // output message
				else{
					if (myDeque2.size>=maxSize)
						myDeque2.removeFirst(); 
						// remove the first node in the redo deque if the size exceeds the maximum allowed size;
					System.out.println("result > redone " + myDeque2.getLast() + " command"); // output message
					String element2 = myDeque2.getLast().getElement(); // get the last element in the deque
					myDeque.addLast(new DNode(element2, null, null));
					// create a new node with that element and add it to the first deque
					myDeque2.removeLast(); // remove the last node from the redo deque
					
					// System.out.println(myDeque.size); // size check
				}
			}
			else if (inStr.compareTo("print")==0){ // if user selects to "print"
				myDeque.toString();
			}
			else {
				if (myDeque.size>=maxSize)
					myDeque.removeFirst(); // remove the first node if the size exceeds the maximum allowed size;
				// just echo the input string
				System.out.println("Type edit command > " + inStr); // output showing the input selection
				myDeque.addLast(new DNode(inStr, null, null));
				// create a new node with the inputted string as an element
				// and add the node to the deque.
				
				// System.out.println(myDeque.size); // size check
			}
		}
	}
}