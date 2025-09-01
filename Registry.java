package crime_net;
import java.util.ArrayList;
/*   Method                            line
 * Constructor                          20
 * addSuspect                           29
 * addCommunication                     35
 * findOwner                            50
 * getSuspectWithMostPartners           64
 *getLongestPhoneCallBetween            76   
 *getMessagesBetween                    94  
 findSuspect                             */

public class Registry 
{
	public ArrayList<Suspect> suspects;
	public ArrayList<Communication> communication;
	
	
//---------------------------------Constructors--------------------------------------------
	public Registry()
	{
		suspects= new ArrayList<>();
		communication= new ArrayList<>();
	}
	
//===================================Methods==========================================
	
//--------------------------Adds Suspect in Registry----------------------------------------
	public void addSuspect(Suspect aSuspect)
	{
		this.suspects.add(aSuspect);
	}
	
//------------------------Adds Type of Communication in Registry-------------------------
	public void addCommunication(Communication aCommunication)
	{
		this.communication.add(aCommunication);
		Suspect s1= findOwner(aCommunication.getNumber1());
		Suspect s2= findOwner(aCommunication.getNumber2());
		
		if((s1!=null) && (s2!=null))
		{
			s1.addPartner(s2);
			s2.addPartner(s1);
		}
	
	}
	
//-------------------Searches for the owner of a PhoneNumber--------------------------
	public Suspect findOwner(String aNumber)
	{
		for(Suspect sus : this.suspects)
		{
			for(String phone : sus.phoneNumbers) 
			{
				if (phone.equals(aNumber)) return sus;
			}
		}
		return  null;
	}
	
	
//----------------------Finds Suspect with most Partners---------------------------
	public Suspect getSuspectWithMostPartners() 
	{
		Suspect temp = new Suspect();
		temp.setPartnersCounter(0);
		for(Suspect sus : this.suspects)
		{
			if (sus.getPartnersCounter()>temp.getPartnersCounter()) temp=sus;
				
		}
		return temp;
	}
	//----------------------Finds PhoneCall with longest Duration---------------------------
		public PhoneCall getLongestPhoneCallBetween(String number1, String number2) 
		{
			PhoneCall maxCall = new PhoneCall("", "", 0, 0, 0, -1);
			for(Communication com: this.communication)
			{
				if (com instanceof PhoneCall)
				{
					PhoneCall temp = (PhoneCall) com;
					if( (( (temp.getNumber1().equals(number1)) && (temp.getNumber2().equals(number2)) )
					||	( (temp.getNumber2().equals(number1)) && (temp.getNumber1().equals(number2))))
					&& (temp.getDuration()>maxCall.getDuration()))	maxCall=temp;
						
					
				}
			}
			return maxCall;
		}
//-----------------------Prints SMSs between two numbers that contain specific words-----------------
		public ArrayList<SMS> getMessagesBetween(String number1, String number2)
		{
			ArrayList<SMS> list = new ArrayList<>();
			String[] words = new String[4];
			words[0]="Bomb";
			words[1]="Attack";
			words[2]="Explosives";
			words[3]="Gun";
			for(Communication com : this.communication)
			{
				if (com instanceof SMS)
				{
					SMS temp = (SMS) com;
					if(  (temp.getNumber1().equals(number1) && temp.getNumber2().equals(number2 ) )
					|| (temp.getNumber2().equals(number1) && temp.getNumber1().equals(number2)) ) 
					{
					  for (int i=0; i<4; i++)
					  {
						  if (temp.getContext().contains(words[i]))
							 {
							  list.add(temp);
							  break;
							 }
					  }
					}
						
					
				}
			}			
			return list;
		}
	
//--------------------------------Checks if a Suspect is registered-----------------------------------------
		public boolean findSuspect(Suspect ASuspect)
		{
			for(Suspect sus : this.suspects)
			{
				if (ASuspect.equals(sus)) return true;
			}
			
			return false;
		}
		
}