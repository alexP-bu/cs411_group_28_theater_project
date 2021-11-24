package theater_project_code;

//doubly linked list implementation
public class linked_list {
	Node head;
	
	//node construction
	static class Node{
		String name;
		Node next;
		Node prev;
		
		Node(String name){
			this.name = name;
			this.next = null;
			this.prev = null;
		}
	}
	//insert new node using data to end of linked list
	public linked_list insert(linked_list list, String name) {
		Node new_node = new Node(name);
		
		if(list.head == null) {
			list.head = new_node;
		} else {
			Node last = list.head;
			while(last.next != null) {
				last = last.next;
			}
			last.next = new_node;
			new_node.prev = last;
		}
		return list;
	}
	//insert new node using node to end of linked list
	public linked_list insert(linked_list list, Node n) {
		if(list.head == null) {
			list.head = n;
			n.prev = null;
		} else {
			Node last = list.head;
			while(last.next != null) {
				last = last.next;
			}
			last.next = n;
			n.prev = last;
		}
		return list;
	}
	
	public linked_list delete(linked_list list, String name) {
		Node currentNode = list.head;
		//find node with matching name
		while(!currentNode.name.equals(name)) {
			currentNode = currentNode.next;
		}
		//if node found, delete it
		if(currentNode != null) {
			if(currentNode.next == null) {
				//node is at end of list, just unlink it
				currentNode.prev.next = null;
				currentNode.prev = null;
			}else if (currentNode.prev == null) {
				//node is at head of list, unlink and set new head
				list.head = currentNode.next;
				list.head.prev = null;
				currentNode.next = null;
			}else {
				//node is in middle of list, just delete it
				currentNode.prev.next = currentNode.next;
				currentNode.next.prev = currentNode.prev; 
				currentNode.next = null;
				currentNode.prev = null;
			}
		}
		return list;
	}
	
	//print linked list contents to console
	public void print(linked_list list) {
		Node currentNode = list.head;
		System.out.print("Linked List: [");
		while(currentNode != null) {
			System.out.print(currentNode.name + ";");
		}
		System.out.print("]\n");
	}
	
	public boolean contains(linked_list list, String keyword) {
		Node currentNode = list.head;
		while(currentNode != null) {
			if (currentNode.name.equals(keyword)) {
				return true;
			}
		}
		return false;
	}
	
	//test harness code
	public static void main(String[] args) {
		//linked_list list = new linked_list();
		
	}
}
