// Paul's code so far.Got the gui roughlygoing.Only made up mock versions of methods to make sure it works.

	
	
	public static int menuBoxInt(String options)
	{   int x=-1;
		String text = JOptionPane.showInputDialog(null, options);
		boolean valid = validateNumberInput(text); 
		if (valid=true)
		{
		// parse to int and return the users choice
		 x = Integer.parseInt(text);
		}
		return x;
	}
	
	public static boolean validateNumberInput(String text)
	{
		String result="";
		String pattern="[1-4]{1}";
		String message1 = "A number must be input.";
	    String message2 = "Format of user input is incorrect, a number  is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					verified=true;
					}
			}
		}
		return verified ;
	}
	
				
	public static boolean validateInput(String text)
	{
		String result="";
		String pattern="[A-Za-z||0-9]{1,}";
		String message1 = "A Name must be input.";
	    String message2 = "Format of user input is incorrect, a Name is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					verified=true;
					}
			}
		}
		return verified ;
	}
        private static int currentAdminNo=1;
		private static int leagueNo=1;
		final static String leagueFile="league.txt";
		final static String adminFile="administrator.txt";
	
	public static void createNewLeague()
	{
		String leagueName=""; String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+leagueNo;
		writeFile(leagueFileInput,leagueFile);
		leagueNo++;
				}
	
		 
	public static void writeFile(String input, String fileName) 
	 {
         try (Writer writer = new BufferedWriter(new OutputStreamWriter(
         new FileOutputStream(fileName), "utf-8"))) 
		 {
             writer.write(input);
         }
         catch(Exception e)
         {} 
         
	 }
	 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		 return input;
	 }
	 
	
	
	
	public static int getNumberOfLeaguesMade()
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0;
		int temp=0;
		int numberOfLeagues=0;
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==currentAdminNo)
			{
				found=true;
				currentAdminPostion=i;
			}
		}
		for(int i=currentAdminPostion;i<arrayOfDetails.length && sameAdmin==true;i=i+3)
		{
			if(currentAdminNo!=Integer.parseInt(arrayOfDetails[i]))
			{
				sameAdmin=false;
			}
			else
			{
				numberOfLeagues=Integer.parseInt(arrayOfDetails[i+2]);
			}
		}
		return numberOfLeagues;
	}
	
	public static String readFile(String textFile)
	{
		String fileText="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			fileText=in.nextLine();
			fileText=fileText+",";
		}
		in.close();
		reader.close();
		}
		catch(Exception E)
		{}
		return fileText;
	}
	
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	
	public static void addTeamsToLeague()
	{
		String info=""; String teamName=""; String teamFileInfo=""; String teamFileName="";
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:");
		teamFileName=whichLeague+"_"+"participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague)
		{
			outputBoxs("This league does not exist.");
		}
		else
		{
			int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);	
        }

		}
	}
	public static String createNewLeague(String x)
	{
		outputBoxs(x);
		return x;
	}
	
	public static String generateFixtures(String x)
	{
		outputBoxs(x);
		return x;
	}
	public static String generateTable(String x)
	{
		outputBoxs(x);
		return x;
	}
	public static String removeTeamsFromLeague(String x)
	{
		outputBoxs(x);
		return x;
	}
	public static String removeLeague(String x)
	{
		outputBoxs(x);
		return x;
	}
	public static String addTeamsToLeague(String x)
	{
		outputBoxs(x);
		return x;
	}
	public static String displayTable(String x)
	{
		outputBoxs(x);
		return x;
	}
	public class displayResults
