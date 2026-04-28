package uo.mp.util.Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import uo.mp.util.check.ArgumentChecks;

public class ArrayList<T> implements List<T> {
	
	private class ArrayListIterator implements Iterator<T> {

		private int index = -1;
		
		@Override
		public boolean hasNext() {
			return index + 1 < size();
		}

		@Override
		public T next() {
			if(! hasNext() ) {
				throw new NoSuchElementException("No element at that position");
			}
			return (T) get(++index);
		}

	}

	private final static int DEFAULT_CAPACITY = 20;
	
	private T[] elements;
	private int numberOfElements;

	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		ArgumentChecks.isTrue( capacity >= 0 );
		this.elements = (T[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return numberOfElements == 0;
	}

	@Override
	public boolean contains(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    
	    for (int i = 0; i < numberOfElements; i++) {
	        if (elements[i].equals(obj)) {
	            return true;
	        }
	    }
	    
	    return false;
	}

	@Override
	public boolean add(T element) {
		ArgumentChecks.isNotNull(element);
		elements[numberOfElements] = element;
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
	    for (int i = 0; i < numberOfElements; i++) {
	        elements[i] = null;
	    }
	    numberOfElements = 0;
	}

	@Override
	public T get(int index) {
	    if (index < 0 || index >= numberOfElements) {
	        throw new IndexOutOfBoundsException();
	    }
	    return elements[index];
	}

	@Override
	public T set(int index, T element) {
	    if (element == null) {
	        throw new NullPointerException();
	    }
	    if (index < 0 || index >= numberOfElements) {
	        throw new IndexOutOfBoundsException();
	    }
	    
	    T oldValue = elements[index];
	    elements[index] = element;
	    return oldValue;
	}

	@Override
	public void add(int index, T element) {
		ArgumentChecks.isNotNull(element);
		if(index < 0 || index > numberOfElements) {
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = numberOfElements; i > index; i--) {
			elements[i] = elements [i - 1];
		}
		
		elements[index] = element;
		numberOfElements++;
		
	}

	@Override
	public T remove(int index) {
	    if (index < 0 || index >= numberOfElements) {
	        throw new IndexOutOfBoundsException();
	    }

	    T removed = elements[index];
	    for (int i = index; i < numberOfElements - 1; i++) {
	        elements[i] = elements[i + 1];
	    }

	    elements[numberOfElements - 1] = null;
	    numberOfElements--;

	    return removed;
	}

	@Override
	public int indexOf(Object o) {
	    if (o == null) {
	        return -1;
	    }

	    for (int i = 0; i < numberOfElements; i++) {
	        if (elements[i].equals(o)) {
	            return i;
	        }
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
		return new ArrayListIterator();
	}
	
	
	
	
}