package com.rijkv.simpleconsole;

import java.util.Timer;
import java.util.TimerTask;

public class CommandSender {

	public static void executeCommand(String input, boolean logCommand)
	{
		if (input.equalsIgnoreCase(""))
			return;
		
		if (logCommand)
		{
			Console.logCommand(input);
		}
		String secondInput = removeFirstArg(input);
		input = removeChar(input, '\n');
		String[] args = input.split(" ");
		
		for (int i = 0; i < args.length; i++)
		{
			args[i] = removeChar(args[i], '\n');
		}
		
		
		
		// THE COMMANDS
		if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("cls") )
		{
			Console.clear();
		}
		else if (args[0].equalsIgnoreCase("print"))
		{
			Console.print(secondInput);
		} else if (args[0].equalsIgnoreCase("exit") || args[0].equalsIgnoreCase("quit"))
		{
			Console.print("Bye! Bye!");
			Runtime.getRuntime().exit(0);
		} else if (args[0].equalsIgnoreCase("help"))
		{
			Console.print("[HELP] List of commands:");
			Console.print("clear/cls 	-> clear the console");
			Console.print("print 		-> print a message in the console");
			Console.print("run 		-> run a program");
			Console.print("openurl 	-> open a url in the default web browser");
			Console.print("kill 		-> kill a process");
			Console.print("forcekill/fkill -> forcekill a process");
			Console.print("cmd 		-> run command in windows command promt");
			Console.print("action 		-> loads a script file with .ini extension in the /actions folder");
			Console.print("actionloop 	-> loops an action for a specific amount of time & delay (ms)");
		} 
		else if (args[0].equalsIgnoreCase("run"))
		{
			if (args.length > 1)
			{
				Actions.runExe(secondInput);
			}	
			else 
			{
				Console.print("What am I suposed to do with running nothing??");
			}
		}
		else if (args[0].equalsIgnoreCase("openurl"))
		{
			if (args.length > 1)
			{
				Actions.openURL(args[1]);
			}	
			else 
			{
				Console.print("No url provided!");
			}
		}
		else if (args[0].equalsIgnoreCase("kill"))
		{
			if (args.length > 1)
			{
				Actions.killProcess(secondInput);
			}	
			else 
			{
				Console.print("Killing nothing...");
				Console.print("So nothing happens");
			}
		}
		else if (args[0].equalsIgnoreCase("fkill") || args[0].equalsIgnoreCase("forcekill"))
		{
			if (args.length > 1)
			{
				Actions.forceKillProcess(secondInput);
			}	
			else 
			{
				Console.print("Killing nothing...");
				Console.print("So nothing happens");
			}
		}
		else if (args[0].equalsIgnoreCase("cmd"))
		{
			if (args.length > 1)
			{
				Actions.runCMDCommand(secondInput);
			}	
			else
			{
				Actions.runCMDCommand("cmd");
			}
		}
		else if (args[0].equalsIgnoreCase("actionloop")) {
			if (args.length != 4)
			{
				Console.print("Make sure that you have exactly 4 arguments!");
			}
			else 
			{
				loopAction(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			}
		}
		else if (args[0].equalsIgnoreCase("action")) {
			if (args.length == 2)
			{
				if (args[1].equalsIgnoreCase("list"))
				{
					FileLoader.DisplayFileList();
				}
				else
				{
					FileLoader.LoadFile(args[1]);
				}
			} 
			else
			{
				Console.print("INVALID SYNTAX!");
			}
		}
		else if (args[0].equalsIgnoreCase("hide")) {
			Actions.hideWindow();
		}
		else {
			Console.print("Unknown command! Check your syntax!");
		}
	}
	
	public static void loopAction(String actionName, int delay, int count) 
	{
		
        final Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
        	int counter = 0;
            public void run() {
            	counter++;
            	if (counter > count)
            	{
            		cancel();
                    Console.print("Finished actionloop for " + actionName + "!");
            		return;
            	}
            	
            	executeCommand("action " + actionName, false);
            }
        }, 0, delay);
	}
	
	public static String removeChar(String s, char c) {
		  StringBuffer r = new StringBuffer( s.length() );
		  r.setLength( s.length() );
		  int current = 0;
		  for (int i = 0; i < s.length(); i ++) {
		     char cur = s.charAt(i);
		     if (cur != c) r.setCharAt( current++, cur );
		  }
		  return r.toString();
	}

	private static String removeFirstArg(String input)
	{
		String[] arr = input.split(" ", 2);
		if (arr.length >= 2)
			return arr[1];
		else 
			return null;
	}
}	