public static void main (String[] args)
{
}
public static String displayResults(String leagueNumber)
	{
		 
	}
	
		public static String getTeamScore(int fixtureNumber, String resultsFileName)
	{
		
		String[]teamScore={fixtureNumber,0,0};
		boolean found=false;
		int current=1;
		int temp=0;
		int numberOfLeagues=0;
		String [] arrayOfDetails=readFile(resultsFileNameName).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==fixtureNumber)
			{
				found=true;
				teamScore[current]=arrayOfDetails[i+1];
				current++;
				teamScore[cuurent]=arrayOfDetails[i+2];
					
			}
		}
		return teamScore;
	}
}
import java.io.*;
import javax.swing.*;
import java.util.*;
public class annettesWay
{ 
	
/*
public static void main(String [] args) throws IOException
{
	
	if  (validLogin())
	{
		createLeague();
		addTeams();
		generateFixtures();
		inputOutcomes();
		editOutcomes();
		generateTable();
		
		
		public static int [][] generateTable();
		{
		boolean readFile; 
		readFile = readFilesIntoArrayLists();
		if (!readFile)
		System.out.println("One or more files do not exist.");
		else
		{
      createEmptyLeaderBoard();
      processResults();
      orderLeaderBoard();
      displayLeaderboard();
		}
	}
	}
	else break;
}
 public static boolean validLogin() throws IOException
  {
	ArrayList<ArrayList<String>> admins = new ArrayList<ArrayList<String>>();
    String filename = "Administrators.txt";
	String errorMessage1 = filename + " not found";
	String errorMessage2 = "No administrators registered";
	File adminFile = new File(filename);
	String fileElements[];
	String adminName     = "admin1";
	String adminPassword = "$today1$";
	int    recordCount, adminID;
	boolean found = false;
	if (!(adminFile.exists()))
	  System.out.println(errorMessage1);
	else if (adminFile.length() == 0)
	  System.out.println(errorMessage2);
	else
	{
      Scanner in = new Scanner(adminFile);
	  admins.add(new ArrayList<String>());
	  admins.add(new ArrayList<String>());
	  admins.add(new ArrayList<String>());
	  while (in.hasNext())
	  {
		fileElements = (in.nextLine()).split(",");
		for (int i = 0; i < fileElements.length; i++)
		   (admins.get(i)).add(fileElements[i]);
	  }
	  in.close();
	  
	  for (int list = 0; list < admins.size(); list++)
        for (int item = 0; item < admins.get(list).size(); item++)
         System.out.println(admins.get(list).get(item));
	 
	  
	  for (recordCount = 0; recordCount < admins.get(0).size(); recordCount++)
		System.out.println(admins.get(0).get(recordCount) + " " + 
		                   admins.get(1).get(recordCount) + " " + 
						   admins.get(2).get(recordCount));  
		
	  for (recordCount = 0; 
	           recordCount < admins.get(0).size() && !found; 
			   recordCount++)
	  {
	    if ((admins.get(1).get(recordCount)).equals(adminName) &&
		    (admins.get(2).get(recordCount)).equals(adminPassword))
		  found = true;	
	  }
	  
	  if (found)
	  {
	    adminID = Integer.parseInt(admins.get(0).get(--recordCount));
	    System.out.println("Welcome Administrator " + adminID);
	  }
	  else
	    System.out.println("Invalid user details");
    }
	return found;
  }
   public static boolean readFilesIntoArrayLists() throws IOException
  {
    String filename1 = "Teams20152016.txt";
    String filename2 = "Fixtures20152016.txt";
    String filename3 = "Outcomes20152016.txt";
    
    String fileElements[];
	File inputFile1 = new File(filename1);
	File inputFile2 = new File(filename2);
	File inputFile3 = new File(filename3);
	
	teams = new ArrayList<ArrayList<String>>();
	teams.add(new ArrayList<String>());
    teams.add(new ArrayList<String>());
  
    fixtures = new ArrayList<ArrayList<Integer>>();
	fixtures.add(new ArrayList<Integer>());
    fixtures.add(new ArrayList<Integer>());
    fixtures.add(new ArrayList<Integer>());
    
    results = new ArrayList<ArrayList<Integer>>();
	results.add(new ArrayList<Integer>());
    results.add(new ArrayList<Integer>());
    results.add(new ArrayList<Integer>());
    
	if (inputFile1.exists() && inputFile2.exists() && inputFile3.exists())
	{
	  Scanner in;
	  in = new Scanner(inputFile1);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    teams.get(0).add(fileElements[0]);  
	    teams.get(1).add(fileElements[1]);  
	  } 
	  in.close();
	  in = new Scanner(inputFile2);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    fixtures.get(0).add(Integer.parseInt(fileElements[0]));  
	    fixtures.get(1).add(Integer.parseInt(fileElements[1]));  
	    fixtures.get(2).add(Integer.parseInt(fileElements[2]));  
	  } 
	  in.close();
	  in = new Scanner(inputFile3);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    results.get(0).add(Integer.parseInt(fileElements[0]));  
	    results.get(1).add(Integer.parseInt(fileElements[1]));  
	    results.get(2).add(Integer.parseInt(fileElements[2]));  
	  } 
	  in.close();
	  return true;
    }
    else
      return false;
  }
  
  public static void createEmptyLeaderBoard()
  {
	// find out the number of teams/players which will determine 
	// the number of rows  
    int rows = teams.get(0).size();
	int columns = 14;  
	leaderBoard = new int[rows][columns];
	// place team numbers in column 0 of leader board
	for (int i = 0; i < leaderBoard.length; i++)
      leaderBoard[i][0] = Integer.parseInt(teams.get(0).get(i));
  }	  
  
  public static void processResults()
  {
	int fixtureNumber, homeTeamScore, awayTeamScore, homeTeamNumber, awayTeamNumber;
	int position;
	for (int i = 0; i < results.get(0).size(); i++)  
    {
	  fixtureNumber  = results.get(0).get(i);
	  homeTeamScore  = results.get(1).get(i);
	  awayTeamScore  = results.get(2).get(i);
	  position       = fixtures.get(0).indexOf(fixtureNumber);
	  homeTeamNumber = fixtures.get(1).get(position);
	  awayTeamNumber = fixtures.get(2).get(position);
	  if (homeTeamScore == awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
	  }  
	  else if (homeTeamScore > awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);  
	  }  
	  else
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);
		recordFixtureResultForAwayTeam(awayTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);  
	  }    
    }
  }	 
  
  public static void recordFixtureResultForHomeTeam(int hTN, int w, int d, int l, 
                                                       int hTS, int aTS, int p)
  {
	leaderBoard[hTN-1][1]++;        			// gamesPlayed
	leaderBoard[hTN-1][2]+= w;      			// homeWin
	leaderBoard[hTN-1][3]+= d;      			// homeDraw
	leaderBoard[hTN-1][4]+= l;      			// homeLoss
	leaderBoard[hTN-1][5]+= hTS;    			// homeTeamScore
	leaderBoard[hTN-1][6]+= aTS;    			// awayTeamScore
	leaderBoard[hTN-1][12] += (hTS - aTS);    	// goalDifference
	leaderBoard[hTN-1][13] += p;    			// points
  }
 
  public static void recordFixtureResultForAwayTeam(int aTN, int w, int d, int l, 
                                                       int hTS, int aTS, int p)
  {
	leaderBoard[aTN-1][1]++;        			// gamesPlayed
	leaderBoard[aTN-1][7]+= w;      			// awayWin
	leaderBoard[aTN-1][8]+= d;      			// awayDraw
	leaderBoard[aTN-1][9]+= l;      			// awayLoss
	leaderBoard[aTN-1][10]+= aTS;    			// awayTeamScore
	leaderBoard[aTN-1][11]+= hTS;    			// homeTeamScore
	leaderBoard[aTN-1][12] += (aTS - hTS);    	// goalDifference
	leaderBoard[aTN-1][13] += p;    			// points  
  }	
  
  public static void orderLeaderBoard()
  {
	int [][] temp = new int[leaderBoard.length][leaderBoard[0].length];
    boolean finished = false;
    while (!finished) 
    {
      finished = true;
      for (int i = 0; i < leaderBoard.length - 1; i++) 
      {
        if (leaderBoard[i][13] < leaderBoard[i + 1][13])
        {
          for (int j = 0; j < leaderBoard[i].length; j++) 
          {
            temp[i][j]            = leaderBoard[i][j];
            leaderBoard[i][j]     = leaderBoard[i + 1][j];
            leaderBoard[i + 1][j] = temp[i][j];
          }
          finished = false;
        }
      }
    }
  }	  
	  
  public static void displayLeaderboard()
  {
	int aTeamNumber;
	String aTeamName, formatStringTeamName;
	String longestTeamName       = teams.get(1).get(0);
    int    longestTeamNameLength = longestTeamName.length();
    
    for (int i = 1; i < teams.get(1).size(); i++)
    {
	  longestTeamName = teams.get(1).get(i);  
      if (longestTeamNameLength < longestTeamName.length())
        longestTeamNameLength = longestTeamName.length();
    }
    formatStringTeamName = "%-" + (longestTeamNameLength + 2) + "s";
    System.out.printf(formatStringTeamName,"Team Name");
    System.out.println("  GP  HW  HD  HL  GF  GA  AW  AD  AL  GF  GA   GD   TP"); 
   
    for (int i = 0; i < leaderBoard.length; i++)
    {
	  aTeamNumber       = leaderBoard[i][0];
	  aTeamName         = teams.get(1).get(aTeamNumber - 1);       
      System.out.printf(formatStringTeamName, aTeamName);
      System.out.printf("%4d", leaderBoard[i][1]);
      System.out.printf("%4d", leaderBoard[i][2]);
      System.out.printf("%4d", leaderBoard[i][3]);
      System.out.printf("%4d", leaderBoard[i][4]);
      System.out.printf("%4d", leaderBoard[i][5]);
      System.out.printf("%4d", leaderBoard[i][6]);
      System.out.printf("%4d", leaderBoard[i][7]);
	  System.out.printf("%4d", leaderBoard[i][8]);
      System.out.printf("%4d", leaderBoard[i][9]);
      System.out.printf("%4d", leaderBoard[i][10]);
      System.out.printf("%4d", leaderBoard[i][11]);
      System.out.printf("%5d", leaderBoard[i][12]);
      System.out.printf("%5d", leaderBoard[i][13]);
      System.out.println();
    }
  } 
}
*/
import java.io.*;
import javax.swing.*;
import java.util.*;
public class run
{
    
