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
		boolean checker=checkIfItExists(fixture);
		if (checker==true)
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
		String temp = dropDown(fixtureDisplay, "Select a fixture");
		fixtureChoice = Arrays.asList(fixtureDisplay).indexOf(temp);
		matchNumberChoice = Integer.toString(fixtureChoice);
		resultExists = readFile(resultsFileName, Integer.toString(fixtureChoice), 0);
		if (resultExists == true)
		{		
			choice = JOptionPane.showConfirmDialog(null, "Already entered result for this fixture, Do you want to edit the result?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);	
			if (choice == JOptionPane.YES_OPTION) //If yes
			{
			removeLineFromFile(resultsFileName, Integer.toString(fixtureChoice), 0);
			
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


	public static void fixtureGeneration()throws IOException
	{
		int numberOfTeams, totalNumberOfRounds, numberOfMatchesPerRound;
		int roundNumber;
		int matchNumber=0;
		int homeTeamNumber, awayTeamNumber, even, odd;
		String choice;
		boolean additionalTeamIncluded = false;
		String [] poop=readFile(leagueFile,"1",0,1);
		int whichLeague=optionBoxs(poop,"Select a league:");
		whichLeague=whichLeague+1;
		String teamFileName=whichLeague+"_participants.txt";
		String fixtureGenerationFileName=whichLeague+"_fixtures.txt";
		File check = new File(fixtureGenerationFileName);
		if ((check.exists()))
		{
			//Give option to regen fixtures or back out to menu
			deleteFile(fixtureGenerationFileName);
			fixtureGeneration();
		}
		else
		{	
			int selection=getNumberOfTeams(teamFileName);
			String [][] fixtures;
			String [][] revisedFixtures;
			String []   elementsOfFixture;
			String fixtureAsText;
			String info="";
			if (selection != 0)
			{
		   numberOfTeams = selection; 
		   if (numberOfTeams % 2 == 1)
		   {
			 numberOfTeams++;
			 additionalTeamIncluded = true;
		   }
		   totalNumberOfRounds     = numberOfTeams - 1;
		   numberOfMatchesPerRound = numberOfTeams / 2;
		   fixtures = new String[totalNumberOfRounds][numberOfMatchesPerRound];  
			
		   for (roundNumber = 0; roundNumber < totalNumberOfRounds; roundNumber++) 
		   {
			 for (matchNumber = 0; matchNumber < numberOfMatchesPerRound; matchNumber++) 
			 {
			   homeTeamNumber = (roundNumber + matchNumber) % (numberOfTeams - 1);
			   awayTeamNumber = (numberOfTeams - 1 - matchNumber + roundNumber) % (numberOfTeams - 1);
			   if (matchNumber == 0) 
				 awayTeamNumber = numberOfTeams - 1;
			   fixtures[roundNumber][matchNumber] = (homeTeamNumber + 1) + "," + (awayTeamNumber + 1);
			 }
		   } 
		   revisedFixtures = new String[totalNumberOfRounds][numberOfMatchesPerRound];
		   even = 0;
		   odd = numberOfTeams / 2;
		   for (int i = 0; i < fixtures.length; i++) 
		   {
			 if (i % 2 == 0) 	
			   revisedFixtures[i] = fixtures[even++];
			 else 				
			   revisedFixtures[i] = fixtures[odd++];
		   }
		   fixtures = revisedFixtures;
		   int matchCounter=1;
		   for (roundNumber = 0; roundNumber < fixtures.length; roundNumber++) 
		   {
			 if (roundNumber % 2 == 1) 
			 {
			   fixtureAsText = fixtures[roundNumber][0];
			   elementsOfFixture = fixtureAsText.split(",");
			   fixtures[roundNumber][0] = elementsOfFixture[1] + "," + elementsOfFixture[0];
			 }
		   }
			for (roundNumber = 0; roundNumber < totalNumberOfRounds; roundNumber++) 
		   {
			   for (matchNumber = 0; matchNumber < numberOfMatchesPerRound; matchNumber++) 
				{
						info=matchCounter+",";
						info=info+fixtures[roundNumber][matchNumber];
						writeFile(info,fixtureGenerationFileName);
						matchCounter++;	   
				}	
		   }
		}	
		}
	}
		
}
/*

	public static void displayResults(String leagueChoice)
	//FIX JOP DISPLAY*********************
	{		
		String resultsFileName = leagueChoice + "_results.txt";
		String [] temp = readFixtures(leagueChoice);
		String fileElements[] = (getFileDetails(resultsFileName));
		
		String fixtureNumber = "", homeScore = "", awayScore = "";
		String homeTeam = "", awayTeam = "";
		String[] resultDisplay = new String[teamDetails.size()];
		for (int i = 0;i<teamDetails.size();i++)
		{
			fixtureNumber = (teamDetails.get(0).get(i));
			homeTeam = teamDetails.get(1).get(i);
			awayTeam = teamDetails.get(2).get(i);
			for (int j = 0; j < fileElements.length-1;j++)
			{
				if (fileElements[0] == teamDetails.get(0).get(i));
				{
					homeScore = fileElements[1];
					awayScore = fileElements[2];
				}
			}			
			resultDisplay[i] = (fixtureNumber + ". " + homeTeam + ": " + homeScore + " - " + awayScore + " :" + awayTeam);
			System.out.println(resultDisplay[i]);
		}
	
	}
	public static void editResults(String leagueNumber) throws IOException
	//FIX JOP DISPLAY OF RESULTS***************
	{
		ArrayList<ArrayList<String>> editResults = new ArrayList<ArrayList<String>>();
		editResults.add(new ArrayList<String>());
		editResults.add(new ArrayList<String>());
		editResults.add(new ArrayList<String>());
		
		boolean resultExists = false;
		String [] fixtureDisplay = readFixtures(leagueNumber);
		String pattern = "[0-9]{1,}";
		String fixture=leagueNumber+"_fixtures.txt";
		String resultsFileName = leagueNumber + "_results.txt";
		int fixtureChoice = 0, choice = 0;
		String homeScore = "", awayScore = "";
		
		boolean checker=checkIfItExists(fixture);
		if (checker==true)
		{
			String temp = dropDown(fixtureDisplay, "Select a fixture");
			fixtureChoice = Arrays.asList(fixtureDisplay).indexOf(temp);
			resultExists = readFile(resultsFileName, Integer.toString(fixtureChoice), 0);
			if (resultExists == true)
			{		
				choice = JOptionPane.showConfirmDialog(null, "Already entered result for this fixture, Do you want to edit the result?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);	
				if (choice == JOptionPane.YES_OPTION) //If yes
				{
				removeLineFromFile(resultsFileName, Integer.toString(fixtureChoice), 0);
				editResults.get(0).add(Integer.toString(fixtureChoice));
				while (!(homeScore.matches(pattern)))
					homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
				editResults.get(1).add(homeScore);	
				while (!(awayScore.matches(pattern)))
					awayScore = JOptionPane.showInputDialog(null, "Enter away score:");		
				editResults.get(2).add(awayScore);
				
				String output = fixtureChoice+1 + "," + homeScore + "," + awayScore;
				writeFile(output,resultsFileName);
				//Give option to edit others or back out of menu
				}
				
				editResults(leagueNumber);
				//ELSE TO BACK OUT TO MENU OPTIONS
			}	
			else 
			{
				editResults.get(0).add(Integer.toString(fixtureChoice));
				while (!(homeScore.matches(pattern)))
					homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
				editResults.get(1).add(homeScore);	
				while (!(awayScore.matches(pattern)))
					awayScore = JOptionPane.showInputDialog(null, "Enter away score:");
				editResults.get(2).add(awayScore);
			
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
		public static String[] getFileDetails(String fileName) 
	{
		String [] fileElements = {""};
		ArrayList<ArrayList<String>> fileDetails = new ArrayList<ArrayList<String>>();
		fileDetails.add(new ArrayList<String>());
		fileDetails.add(new ArrayList<String>());
		fileDetails.add(new ArrayList<String>());
		try
		{
			File aFile = new File(fileName);
			if (aFile.exists())
			{
				Scanner in;
				in = new Scanner(aFile);
				while(in.hasNext())
				{
					fileElements = (in.nextLine()).split(",");
					fileDetails.get(0).add(fileElements[0]);  
					fileDetails.get(1).add(fileElements[1]);
					fileDetails.get(2).add(fileElements[2]);
				}
				in.close();
			}
		}
		catch (Exception e)
		{}
		return fileElements;
	}
	public static String[] readFixtures(String leagueChoice)
	{
		fixtureDetails = new ArrayList<ArrayList<String>>(); //MAKE GLOBAL
		fixtureDetails.add(new ArrayList<String>());
		fixtureDetails.add(new ArrayList<String>());		
		fixtureDetails.add(new ArrayList<String>());
		
		teamDetails = new ArrayList<ArrayList<String>>(); //MAKE GLOBAL
		teamDetails.add(new ArrayList<String>());
		teamDetails.add(new ArrayList<String>());		
		teamDetails.add(new ArrayList<String>());
		
		String homeTeam = "", awayTeam = "", fixtureNumber = "";
		String temp1 = "", temp2 = "";
		String fileElements[] = {""};
		String fixtureDisplay [] = {""};
		String currentLeagueParticipants = leagueChoice + "_participants.txt";
		int teamCount = getNumberOfTeams(currentLeagueParticipants);
		try
		{
			File aFile = new File(leagueChoice + "_fixtures.txt");
			if (aFile.exists())
			{
				Scanner in = new Scanner(aFile);
				while (in.hasNext())
				{
					if (Integer.parseInt(fileElements[1]) > teamCount || Integer.parseInt(fileElements[2]) > teamCount)
					{
						System.out.println("TEST");
					}
					else
					{
						fileElements = (in.nextLine().split(","));
						fixtureDetails.get(0).add((fileElements[0]));  
						fixtureDetails.get(1).add((fileElements[1]));
						fixtureDetails.get(2).add((fileElements[2]));
						
						temp1 = getTeamName(Integer.parseInt(fileElements[1]), currentLeagueParticipants);
						temp2 = getTeamName(Integer.parseInt(fileElements[2]), currentLeagueParticipants);
						
						teamDetails.get(0).add(fileElements[0]);
						teamDetails.get(1).add(temp1);
						teamDetails.get(1).add(temp2);
					}
				}
				in.close();
				
				fixtureDisplay = new String[fixtureDetails.size()];
				for (int i = 0;i < teamDetails.size();i++)
				{
					fixtureNumber = teamDetails.get(0).get(i);
					homeTeam = teamDetails.get(1).get(i);
					awayTeam = teamDetails.get(2).get(i);
					
					
					fixtureDisplay[i] = (fixtureNumber + ". " + homeTeam + " V " + awayTeam);
				}
			}
		}
		catch(Exception e)
		{}
		return fixtureDisplay;
	}	
	
*/
