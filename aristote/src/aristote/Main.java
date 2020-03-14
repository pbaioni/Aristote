package aristote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main
{
	private static int sum = 38;

	private static TripletHelper tripletHelper;

	private static ResultHelper resultHelper;

	private static List<Crown> crowns = new ArrayList<>();

	public static void main(String[] args)
	{
		tripletHelper = new TripletHelper(sum);

		resultHelper = new ResultHelper(sum);

		PermutationHelper permutationHelper = new PermutationHelper(resultHelper);

		findCrowns();
		
		System.out.println("Crowns added: " + crowns.size() + "\n");
		System.out.println("Starting analysis..." + "\n");
		
		for (Crown crown : crowns)
		{
			int[] crownAsArray = permutationHelper.buildArray(crown.getArrayCrown(), 12);
			permutationHelper.findAllPermutations(crownAsArray, 12);
		}
		
		System.out.println("End of analysis" + "\n");

	}

	private static void findCrowns()
	{
		for(Triplet t : tripletHelper.getTriplets())
		{
			addPossibleTripletsInCrown(t.getValues());
		}
	}

	
	private static List<Integer> addPossibleTripletsInCrown(List<Integer> startSequence)
	{
		List<Triplet> tripletsToAdd = tripletHelper.getPossibleTripletsToAdd(startSequence);

		if (startSequence.size() <= 9)
		{
			// nominal case
			for (Triplet t : tripletsToAdd)
			{
				List<Integer> newSequence = new ArrayList<>(startSequence);
				newSequence.add(t.getB());
				newSequence.add(t.getC());
				addPossibleTripletsInCrown(newSequence);
			}
		} else if (startSequence.size() == 11)
		{
			//just one element missing
			List<Integer> newSequence = new ArrayList<>(startSequence);
			Integer numberToCompleteCrown = sum - startSequence.get(0) - startSequence.get(startSequence.size() - 1);
			boolean isAlreadyUsed = startSequence.contains(numberToCompleteCrown);

			if (!isAlreadyUsed && numberToCompleteCrown <= 19)
			{
				newSequence.add(numberToCompleteCrown);
				addPossibleTripletsInCrown(newSequence);
			}
		} else if (startSequence.size() == 12)
		{
			// crown complete
			crowns.add(new Crown(startSequence));

		}

		return null;
	}

}
