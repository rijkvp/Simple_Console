package com.rijkv.simpleconsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {

	public static void LoadFile(String fileName)
	{
		File file = new File("./actions/" + fileName + ".ini");  
		Console.print("Loading " + file.getAbsolutePath() + "...");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) {
		       CommandSender.executeCommand(line, false);
		    }
		} catch (FileNotFoundException e) {
			Console.print("File not found!");
			return;
		} catch (IOException e) {
			System.out.println("IO EXCEPTION!!");
			e.printStackTrace();
			return;
		}
	}
}
