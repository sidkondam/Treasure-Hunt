import java.util.*;

public class Kondam_RevertString {
	private String initial; 
	private Stack<String>theStack = new Stack<String>();

	public Kondam_RevertString(String i) {
		initial = i; 
		theStack.push(initial);
	}

	public void insert(int pos,String val) {
		initial = initial.substring(0,pos) + val + initial.substring(pos);
		theStack.push(initial);	
	}

	public void remove(int pos, int length) {
		initial = initial.substring(0,pos) + initial.substring((length+pos));
		theStack.push(initial);
	}

	public void undo() {

		if(theStack.isEmpty()) {
			throw new IllegalStateException();
		}

		theStack.pop();
	}


	public String toString() {
		return theStack.peek();
	}
}