	 private static int currentAdminNo=1;
	 private static int leagueNo=1;
	 final static String leagueFile="league.txt";
	 final static String adminFile="administrator.txt";
     
	 public static void main(String[] args)
	 {	
	     // have these as arrays give a nicer finish? - use optionboxs method
         // include pauls code for main method here
		 String result ="";
		 String [] initialOptions= { "1-create league", "2-Edit/view League", "3-Remove League", "4-Exit Application" };
		 String [] subOptions={" 1-Fixture Generation", "2-View Table", "3-Input results", "4-Add/remove teams", "5-Exit Application"};
		String []subOptionsOfSubOptions={ "1-Add teams", "2-remove teams","3-Exit Application"};
		int x=optionBoxs(initialOptions);
		int y=0;
		int z=0;
		
		switch (x)
		{
			case 0: createNewLeague();
		    break;
			case 1: y=optionBoxs(subOptions);
			
			switch (y)
		{
			case 0: fixtureGeneration();
		    break;
			case 1: displayTable();
			break;
			case 2: inputResults();
			break;
			case 3: z=optionBoxs(subOptionsOfSubOptions);
			
			switch (z)
		{
			case 0: addTeams();
		    break;
			case 1: removeTeams();
			break;

		}
			break;
			case 4: break;

		}

			break;
			case 2: removeLeague();
			break;
			case 3: break;

		}
		
		
		
	}
		 
		 
	 
     
	 
	 // START OF GUI METHODS
	 
    /**
	 * GUI Method for displaying a message output
	 * Input - Any string input 
	 * Output - No output from the method - The String is converted to a GUI output box.
	 */
	public static void outputBoxs(String output) // be aware many other methods are calling outputBoxs - want just "outputBox"
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	

	
	 
