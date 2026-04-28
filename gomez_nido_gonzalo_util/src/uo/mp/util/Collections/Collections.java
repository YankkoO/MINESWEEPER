package uo.mp.util.Collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Collections {

	/**
	 * 
	 * @param <T>
	 * @param list
	 */
	public static <T> void sort(List<T> list) {
		List<T> sorted = new ArrayList<>();
		
		for(T value : list) {
			int pos = findPositionForValue(sorted, value);
			sorted.add(pos, value);
		}
		copyFromTo(sorted, list);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param list
	 * @param comp
	 */
	public static <T> void sort(List<T> list, Comparator<T> comp) {
		List<T> sorted = new ArrayList<>();
		
		for(T value : list) {
			int pos = findPositionForValue(sorted, value, comp);
			sorted.add(pos, value);
		}
		copyFromTo(sorted, list);
	}

	/**
	 * 
	 * @param <T>
	 * @param sorted
	 * @param list
	 */
	private static <T> void copyFromTo(List<T> sorted, List<T> list) {
		list.clear();
		for(T i : sorted) {
			list.add(i);
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param sorted
	 * @param value
	 * @return
	 */
	private static <T> int findPositionForValue(List<T> sorted, T value) {
		for(int i = 0 ; i < sorted.size(); i++) {
			T elem = sorted.get(i);
			@SuppressWarnings("unchecked")
			Comparable<T> val = (Comparable<T>)value;
			
			if(val.compareTo(elem) < 0) {
				return i;
			}
		}
		return sorted.size();
	}
	
	private static <T> int findPositionForValue(List<T> sorted, T value, Comparator<T> comp) {
		for(int i = 0 ; i < sorted.size(); i++) {
			T elem = sorted.get(i);

			
			if(comp.compare(value, elem) < 0) {
				return i;
			}
		}
		return sorted.size();
	}
	
}
