import java.io.*;
import javax.swing.*;
import java.util.*;
public class League
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
	if(isLoggedIn)
	{
	String [] initialOptions= { "1-create league", "2-Edit/view League", "3-Remove League", "4-Exit Application" };
		 String [] subOptions={" 1-Fixture Generation", "2-View Table", "3-Input results", "4-Add/remove teams", "5-Exit Application"};
		String []subOptionsOfSubOptions={ "1-Add teams", "2-remove teams","3-Exit Application"};
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
					case 1: outputBoxs("yes");//displayTable();
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
		boolean isRightFormat=true;
		int x=0;
		isRightFormat=validateNumberInput(text);
		if(isRightFormat==true)
		{
		x = Integer.parseInt(text);
		}
		return x;
	}
	
	public static void createNewLeague()throws IOException
	{
		String leagueName=""; 
		String leagueFileInput="";
		File leagueFilers = new File(leagueFile);
		if (!(leagueFilers.exists()))
		{
			leagueFilers.createNewFile();
			leagueName=menuBox("Enter your league name:");
			if (leagueName != null && !leagueName.equals(""))
			{
			leagueFileInput=currentAdminNo+","+leagueName+",1";
			writeFile(leagueFileInput,leagueFile);
			addTeamsToLeague();
			outputBoxs(leagueName+" has been created.");
			}
			else
			{
				outputBoxs("You must enter a team name.");
				//delete League file here then.
			}
		}
		else
		{
			leagueName=menuBox("Enter your league name:");
			if (leagueName != null && !leagueName.equals(""))
			{
			leagueFileInput=currentAdminNo+","+leagueName+","+(getNumberOfLeaguesMade()+1);
			writeFile(leagueFileInput,leagueFile);
			addTeamsToLeague();
			outputBoxs(leagueName+" has been created.");
			}
			else
			{
			outputBoxs("you must enter a team name.");
			}
		}
	}
	
	public static String readFile(String textFile)
	{
		String fileText="";
		String temp="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			temp=in.nextLine();
			fileText=fileText+temp+",";
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
	String [] selectionOfLeagues=readFile(leagueFile,"1",0,1);
	int whichLeague=optionBoxs(selectionOfLeagues,"Select a league:");
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
		String pattern="[0-9]{1,2}";
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
}