	/**
	 * GUI Method that returns the position of the option that the user choose from an array of inputs. 
	 * Input - Array String of options that the user wants displayed on the buttons, e.g input: String[] opt = {"opt1", "opt2", "opt3"};
	 * Output - Integer output of the corresponding position in the array that the user made. 
	 */	
	public static int optionBoxs(String[] options)
	{
        int result = JOptionPane.showOptionDialog(null, "Returns pos of choice in array", "Click button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return result;
	}
	 

	public static String menuBox(String options)  //== method name
	{
		String text = JOptionPane.showInputDialog(null, options);
		return text;
	}

    /**
	 * GUI Method User enters a string with user suggested values that the user chooses what option they desire by entering the number they desire 
	 * Input - String options = "1) Option1 \n2) Option2  \n3) Option3"; in that format
	 * Returns a int output
	 */	
	public static int menuBoxInt(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		int x = Integer.parseInt(text);
		return x;
		
		/// include pauls validation
	}
	
	// READING AND WRITING METHODS
		/**
	 * Input - filename to push text to and the actual text you want put in the file.
	 * Output - 
 	 */     // this method both appends to already created files and if a file doesnt exist it creates it and adds to it
     public static void writeFile(String filename, String output)   /// fileWriter   or   writeFile   -- what has sean already started with
	 {
     try
     {
	     FileWriter aFileWriter = new FileWriter(filename,true);
         PrintWriter out = new PrintWriter(aFileWriter);			 
	     out.print("\n" + output); // or output + "\n"
	     out.close();
		 aFileWriter.close();
     }
	 catch(Exception e)
	 {}
	 }
	 
	 // sean's readfile  - was readFile -- changed to readFile1 in the method commented below too
	 	public static String readFile(String textFile)
	{
		String fileText="";
		try
		{
		    FileReader reader=new FileReader(textFile);
		    Scanner in=new Scanner(reader);
		    while(in.hasNext())
		    {
			    fileText=in.nextLine();
			    fileText=fileText+",";
		    }
		    in.close();
		    reader.close();
		}
		catch(Exception E)
		{}
	    return fileText;
	}
	
		 /*
	  * Input - String textFile -- name of the textFile to search
                String searchedItem -- what string value are we looking for in the text file
                int itemPositionNo -- what position in the line format is this "searchedItem" located
                int returnedItemNo -- if the "searchedItem" is found in the "itemPositionNo" in the file format return a string array of everything in the location "returnedItemNo"				
	  
	  File Format  -- leagueID,LeagueName,adminID
	  1,Premiership,1     
	  2,SnookerLeage,1
	  
	  // if you wanted to check for all adminID value 1 in the position 3 -- then if true return all the leagues name in position 1 
	  
	  * Output -    // also anyone wanting to use this ask me to explain it
	  */
	 public static String[] readFile(String textFile, String searchedItem, int itemPositionNo, int returnedItemNo)  
	 {
		 String x="";
		 try
		 {
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText= in.nextLine();
		        String[] split = fileText.split(","); 
				if (split[itemPositionNo].equals(searchedItem))
				{
					// if true store the item in position returnedItemNo
					x += split[returnedItemNo]+",";
					
				}
				
			}
			in.close();
			reader.close();		
            
		 }
		 catch (Exception e)
		 {}
		 String[] returned = x.split(",");
		 return returned;
    } 
	
	
		/**
     * Input -- textFile -- name of the file to search 
	            searchedItem -- what is the string you want to search for e.g look for a username enter "john.brown" 
                itemPositionNo -- what position is the "searchedItem" stored in				
	 *
	 */
	public static Boolean readFile(String textFile, String searchedItem, int itemPositionNo)
    {
		boolean found = false;
		try
		 {
			
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText=in.nextLine();
		        String[] split = fileText.split(",");
				if (split[itemPositionNo].equals(searchedItem))
				{
					found = true;
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
	
	
	
	
    
	public static void addTeamsToLeague()
	{
		String info=""; String teamName=""; String teamFileInfo=""; String teamFileName=""; //=
		int whichLeague=getNumberOfLeaguesMade()+1;
		
		teamFileName=whichLeague+"_"+"participants.txt";
	
			int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName); //=
			}
		
	
	}
	
	
	
    
     	public static void createNewLeague()  
	{
		String leagueName=""; String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+leagueNo;
		writeFile(leagueFileInput,leagueFile);
	    leagueNo++;  
		addTeamsToLeague();
	}
    	
	
	
	

	
	public static int getNumberOfLeaguesMade()  
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0; 
		int temp=0;
		int numberOfLeagues=0;
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==currentAdminNo)
			{
				found=true;
				currentAdminPostion=i;
			}
		}
		for(int i=currentAdminPostion;i<arrayOfDetails.length && sameAdmin==true;i=i+3)
		{
			if(currentAdminNo!=Integer.parseInt(arrayOfDetails[i]))
			{
				sameAdmin=false;
			}
			else
			{
				numberOfLeagues=Integer.parseInt(arrayOfDetails[i+2]);
			}
		}
		return numberOfLeagues;
	}
	
	 
	 
	 
	 
	 
	 	 //=
		 /*
	public static void addTeamsToLeague()
	{
		String info=""; String teamName=""; String teamFileInfo=""; String teamFileName="";
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:"); //=
		teamFileName=whichLeague+"_"+"participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague) //=
		{
			outputBoxs("This league does not exist.");
		}
		else
		{
			int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
			}
		}
     }
	 
	 
		
	/**
	 *
	 *
	 */
     	public static void addScoringScheme(int leagueNumber)
	{
		
		int win = menuBoxInt("Enter the number of points given for a win:");
		int draw = menuBoxInt("Enter the number of points given for a Draw:");
		int lose = menuBoxInt("Enter the number of points given for a Lose:");
		
		
		String fileName = (leagueNumber+"_scoring.txt");
		String output = (win+","+draw+","+lose);
		writeFile(fileName, output);	
	}
		
		
		
	     /// includde this in all the gui methods
		 /// is there a else in reply to the first if
	 	public static boolean validateNumberInput(String text)
	{
		String result="";
		String pattern="[1-4]{1}";
		String message1 = "A number must be input.";
	    String message2 = "Format of user input is incorrect, a number  is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					verified=true;
					}
			}
		}
		return verified ;
	}
	
	// include in gui method
	public static boolean validateInput(String text)
	{
		String result="";
		String pattern="[A-Za-z||0-9]{1,}";
		String message1 = "A Name must be input.";
	    String message2 = "Format of user input is incorrect, a Name is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					verified=true;
					}
			}
		}
		return verified ;
	}
	public static void fixtureGeneration()
	{
		String info=""; 
		String fixtureFileInfo=""; 
		String fixtureGenerationFileName="";
		int numberOfTeams=0;
		String teamFileName="";
		int totalNumberOfRounds=0;
		int numberOfMatchesPerRound=0;
		int roundNumber=0;
		int matchNumber=0;
		int homeTeamNumber=0;
		int awayTeamNumber=0;
		int even=0;
		int odd=0;
		boolean additionalTeamIncluded=false;
		String [][] fixtures;
		String [][]revisedFixtures;
		String [] elementOfFixture;
		String fixtureAsText="";
		int whichLeague=menuBoxInt("Enter which league number to generate fixtures for:");
		fixtureGenerationFileName=whichLeague+"_"+"fixtures.txt";
		teamFileName=whichLeague+"_participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague)
		{
			outputBoxs("You did not create this league, access denied.");
		}
		else
		{
			numberOfTeams=getNumberOfTeams(teamFileName);
			if (numberOfTeams%2==1)
			{
				numberOfTeams++;
				additionalTeamIncluded=true;
			}
			totalNumberOfRounds=numberOfTeams-1;
			numberOfMatchesPerRound=numberOfTeams/2;
			fixtures=new String[totalNumberOfRounds][numberOfMatchesPerRound];
			for(roundNumber=0; roundNumber<totalNumberOfRounds;roundNumber++)
			{
				for(matchNumber=0;matchNumber<numberOfMatchesPerRound;matchNumber++)
				{
					homeTeamNumber=(roundNumber+matchNumber)%(numberOfTeams-1);
					awayTeamNumber=(numberOfTeams-1-matchNumber+roundNumber)%(numberOfTeams-1);
					if(matchNumber==0)
						awayTeamNumber=numberOfTeams-1;
					fixtures[roundNumber][matchNumber]=(homeTeamNumber+1)+"v"+(awayTeamNumber+1);
				}
			}
			revisedFixtures=new String[totalNumberOfRounds][numberOfMatchesPerRound];
			even=0;
			odd=numberOfTeams/2;
			for(int i=0;i<fixtures.length;i++)
			{
				if(i%2==0)
					revisedFixtures[i]=fixtures[even++];
				else
			revisedFixtures[i]=fixtures[odd++];
			}
			fixtures=revisedFixtures;
			for(roundNumber=0;roundNumber<fixtures.length;roundNumber++)
			{
				if(roundNumber%2==1)
				{
					fixtureAsText=fixtures[roundNumber][0];
					elementOfFixture=fixtureAsText.split("v");
					fixtures[roundNumber][0]=elementOfFixture[1]+"v"+elementOfFixture[0];
				}
			}
			int matchCounter=1;
			for(roundNumber=0;roundNumber<totalNumberOfRounds;roundNumber++)
			{
				for(matchNumber=0;matchNumber<numberOfMatchesPerRound;matchNumber++)
				{
					info=matchCounter+",";
					info=info+fixtures[roundNumber][matchNumber].substring(0,1)+","+fixtures[roundNumber][matchNumber].substring(2,3);
					writeFile(info,fixtureGenerationFileName);
					matchCounter++;
				}
			}
		}
	}public static int getNumberOfTeams(String teamFileName)
	{
		int numberOfTeams=0;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		numberOfTeams=Integer.parseInt(arrayOfDetails[arrayOfDetails.length - 2]);
		return numberOfTeams;
	}
	public static void inputResults()
	{
		System.out.println("it works");
	}
	public static void addTeams()
	{
		System.out.println("it works");
	}
	public static void removeTeams()
	{
		System.out.println("it works");
	}
	public static void removeLeague()
	{
		System.out.println("it works");
	}
	public static void displayTable()
	{
		System.out.println("it works");
	}
	
	
	
