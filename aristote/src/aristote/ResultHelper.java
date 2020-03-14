package aristote;

import java.util.List;

public class ResultHelper
{
	private int sum;

	public ResultHelper(int sum)
	{
		this.sum = sum;
	}

	public boolean checkResult(int[] permutation)
	{

		for (int i = 2; i <= 12; i += 2)
			if (!checkQuadruplet(permutation, i))
			{
				return false;
			}
		
		for (int i = 3; i <= 7; i += 2)
			if (!checkQuintuplet(permutation, i))
			{
				return false;
			}
		
		return true;
	}

	public void displayResult(int[] result)
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

	public void displayCrown(List<Integer> crown)
	{
		String crownMsg = "{";

		for (Integer i : crown)
		{
			crownMsg += i + ", ";
		}

		crownMsg += "}";

		System.out.println(crownMsg);
	}

	private boolean checkQuintuplet(int[] arr, int i)
	{
		boolean q1 = arr[0] + arr[12] + arr[18] + arr[15] + arr[6] == sum;
		
		boolean q2 = arr[2] + arr[13] + arr[18] + arr[16] + arr[8] == sum;
		
		boolean q3 = arr[4] + arr[14] + arr[18] + arr[17] + arr[10] == sum;

		return q1 && q2 && q3;
	}

	private boolean checkQuadruplet(int[] arr, int i)
	{
		boolean q1 = arr[1] + arr[13] + arr[14] + arr[5] == sum;
		boolean q2 = arr[3] + arr[14] + arr[15] + arr[7] == sum;
		boolean q3 = arr[5] + arr[15] + arr[16] + arr[9] == sum;
		boolean q4 = arr[7] + arr[16] + arr[17] + arr[11] == sum;
		boolean q5 = arr[9] + arr[17] + arr[12] + arr[1] == sum;
		boolean q6 = arr[11] + arr[12] + arr[13] + arr[3] == sum;

		return q1 & q2 & q3 & q4 & q5 & q6;
	}

}
