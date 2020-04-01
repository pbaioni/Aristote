package aristote;

import java.util.ArrayList;
import java.util.List;

public class Main
{
	private static int sum = 38;

	private static TripletHelper tripletHelper;

	private static ResultHelper resultHelper;

	private static List<Crown> crowns = new ArrayList<>();

	public static void main(String[] args)
	{
		//tripletHelper creates all the possible triplets (3 numbers giving "sum", if added)
		tripletHelper = new TripletHelper(sum);

		//Helper to check and display results
		resultHelper = new ResultHelper(sum);

		//a crown is a sequence of 12 numbers placed on the external edges of the aristote game
		//PermutationHelper implements an algorithm to test all the possible permutations
		//for the inner numbers, those not placed on the edge
		PermutationHelper permutationHelper = new PermutationHelper(resultHelper);

		//finding all possible crowns (12 external numbers = 6 triplets linked as a chain, sharing firsts and lasts)
		findCrowns();
		
		System.out.println("Valid crowns found: " + crowns.size() + "\n");
		System.out.println("Looking for complete solutions now..." + "\n");
		
		//for each crown (12 numbers) we are going to test all the possible permutations with the numbers left (7 numbers) placed as inner numbers
		//and check if there's a valid solution
		for (Crown crown : crowns)
		{
			int[] crownAsArray = permutationHelper.buildArray(crown.getArrayCrown(), 19);
			permutationHelper.findAllPermutations(crownAsArray, 12);
		}
		
		System.out.println("End of analysis" + "\n");

	}

	private static void findCrowns()
	{
		//using each triplet as the beginning of a crown and seeing how far it allows to go in the building process
		//some of them will allow a complete crown to be created
		for(Triplet t : tripletHelper.getTriplets())
		{
			addPossibleTripletsInCrown(t.getValues());
		}
	}

	
	private static void addPossibleTripletsInCrown(List<Integer> startSequence)
	{
		//not all the left triplets can be added to our chain, we must filter on those having the first number equal to the last of the chain
		//and not using a number already used
		List<Triplet> compatibleTriplets = tripletHelper.getCompatibleTriplets(startSequence);

		if (startSequence.size() <= 9)
		{
			// nominal case, 5 first triplets of the crown chain (adding two more numbers to the input sequence)
			for (Triplet t : compatibleTriplets)
			{
				//testing all the possible compatible triplets and see how far we can go in the crown building
				List<Integer> newSequence = new ArrayList<>(startSequence);
				newSequence.add(t.getB());
				newSequence.add(t.getC());
				//recursive call to this method with a longer sequence than the revious step
				addPossibleTripletsInCrown(newSequence);
			}
		} else if (startSequence.size() == 11)
		{
			//just one number missing
			//the last triplet of the crown must have the first number equal the last number of the sequence
			//and the last one equal to the first of the sequence because we are ending the circular structure of the crown
			//only the central number of the last triplet is unknown
			Integer numberToCompleteCrown = sum - startSequence.get(0) - startSequence.get(startSequence.size() - 1);
			boolean isAlreadyUsed = startSequence.contains(numberToCompleteCrown);

			if (!isAlreadyUsed && numberToCompleteCrown <= 19)
			{
				//completing crown
				startSequence.add(numberToCompleteCrown);			
				// adding completed crown
				crowns.add(new Crown(startSequence));

			}
		} 
	}

}
