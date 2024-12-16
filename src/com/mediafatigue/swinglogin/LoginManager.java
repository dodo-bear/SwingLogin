package com.mediafatigue.swinglogin;

import java.io.File;
import java.io.IOException;

public class LoginManager {
	
	private static BootlegArrayList data;
	private static String fileName = "data.txt";

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
	
	public static String[][] getData(){
		return BootlegArrayList.castToStringArray(data.toArray());
	}
	public static BootlegArrayList getArrList() {
		return data;
	}
	
	public static void setData(String[][] nData) {
		data.setArray(nData);
	}
	
	public static String getFileName() {
		return fileName;
	}
}
