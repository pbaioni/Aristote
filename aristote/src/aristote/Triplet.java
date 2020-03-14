package aristote;

import java.util.ArrayList;
import java.util.List;

public class Triplet
{
	private Integer a;
	
	private Integer b;
	
	private Integer c;
	
	private List<Integer> values = new ArrayList<>();

	public Triplet(int a, int b, int c)
	{
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		values.add(getA());
		values.add(getB());
		values.add(getC());
	}
	
	public boolean contains(Integer value)
	{
		return a==value || b==value || c==value;
	}
	
	public Integer getA()
	{
		return a;
	}

	public void setA(Integer a)
	{
		this.a = a;
	}

	public Integer getB()
	{
		return b;
	}

	public void setB(Integer b)
	{
		this.b = b;
	}

	public Integer getC()
	{
		return c;
	}

	public void setC(Integer c)
	{
		this.c = c;
	}

	public List<Integer> getValues()
	{
		return values;
	}

}
