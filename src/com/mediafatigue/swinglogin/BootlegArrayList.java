package com.mediafatigue.swinglogin;

public class BootlegArrayList {

	Object[] arr;
	
	public BootlegArrayList() {
		arr = new Object[0];
	}
	
	public void add(Object obj) {
		if(arr.length == 0 || arr[arr.length - 1] != null) {
			Object[] arrNew = new Object[arr.length + 1];
			copyTo(arr, arrNew);
			arr = arrNew;
		}
		arr[arr.length - 1] = obj;
	}
	
	public void remove(Object obj) {
		int index = find(obj);
		if(index != -1) {
			arr[index] = null;
			for(int i = 0; i < arr.length; i++) {
				if(i > index) {
					arr[i-1] = arr[i];
				}
			}
			Object[] arrNew = new Object[arr.length - 1];
			copyTo(arr, arrNew);
			arr = arrNew;
		}
	}
	
	public int find(Object obj) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}
	
	public void copyTo(Object[] arr1, Object[] arr2) {
		for(int i = 0; i < arr1.length; i++) {
			if(i + 1 <= arr2.length) {
				arr2[i] = arr1[i];
			}
		}
	}
	
	public static String[][] castToStringArray(Object[] input) {
        String[][] result = new String[input.length][];
        for (int i = 0; i < input.length; i++) {
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