/*	
import java.io.*;
import javax.swing.*;
import java.util.*;
public class League
{
	private static int currentAdminNo=1;
	private static int leagueNo=1;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	
	public static void main(String [] args)
	{
	createNewLeague();
    addTeamsToLeague();
	fixtureGeneration();
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
		 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		 return input;
	 }
	 
	 public static int menuBoxInt(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		int x = Integer.parseInt(text);
		return x;
	}
	
	public static void createNewLeague()
	{
		String leagueName=""; 
		String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+leagueNo;
		writeFile(leagueFileInput,leagueFile);
	    leagueNo++;
	}
	
	public static int getNumberOfLeaguesMade()
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0;
		int temp=0;
		int numberOfLeagues=0;
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==currentAdminNo)
			{
				found=true;
				currentAdminPostion=i;
			}
		}
		for(int i=currentAdminPostion;i<arrayOfDetails.length && sameAdmin==true;i=i+3)
		{
			if(currentAdminNo!=Integer.parseInt(arrayOfDetails[i]))
			{
				sameAdmin=false;
			}
			else
			{
				numberOfLeagues=Integer.parseInt(arrayOfDetails[i+2]);
			}
		}
		return numberOfLeagues;
	}
	
	public static String readFile(String textFile)
	{
		String fileText="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			fileText=in.nextLine();
			fileText=fileText+",";
		}
		in.close();
		reader.close();
		}
		catch(Exception E)
		{}
		return fileText;
	}
	
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	
	public static void outputBoxs(int output)
	{
		JOptionPane.showMessageDialog(null,output);
	}
	
	public static void addTeamsToLeague()
	{
		String info=""; 
		String teamName=""; 
		String teamFileInfo=""; 
		String teamFileName="";
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:");
		teamFileName=whichLeague+"_participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague)
		{
			outputBoxs("This league does not exist.");
		}
		else
		{
			int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
			}
		}
	}
	
	public static void fixtureGeneration()
	{
		String info=""; 
		String fixtureFileInfo=""; 
		String fixtureGenerationFileName="";
		int numberOfTeams=0;
		String teamFileName="";
		int totalNumberOfRounds=0;
		int numberOfMatchesPerRound=0;
		int roundNumber=0;
		int matchNumber=0;
		int homeTeamNumber=0;
		int awayTeamNumber=0;
		int even=0;
		int odd=0;
		boolean additionalTeamIncluded=false;
		String [][] fixtures;
		String [][]revisedFixtures;
		String [] elementOfFixture;
		String fixtureAsText="";
		int whichLeague=menuBoxInt("Enter which league number to generate fixtures for:");
		fixtureGenerationFileName=whichLeague+"_"+"fixtures.txt";
		teamFileName=whichLeague+"_participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague)
		{
			outputBoxs("You did not create this league, access denied.");
		}
		else
		{
			numberOfTeams=getNumberOfTeams(teamFileName);
			if (numberOfTeams%2==1)
			{
				numberOfTeams++;
				additionalTeamIncluded=true;
			}
			totalNumberOfRounds=numberOfTeams-1;
			numberOfMatchesPerRound=numberOfTeams/2;
			fixtures=new String[totalNumberOfRounds][numberOfMatchesPerRound];
			for(roundNumber=0; roundNumber<totalNumberOfRounds;roundNumber++)
			{
				for(matchNumber=0;matchNumber<numberOfMatchesPerRound;matchNumber++)
				{
					homeTeamNumber=(roundNumber+matchNumber)%(numberOfTeams-1);
					awayTeamNumber=(numberOfTeams-1-matchNumber+roundNumber)%(numberOfTeams-1);
					if(matchNumber==0)
						awayTeamNumber=numberOfTeams-1;
					fixtures[roundNumber][matchNumber]=(homeTeamNumber+1)+"v"+(awayTeamNumber+1);
				}
			}
			revisedFixtures=new String[totalNumberOfRounds][numberOfMatchesPerRound];
			even=0;
			odd=numberOfTeams/2;
			for(int i=0;i<fixtures.length;i++)
			{
				if(i%2==0)
					revisedFixtures[i]=fixtures[even++];
				else
			revisedFixtures[i]=fixtures[odd++];
			}
			fixtures=revisedFixtures;
			for(roundNumber=0;roundNumber<fixtures.length;roundNumber++)
			{
				if(roundNumber%2==1)
				{
					fixtureAsText=fixtures[roundNumber][0];
					elementOfFixture=fixtureAsText.split("v");
					fixtures[roundNumber][0]=elementOfFixture[1]+"v"+elementOfFixture[0];
				}
			}
			int matchCounter=1;
			for(roundNumber=0;roundNumber<totalNumberOfRounds;roundNumber++)
			{
				for(matchNumber=0;matchNumber<numberOfMatchesPerRound;matchNumber++)
				{
					info=matchCounter+",";
					info=info+fixtures[roundNumber][matchNumber].substring(0,1)+","+fixtures[roundNumber][matchNumber].substring(2,3);
					writeFile(info,fixtureGenerationFileName);
					matchCounter++;
				}
			}
		}
	}
/*	
	public static int getNumberOfTeams(String teamFileName)
	{
		int numberOfTeams=0;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		numberOfTeams=Integer.parseInt(arrayOfDetails[arrayOfDetails.length - 2]);
		return numberOfTeams;
	}
	
	public static String getTeamName(int teamNumber, String teamFileName)
	{
		String teamName="";
		int arrayNum=0;
		int positionInArray=1;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		for(int i=0;i<arrayOfDetails.length;i++)
		{
			if(i%2==0)
			{
				arrayNum=Integer.parseInt(arrayOfDetails[i]);
				if(teamNumber==arrayNum)
					positionInArray=i+1;
			}	
		}	
		teamName=arrayOfDetails[positionInArray];
		return teamName;
	}
	public static void inputResults()
	{
		System.out.println("it works");
	}
}
*/
	import java.io.*;
import javax.swing.*;
import java.util.*;
public class gui
{
	private static int currentAdminNo;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	private static String item1;
	
