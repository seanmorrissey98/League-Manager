import java.io.*;
import javax.swing.*;
import java.util.*;
public class League
{
	private static int currentAdminNo=1;
	private static int leagueNo=1;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	private static String item1;
	
	public static void main(String [] args)throws IOException
	{
	checkIfExists(adminFile);
	String username = JOptionPane.showInputDialog(null, "Enter username");
	String password = JOptionPane.showInputDialog(null, "Enter password"); //Will work on method for hidden password input in swing
	boolean isLoggedIn = loginMethod(username, password);
	createNewLeague();
	//fixtureGeneration();
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
	
	public static void addTeamsToLeague()
	{
		String info=""; 
		String teamName=""; 
		String teamFileInfo=""; 
		String teamFileName="";
		int whichLeague=getNumberOfLeaguesMade()+1;
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
	
	public static String [] getLeagueNames(int adminNo)
	{
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		String [] leagueNames= new String [getNumberOfLeaguesMade()];
		int counter=0;
		int adminPosition=0;
		int adminLastPosition=0;
		boolean found=false;
		boolean sameAdmin=true;
		int temp=0;
		for(int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==adminNo)
			{
				found=true;
				adminPosition=i;
			}
		}
		for(int i=adminPosition;i<arrayOfDetails.length&&found==true;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp!=adminNo)
			{
				found=false;
				adminLastPosition=i;
			}

		}
		for(int i=adminPosition;i<arrayOfDetails.length&&i<adminLastPosition&&sameAdmin==true;i=i+3)
		{
			leagueNames[counter]=arrayOfDetails[i+1];
			counter++;
		}
		return leagueNames;
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

}
