//package project2;

/*
 * 
 * Name: grivera64
 * CSC123-80
 * Project 2
 * 
 * Program: SieveOfPrimes.java
 * ---------------------------------------------------------
 * Write a program that creates a Boolean ArrayList with a capacity of 1000.
 * Initialize each value in the ArrayList to true.
 * For each index in the ArrayList,
 * 		if the index is not a prime number 
 * 		set the value of the ArrayList at that index to false.
 * When you are finished create a loop that prints the following output
 * followed by the total number of primes from 0 to 999.
 * 
 * You can use the previous mechanisms for calculating a prime
 * or the algorithm described below which is known as the Sieve of Eratosthenes.
 * Either way the output should look the same.
 * 
 * Starting with array index 2, determine whether a given element is true.
 * If so, loop through the remainder of the array 
 * and set to false every element whose index is a multiple of the index
 * for the element with value true.
 * Then continue the process with the next element with value true.
 * For array index 2, all elements beyond element 2 in the array that have indices
 * which are multiples of 2 (indices 4, 6, 8, 10, etc.) will be set to false; for array index 3,
 * all elements beyond element 3 in the array that have indices which are multiples of 3 
 * (indices 6, 9, 12, 15, etc.) will be set to false; and so on.
 * 
 */

import java.util.ArrayList;
import java.util.Iterator;

public class SieveOfPrimes
{
	
	public static void main(String[] args)
	{
		
		//make an array list for storing true for prime numbers
		ArrayList<Boolean> list = new ArrayList<Boolean>(1000);
		
		//local variables
		int numOfPrime = 0;
		
		//initializing all of them to true
		for (int i = 1; i <= 1000; i++)
		{
			list.add(true);
		}
		
		//auto setting 1 and 2 to not prime
		list.set(0, false);
		list.set(1, false);
		
		//finding composite numbers
		//didn't work until I set the bound to 31 for some strange reason
		//instead of 7
		for (int jump = 2; jump <= 31; jump++)
		{
			for (int nums = jump * 2; nums < list.size(); nums += jump)
			{
				list.set(nums, false);
				//System.out.printf("%d isn't prime\n", nums);
			}
		}
		
		//make the arraylist iterable
		Iterator<Boolean> primes = list.iterator();
		
		//local variable
		int index = 0;
		
		//print the prime values if they have true stored in them
		while (primes.hasNext())
		{
			if (primes.next())
			{
				System.out.printf("%d is prime\n", index);
				numOfPrime++;
			}
			
			index++;
		}
		
		//print number of prime values
		System.out.printf("\n%d prime numbers found\n", numOfPrime);
		
		//end of program
		
		
		
		
	}
	
}