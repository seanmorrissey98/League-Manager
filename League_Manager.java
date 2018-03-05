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
	
	public static String [] getTeamScore(int fixtureNumber, String resultsFileName)
	{
		int arrayNum=0;
		int positionInArray=1;
		String [] arrayOfDetails=readFile(resultsFileNameName).split(",");
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
