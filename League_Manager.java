import java.io.*;
import javax.swing.*;
import java.util.*;
public class finalLeague
{
	private static int currentAdminNo;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	private static String item1;
	public static ArrayList<ArrayList<String>>  teams;
    public static ArrayList<ArrayList<Integer>> fixtures;	
    public static ArrayList<ArrayList<Integer>> results;
    public static int [][] leaderBoard;
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static void main(String [] args)throws IOException
	{
	checkIfExists(adminFile);
	String username = JOptionPane.showInputDialog(null, "Enter username");
	String password = JOptionPane.showInputDialog(null, "Enter password"); //Will work on method for hidden password input in swing
	boolean isLoggedIn = loginMethod(username, password);
	if(isLoggedIn)
	{
	String [] initialOptions= { "1-create league", "2-Edit/view League", "3-Remove League" };
		 String [] subOptions={" 1-Fixture Generation", "2-View Table", "3-Input results", "4-Add teams","5-Back to Main Menu"};
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
					case 1: String leagueNumber = JOptionPane.showInputDialog(null, "Enter league number to edit");
					int league=Integer.parseInt(leagueNumber);
					generateTable(league);//displayTable();
					break;
					case 2:String leagueNum = JOptionPane.showInputDialog(null, "Enter league number to edit");
					       editResults(leagueNum);  
				    break;
					case 3: String leagueNumbers = JOptionPane.showInputDialog(null, "Enter league number to edit");
					int leagues=Integer.parseInt(leagueNumbers);
					addTeamsToLeague(leagues);
					break;
					case 4: break;
				}
                break;
			
				case 2: removeLeague(Integer.toString(currentAdminNo));//removeLeague();
				break;
				}
			}
		}
		
	}
	
	 /**
	   *
	   *
	   *
	   *
	   **/		 
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
	 /**
	   *
	   *
	   *
	   *
	   **/		 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		if(!(input != null))
		 {
			outputBoxs("You must enter a word/number");
			input=menuBox(Options);
		 }
		else if(input.equals(""))
		 {
			outputBoxs("You must enter a word/number");
			input=menuBox(Options);
		 }
		 else if(input.contains(","))
		 {
			 outputBoxs("You have entered a invalid character");
			input=menuBox(Options);
		 }
		 return input;
	 }
	 /**
	   *
	   *
	   *
	   *
	   **/	 
	 public static int menuBoxInt(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		int x = Integer.parseInt(text);
		return x;
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static void createNewLeague()throws IOException
	{
		String leagueName=""; 
		boolean added;
		String leagueFileInput="";
		File leagueFilers = new File(leagueFile);
		if (!(leagueFilers.exists()))
		{
			leagueFilers.createNewFile();
			leagueName=menuBox("Enter your league name:");
			if (!leagueName.equals(""))
			{
			added=addTeamsToLeague(1);
			if(added)
			{
			leagueFileInput=currentAdminNo+","+leagueName+",1";
			writeFile(leagueFileInput,leagueFile);
			outputBoxs(leagueName+" has been created.");
			}
			}
			else
			{
				outputBoxs("You must enter a team name.");
				deleteFile(leagueFile);
			}
		}
		else
		{
			leagueName=menuBox("Enter your league name:");
			if (!leagueName.equals(""))
			{
			added=addTeamsToLeague(getNumberOfLeaguesMade()+1);
			if(added)
			{
			leagueFileInput=currentAdminNo+","+leagueName+","+(getNumberOfLeaguesMade()+1);
			writeFile(leagueFileInput,leagueFile);
			outputBoxs(leagueName+" has been created.");
			}
			}
			else
			{
			outputBoxs("you must enter a team name.");
			}
		}
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static int getNumberOfLeaguesMade()
	{
		int numberOfLeagues=0;
		String adminNumberAsString="";
		adminNumberAsString=adminNumberAsString+currentAdminNo;
		String [] temp=readFile(leagueFile,adminNumberAsString,0,2);
		if(temp[temp.length-1].equals(""))
		{
			numberOfLeagues=0;
		}
		else
		{
			numberOfLeagues=Integer.parseInt(temp[temp.length-1]);
		}
		return numberOfLeagues;
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static String readFile(String textFile)
	{
		String fileText="";
		String x="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			x=in.nextLine();
			fileText=fileText+x+",";
		}
		in.close();
		reader.close();
		}
		catch(Exception e)
		{}
		return fileText;
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static void outputBoxs(int output)
	{
		JOptionPane.showMessageDialog(null,output);
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static boolean addTeamsToLeague(int whichLeagues)throws IOException
	{
		boolean added=false;
		String info=""; 
		String teamName=""; 
		String teamFileInfo=""; 
		String teamFileName="";
		int whichLeague=whichLeagues;
		teamFileName=currentAdminNo+"_"+whichLeague+"_participants.txt";
		File teamFile = new File(teamFileName);
		if (!(teamFile.exists()))
		{
			String numberOfTeams=menuBox("Enter the amount of teams/players:");
			boolean isRightFormat=validateNumberInput(numberOfTeams);
			if(isRightFormat)
			{
				int numberOfTeams2=Integer.parseInt(numberOfTeams);
				if(numberOfTeams2>1)
				{
				for(int i=0;i<numberOfTeams2;i++)
				{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
				}
				added=true;
				}
				else
				{
					outputBoxs("number must be between 2 and 99");
				}
		    }
		}
		else
		{
		outputBoxs("Warning this will regenerate fixtures if you have already done so.");
		String numberOfTeams=menuBox("Enter the amount of teams/players:");
		boolean isRightFormat=validateNumberInput(numberOfTeams);
		if(isRightFormat)
		{
		int numberOfTeams2=Integer.parseInt(numberOfTeams);
		int counter=getNumberOfTeams(teamFileName);
		if(counter+numberOfTeams2>99)
		{
			outputBoxs("cannot have more than 99 teams");
		}
		else
		{
			for(int i=0;i<numberOfTeams2;i++)
			{
				info="enter team/player number:"+(counter+1);
				teamName=menuBox(info);
				teamFileInfo=(counter+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
				counter++;
				
			}
			added=true;
			outputBoxs("please reselect your league");
			fixtureGeneration();
		}
		}
		}
		return added;
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static void fixtureGeneration()throws IOException
	{
	int numberOfTeams, totalNumberOfRounds, numberOfMatchesPerRound;
    int roundNumber;
	boolean added;
	String currentAdminNoAsString="";
	currentAdminNoAsString=currentAdminNoAsString+currentAdminNo;
	int matchNumber=0;
	int homeTeamNumber, awayTeamNumber, even, odd;
    boolean additionalTeamIncluded = false;
	String [] selectionOfLeagues=readFile(leagueFile,currentAdminNoAsString,0,1);
	String whichLeaguer=dropDown(selectionOfLeagues,"Select a League");
	if(whichLeaguer.equals(""))
	{
		outputBoxs("you have not selected a league or no leagues exist.");
	}
	else
	{
	String [] whichLeagues=readFile(leagueFile,whichLeaguer,1,2);
	int whichLeague=Integer.parseInt(whichLeagues[0]);
	String teamFileName=currentAdminNo+"_"+whichLeague+"_participants.txt";
	String fixtureGenerationFileName=currentAdminNo+"_"+whichLeague+"_fixtures.txt";
	File teamFile = new File(teamFileName);
	File fixtureFile = new File(fixtureGenerationFileName);
	String [][] fixtures;
    String [][] revisedFixtures;
    String []   elementsOfFixture;
    String fixtureAsText;
	String info="";
	if(fixtureFile.exists())
	{
		outputBoxs("Fixtures alredy exist for this league, it will now be regenerated");
		deleteFile(fixtureGenerationFileName);
		if (!(teamFile.exists()))
	{
		outputBoxs("There is no teams in this league.");
		added=addTeamsToLeague(whichLeague);
	}
	else
	{
	int selection=getNumberOfTeams(teamFileName);	
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
	else
	{
	if (!(teamFile.exists()))
	{
		outputBoxs("There is no teams in this league.");
		added=addTeamsToLeague(whichLeague);
	}
	else
	{
	int selection=getNumberOfTeams(teamFileName);	
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
}
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static int getNumberOfTeams(String teamFileName)
	{
		int numberOfTeams=0;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		numberOfTeams=Integer.parseInt(arrayOfDetails[arrayOfDetails.length - 2]);
		return numberOfTeams;
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
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
	 /**
	   *
	   *
	   *
	   *
	   **/	
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
	 /**
	   *
	   *
	   *
	   *
	   **/	
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
	 /**
	   *
	   *
	   *
	   *
	   **/	
	
	public static void checkIfExists(String fileName)throws IOException
	{
		File adminFile = new File(fileName);
		if (!(adminFile.exists()))
			adminFile.createNewFile();
	}
	 /**
	   *
	   *
	   *
	   *
	   **/
	public static boolean checkIfItExists(String fileName)throws IOException
	{
		File file = new File(fileName);
		boolean found =false;
		if ((file.exists()))
		{
			return true;
		}
			
			else return false;
			
	}
	
	 /**
	   *
	   *
	   *
	   *
	   **/	
	public static int optionBoxs(String[] options,String whatYouWantItToSay)
	{
        int result = JOptionPane.showOptionDialog(null, whatYouWantItToSay, "League Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return result;
	}
	 /**
	   *
	   *
	   *
	   *
	   **/	
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
	 /**
	   *
	   *
	   *
	   *
	   **/
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
	   *
	   *
	   *
	   *
	   **/	
	public static void generateTable(int leagueNumber)throws IOException
	{
		boolean readFile; 
		readFile = readFilesIntoArrayLists(leagueNumber);
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
	 /**
	   *
	   *
	   *
	   *
	   **/	
	 public static boolean readFilesIntoArrayLists( int leagueNumber) throws IOException
  {
    String filename1 = currentAdminNo+"_"+leagueNumber+"_participants.txt";
    String filename2 = currentAdminNo+"_"+leagueNumber+"_fixtures.txt";
    String filename3 = currentAdminNo+"_"+leagueNumber+"_Results.txt";
    
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
	 /**
	   *
	   *
	   *
	   *
	   **/  
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
	 /**
	   *
	   *
	   *
	   *
	   **/  
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
 	 /**
	   *
	   *
	   *
	   *
	   **/                                                      int hTS, int aTS, int p)
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
 	 /**
	   *
	   *
	   *
	   *
	   **/
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
	 /**
	   *
	   *
	   *
	   *
	   **/  
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
	 /**
	   *
	   *
	   *
	   *
	   **/	  
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
    //System.out.println("  GP  HW  HD  HL  GF  GA  AW  AD  AL  GF  GA   GD   TP");
    System.out.printf("%5s" ,"GP");
    System.out.printf("%5s" ,"HW");
    System.out.printf("%5s" ,"GP");
    System.out.printf("%5s" ,"GP");
      System.out.printf("%5s" ,"GP");
        System.out.printf("%5s" ,"GP");
          System.out.printf("%5s" ,"GP");
            System.out.printf("%5s" ,"GP");
              System.out.printf("%5s" ,"GP");
                System.out.printf("%5s" ,"GP");
                  System.out.printf("%5s" ,"GP");
                    System.out.printf("%6s" ,"GP");
                      System.out.printf("%6s" ,"GP");
      
      System.out.println();
   
    for (int i = 0; i < leaderBoard.length; i++)
    {
	  aTeamNumber       = leaderBoard[i][0];
	  aTeamName         = teams.get(1).get(aTeamNumber - 1);      
      System.out.printf(formatStringTeamName, aTeamName);
      System.out.printf("%5d", leaderBoard[i][1]);
      System.out.printf("%5d", leaderBoard[i][2]);
      System.out.printf("%5d", leaderBoard[i][3]);
      System.out.printf("%5d", leaderBoard[i][4]);
      System.out.printf("%5d", leaderBoard[i][5]);
      System.out.printf("%5d", leaderBoard[i][6]);
      System.out.printf("%5d", leaderBoard[i][7]);
	  System.out.printf("%5d", leaderBoard[i][8]);
      System.out.printf("%5d", leaderBoard[i][9]);
      System.out.printf("%5d", leaderBoard[i][10]);
      System.out.printf("%5d", leaderBoard[i][11]);
      System.out.printf("%6d", leaderBoard[i][12]);
      System.out.printf("%6d", leaderBoard[i][13]);
      System.out.println();
    }
  } 
	 /**
	   *
	   *
	   *
	   *
	   **/  
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
	 /**
	   *
	   *
	   *
	   *
	   **/
	public static String[] readFixtures(String leagueChoice)
	{
		ArrayList<ArrayList<String>> teams = new ArrayList<ArrayList<String>>();
		teams.add(new ArrayList<String>());
		teams.add(new ArrayList<String>());
		String homeTeam = "", awayTeam = "";
		String currentLeagueFixtures = currentAdminNo+"_"+leagueChoice + "_fixtures.txt";
		String currentLeagueParticipants = currentAdminNo+"_"+leagueChoice + "_participants.txt";
		String [] fileElements;
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
				fixtureCount++;
				fileElements = in.nextLine().split(",");
				teams.get(0).add(fileElements[1]);
				teams.get(1).add(fileElements[2]);
			}
			read.close();
			in.close();
			
			fixtureDisplay = new String[fixtureCount];
			for (int i = 0;i < fixtureCount;i++)
			{
				homeTeam = getTeamName(Integer.parseInt(teams.get(0).get(i)), currentLeagueParticipants);
				awayTeam = getTeamName(Integer.parseInt(teams.get(1).get(i)), currentLeagueParticipants);
				fixtureDisplay[i] = (homeTeam + " V " + awayTeam);
				System.out.print(fixtureDisplay[i]);
			}
		}
		
		catch(Exception e)
		{}
	return fixtureDisplay;	
	}
	 /**
	   *
	   *
	   *
	   *
	   **/
	public static void editResults(String leagueNumber) throws IOException
	//FIX JOP DISPLAY OF RESULTS***************
	{
		String fixture=currentAdminNo+"_"+leagueNumber+"_fixtures.txt";
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
		String resultsFileName = currentAdminNo+"_"+leagueNumber + "_Results.txt";
		fixtureChoice = JOptionPane.showOptionDialog(null, "Choose fixture to edit", "Click button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, fixtureDisplay, fixtureDisplay[0]); //MAY CHANGE GUI
		matchNumberChoice = Integer.toString(fixtureChoice);
		resultExists = readFile(resultsFileName, Integer.toString(fixtureChoice+1), 0);
		if (resultExists == true)
		{		
			choice = JOptionPane.showConfirmDialog(null, "Already entered result for this fixture, Do you want to edit the result?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);	
			if (choice == JOptionPane.YES_OPTION) //If yes
			{
			removeLineFromFile(resultsFileName, Integer.toString(fixtureChoice+1), 0);
			
			homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
			while (!(homeScore.matches(pattern)))
				homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
			awayScore = JOptionPane.showInputDialog(null, "Enter away score:");
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
			homeScore = JOptionPane.showInputDialog(null, "Invalid input\nEnter home score:");
			while (!(homeScore.matches(pattern)))
				homeScore = JOptionPane.showInputDialog(null, "Enter home score:");
			awayScore = JOptionPane.showInputDialog(null, "Enter away score:");
			while (!(awayScore.matches(pattern)))
				awayScore = JOptionPane.showInputDialog(null, "Invalid input\nEnter away score:");
		
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
	 /**
	   *
	   *
	   *
	   *
	   **/
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
		
	 /**
	   *
	   *
	   *
	   *
	   **/
	
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
	 /**
	   *
	   *
	   *
	   *
	   **/
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
		
	 /**
	   *
	   *
	   *
	   *
	   **/
	
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
	 /**
	   *
	   *
	   *
	   *
	   **/
public static void removeLeague(String adminNumber)
	 {
		 // load all the leagues that a admin has access to into a array
                 String [] temp=readFile("league.txt",adminNumber,0,1);		// adminNumber 
		 // pass array with all of adminNumber's Leagues into dropdown
		 String leagueToRemove = dropDown(temp,"Choose a league to remove.");
		
		 // load league names into arraylist except the one league you wish to remove. (!leagueToRemove)
		 ArrayList<String> leagues = new ArrayList<String>();
		 int idNumber=0;
		 try{
		 Scanner s = new Scanner(new File("league.txt"));
		 String[] details;
		
		 while(s.hasNext())
		 {
			 // check for leagueToRemove if not in the current line add to arraylist
			 String v = s.next();
			 details = v.split(",");
			 if (details[1].equals(leagueToRemove))
			 {
				 //get the league's ID number: details[2] : needed for removing 3_scoring, 3_participants etc
				 idNumber = Integer.parseInt(details[2]);
			 }
			 else
			 {
				 leagues.add(v);
			 }
		 }
		 s.close();
		 }
		 catch(IOException e){}
		 
		 // Rewriting the leagues file with the arraylist with the desired league removed
		 try{
		 PrintWriter output = new PrintWriter("league.txt"); 
                 for (int i = 0; i<leagues.size();i++)
		 {
			 output.println(leagues.get(i));
			 //System.out.println(leagues.get(i));
		 }
                 output.close();
		 }
		 catch(IOException e)
		 {}
		 
		 
		 deleteFile(adminNumber+"_"+idNumber+"_scoring.txt");
		 deleteFile(adminNumber+"_"+idNumber+"_participants.txt");
		 deleteFile(adminNumber+"_"+idNumber+"_fixtures.txt");
		 deleteFile(adminNumber+"_"+idNumber+"_results.txt");
		 
	         ///CALL DELETEFILE METHOD FOR THE ABOVE
		 ///  deleteFile(idNumber+"_results.txt");
                 ///  Awaiting guidance on the new file structure before adding it in.		 
		 /// reading the file and if the line is not something you want to remove then add to arraylist - else dont add to arraylist
     }
	 /**
	   * Creates a dropdown menu with a list of options to choose, returns the string of the value the user choose
	   * String[] options is the list of things you want the user to choose from
	   * String dialogText is the message you want displayed to guide the user on the dropdown box
	   **/
	 public static String dropDown(String[] options, String dialogText)
	 {
		 String selected="";
		 if(options.length==0)
		 {
			 
		 }
		 else
		 {
		 selected = (String) JOptionPane.showInputDialog(null, dialogText,"Input",1,null,options,options[0]);  
		 }
		 return selected;
	 }
	 /**
	   * Delete File removes files from the users computer.
	   * Input - String of the file name that you want removed.
	   * Output - Void no output to the program but removes file from the system computer.
	   *
	   **/
	 public static void deleteFile(String deleteFile)
	{
	    String filename = deleteFile;  
        File aFile = new File(filename);
            if (!(aFile.exists()))
                outputBoxs(aFile.getName() + " does not exist.");
            else if(aFile.delete())
                outputBoxs(aFile.getName() + " is now deleted.");
            else
                outputBoxs("Operation to delete file failed.");
    }
	 /**
	   *
	   *
	   *
	   *
	   **/
	 public static void addScoringScheme(int leagueNumber)
	{
		
		int win = menuBoxInt("Enter the number of points given for a win:");
		int draw = menuBoxInt("Enter the number of points given for a Draw:");
		int lose = menuBoxInt("Enter the number of points given for a Lose:");
		
		
		String fileName = (leagueNumber+"_scoring.txt");
		String output = (win+","+draw+","+lose);
		writeFile(fileName, output);	
	}
		
}
