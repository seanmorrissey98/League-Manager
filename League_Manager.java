import java.io.*;
import javax.swing.*;
import java.util.*;
public class League_Manager 
{
    
	 private static int currentAdminNo=1;
	 private static int leagueNo=1;
	 final static String leagueFile="league.txt";
	 final static String adminFile="administrator.txt";
     
	 public static void main(String[] args)
	 {	
	     String initialOptions= " 1-create league \n 2-Edit/view League \n 3-Remove League \n 4-Exit Application ";
		 String subOptions=" 1-Fixture Generation \n 2-View Table 3-Input results \n 4-Add/remove teams \n 5-number of leagues made";
		 String subOptionsOfSubOptions=" 1-Add teams 2-remove teams";
	     // have these as arrays give a nicer finish? - use optionboxs method
         // include pauls code for main method here
		 
		 
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
     public static void fileWriter(String filename, String output)
	 {
     try
     {
	     FileWriter aFileWriter = new FileWriter(filename,true);
         PrintWriter out = new PrintWriter(aFileWriter);			 
	     out.print("\n" + output); 
	     out.close();
		 aFileWriter.close();
     }
	 catch(Exception e)
	 {}
	 }
	 
	 // sean's readfile  - was readFile -- changed to readFile1 in the method commented below too
	 	public static String readFile1(String textFile)
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
	
	
	
	/*
    ///////  work in progress still ? i think
	public static void addTeamsToLeague()
	{
		String info=""; String teamName=""; String teamFileInfo=""; String teamFileName="";
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:"); // users dont know what number their leagues are!!!!!!
		teamFileName=whichLeague+"_"+"participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague) // getnumber - ie 5 leagues made - but whichLeague is 10
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
				writeFile(teamFileInfo,teamFileName); // writeFile or fileWriter !!
			}
		}
	*/

	//=========
	
    /*
     	public static void createNewLeague()  // need to call the new writefall method
	{
		String leagueName=""; String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+leagueNo;
		writeFile(leagueFileInput,leagueFile);
	    leagueNo++;
	}
    */ 	
	
	//======
	
	/*
	// is this giving a list of leagues he has or just counting the amount he made
	public static int getNumberOfLeaguesMade()  // can the new readFile method be used? 
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0; 
		int temp=0;
		int numberOfLeagues=0;
		String [] arrayOfDetails=readFile1(leagueFile).split(",");
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
	} */
	
	 ///===========
	 
	 
	 /*
	 
	 	 ///////  work in progress still ? i think
	public static void addTeamsToLeague()
	{
		String info=""; String teamName=""; String teamFileInfo=""; String teamFileName="";
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:"); // users dont know what number their leagues are!!!!!!
		teamFileName=whichLeague+"_"+"participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague) // getnumber - ie 5 leagues made - but whichLeague is 10
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
				writeFile(teamFileInfo,teamFileName); // writeFile or fileWriter !!
			}
		}
     }
	 
	 */
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
	
	
	
	
	
	
	 
	
	
	

	

}



