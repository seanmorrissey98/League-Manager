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
	
	public static void displayFixtures(String leagueChoice) //CAN ADAPT FOR EDIT RESULTS ALSO
	{
		ArrayList<ArrayList<String>> teams = new ArrayList<ArrayList<String>>();
		teams.add(new ArrayList<String>());
		teams.add(new ArrayList<String>());
		String temp = "";
		String homeTeam = "", awayTeam = "";
		String currentLeagueFixtures = leagueChoice + "_fixtures.txt"; 
		String currentLeagueParticipants = leagueChoice + "_participants.txt";
		String [] fileElements;
		Scanner in;
		FileReader read;
		String [] fixtureDisplay;
		int fixtureCount = 0;
		try
		{
			read = new FileReader(currentLeagueFixtures);
			in = new Scanner(read);
			while(in.hasNext())
			{
				fixtureCount++;
				fileElements = in.nextLine().split(",");
				teams.get(0).add(fileElements[1]);
				teams.get(1).add(fileElements[2]);
			}

			read.close();
			in.close();	
			
			fixtureDisplay = new String[fixtureCount];
			for (int i = 0;i< fixtureCount;i++)
			{	
				homeTeam = getTeamName(Integer.parseInt(teams.get(0).get(i)), currentLeagueParticipants);
				awayTeam = getTeamName(Integer.parseInt(teams.get(1).get(i)), currentLeagueParticipants);
				//JOptionPane.showMessageDialog(null, homeTeam + " V " + awayTeam);
				fixtureDisplay[i] = (homeTeam + " - " + awayTeam);
			}
			System.out.println(Arrays.toString(fixtureDisplay));
			//Need to display fixtures in JOptionPane
		}
		catch(Exception e)
		{}
	}
	
		public static void editResults() 
	{
		int choice = 0;
		int fixtureChoice = 0;
		String [] yesNo = {"yes", "no"};
		boolean resultExists = false;
		String matchNumberChoice = "";
		String leagueNumber = JOptionPane.showInputDialog(null, "Enter league number to edit"); // MENU FOR LEAGUE EDITING TO PASS IN LEAGUE NUMBER NEEDED
		String [] fixtureDisplay = displayFixtures(leagueNumber);
		String resultsFileName = leagueNumber + "_Results.txt";
		fixtureChoice = JOptionPane.showOptionDialog(null, "Choose fixture to edit", "Click button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, fixtureDisplay, fixtureDisplay[0]);
		matchNumberChoice = Integer.toString(fixtureChoice);
		resultExists = readFile(resultsFileName, Integer.toString(fixtureChoice+1), 0);
		if (resultExists == true)
		{		
			choice = JOptionPane.showConfirmDialog(null, "Already entered result for this fixture, Do you want to edit the result?");	
			if (choice == 0) //If yes
			{
			removeLineFromFile(resultsFileName, Integer.toString(fixtureChoice+1), 0);
			String homeScore = JOptionPane.showInputDialog(null, "Enter home score");
			String awayScore = JOptionPane.showInputDialog(null, "Enter away score");
			String output = fixtureChoice+1 + "," + homeScore + "," + awayScore;
			writeFile(output,resultsFileName);
			//Give option to edit others or back out of menu
			}
			//ELSE TO BACK OUT TO MENU OPTIONS
		}
		else 
		{
			String homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
			String awayScore = JOptionPane.showInputDialog(null, "Enter away score:");
			String output = fixtureChoice+1 + "," + homeScore + "," + awayScore;
			writeFile(output,resultsFileName);

			//Give option to edit others or back out of menu
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
		ArrayList<ArrayList<String>> teams = new ArrayList<ArrayList<String>>();
		String temp = "";
		String homeTeam = "", awayTeam = "";
		String [] fileElements;
		Scanner in;
		FileReader read;
		try
		{
			System.out.println("test case");
			read = new FileReader("1_fixtures.txt");
			in = new Scanner(read);
			while(in.hasNext())
			{
				temp = in.nextLine();
				System.out.println("uijq");
				fileElements = (temp.split(","));
				System.out.println(Arrays.toString((fileElements)));
				teams.get(0).add(fileElements[1]); //home Teams
				System.out.println("sd");
				teams.get(1).add(fileElements[2]);
				System.out.println("12345");
			}
			System.out.println("123");
			read.close();
			in.close();	
			
			for (int i = 0;i< teams.size();i++)
			{	
				System.out.println("Tester");
				for (int j=0;j<teams.get(i).size();j++)
				{
					System.out.println("Test");
					homeTeam = getTeamName(Integer.parseInt(teams.get(0).get(i)), "1_participants.txt");
					awayTeam = getTeamName(Integer.parseInt(teams.get(1).get(j)), "1_participants.txt");
					JOptionPane.showMessageDialog(null, homeTeam + " V " + awayTeam);
				}
			
			}
		}
		catch(Exception e)
		{}
	}

		
	public static void overwriteFile(String file, String toDel)
	{
		try
		{
		File tempFile = newFile (file.getAbsolutePath() + ".tmp");
		BufferedReader br = new BufferedReader(new FileReader(file));
		PrintWriter pw = new PrintWriter (new FileWriter(tempFile));
		String line;
		String[] fileElements;
		while ((line = br.readLine()) != null)
		{
			fileElements = (br.nextLine()).split(",");
			if (!(fileElements[pos].equals(toDel)))
			{
				pw.println(line);
				pw.flush();
			}
		}
			pw.close();
			br.close();
			
		    if (!(aFile.exists()))
				System.out.println(aFile.getName() + " does not exist.");
			else if(aFile.delete())
				System.out.println(aFile.getName() + " is now deleted.");
			else if (tempFile.renameTo(file))
				System.out.println("Renamed file");
					
		}
		
		catch(Exception e)
		{}
	}
}
