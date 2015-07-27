public class DLinkedList {
	
	private Node handle = new Node();

	public boolean isEmpty(){
		return handle.next == handle;
	}


	private void splice(Node a, Node b, Node t){
		
	}

	private class Node {
		private int e;
		public Node next;
		public Node prev;

		public Node(){
			this.next = this;
			this.prev = this;
		}
	}	
	
} 

