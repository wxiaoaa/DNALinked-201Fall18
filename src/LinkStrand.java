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
		while (current != myLast) {
			myString.append(current.info);
			current = current.next;
		}	
		myString.append(myLast.info);
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
		StringBuilder ss;
		
		if (this.myAppends==0) {
			ss = new StringBuilder(myFirst.info);
			ss.reverse();
			rev = new LinkStrand(ss.toString());
			return rev;
		}
		
		Node current = myFirst;
		Node nFirst = current;
		
		while (current.next.next != null) {
			Node temp = new Node(current.next.info);
			temp.next = nFirst;		
			nFirst = temp;
			current.next = current.next.next;
		}
		
		current.next = null;
		Node first = new Node(myLast.info);
		first.next = nFirst;
		nFirst = first;
		
		while (first != null) {
			ss = new StringBuilder(first.info);
			ss.reverse();
			first.info = ss.toString();
			first = first.next;			
		}
		
		rev.myFirst = nFirst;
		rev.myLast = first;
		rev.myAppends = this.myAppends;
		rev.mySize = this.mySize;
		
		return rev;
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