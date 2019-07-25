package com.rijkv.simpleconsole;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Actions {
	
	// RUN/KILL ACTIONS
	
	public static void runExe(String path)
	{
		int indexOfLast = path.lastIndexOf("\\");
		String folder = path;
		if(indexOfLast >= 0) folder = path.substring(0, indexOfLast);
		Console.print("Running " + path + "...");
		try {
			Runtime.getRuntime().exec(path, null, new File(folder));
		} catch (IOException e) {
			Console.print("ERROR! failed to run " + path);
			Console.print("Make sure the path is correct");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public static void killProcess(String processName)
	{
		Console.print("Killing " + processName + "...");
		try {
			Runtime.getRuntime().exec("taskkill /IM " + processName);
		} catch (IOException e) {
			Console.print("Failed to kill the process " + processName);
			e.printStackTrace();
		}
	}
	
	public static void forceKillProcess(String processName)
	{
		Console.print("Force killing " + processName + "...");
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + processName);
		} catch (IOException e) {
			Console.print("Failed to kill the process " + processName);
			e.printStackTrace();
		}
	}
	
	
	// WEBPAGE ACTIONS
	
	public static void openURL(String url)
	{
		try {
			openWebpage(new URL(url));
		} catch (MalformedURLException e) {
			Console.print("Failed to open the URL: " + url);
			e.printStackTrace();
		}
	}

	public static boolean openWebpage(java.net.URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	// OTHER ACTIONS
	
	public static void hideWindow()
	{
		Console.hideFrame();
	}
	
	public static void runCMDCommand(String command)
	{
		Console.print("CMD > " + command);
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			Console.print("Failed execute command: " + command);
			e.printStackTrace();
		}
	}
}