	public static void main(String [] args)throws IOException
	{
		
		checkIfExists(adminFile);
		String username = JOptionPane.showInputDialog(null, "Enter username");
		String password = JOptionPane.showInputDialog(null, "Enter password"); //Will work on method for hidden password input in swing
		boolean isLoggedIn = loginMethod(username, password);
		
		if(isLoggedIn==true)
		{
			//while (!System.exit(0))
			
		String result ="";
		String [] initialOptions= { "1-create league", "2-Edit/view League", "3-Remove League", "4-Exit Application" };
		String [] subOptions={" 1-Fixture Generation", "2-View Table", "3-Input results", "4-Exit Application"};
		String []subOptionsOfSubOptions={ "1-input reults", "2-edit results","3-Exit Application"};
		
		
		boolean main = true;
		while(main)  // && not null 
		{	
		    int x=optionBoxs(initialOptions,"Choose an option");
		    int y=0;
		    int z=0;
		
		    switch (x)
		    {
			    case 0: createNewLeague();
		        break;
			    case 1: y=optionBoxs(subOptions,"Choose an option");
			
			    switch (y)
			    {
					case 0: fixtureGeneration();
					break;
					case 1: displayTable();
					break;
					case 2: z=optionBoxs(subOptionsOfSubOptions,"Choose an option");
						switch (z)
						{
							case 0: inputResults();
							break;
							case 1: editResults();
							break;
							case 2: 
		                    break;
							case 3: main = false;
							break;
							
						}
					case 3: break;
					case 4: main = false;
					break;

				}

				break;
				case 2: removeLeague();
				break;
				case 3: main = false; 
				break;

			}
			
		
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
		 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		 return input;
	 }
	 
	 public static int menuBoxInt(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		int x = Integer.parseInt(text);
		return x;
	}
	
	public static void createNewLeague()throws IOException
	{
		String leagueName=""; 
		String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+(getNumberOfLeaguesMade()+1);
		writeFile(leagueFileInput,leagueFile);
		addTeamsToLeague();
	}
	
	public static int getNumberOfLeaguesMade()throws IOException
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0;
		int temp=0;
		int numberOfLeagues=0;
		File leagueFilers = new File(leagueFile);
		if (!(leagueFilers.exists()))
		{
			leagueFilers.createNewFile();
		}
		else
		{
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==currentAdminNo)
			{
				found=true;
				currentAdminPostion=i;
			}
		}
		for(int i=currentAdminPostion;i<arrayOfDetails.length && sameAdmin==true;i=i+3)
		{
			if(currentAdminNo!=Integer.parseInt(arrayOfDetails[i]))
			{
				sameAdmin=false;
			}
			else
			{
				numberOfLeagues=Integer.parseInt(arrayOfDetails[i+2]);
			}
		}
		}
		return numberOfLeagues;
	}
	
	public static String readFile(String textFile)
	{
		String fileText="";
		String ass="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			ass=in.nextLine();
			fileText=fileText+ass+",";
		}
		in.close();
		reader.close();
		}
		catch(Exception e)
		{}
		return fileText;
	}
	
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	
	public static void outputBoxs(int output)
	{
		JOptionPane.showMessageDialog(null,output);
	}
	
	public static void addTeamsToLeague()throws IOException
	{
		String info=""; 
		String teamName=""; 
		String teamFileInfo=""; 
		String teamFileName="";
		int whichLeague=getNumberOfLeaguesMade();
		teamFileName=whichLeague+"_participants.txt";
		int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
			}
	}
	
	public static void fixtureGeneration()throws IOException
	{
	int numberOfTeams, totalNumberOfRounds, numberOfMatchesPerRound;
    int roundNumber;
	int matchNumber=0;
	int homeTeamNumber, awayTeamNumber, even, odd;
    boolean additionalTeamIncluded = false;
	String [] poop=readFile(leagueFile,"1",0,1);
	int whichLeague=optionBoxs(poop,"Select a league:");
	whichLeague=whichLeague+1;
	String teamFileName=whichLeague+"_participants.txt";
	String fixtureGenerationFileName=whichLeague+"_fixtures.txt";
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

	public static int getNumberOfTeams(String teamFileName)
	{
		int numberOfTeams=0;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		numberOfTeams=Integer.parseInt(arrayOfDetails[arrayOfDetails.length - 2]);
		return numberOfTeams;
	}
	
	public static String getTeamName(int teamNumber, String teamFileName)
	{
		String teamName="";
		int arrayNum=0;
		int positionInArray=1;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		for(int i=0;i<arrayOfDetails.length;i++)
		{
			if(i%2==0)
			{
				arrayNum=Integer.parseInt(arrayOfDetails[i]);
				if(teamNumber==arrayNum)
					positionInArray=i+1;
			}	
		}	
		teamName=arrayOfDetails[positionInArray];
		return teamName;
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
				currentAdminNo = Integer.parseInt(item1);
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
	
	
	public static void checkIfExists(String fileName)throws IOException
	{
		File adminFile = new File(fileName);
		if (!(adminFile.exists()))
			adminFile.createNewFile();
	}
	
	
	public static int optionBoxs(String[] options,String whatYouWantItToSay)
	{
        int result = JOptionPane.showOptionDialog(null, whatYouWantItToSay, "League Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return result;
	}
	
	public static boolean validateNumberInput(String text)
	{
		String result="";
		String pattern="[0-9]{0,10}";
		String message1 = "A number must be input.";
	    String message2 = "Format of user input is incorrect, a number  is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        	{
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					}
				
					else
					{
					verified=true;
					}
				}
			}
		}
		return verified ;
	}

	 public static String[] readFile(String textFile, String searchedItem, int itemPositionNo, int returnedItemNo)  
	 {
		 String x="";
		 try
		 {
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText= in.nextLine();
		        String[] split = fileText.split(","); 
				if (split[itemPositionNo].equals(searchedItem))
				{
					// if true store the item in position returnedItemNo
					x += split[returnedItemNo]+",";
					
				}
				
			}
			in.close();
			reader.close();		
            
		 }
		 catch (Exception e)
		 {}
		 String[] returned = x.split(",");
		 return returned;
    } 
	public static void displayTable()
	{
		System.out.println("Display");
	}
	public static void inputResults()
	{
		System.out.println("input");
	}
	public static void editResults()
	{
		System.out.println("edit");
	}
	public static void removeLeague()
	{
		System.out.println("remove");
	}
}
	
	
	
	
	 
	
	
	import java.io.*;
import javax.swing.*;
import java.util.*;
public class newestversion
{
	private static int currentAdminNo;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	private static String item1;
	public static ArrayList<ArrayList<String>>  teams;
    public static ArrayList<ArrayList<Integer>> fixtures;	
    public static ArrayList<ArrayList<Integer>> results;
    public static int [][] leaderBoard;
	
