import javax.swing.*;
import java.util.*;
import java.io.*;

public class LoginDev
{
	private static String item1;
	private Static String loggedInAdminID; //Can update to int if needed
	public static void main (String [] args)
	{ 	
		
		String username = JOptionPane.showInputDialog(null, "Enter username");
		String password = JOptionPane.showInputDialog(null, "Enter password"); //Will work on method for hidden password input in swing
		boolean isLoggedIn = loginMethod(username, password);
		if (isLoggedIn == true)
		{
			//Pauls Menu methods 
		}
	}
	
	
	public static boolean loginMethod(String username, String password)
	{
		String loginMessage = "";
		int maxLoginAttempts = 2; 
		boolean loggedInStatus = false;
		boolean foundUsername = readFile("Administrators.txt", username, 1);
		boolean foundPassword = readFile("Administrators.txt", password, 2);
		
		while (maxLoginAttempts > 0)
		{
			if (foundUsername == true && foundPassword == true)
			{
				loggedInAdminID = item1;
				loggedInStatus = true;
				loginMessage = "Successfully logged in as " + username;
				JOptionPane.showMessageDialog(null, loginMessage);
				break;
			}
			else
			{
				loginMessage = "Incorrect login details\n" + maxLoginAttempts + " attempt(s) remaining";
				JOptionPane.showMessageDialog(null, loginMessage);
				maxLoginAttempts--;
				username = JOptionPane.showInputDialog(null, "Enter username");
				password = JOptionPane.showInputDialog(null, "Enter password");
				if (maxLoginAttempts == 0)
				{
					loginMessage = "Invalid login details\nNo login attempts remaining";
					JOptionPane.showMessageDialog(null, loginMessage);
				}
			}
		}
	return loggedInStatus;
	}
	
	public static Boolean readFile(String textFile, String searchedItem, int itemPositionNo)
    	{
		boolean found = false;
		try
		 {
			item1 = "";
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText=in.nextLine();
		        String[] split = fileText.split(",");
				if (split[itemPositionNo].equals(searchedItem))
				{
					found = true;
					item1 = split[0]; // Admin#, League#, fixture#.
					break;
				}
			}
			in.close();
			reader.close();	
		 }
		 catch (Exception e)
		 {}
		
		return found;
	}
	
	public static void editResults()
	{
		int choice = 0;
		String [] yesNo = {"yes", "no"};
		boolean resultExists = false;
		String matchNumberChoice ="";
		String leagueNumber = JOptionPane.showInputDialog(null, "Enter league number to edit");
		resultsFileName = leagueNumber +"_Results.txt";
		matchNumberChoice = JOptionPane.showInputDialog(null, "Enter fixture number to edit");
		resultExists = readFile(resultsFileName, matchNumberChoice, 0);
		if (resultExists == true)
		{
				
			choice = JOptionPane.showConfirmDialog(null, "Already entered result for this fixture, Do you want to edit the result?");
				
			if (choice == 0) //If yes
			{
			//will put method to remove existing fixture result and overwrite it
			String homeScore = JOptionPane.showInputDialog(null, "Enter home score");
			String awayScore = JOptionPane.showInputDialog(null, "Enter away score");
			String output = matchNumberChoice + "," + homeScore + "," + awayScore;
			writeFile(output,resultsFileName);
			editResults();	
			}
			
		}
		else 
		{
			String homeScore = JOptionPane.showInputDialog(null, "Enter home score");
			String awayScore = JOptionPane.showInputDialog(null, "Enter away score");
			String output = matchNumberChoice + "," + homeScore + "," + awayScore;
			writeFile(output,resultsFileName);
			editResults();
		}
		
	}
/* TESTING IGNORE	
	public static void displayResults()
	{
		String temp = "";
		String fixtureNumber, homeScore, awayScore;
		FileReader test = newFileReader("1_Results.txt");
		Scanner testing = new Scanner(testing);
		while (testing.hasNext())
		{
			int firstComma = temp.indexOf(",");
			int secondComma = temp.indexOf(",", firstComma+1);
			temp = testing.nextLine();
			
			fixtureNumber = temp.substring(0, firstComma);
			homeScore = temp.substring(firstComma+1, secondComma);
			awayScore = temp.substring(secondComma+1);
			
			System.out.println("Fixture: " + fixtureNumber + "\tHome Score: " + homeScore + "\tAway Score: " + awayScore);
		}
	}
*/	
}
