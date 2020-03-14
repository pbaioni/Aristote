package aristote;

import java.util.List;

public class Crown
{
	private int[] arrayCrown = new int[12];

	public Crown(List<Integer> listCrown)
	{
		buildArrayCrown(listCrown);
	}

	private void buildArrayCrown(List<Integer> listCrown)
	{
		for (int i = 0; i < listCrown.size(); i++)
		{
			arrayCrown[i] = listCrown.get(i);
		}
	}

	public int[] getArrayCrown()
	{
		return arrayCrown;
	}

	public void setArrayCrown(int[] arrayCrown)
	{
		this.arrayCrown = arrayCrown;
	}
	
}
