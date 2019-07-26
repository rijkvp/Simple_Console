package com.rijkv.simpleconsole;

import java.io.*;

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
	
	public static void DisplayFileList()
	{
		File dir = new File("./actions/");
		File [] files = dir.listFiles(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".ini");
		    }
		});
		if (files != null)
		{
			for (File file : files) {
			    Console.print(file.getName());
			}
		}
		else
		{
			Console.print("No actions found.");
		}
	}
}
