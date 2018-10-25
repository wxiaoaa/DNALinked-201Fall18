import java.util.*;

public class LinkStrand implements IDnaStrand {
	
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	
	private int myIndex = 0;
	private int myLocalIndex = 0;
	private Node myCurrent = myFirst;
	
	public LinkStrand() {
		this("");
	}
	
	@Override
	public void initialize(String source) {
		// TODO Auto-generated method stub
		Node First = new Node(source);
		myFirst = First;
		myLast = myFirst;
		mySize = First.info.length();
		myAppends = 0;
		
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}
	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}
	
	@Override
	public String toString() {
		StringBuilder myString = new StringBuilder(myFirst.info);
		if (myFirst.equals(myLast)) {
			return myString.toString();
		}
		Node current = myFirst.next;
		while (current != null) {
			myString.append(current.info);
			current = current.next;
		}	
		return myString.toString();
	}


	@Override
	public IDnaStrand getInstance(String source) {
		// TODO return LinkStrand object
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub	
		myAppends++;
		Node append = new Node(dna);
		myLast.next = append;
		myLast = myLast.next;
		mySize += dna.length();	
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		
		LinkStrand rev = new LinkStrand();
		rev.myFirst = this.myFirst;
		rev.myLast = this.myLast;
		rev.myAppends = this.myAppends;
		rev.mySize = this.mySize;
		
		StringBuilder ss;
		
		if (rev.myAppends == 0) {
			ss = new StringBuilder(rev.myFirst.info);
			ss.reverse();
			rev = new LinkStrand(ss.toString());
			return rev;
		} else {
			
			Node current = rev.myFirst;
			Node nFirst = current;
			
			while (current.next != null) {
				Node temp = new Node(current.next.info);
				temp.next = nFirst;		
				nFirst = temp;
				current.next = current.next.next;
			}
			
			rev.myFirst = nFirst;
			rev.myLast = current;
			//	
			Node loo = rev.myFirst;
			
			ss = new StringBuilder(loo.info);
			ss.reverse();
			Node lFirst = new Node(ss.toString());		
			rev.myFirst = lFirst;
			
			while (loo.next != null) {
				loo = loo.next;
				ss = new StringBuilder(loo.info);
				ss.reverse();
				Node temp = new Node(ss.toString());
				lFirst.next = temp;
				lFirst = lFirst.next;
			}			
			rev.myLast = lFirst;
			
			return rev;
		}		
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		
		if (index > mySize-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index < myIndex) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
        while (myIndex != index) {
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex >= myCurrent.info.length()) {
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
           return myCurrent.info.charAt(myLocalIndex);
	}
}