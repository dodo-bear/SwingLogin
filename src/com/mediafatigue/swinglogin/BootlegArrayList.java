package com.mediafatigue.swinglogin;

/**
 * A reimplementation of <code>ArrayList</code> functionality from the ground up, to allow dynamic array sizing.
 */
public class BootlegArrayList {

	Object[] arr;
	
	/**
	 * Sets up an empty, sizeless <code>Object[]</code>, which will be given a size later.
	 */
	public BootlegArrayList() {
		arr = new Object[0]; //Empty, because we resize it later.
	}
	
	/**
	 * Adds an item to the list, expanding the array if necessary.
	 * @param obj The item to be added.
	 */
	public void add(Object obj) {
		if(arr.length == 0 || arr[arr.length - 1] != null) { //Will adding something overflow the array?
			Object[] arrNew = new Object[arr.length + 1]; //If it will, make a bigger one and throw out the old one.
			copyTo(arr, arrNew);
			arr = arrNew;
		}
		arr[arr.length - 1] = obj; //Append the thing we're adding.
	}
	
	/**
	 * Removes the selected item from the list, decrementing the indices of higher items and shortening the overall array.
	 * @param obj The item to be removed.
	 */
	public void remove(Object obj) {
		int index = find(obj);
		if(index != -1) { //Make sure it's there at all.
			arr[index] = null;
			for(int i = 0; i < arr.length; i++) { //Decrement everything after it, leaving a duplicate element at the end...
				if(i > index) {
					arr[i-1] = arr[i];
				}
			}
			Object[] arrNew = new Object[arr.length - 1];
			copyTo(arr, arrNew); //...which is truncated away here.
			arr = arrNew;
		}
	}
	
	/**
	 * Determines the position of an item in the list.
	 * @param obj The item to be located.
	 * @return The index of the item in the list, -1 if it is not present.
	 */
	public int find(Object obj) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Copies the contents of one array into another, truncating any excess values.
	 * @param arr1 The array to take data from.
	 * @param arr2 The array to paste values into.
	 */
	public void copyTo(Object[] arr1, Object[] arr2) {
		for(int i = 0; i < arr1.length; i++) {
			if(i + 1 <= arr2.length) {
				arr2[i] = arr1[i];
			}
		}
	}
	
	/**
	 * Convenience method, converts an <code>Object</code> array of <code>String</code> arrays into a 2D <code>String</code> array.
	 * @param input The array to be converted.
	 * @return input, with its type changed to <code>String</code>.
	 */
	public static String[][] castToStringArray(Object[] input) {
        String[][] result = new String[input.length][];
        for (int i = 0; i < input.length; i++) { //Make an antirely new array and flow everything into it
            result[i] = new String[((Object[])input[i]).length];
            for (int j = 0; j < ((Object[])input[i]).length; j++) {
                result[i][j] = (String) ((Object[])input[i])[j];
            }
        }
        return result;
    }
	
	public Object[] toArray() {
		return arr;
	}
	
	public void setArray(Object[] n) {
		arr = n;
	}
}
