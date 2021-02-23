//package project2;

/*
 * 
 * Name: grivera64
 * CSC123-80
 * Project 2
 * 
 * Program: SalesAnalysis.java
 * ---------------------------------------
 * 
 * Write a program SalesAnalysis That does the following.
 * You can use the salesdata.txt file in the Program Projects Answers/Project 2 tab for input.
 * The data file will have a set of comma delimited values of type double 
 * 
 * In main:
 * 
 * Create a Scanner to read input from the keyboard.
 * Request the name of the input file to read data from.
 * While there is data in the file
 * 		Read in a line of data as a String
 * 		Increment the week number, which was initialized to 0.
 * 		Call getWeeklySales with the input line, returning the sum of the numbers as a double
 * 		Print out the total sales for the week.
 * 		Print out the average for the week (assume that there are sales all seven days) 
 * 		Accumulate the total weekly sales 
 * 		Determine highest to lowest so far 
 * 
 * Close the Scanner that read in the data
 * 
 * Print out the additional data as shown in the Sample output below. 
 * 
 * 
 * getWeeklySales method:
 * 
 * Accepts a String with 7 decimal numbers delimited by a comma
 * Trim the white space
 * Tokenize the String in an array of type String
 * Sum together the elements using the Double.parseDouble method.
 * Return the sum.
 * 
 */


import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class SalesAnalysis
{
	
	public static void main(String[] args) throws IOException
	{
		
		//create scanner for input
		final Scanner keyboard = new Scanner(System.in);
		
		//local variables
		String fileName;
		File fileDir;
		Scanner inFile;
		int weekNum = 0,
			highest = 0,
			lowest = 0;
		double sum = 0.0,
			oldSum = 0.0,
			totalSales = 0;
		
		//ask for the file name until the file exists
		do
		{
			System.out.printf("Please enter the name of the input file: ");
			fileName = keyboard.nextLine();
			
			fileDir = new File(fileName);
			
			if (!fileDir.exists())
			{
				System.out.printf("Please try again...\n\n");
			}
			else
			{
				//System.out.printf("File exists!\n\n"); //Not in original
			}
		} while (!fileDir.exists());
		
		//open the existent file
		inFile = new Scanner(fileDir);
		
		//read the existent file
		while (inFile.hasNext())
		{
		
			//read one line
			String currLine = inFile.nextLine();
			
			//current week number
			weekNum++;
			
			//find the weekly sale
			oldSum = sum;
			sum = getWeeklySales(currLine);
			System.out.printf("The total sales for week %d: $%,.2f\n"
					+ "The average daily sales: $%,.2f\n\n", 
					weekNum, sum, sum / 7.0);
			
			//add to total sales
			totalSales += sum;
			
			//determine the highest and lowest week
			if (sum > oldSum)
			{
				highest = weekNum;
			}
			else if (sum < oldSum && (lowest == 0 || sum < lowest))
			{
				lowest = weekNum;
			}
		
		}
		
		//close the file scanner
		inFile.close();
		
		//print the total/average sales for all weeks
		System.out.printf("Total sales for all weeks: $%,.2f\n"
				+ "Average weekly sales: $%,.2f\n\n", 
				totalSales, (totalSales) / ((double) weekNum));
		
		//print the high-lows
		System.out.printf("The highest sales were made during week %d\n"
				+ "The lowest sales were made during week %d", highest, lowest);
		
		//close input
		keyboard.close();
		
	}
	
	//finds the weekly sales for the current line of the file
	public static double getWeeklySales(String line)
	{
		
		//local variable storing the return value of sum
		double sum = 0.0;
		
		//trimming whitespace in the string
		line = line.trim();
		
		//creating separate strings of each value
		String[] values = line.split(",");
		
		//parsing a double from the string and adding it to the sum
		for (String val : values)
		{
			sum += Double.parseDouble(val);
		}
		
		//returning sum to the main method
		return sum;
	}
	
}