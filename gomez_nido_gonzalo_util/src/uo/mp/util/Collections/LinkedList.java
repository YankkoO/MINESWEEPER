package uo.mp.util.Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import uo.mp.util.check.ArgumentChecks;

public class LinkedList<T> implements List<T> {

	private class LinkedListIterator implements Iterator<T> {
		
		private Node<T> current = head;
		

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(! hasNext()) {
				throw new NoSuchElementException("No element found at this position");
			}
			T res = current.value;
			current = current.next;
			return res;
			
		}

	}

	private static class Node<T> {
		private T value;
		private Node<T> next;

		Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

	private Node<T> head;
	private int numberOfElements;

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return numberOfElements == 0;
	}

	@Override
	public boolean contains(Object o) {

		Node<T> pointer = head;
		while (pointer != null) {
			if (pointer.value.equals(o)) {
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	@Override
	public boolean add(T element) {
		ArgumentChecks.isNotNull(element);
		Node<T> current = new Node<T>(element, null);
		if (head == null) {
			head = current;
		} else {
			Node<T> pointer = head;
			while (pointer.next != null) {
				pointer = pointer.next;
			}
			pointer.next = current;
		}

		numberOfElements++;
		return true;
	}

	@Override
	public boolean remove(T o) {
	    if (o == null) {
	        return false;
	    }
	    
	    int index = indexOf(o);
	    if (index != -1) {
	        remove(index);
	        return true;
	    }
	    
	    return false;
	}

	@Override
	public void clear() {
		head = null;
		numberOfElements = 0;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= numberOfElements) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current.value;
	}

	@Override
	public T set(int index, T element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= numberOfElements) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> pointer = head;
		for (int i = 0; i < index; i++) {
			pointer = pointer.next;
		}

		T oldValue = pointer.value;
		pointer.value = element;
		return oldValue;
	}

	@Override
	public void add(int index, T element) {
		ArgumentChecks.isNotNull(element);
		if (index < 0 || index > numberOfElements) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			head = new Node<T>(element, head);
		} else {

			Node<T> previous = head;
			for (int i = 0; i < index - 1; i++) {
				previous = previous.next;
			}

			Node<T> newNode = new Node<T>(element, previous.next);

			previous.next = newNode;
		}
		numberOfElements++;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= numberOfElements) {
			throw new IndexOutOfBoundsException();
		}

		T removed;

		if (index == 0) {
			removed = head.value;
			head = head.next;
		} else {
			Node<T> previous = head;
			for (int i = 0; i < index - 1; i++) {
				previous = previous.next;
			}
			removed = previous.next.value;
			previous.next = previous.next.next;
		}

		numberOfElements--;
		return removed;
	}

	@Override
	public int indexOf(Object o) {
	    if (o == null) {
	        return -1;
	    }

	    Node<T> current = head;
	    for (int i = 0; i < numberOfElements; i++) {
	        if (current.value.equals(o)) {
	            return i;
	        }
	        current = current.next;
	    }
	    return -1;
	}

	@Override
	public String toString() {
		String res = "[";
		for (int i = 0; i < numberOfElements; i++) {
			res += get(i).toString();
			if (i < numberOfElements - 1) {
				res += ", ";
			}
		}
		res += "]";
		return res;
	}

	@Override
	public int hashCode() {
		int result = 1;
		for (int i = 0; i < size(); i++) {
			T obj = get(i);
			result = 31 * result + (obj == null ? 0 : obj.hashCode());
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof List)) {
			return false;
		}

		List<?> other = (List<?>) obj;

		if (this.size() != other.size()) {
			return false;
		}

		for (int i = 0; i < this.size(); i++) {
			Object o1 = this.get(i);
			Object o2 = other.get(i);

			if (o1 == null) {
				if (o2 != null) {
					return false;
				}
			} else if (!o1.equals(o2)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

}
