package crime_net;
/*   Method            line
 * Constructor          14
 * getDuration          22
 * printInfo            29
 */

public class PhoneCall extends Communication
{
	private int duration;


//-----------------------------Constructor and getter------------------------------------
	public PhoneCall(String number1, String number2, int year, int month, int day, int duration) 
	{
		super(number1, number2, year, month, day);
		this.duration=duration;
	}
	
	

	public int getDuration() 
	{
		return duration;
	}


	//----------------------------Overriding printInfo for PhoneCall--------------------	
	public void printInfo()
	{
		System.out.println("This phone call has the following info");
		super.printInfo();
		System.out.println("Duration: " +this.duration);
	}
}
