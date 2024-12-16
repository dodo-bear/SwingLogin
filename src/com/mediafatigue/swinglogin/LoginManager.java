package com.mediafatigue.swinglogin;

import java.io.File;
import java.io.IOException;

/**
 * 
 */
public class LoginManager {
	
	private static BootlegArrayList data;
	private static String fileName = "data.txt";

	/**
	 * Sets up a basic testing environment with full functionality.
	 * @param args
	 */
	public static void main(String[] args) {
		data = new BootlegArrayList();
		new UIFrame(50, 50, "Login");

		File f = new File(fileName);
		if(f.isFile()) {
			try {
				data.setArray(DatabaseManager.readFromFile(fileName));
				System.out.println("Read data to internal");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			data.setArray(new String[0][2]);
		}
	}
	
	/**
	 * @return
	 */
	public static String[][] getData(){
		return BootlegArrayList.castToStringArray(data.toArray());
	}
	/**
	 * Returns data as a <code>BootlegArrayList</code> to retain functionality.
	 * @return 
	 */
	public static BootlegArrayList getArrList() {
		return data;
	}
	
	/**
	 * Sets the <code>BootlegArrayList</code> to an existing array.
	 * @param nData The new <code>String[][]</code> to set the value of <code>data</code> to.
	 */
	public static void setData(String[][] nData) {
		data.setArray(nData);
	}
	
	/**
	 * @return
	 */
	public static String getFileName() {
		return fileName;
	}
}
