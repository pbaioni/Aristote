package aristote;

import java.util.stream.IntStream;

public class PermutationHelper
{
	private ResultHelper resultHelper;

	public PermutationHelper(ResultHelper resultHelper)
	{
		this.resultHelper = resultHelper;
	}

	//creating an array long as totalLength where the first part is a copy of fixedChunk array
	//the extra length is completed with all the numbers not contained in fixedChunk
	//the final goal is to have an array containing all the numbers from 1 to totalLength, unordered
	public int[] buildArray(int[] fixedChunk, int totalLength)
	{
		//copying fixedChunk into the first part of our array
		int fixedChunkLength = fixedChunk.length;
		int[] array = new int[totalLength];
		for (int i = 0; i < fixedChunkLength; i++)
		{
			array[i] = fixedChunk[i];
		}

		//completing the array until totalLength with the number missing in fixedChunk
		int extraIndexes = fixedChunkLength;
		for (Integer i = totalLength; i >= 1; i--)
		{
			final int add = i;
			if (!IntStream.of(array).anyMatch(x -> x == add))
			{
				//the i number is missing, adding it to the array
				array[extraIndexes] = i;
				extraIndexes++;
			}
		}

		return array;
	}

	//find all possible permutations for an array with a fixed part
	//the element between indexes "0" and "startPermutingIndex-1" will be untouched
	//all the possible permutations between indexes "startPermutingIndex" and "array length" will be checked
	public void findAllPermutations(int[] arrayToPermute, int startPermutingindex)
	{
		if (startPermutingindex >= arrayToPermute.length - 1)
		{ // If we are at the last element there's nothing left to permute, we can check the result
			if (resultHelper.checkResult(arrayToPermute))
			{
				//the checks on quadruplets and quintuplets succeded, we have a valid solution
				System.out.println("Solution found: ");
				displayResult(arrayToPermute);
			}

			return;
		}

		for (int i = startPermutingindex; i < arrayToPermute.length; i++)
		{
			// Swap the elements at indices index and i
			int tempSwap = arrayToPermute[startPermutingindex];
			arrayToPermute[startPermutingindex] = arrayToPermute[i];
			arrayToPermute[i] = tempSwap;

			// Recurse on the sub array arr[index+1...end]
			findAllPermutations(arrayToPermute, startPermutingindex + 1);

			// Swap the elements back
			tempSwap = arrayToPermute[startPermutingindex];
			arrayToPermute[startPermutingindex] = arrayToPermute[i];
			arrayToPermute[i] = tempSwap;
		}
	}

	private void displayResult(int[] result)
	{
		// Print the array
		System.out.print("[");
		for (int i = 0; i < result.length - 1; i++)
		{
			System.out.print(result[i] + ", ");
		}
		if (result.length > 0)
			System.out.print(result[result.length - 1]);
		System.out.println("]\n");
	}
}
