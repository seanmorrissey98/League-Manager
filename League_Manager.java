import javax.swing.*;
import java.util.*;
import java.io.*;

public class LoginDev
{
	final static String adminFile = "Administrators.txt";
	private static String item1;
	private static String loggedInAdminID; //Can update to int if needed
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
		int maxLoginAttempts = 3;
		String loginMethod = "";
		boolean loggedInStatus = false;
		boolean foundUserDetails = false;
		
		for (int i=maxLoginAttempts;i>0;i--)
		{
			foundUserDetails = readFile(adminFile, username, password, 1, 2);
			if (foundUserDetails == true)
			{
				loggedInAdminID = item1;
				loggedInStatus = true;
				JOptionPane.showMessageDialog(null, "Successfully logged in as " + username);
				break;
			}
			else
			{
				if (maxLoginAttempts == 1)
				{
					JOptionPane.showMessageDialog(null, "Incorrect login details\nNo attempt remaining");
					break;
				}	
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect login details\n" + (maxLoginAttempts-1) + " attempt(s) remaining");
					maxLoginAttempts--;
					username = JOptionPane.showInputDialog(null, "Enter username");
					password = JOptionPane.showInputDialog(null, "Enter password");
					foundUserDetails = readFile(adminFile, username, password, 1, 2);
				}
			}
		}
		return loggedInStatus;
	}	
	
	
	public static Boolean readFile(String fileName, String str1, String str2, int pos1, int pos2)
   	{
		String[] fileElements;	
		boolean found = false;
		Scanner in;
		FileReader read;
		try
		{

	        read = new FileReader(fileName);
			in = new Scanner(read);
			while(in.hasNext())
			{    
		        fileElements= (in.nextLine()).split(",");
				
				if (fileElements[pos1].equals(str1) && fileElements[pos2].equals(str2))
				{
					found = true;
					item1 = fileElements[0]; // Admin#, League#, fixture#.
					break;
				}
			}
			in.close();
			read.close();	
		 }
		 catch (Exception e)
		 {}
		
		return found;
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

	public static String[] getAdminLeagues() //WIP Method to store admin's league(names) in array, can be adjusted to take league number
	{
		ArrayList<String> adminLeagues = new ArrayList<String>();
		String leagueName = "";
		try
		{
			FileReader read = new FileReader(leagueFile);
			Scanner in = new Scanner(read);
			while(in.hasNext())
			{
				String text = in.nextLine();
				String[] fileElements = text.split(",");
				if (fileElements[2].equals(loggedInAdminID))
				{
					leagueName = fileElements[1];
					adminLeagues.add(leagueName);
				}
			}
		}
		catch(Exception e)
		{}
		String [] leagueChoices = adminLeagues.toArray(new String[adminLeagues.size()]);
		return leagueChoices;
	}
		
//Working on method to search file if fixture result is already inputted and replace it with a new result		
		
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
