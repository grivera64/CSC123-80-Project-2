//package project2;

/*
 * 
 * Name: grivera64
 * CSC123-80
 * Project 2
 * 
 * Program: CompareTimes.java
 * --------------------------------------------------
 * This problem can be done in main.
 * 
 * Apply the increment operator (--) 10 million times,
 * first to an Integer reference and then to a variable of type int, each set initially to -1
 * Compare the running times.
 * 
 * To compare running times we use the method long System.currentTimeMillis()
 * that returns the current time in milliseconds.
 * 
 * For both the Integer reference x and the primitive int y, the main method:
 * 1. records the starting time in milliseconds,
 * 2. decrements with -- a variable 10,000,000 times, 
 * 3. records the ending time in milliseconds,
 * 4. and displays the elapsed time, ending time - starting time.
 * 
 */

public class CompareTimes
{
	
	public static void main(String[] args)
	{
		
		//setting the Integer and int to -1
		int primitive = -1;
		Integer wrapper = -1;
		
		//getting starting time for primitive test
		long startingTimePrimitive = System.currentTimeMillis();
		
		//testing
		for (int times = 1; times <= 10000000; times++)
		{
			primitive--;
		}
		
		//getting ending time
		long endingTimePrimitive = System.currentTimeMillis();
		
		//getting starting time for wrapper test
		long startingTimeWrapper = System.currentTimeMillis();
		
		//testing
		for (int times = 1; times <= 10000000; times++)
		{
			wrapper--;
		}
		
		//getting ending time
		long endingTimeWrapper = System.currentTimeMillis();
		
		//printing results
		System.out.printf("Wrapper time: %d milliseconds\ninteger time: %d milliseconds\n", 
				endingTimeWrapper - startingTimeWrapper,
				endingTimePrimitive - startingTimePrimitive);
		
		
	}
	
}