	public static void main(String [] args)throws IOException
	{
	checkIfExists(adminFile);
	String username = JOptionPane.showInputDialog(null, "Enter username");
	String password = JOptionPane.showInputDialog(null, "Enter password"); //Will work on method for hidden password input in swing
	boolean isLoggedIn = loginMethod(username, password);
	if(isLoggedIn)
	{
	String [] initialOptions= { "1-create league", "2-Edit/view League", "3-Remove League", "4-Exit Application" };
		 String [] subOptions={" 1-Fixture Generation", "2-View Table", "3-Input results", "4-Add/remove teams", "5-Exit Application"};
		String []subOptionsOfSubOptions={ "1-Add teams", "2-remove teams","3-Exit Application"};
	        boolean main = true;
		int x=0;
		while(main&&x==0||x==1||x==2||x==3)  // && not null 
		{	
		    x=optionBoxs(initialOptions,"Choose an option");
		    int y=0;
		    int z=0;
		
		    switch (x)
		    {
			    case 0: createNewLeague();
		        break;
			    case 1: y=optionBoxs(subOptions,"Choose an option");
			
			    switch (y)
			    {
					case 0: fixtureGeneration();
					break;
					case 1: generateTable();//displayTable();
					break;
					case 2: z=optionBoxs(subOptionsOfSubOptions,"Choose an option");
						switch (z)
						{
							case 0: outputBoxs("yes");//inputResults();
							break;
							case 1: outputBoxs("yes");//editResults();
							break;
							case 2: 
		                    break;
							case 3: main = false;
							break;
							
						}
					    break;
					case 3: break;
					case 4: main = false;
					break;

				}

				break;
				case 2: outputBoxs("yes");//removeLeague();
				break;
				case 3: main = false; 
				break;

				}
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
		 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		 return input;
	 }
	 
	 public static int menuBoxInt(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		int x = Integer.parseInt(text);
		return x;
	}
	
	public static void createNewLeague()throws IOException
	{
		String leagueName=""; 
		String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+(getNumberOfLeaguesMade()+1);
		writeFile(leagueFileInput,leagueFile);
		addTeamsToLeague();
		outputBoxs(leagueName+" has been created.");
	}
	
	public static int getNumberOfLeaguesMade()throws IOException
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0;
		int temp=0;
		int numberOfLeagues=0;
		File leagueFilers = new File(leagueFile);
		if (!(leagueFilers.exists()))
		{
			leagueFilers.createNewFile();
		}
		else
		{
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==currentAdminNo)
			{
				found=true;
				currentAdminPostion=i;
			}
		}
		for(int i=currentAdminPostion;i<arrayOfDetails.length && sameAdmin==true;i=i+3)
		{
			if(currentAdminNo!=Integer.parseInt(arrayOfDetails[i]))
			{
				sameAdmin=false;
			}
			else
			{
				numberOfLeagues=Integer.parseInt(arrayOfDetails[i+2]);
			}
		}
		}
		return numberOfLeagues;
	}
	
	public static String readFile(String textFile)
	{
		String fileText="";
		String ass="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			ass=in.nextLine();
			fileText=fileText+ass+",";
		}
		in.close();
		reader.close();
		}
		catch(Exception e)
		{}
		return fileText;
	}
	
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	
	public static void outputBoxs(int output)
	{
		JOptionPane.showMessageDialog(null,output);
	}
	
	public static void addTeamsToLeague()throws IOException
	{
		String info=""; 
		String teamName=""; 
		String teamFileInfo=""; 
		String teamFileName="";
		int whichLeague=getNumberOfLeaguesMade();
		teamFileName=whichLeague+"_participants.txt";
		int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
			}
	}
	
	public static void fixtureGeneration()throws IOException
	{
	int numberOfTeams, totalNumberOfRounds, numberOfMatchesPerRound;
    int roundNumber;
	int matchNumber=0;
	int homeTeamNumber, awayTeamNumber, even, odd;
    boolean additionalTeamIncluded = false;
	String [] poop=readFile(leagueFile,"1",0,1);
	int whichLeague=optionBoxs(poop,"Select a league:");
	whichLeague=whichLeague+1;
	String teamFileName=whichLeague+"_participants.txt";
	String fixtureGenerationFileName=whichLeague+"_fixtures.txt";
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

	public static int getNumberOfTeams(String teamFileName)
	{
		int numberOfTeams=0;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		numberOfTeams=Integer.parseInt(arrayOfDetails[arrayOfDetails.length - 2]);
		return numberOfTeams;
	}
	
	public static String getTeamName(int teamNumber, String teamFileName)
	{
		String teamName="";
		int arrayNum=0;
		int positionInArray=1;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		for(int i=0;i<arrayOfDetails.length;i++)
		{
			if(i%2==0)
			{
				arrayNum=Integer.parseInt(arrayOfDetails[i]);
				if(teamNumber==arrayNum)
					positionInArray=i+1;
			}	
		}	
		teamName=arrayOfDetails[positionInArray];
		return teamName;
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
				currentAdminNo = Integer.parseInt(item1);
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
	
	
	public static void checkIfExists(String fileName)throws IOException
	{
		File adminFile = new File(fileName);
		if (!(adminFile.exists()))
			adminFile.createNewFile();
	}
	
	
	public static int optionBoxs(String[] options,String whatYouWantItToSay)
	{
        int result = JOptionPane.showOptionDialog(null, whatYouWantItToSay, "League Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return result;
	}
	
	public static boolean validateNumberInput(String text)
	{
		String result="";
		String pattern="[0-9]{0,10}";
		String message1 = "A number must be input.";
	    String message2 = "Format of user input is incorrect, a number  is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        	{
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					}
				
					else
					{
					verified=true;
					}
				}
			}
		}
		return verified ;
	}

	 public static String[] readFile(String textFile, String searchedItem, int itemPositionNo, int returnedItemNo)  
	 {
		 String x="";
		 try
		 {
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText= in.nextLine();
		        String[] split = fileText.split(","); 
				if (split[itemPositionNo].equals(searchedItem))
				{
					// if true store the item in position returnedItemNo
					x += split[returnedItemNo]+",";
					
				}
				
			}
			in.close();
			reader.close();		
            
		 }
		 catch (Exception e)
		 {}
		 String[] returned = x.split(",");
		 return returned;
    } 
	public static void generateTable()throws IOException
	{
		boolean readFile; 
		readFile = readFilesIntoArrayLists();
		if (!readFile)
		System.out.println("One or more files do not exist.");
			else
			{
			createEmptyLeaderBoard();
			processResults();
			orderLeaderBoard();
			displayLeaderboard();
			}
	}
	
	 public static boolean readFilesIntoArrayLists() throws IOException
  {
    String filename1 = "Teams20152016.txt";
    String filename2 = "Fixtures20152016.txt";
    String filename3 = "Outcomes20152016.txt";
    
    String fileElements[];
	File inputFile1 = new File(filename1);
	File inputFile2 = new File(filename2);
	File inputFile3 = new File(filename3);
	
	teams = new ArrayList<ArrayList<String>>();
	teams.add(new ArrayList<String>());
    teams.add(new ArrayList<String>());
  
    fixtures = new ArrayList<ArrayList<Integer>>();
	fixtures.add(new ArrayList<Integer>());
    fixtures.add(new ArrayList<Integer>());
    fixtures.add(new ArrayList<Integer>());
    
    results = new ArrayList<ArrayList<Integer>>();
	results.add(new ArrayList<Integer>());
    results.add(new ArrayList<Integer>());
    results.add(new ArrayList<Integer>());
    
	if (inputFile1.exists() && inputFile2.exists() && inputFile3.exists())
	{
	  Scanner in;
	  in = new Scanner(inputFile1);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    teams.get(0).add(fileElements[0]);  
	    teams.get(1).add(fileElements[1]);  
	  } 
	  in.close();
	  in = new Scanner(inputFile2);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    fixtures.get(0).add(Integer.parseInt(fileElements[0]));  
	    fixtures.get(1).add(Integer.parseInt(fileElements[1]));  
	    fixtures.get(2).add(Integer.parseInt(fileElements[2]));  
	  } 
	  in.close();
	  in = new Scanner(inputFile3);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    results.get(0).add(Integer.parseInt(fileElements[0]));  
	    results.get(1).add(Integer.parseInt(fileElements[1]));  
	    results.get(2).add(Integer.parseInt(fileElements[2]));  
	  } 
	  in.close();
	  return true;
    }
    else
      return false;
  }
  
  public static void createEmptyLeaderBoard()
  {
	// find out the number of teams/players which will determine 
	// the number of rows  
    int rows = teams.get(0).size();
	int columns = 14;  
	leaderBoard = new int[rows][columns];
	// place team numbers in column 0 of leader board
	for (int i = 0; i < leaderBoard.length; i++)
      leaderBoard[i][0] = Integer.parseInt(teams.get(0).get(i));
  }	  
  
  public static void processResults()
  {
	int fixtureNumber, homeTeamScore, awayTeamScore, homeTeamNumber, awayTeamNumber;
	int position;
	for (int i = 0; i < results.get(0).size(); i++)  
    {
	  fixtureNumber  = results.get(0).get(i);
	  homeTeamScore  = results.get(1).get(i);
	  awayTeamScore  = results.get(2).get(i);
	  position       = fixtures.get(0).indexOf(fixtureNumber);
	  homeTeamNumber = fixtures.get(1).get(position);
	  awayTeamNumber = fixtures.get(2).get(position);
	  if (homeTeamScore == awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
	  }  
	  else if (homeTeamScore > awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);  
	  }  
	  else
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);
		recordFixtureResultForAwayTeam(awayTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);  
	  }    
    }
  }	 
  
  public static void recordFixtureResultForHomeTeam(int hTN, int w, int d, int l, 
                                                       int hTS, int aTS, int p)
  {
	leaderBoard[hTN-1][1]++;        			// gamesPlayed
	leaderBoard[hTN-1][2]+= w;      			// homeWin
	leaderBoard[hTN-1][3]+= d;      			// homeDraw
	leaderBoard[hTN-1][4]+= l;      			// homeLoss
	leaderBoard[hTN-1][5]+= hTS;    			// homeTeamScore
	leaderBoard[hTN-1][6]+= aTS;    			// awayTeamScore
	leaderBoard[hTN-1][12] += (hTS - aTS);    	// goalDifference
	leaderBoard[hTN-1][13] += p;    			// points
  }
 
  public static void recordFixtureResultForAwayTeam(int aTN, int w, int d, int l, 
                                                       int hTS, int aTS, int p)
  {
	leaderBoard[aTN-1][1]++;        			// gamesPlayed
	leaderBoard[aTN-1][7]+= w;      			// awayWin
	leaderBoard[aTN-1][8]+= d;      			// awayDraw
	leaderBoard[aTN-1][9]+= l;      			// awayLoss
	leaderBoard[aTN-1][10]+= aTS;    			// awayTeamScore
	leaderBoard[aTN-1][11]+= hTS;    			// homeTeamScore
	leaderBoard[aTN-1][12] += (aTS - hTS);    	// goalDifference
	leaderBoard[aTN-1][13] += p;    			// points  
  }	
  
  public static void orderLeaderBoard()
  {
	int [][] temp = new int[leaderBoard.length][leaderBoard[0].length];
    boolean finished = false;
    while (!finished) 
    {
      finished = true;
      for (int i = 0; i < leaderBoard.length - 1; i++) 
      {
        if (leaderBoard[i][13] < leaderBoard[i + 1][13])
        {
          for (int j = 0; j < leaderBoard[i].length; j++) 
          {
            temp[i][j]            = leaderBoard[i][j];
            leaderBoard[i][j]     = leaderBoard[i + 1][j];
            leaderBoard[i + 1][j] = temp[i][j];
          }
          finished = false;
        }
      }
    }
  }	  
	  
  public static void displayLeaderboard()
  {
	int aTeamNumber;
	String aTeamName, formatStringTeamName;
	String longestTeamName       = teams.get(1).get(0);
    int    longestTeamNameLength = longestTeamName.length();
    
    for (int i = 1; i < teams.get(1).size(); i++)
    {
	  longestTeamName = teams.get(1).get(i);  
      if (longestTeamNameLength < longestTeamName.length())
        longestTeamNameLength = longestTeamName.length();
    }
    formatStringTeamName = "%-" + (longestTeamNameLength + 2) + "s";
    System.out.printf(formatStringTeamName,"Team Name");
    System.out.println("  GP  HW  HD  HL  GF  GA  AW  AD  AL  GF  GA   GD   TP"); 
   
    for (int i = 0; i < leaderBoard.length; i++)
    {
	  aTeamNumber       = leaderBoard[i][0];
	  aTeamName         = teams.get(1).get(aTeamNumber - 1);       
      System.out.printf(formatStringTeamName, aTeamName);
      System.out.printf("%4d", leaderBoard[i][1]);
      System.out.printf("%4d", leaderBoard[i][2]);
      System.out.printf("%4d", leaderBoard[i][3]);
      System.out.printf("%4d", leaderBoard[i][4]);
      System.out.printf("%4d", leaderBoard[i][5]);
      System.out.printf("%4d", leaderBoard[i][6]);
      System.out.printf("%4d", leaderBoard[i][7]);
	  System.out.printf("%4d", leaderBoard[i][8]);
      System.out.printf("%4d", leaderBoard[i][9]);
      System.out.printf("%4d", leaderBoard[i][10]);
      System.out.printf("%4d", leaderBoard[i][11]);
      System.out.printf("%5d", leaderBoard[i][12]);
      System.out.printf("%5d", leaderBoard[i][13]);
      System.out.println();
    }
  } 


	

}
