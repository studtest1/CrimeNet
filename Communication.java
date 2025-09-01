package crime_net;
/*    Method                line
 * Constructor               16
 * Constructror(null)        26
 * getNumber1                36
 * getNumber2                42
 * printInfo                 54
 */

public class Communication 
{
	private String number1, number2;
	private int year, month, day;
//-----------------Constructors------------------------

	public Communication(String number1, String number2, int year, int month, int day) 
	{
		this.number1 = number1;
		this.number2 = number2;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	
	public Communication() 
	{
		this.number1 = null;
		this.number2 = null;
		this.year = 0;
		this.month = 0;
		this.day = 0;
	}
	
//----------------------------Getters & Setters-------------------------	
	public String getNumber1()
	{
		return number1;
	}


	public String getNumber2() 
	{
		return number2;
	}

	
//------------------------------Methods-----------------------------------------------
	public void printInfo()
	{
		System.out.println("Between "+number1 +" --- " +number2);
		System.out.println("on "+day+"/"+month+"/"+year);
	}
	
}
