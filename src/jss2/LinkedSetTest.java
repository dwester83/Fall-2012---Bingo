/* Author: Daniel Wester
 * 
 * Date: 11/15/2012
 * 
 * Course: Comp 2247-01, Fall 2012
 * 
 * Assignment: PGM3
 * 
 * Description: This program will test the LinkedStack<T>, it is currently
 * built to just test the BingoBall class, however it could easily be setup
 * to work with strings, or any other class. It has been tested with my Test
 * Plan, to make sure everything is worked as intended. I also included
 * the equals and compareTo methods from BingoBall so it could be tested
 * as well.
 */

package jss2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LinkedSetTest extends JFrame {
	
	final int EXIT_LOOP = -1;
	
	LinkedSet<BingoBall> set1 = new LinkedSet<BingoBall>();
	LinkedSet<BingoBall> set2 = new LinkedSet<BingoBall>();
	
	JTextArea descriptionJTA, set1JTA, set2JTA, resultsJTA;
	JLabel set1JL, set2JL, resultsJL;
	JButton set1JB, set2JB, intersectJB, differenceJB, compareJB, equalsJB, resetJB, exitJB;
	JPanel northPanel, northPanelBottom, northPanelBottomB, centerPanel, southPanel, southPanelTop, southPanelBottom;
	
	public LinkedSetTest() {
		
		//Description Fields
		descriptionJTA = new JTextArea(" This program will test out the Linked Set class," +
		" specifically the BingoBall class.\n In addition it can also test the equals() and compareTo() from BingoBall.");
		descriptionJTA.setLineWrap(true);
		descriptionJTA.setEditable(false);
		descriptionJTA.setBackground(null);
		set1JL = new JLabel("Set 1");
		set2JL = new JLabel("Set 2");
		resultsJL = new JLabel("Results");
		
		//Sets
		set1JTA = new JTextArea();
		set1JTA.setEditable(false);
		set1JTA.setFont(new Font("Arial",Font.PLAIN,20));
		set1JTA.setBackground(Color.WHITE);
		set1JTA.setBorder(BorderFactory.createLineBorder(Color.black));
		
		set2JTA = new JTextArea();
		set2JTA.setEditable(false);
		set2JTA.setFont(new Font("Arial",Font.PLAIN,20));
		set2JTA.setBackground(Color.WHITE);
		set2JTA.setBorder(BorderFactory.createLineBorder(Color.black));
		
		resultsJTA = new JTextArea();
		resultsJTA.setEditable(false);
		resultsJTA.setFont(new Font("Arial",Font.PLAIN,20));
		resultsJTA.setBackground(Color.WHITE);
		resultsJTA.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//The buttons
		set1JB = new JButton ("Set 1");
		set1JB.addActionListener(new Set1ButtonHandler());
		set2JB = new JButton ("Set 2");
		set2JB.addActionListener(new Set2ButtonHandler());
		intersectJB = new JButton ("Intersect");
		intersectJB.addActionListener(new IntersectButtonHandler());
		differenceJB = new JButton ("Difference");
		differenceJB.addActionListener(new DifferenceButtonHandler());
		compareJB = new JButton ("CompareTo");
		compareJB.addActionListener(new CompareButtonHandler());
		equalsJB = new JButton ("Equals");
		equalsJB.addActionListener(new EqualsButtonHandler());
		resetJB = new JButton ("Reset");
		resetJB.addActionListener(new ResetButtonHandler());
		exitJB = new JButton ("Exit");
		exitJB.addActionListener(new ExitButtonHandler());
		
		//Building the GUI
		northPanel = new JPanel(new GridLayout (2,1));
		northPanelBottom = new JPanel(new GridLayout (1,3));
		centerPanel = new JPanel(new GridLayout(1,1));
		southPanel = new JPanel(new GridLayout (2,1));
		southPanelTop = new JPanel(new GridLayout (1,2));
		southPanelBottom = new JPanel(new GridLayout (2,3));
		
		northPanel.add(descriptionJTA);
		northPanelBottom.add(set1JL);
		northPanelBottom.add(set2JL);
		northPanelBottom.add(resultsJL);
		northPanel.add(northPanelBottom);
		centerPanel.add(set1JTA);
		centerPanel.add(set2JTA);
		centerPanel.add(resultsJTA);
		southPanelTop.add(set1JB);
		southPanelTop.add(set2JB);
		southPanelBottom.add(intersectJB);
		southPanelBottom.add(differenceJB);
		southPanelBottom.add(compareJB);
		southPanelBottom.add(equalsJB);
		southPanelBottom.add(resetJB);
		southPanelBottom.add(exitJB);
		southPanel.add(southPanelTop);
		southPanel.add(southPanelBottom);
		
		//Display the GUI
		setLayout(new BorderLayout());
		setSize(600,450);
		setTitle("LinkedSet<T> Test Program");
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
	
	//Set 1 Button Handler.
	private class Set1ButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			String input = "";
			
			do {
			try {
				input = JOptionPane.showInputDialog("This is for Set 1.    " +
						"Please press cancel or type " + EXIT_LOOP + " to exit.\n\n" + 
						"Enter a BingoBall Number between 1 to 75.");
				if (Integer.parseInt(input) == EXIT_LOOP)
					return;
				BingoBall ball = new BingoBall(Integer.parseInt(input));
				set1.add(ball);
			} catch (NumberFormatException nfe) {
			}
			set1JTA.setText(set1.toString());
			} while (!(input == null));
		}
	}
	
	//Set 2 Button Handler.
	private class Set2ButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			String input = "";
			
			do {
			try {
				input = JOptionPane.showInputDialog("This is for Set 2.    " +
						"Please press cancel or type " + EXIT_LOOP + " to exit.\n\n" + 
						"Enter a BingoBall Number between 1 to 75.");
				if (Integer.parseInt(input) == EXIT_LOOP)
					return;
				BingoBall ball = new BingoBall(Integer.parseInt(input));
				set2.add(ball);
			} catch (NumberFormatException nfe) {
			}
			set2JTA.setText(set2.toString());
			} while (!(input == null));
		}
	}
	
	//Intersect two different sets.
	private class IntersectButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			LinkedSet<BingoBall> set3 = set1.intersection(set2);
			resultsJTA.setText(set3.toString());
		}
	}
	
	//Intersect two different sets.
	private class DifferenceButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			LinkedSet<BingoBall> set3 = set1.difference(set2);
			resultsJTA.setText(set3.toString());
		}
	}
	
	//Compare two different Objects.
	private class CompareButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			String input = "";
			int number;
			
			
			do {
			try {
				input = JOptionPane.showInputDialog("This to compare two different BingoBalls.    " +
						"Please press cancel or type " + EXIT_LOOP + " to exit.\n\n" + 
						"Enter a BingoBall Number between 1 to 75 for ball 1.");
				if (Integer.parseInt(input) == EXIT_LOOP)
					return;
				BingoBall ball1 = new BingoBall(Integer.parseInt(input));
				input = JOptionPane.showInputDialog("This to compare two different BingoBalls.    " +
						"Please press cancel or type " + EXIT_LOOP + " to exit.\n\n" + 
						"Enter a BingoBall Number between 1 to 75 for ball 2.");
				if (Integer.parseInt(input) == EXIT_LOOP)
					return;
				BingoBall ball2 = new BingoBall(Integer.parseInt(input));
				
				number = ball1.compareTo(ball2);
				if (number < 0)
					JOptionPane.showMessageDialog(null,"Ball 1: " + ball1 +
							" is before Ball 2: " + ball2, "Compare Output", JOptionPane.PLAIN_MESSAGE);
				if (number > 0)
					JOptionPane.showMessageDialog(null,"Ball 1: " + ball1 +
							" is after Ball 2: " + ball2, "Compare Output", JOptionPane.PLAIN_MESSAGE);
				if (number == 0)
					JOptionPane.showMessageDialog(null,"Ball 1: " + ball1 +
							" is equal to Ball 2: " + ball2, "Compare Output", JOptionPane.PLAIN_MESSAGE);
				
			} catch (NumberFormatException nfe) {
			}
			} while (!(input == null));
		}
	}
	
	//Compare two different Objects.
	private class EqualsButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			String input = "";
			boolean result;
			
			do {
			try {
				input = JOptionPane.showInputDialog("This will see if two different BingoBalls are equal.    " +
						"Please press cancel or type " + EXIT_LOOP + " to exit.\n\n" + 
						"Enter a BingoBall Number between 1 to 75 for ball 1.");
				if (Integer.parseInt(input) == EXIT_LOOP)
					return;
				BingoBall ball1 = new BingoBall(Integer.parseInt(input));
				input = JOptionPane.showInputDialog("This will see if two different BingoBalls are equal.    " +
						"Please press cancel or type " + EXIT_LOOP + " to exit.\n\n" + 
						"Enter a BingoBall Number between 1 to 75 for ball 2.");
				if (Integer.parseInt(input) == EXIT_LOOP)
					return;
				BingoBall ball2 = new BingoBall(Integer.parseInt(input));
				
				result = ball1.equals(ball2);
				if (result)
					JOptionPane.showMessageDialog(null,"Ball 1: " + ball1 +
							" is equal to Ball 2: " + ball2, "Compare Output", JOptionPane.PLAIN_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,"Ball 1: " + ball1 +
							" is not equal to Ball 2: " + ball2, "Compare Output", JOptionPane.PLAIN_MESSAGE);
				
			} catch (NumberFormatException nfe) {
			}
			} while (!(input == null));
		}
	}
	
	//Reset Button
	private class ResetButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			set1 = new LinkedSet<BingoBall>();
			set2 = new LinkedSet<BingoBall>();
			set1JTA.setText(set1.toString());
			set2JTA.setText(set2.toString());
		}
	}
	
	//Exit Button
	private class ExitButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}
	
	//Main
	public static void main(String[] args){
		new LinkedSetTest();		
	}
}