package aristote;

import java.util.stream.IntStream;

public class PermutationHelper
{
	private ResultHelper resultHelper;

	public PermutationHelper(ResultHelper resultHelper)
	{
		this.resultHelper = resultHelper;
	}

	public int[] buildArray(int[] start, int index)
	{
		int[] array = new int[19];

		for (int i = 0; i < index; i++)
		{
			array[i] = start[i];
		}

		for (Integer i = 19; i >= 1; i--)
		{
			final int add = i;
			if (!IntStream.of(array).anyMatch(x -> x == add))
			{
				array[index] = i;
				index++;
			}
		}

		return array;
	}

	public void findAllPermutations(int[] arr, int index)
	{
		if (index >= arr.length - 1)
		{ // If we are at the last element there's nothing left to permute, we can check the result
			if (resultHelper.checkResult(arr))
			{
				//the checks on quadruplets and quintuplets succeded, we have a consistent solution
				System.out.println("Solution found: ");
				displayResult(arr);
			}

			return;
		}

		for (int i = index; i < arr.length; i++)
		{

			// Swap the elements at indices index and i
			int t = arr[index];
			arr[index] = arr[i];
			arr[i] = t;

			// Recurse on the sub array arr[index+1...end]
			findAllPermutations(arr, index + 1);

			// Swap the elements back
			t = arr[index];
			arr[index] = arr[i];
			arr[i] = t;
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
