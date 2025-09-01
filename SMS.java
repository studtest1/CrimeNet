package crime_net;
/*   Method           line
 * Construtor          14
 * Constructor(null)   22
 * getContext          30
 * printInfo           38
 */

public class SMS extends Communication
{
	private String context;

	//-----------------------------Constructor-------------------------------------------------
		public SMS(String number1, String number2, int year, int month, int day, String context) 
		{
			super(number1, number2, year, month, day);
			this.context=context;
		}
		
		
		
		public SMS()
		{
			super();
			context="";
		}
		
		
		
		public String getContext() 
		{
			return context;
		}



	//----------------------------Overriding printInfo for SMS--------------------		
	 public void printInfo()
	{
		 System.out.println("This SMS has the following info");
		 super.printInfo();
		 System.out.println("Text: " +this.context);
	}
}
