//package project2;

/*
 * 
 * Name: grivera64
 * CSC123-80
 * Project 2
 * 
 * Program: ISBNVerifier.java
 * 
 * -----------------------------
 * 
 * Write a program that prompts the user for a file containing ISBN numbers and then checks if 
 * each the ISBN is correct.
 * I have posted the isbnCodes.txt file in the Program Projects Answers/Project 2 tab 
 * on the web site and you may use it to test your results
 * Here is a complete description of what I want you to do:
 * 
 * In main: 
 * Create a Scanner to read in the name of the input file as a String.
 * Call a method createScanner with the parameter of the String file. 
 * 		createScanner should return a reference to a scanner that allows you 
 * 		to read from the input file.
 * 
 * For each ISBN code in the file 
 * 		Read in the potential ISBN codes one at a time as a StringBuilder. 
 * 		Call checkISBN with the reference to StringBuilder object you have read in. 
 * 		checkISBN returns a String reference with a message telling whether 
 * 		this is a valid code, and if not why not.
 * 		Print the message to the screen , along with the ISBN number entered.
 * 		(see sample output when using the file I have provided).
 * Close the Scanner used to read he file.
 * 
 * In createScanner: 
 * Accept the reference to the file name as a String.
 * Create a Scanner object that reads from that file.
 * Be sure to check for the file’s existence.
 * Return a reference to the created Scanner
 * 
 * In checkISBN:
 * Perform the following checks.
 * If a check leads to an error, return a message listing the error.
 * Below is my suggested order.
 * 
 * Here are steps you may follow:
 * Check that total length is 13.
 * Make sure the 12th position is a digit or X
 * Make sure that there are exactly three ‘-‘ in the code.
 * Check to make sure that each character except the last is a digit or a dash.
 * Extract the nine digits in order
 * Calculate the check digit.
 * If the check digit matches as described above, return a message indicating a good ISBN,
 * 		otherwise return a message indicating that a bad ISBN was created
 * 		because the digit doesn’t match 
 * 
 * Helpful Hint: Here is an example of how to convert the char to an int:
 * char x = '9';
 * int y = x - '0'; // gives 9 remember the subtraction will cast up to int and 
 * 					//the int characters are sequential in UNICODE.
 * 
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class ISBNVerifier
{
	
	public static void main(String[] args) throws IOException
	{
		
		//create keyboard input scanner
		final Scanner keyboard = new Scanner(System.in);
		
		//declaring local variables
		boolean tryAgain = false;
		Scanner inFile;
		
		//asking user for a valid file to read from
		do
		{
			if (tryAgain)
			{
				System.out.printf("Please try again...\n");
				tryAgain = false;
			}
			
			//creating the scanner for the file if it exists
			System.out.printf("Please enter the file name: ");
			inFile = createScanner(keyboard.nextLine());
			
			tryAgain = (inFile == null) ? true : false;
			
			System.out.printf("\nFile %s exist!\n\n",
					(tryAgain) ? "does not" : "does");
			
		
		} while(tryAgain);
		
		//go through each ISBN
		while (inFile.hasNext())
		{
			StringBuilder currLine = new StringBuilder(inFile.nextLine());
			System.out.printf("ISBN %s yields: %s\n\n", currLine, checkISBN(currLine));
			
		}
		
	}
	
	public static Scanner createScanner(String input) throws IOException
	{
		//opening file if possible
		File fileDir = new File(input);
		
		//return the scanner for the opened file if it exists, otherwise return a null scanner
		return (fileDir.exists()) ? new Scanner(fileDir) : null;
		
	}
	
	public static String checkISBN(StringBuilder data)
	{
		
		//get rid of whitespace at front and end
		data.trimToSize();
		
		//check if 13 digits long
		if (data.length() != 13)
		{
			return "ISBN's size is not valid!";
		}
		
		//check if the last character is a digit or 'x'
		if (!(Character.isDigit(data.charAt(12)) || 
				data.charAt(12) == 'X'))
		{
			return "ISBN doesn't end with a valid character";
		}
		
		//check if the ISBN has 3 "-"'s (4 total groupings)
		String[] array = data.toString().split("-");
		
		if (array.length != 4)
		{
			return "ISBN doesn't have 3 \'-\' in it";
		}
		
		//check if the groupings are digits (uses more memory, but takes less time)
		//----------------------------------------------------------
		StringBuilder separateString = new StringBuilder();
		
		for (int index = 0; index <= 3; index++)
		{
			try
			{
				if (!(array[index].equalsIgnoreCase("X") || Long.parseLong(array[index]) >= 0))
				{
					return "ISBN isn't all valid charcters";
				}
				else
				{
					separateString.append(array[index]);
				}
			}
			catch (NumberFormatException e)
			{
				return "ISBN isn't all numbers";
			}
		}
		
		//-----------------------------------------------------------
		
		//local variables
		int multiple = 10,
			sum = 0,
			checkNum = 0;
		
		//find the checkNum
		for (char symbol : separateString.toString().toCharArray())
		{
			sum += multiple * (symbol - '0');
			//System.out.println("DEBUG SUM: " + sum);
			multiple--;
			
			if (multiple < 2)
			{
				//System.out.println("DEBUG: Final SUM: " + sum);
				checkNum = 11 - (sum % 11);
				//System.out.printf("DEBUG: (%d %% 11) => 11 - %d = %d\n",
				//		sum, sum % 11, checkNum);
				break;
			}
		}
		
		//System.out.printf("DEBUG: %s\n", separateString);  //might need to be changed
		//System.out.println("DEBUG CHECKNUM: " + checkNum); //in the future
		
		//determine if the ISBN matches the checkNum
		if ((checkNum == 10) == (data.charAt(12) == 'X'))
		{
			//return valid if all checks were passed
			return "ISBN is valid";
		}
		else
		{
			return "ISBN's last digit doesn't match the checkNum";
		}
		
	}
	
}