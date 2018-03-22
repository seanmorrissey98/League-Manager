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
	
	public static void displayFixtures()
	//FIX JOP DISPLAY*********************
	{
		String temp = "";
		String leagueChoice = JOptionPane.showInputDialog(null, "Enter league number to edit"); //FIX LEAGUE NUMBER CHOICE METHOD
		String [] fixturesFormatted = readFixtures(leagueChoice); //FIXTURES FORMATTED AS "HOME - AWAY"
		//DISPLAY FIXTURES HERE
		for (int i = 0;i < fixturesFormatted.length-1;i++)
		{
			temp += fixturesFormatted[i] + "\n"; 
		}
		JOptionPane.showMessageDialog(null, temp);
	}
	
		public static String[] readFixtures(String leagueChoice)
	{
		ArrayList<ArrayList<String>> teams = new ArrayList<ArrayList<String>>();
		teams.add(new ArrayList<String>());
		teams.add(new ArrayList<String>());
		String homeTeam = "", awayTeam = "";
		String currentLeagueFixtures = leagueChoice + "_fixtures.txt";
		String currentLeagueParticipants = leagueChoice + "_participants.txt";
		String [] fileElements;
		int temp = getNumberOfTeams(currentLeagueParticipants);
		Scanner in;
		FileReader read;
		String[] fixtureDisplay = {""};
		
		int fixtureCount = 0;
		try
		{
			read = new FileReader(currentLeagueFixtures);
			in = new Scanner(read);
			while (in.hasNext())
			{
				fileElements = in.nextLine().split(",");
				if ((Integer.parseInt(fileElements[1])) > temp || (Integer.parseInt(fileElements[2])) > temp)
				{}
				else
				{
				teams.get(0).add(fileElements[1]);
				teams.get(1).add(fileElements[2]);
				}
			}
			read.close();
			in.close();
			
			fixtureDisplay = new String[teams.get(0).size()];
			for (int i = 0;i < teams.get(0).size();i++)
			{
				homeTeam = getTeamName(Integer.parseInt(teams.get(0).get(i)), currentLeagueParticipants);
				awayTeam = getTeamName(Integer.parseInt(teams.get(1).get(i)), currentLeagueParticipants);
				fixtureDisplay[i] = (homeTeam + " V " + awayTeam);
				//System.out.print(fixtureDisplay[i]);
			}
		}
		
		catch(Exception e)
		{}
	return fixtureDisplay;	
	}
	
	
	
		public static void editResults(String leagueNumber) throws IOException
	//FIX JOP DISPLAY OF RESULTS***************
	{
		String fixture=leagueNumber+"_fixtures.txt";
		boolean ass=checkIfItExists(fixture);
		System.out.print(ass);
		if (ass==true)
		{
		String pattern = "[0-9]{1,}";
		int choice = 0;
		int fixtureChoice = 0;
		String homeScore = "", awayScore = "";
		boolean resultExists = false;
		String matchNumberChoice = "";
		String [] fixtureDisplay = readFixtures(leagueNumber);
		String resultsFileName = leagueNumber + "_results.txt";
		//fixtureChoice = JOptionPane.showOptionDialog(null, "Choose fixture to edit", "Click button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, fixtureDisplay, fixtureDisplay[0]); //MAY CHANGE GUI
		String fix = dropDown(fixtureDisplay, "Select a fixture");
		System.out.println(fix + "WUBAB");
		int checker = Arrays.asList(fixtureDisplay).indexOf(fix) + 1;
		System.out.println(checker + "TEST");
		matchNumberChoice = Integer.toString(fixtureChoice);
		resultExists = readFile(resultsFileName, Integer.toString(fixtureChoice+1), 0);
		if (resultExists == true)
		{		
			choice = JOptionPane.showConfirmDialog(null, "Already entered result for this fixture, Do you want to edit the result?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);	
			if (choice == JOptionPane.YES_OPTION) //If yes
			{
			removeLineFromFile(resultsFileName, Integer.toString(fixtureChoice+1), 0);
			
			while (!(homeScore.matches(pattern)))
				homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
			while (!(awayScore.matches(pattern)))
				awayScore = JOptionPane.showInputDialog(null, "Enter away score:");
			String output = fixtureChoice+1 + "," + homeScore + "," + awayScore;
			writeFile(output,resultsFileName);
			//Give option to edit others or back out of menu
			}
			
			editResults(leagueNumber);
			//ELSE TO BACK OUT TO MENU OPTIONS
		}
		else 
		{
			while (!(homeScore.matches(pattern)))
				homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
			while (!(awayScore.matches(pattern)))
				awayScore = JOptionPane.showInputDialog(null, "Enter away score:");
		
			String output = fixtureChoice+1 + "," + homeScore + "," + awayScore;
			writeFile(output,resultsFileName);

			//Give option to edit others or back out of menu
			editResults(leagueNumber);
		}
	}
	else 
	{
		JOptionPane.showMessageDialog(null, "Generate Fixtures First");
	}
	}
	
	public static void removeLineFromFile(String fileName, String toDel, int pos) //Params file name, String to delete, position of string
	{
		try {
		String[] fileElements;
		String line = "";
        File inFile = new File(fileName);

          if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");
            return;
          }

          File tempFile = new File("temp.txt");

          BufferedReader br = new BufferedReader(new FileReader(inFile));
          PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

          while ((line = br.readLine()) != null) 
		  {
			fileElements = line.split(","); 
            if (!fileElements[pos].equals(toDel)) {
              pw.println(line);
              pw.flush();
            }
          }

          pw.close();
          br.close();

          //Delete the original file
          if (!inFile.delete()) {
            System.out.println("Could not delete file");
            return;
          } 

          //Rename the new file to the filename the original file had.
          if (!tempFile.renameTo(inFile)) {
            System.out.println("Could not rename file");
          }

        } catch (FileNotFoundException ex) {
          ex.printStackTrace();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
	}	
		
	}
	
		public static void writeFile(String input, String fileName)
		{
		try
		{
		    FileWriter aFileWriter = new FileWriter(fileName,true);
            PrintWriter out = new PrintWriter(aFileWriter);
			out.print(input);
			out.println();
			out.close();
			aFileWriter.close();	
		}
		catch(Exception e)
		{}
		}
		

	public static void displayResults()
	{
		String leagueNumber = JOptionPane.showInputDialog(null, "Enter league number to edit");							//NEED TO SORT LEAGUE NUMBER VARIABLE
		String homeTeamNumber = "", awayTeamNumber ="";
		String homeTeamName ="", awayTeamName ="";
		String homeTeamScore = "", awayTeamScore = "";
		String temp = "";
		String fixturesFileName = leagueNumber + "_fixtures.txt";
		String teamFileName = leagueNumber + "_participants.txt";
		String resultsFileName = leagueNumber + "_Results.txt";
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		results.add(new ArrayList<String>());
		results.add(new ArrayList<String>());
		String[] fileElements;
		String[] resultDisplay = {""};
		int fixtureCount = 1;
	
		try
		{
			FileReader read = new FileReader(resultsFileName);
			Scanner in = new Scanner(read);
			while (in.hasNext())
			{
				fileElements = in.nextLine().split(",");
				results.get(0).add(fileElements[1]);
				results.get(1).add(fileElements[2]);;
				fixtureCount++;
			}
			read.close();
			in.close();
			
			resultDisplay = new String[fixtureCount];
			for (int i=1;i<fixtureCount;i++)
			{
				homeTeamNumber = getFixtureDetails(fixturesFileName, 1, i);
				awayTeamNumber = getFixtureDetails(fixturesFileName, 2, i);
				homeTeamName = getTeamName(Integer.parseInt(homeTeamNumber), teamFileName);
				awayTeamName = getTeamName(Integer.parseInt(awayTeamNumber), teamFileName);
				homeTeamScore = results.get(0).get(i-1);
				awayTeamScore = results.get(1).get(i-1);
				
				resultDisplay[i] = homeTeamName + "  " + homeTeamScore + " - " + awayTeamScore + "  " + awayTeamName;
				temp += resultDisplay[i] + "\n";
			}
				//FIX THE DISPLAY 
				JOptionPane.showMessageDialog(null, temp);
		}
		catch(Exception e)
		{}
	}

	public static String getFixtureDetails(String fileName, int pos, int fixtureCount) //pos of item to get(), fixture(line)number
	{
		String returnValue = "";
		String fixtureNumber = Integer.toString(fixtureCount);
		String[] fileElements;
		try
		{
			FileReader reader = new FileReader(fileName);
			Scanner in = new Scanner(reader);
			while(in.hasNext())
			{
				fileElements = in.nextLine().split(",");
				if (fileElements[0].equals(Integer.toString(fixtureCount)))
				{
					returnValue =  fileElements[pos];
					break;
				}
			}
			in.close();
			reader.close();
		}
		catch(Exception e)
		{}
		return returnValue;
	}
		
}
