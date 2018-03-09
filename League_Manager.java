// Paul's code so far.Got the gui roughlygoing.Only made up mock versions of methods to make sure it works.
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.*;
public class gui
{
	public static void main(String[]args)
	{   
        String initialOptions= " 1-create league \n 2-Edit/view League \n 3-Remove League \n 4-Exit Application ";
		String subOptions=" 1-Fixture Generation \n 2-View Table 3-Input results \n 4-Add/remove teams \n 5-number of leagues made";
		String subOptionsOfSubOptions=" 1-Add teams 2-remove teams";
		
		
	
		//if (logIn("Enter your username and password"))=true; // thomas method
		//{
			int x=menuBoxInt(initialOptions);
			if (x== 1)
			{
				createNewLeague();
			}
			if (x==2)
			{
				int y=menuBoxInt(subOptions);
				if (y==1)
				{
					String r=menuBox("genfix");
					generateFixtures(r);
				}
				if (y==2)
				{
					String v=menuBox("gentable");
					generateTable(v);
				}
				if (y==3)
				{
					String r=menuBox("displayTable");
					displayTable(r);
				}
				if (y==4)
				{
					int z=menuBoxInt(subOptionsOfSubOptions);
					if (z==1)
					{
					 String r=menuBox("addTeamsToLeague");
					 addTeamsToLeague(r);	
					}
					if (z==2)
					{
						String r=menuBox("addTeamsToLeague");
						removeTeamsFromLeague(r);
					}
				}
				if (y==5)
				{
					getNumberOfLeaguesMade();
				}
			}
			
			if (x==3)
			{
				String r=menuBox("addTeamsToLeague");
				removeLeague(r);
			}
			if (x==4)
			{
				System.exit(0);
			}
			
				else 
				{
					outputBoxs("Number outside 1-5");
				}
		}
			
			
		//}
	
	
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

