package aristote;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TripletHelper
{
	private List<Triplet> triplets = new ArrayList<>();
	
	private int sum;
	
	public TripletHelper(int sum)
	{
		this.sum = sum;
		findTriplets();
	}
	
	private void findTriplets()
	{
		System.out.println("Triplets:");
		for (int i = 1; i <= 19; i++)
		{
			for (int j = 1; j <= 19; j++)
			{
				for (int k = 1; k <= 19; k++)
				{
					boolean uniqueI3 = i != j && i != k;
					boolean uniqueJ3 = j != k;
					if (uniqueI3 && uniqueJ3)
					{
						if (i + j + k == sum)
						{
							System.out.println(i + ", " + j + ", " + k);
							Triplet triplet = new Triplet(i, j, k);
							triplets.add(triplet);
						}
					}

					}

				}
			}
		System.out.println("\n");
		}

	
	public List<Triplet> getPossibleTripletsToAdd(List<Integer> crownSequence)
	{
		int last =crownSequence.get(crownSequence.size()-1);
		List<Triplet> possibleTriplets = triplets.stream().filter(t -> t.getA() == last).distinct().collect(Collectors.toList());
		List<Integer> tempCrownSequence = new ArrayList<>(crownSequence);
		//remove last element
		tempCrownSequence.remove(tempCrownSequence.size()-1);
		
		for(Integer i : tempCrownSequence)
		{
			possibleTriplets = possibleTriplets.stream().filter(t -> !t.contains(i)).collect(Collectors.toList());
		}
		return possibleTriplets;
	}

	public void displayTriplets(List<Triplet> triplets)
	{
		for (Triplet triplet : triplets)
		{
			System.out.println(triplet.getA() + ", " + triplet.getB() + ", " + triplet.getC());
		}

		System.out.println("Triplets size: " + triplets.size());
	}

	public List<Triplet> getTriplets()
	{
		return triplets;
	}

}
