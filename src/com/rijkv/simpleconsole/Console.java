package com.rijkv.simpleconsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Console {
	
	// CONSTS
	public final static Color BG_COLOR = Color.decode("#111111");
	public final static Color TEXT_COLOR = Color.WHITE;
	public final static Color SELECTION_COLOR = TEXT_COLOR;
	public final static Color SELECTED_TEXT_COLOR = BG_COLOR;
	
	static JTextArea ta = new JTextArea();
	static JTextField inputField = new JTextField("");
	
	static String FONT_NAME = "Consolas";
	static JFrame frame = new JFrame();
	
	public Console() {
		JPanel middlePanel = new JPanel ();
		
		middlePanel.setLayout(new BorderLayout());
        middlePanel.setBackground(BG_COLOR);
        middlePanel.setForeground(TEXT_COLOR);
        
        Font font = new Font(FONT_NAME, Font.BOLD, 14);
        ta.setFont(font);
        ta.setForeground(TEXT_COLOR);
        ta.setBackground(BG_COLOR);
        
        Border emptyBorder = BorderFactory.createLineBorder(Color.black);
        
        ta.setText("");
        ta.setBorder(emptyBorder);
        ta.setSelectionColor(SELECTION_COLOR);
        ta.setSelectedTextColor(SELECTED_TEXT_COLOR);
        ta.setEditable(false);
        
        inputField.setFont(font);
        inputField.setForeground(TEXT_COLOR);
        inputField.setBackground(BG_COLOR);
        inputField.setCaretColor(TEXT_COLOR);
        inputField.setBorder(emptyBorder);
        JScrollPane scroll = new JScrollPane(ta);
        middlePanel.add(scroll);
        
        
        @SuppressWarnings("serial")
		Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                CommandSender.executeCommand(inputField.getText(), true);
                inputField.setText("");
            }
        };
        inputField.addActionListener(action);
        middlePanel.add(inputField, BorderLayout.SOUTH);
        
        middlePanel.setBorder(emptyBorder);
        
        frame.add(middlePanel);
        frame.setTitle("Simple Console");
        frame.setVisible(true);
        frame.setSize(800,600);
	}
	
	
	public static void print(String text)
	{
		ta.setText(ta.getText() + "\n" + text);
	}
	
	public static void clear()
	{
		ta.setText("");
	}
	
	public static void logCommand(String command)
	{
		if (ta.getText().equalsIgnoreCase(""))
    		ta.setText(">>> " + command);
    	else
    		ta.setText(ta.getText() + "\n" + ">>> " + command);
	}
	
	@SuppressWarnings("deprecation")
	public static void hideFrame()
	{
		frame.hide();
	}
}