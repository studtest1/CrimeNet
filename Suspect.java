package crime_net;
import java.util.ArrayList;
/*    Method            line thelei allagh
 * Constructor           31
 * Constructor(null)     42
 * getName               53
 * setName               59
 * getCodeName           65
 * setCodeName           71
 * getCity               77
 * setCity               83
 * getPartnersCounter    88
 * setPartnersCounter    93
 * addNumbers            98
 * addPartners           103
 * isConnectedTo         119
 * getCommonPartners     131
 * printPossiblePartner  145
  *findSuggestedPartners 156
 */

public class Suspect implements Comparable<Suspect>
{
	private String name, codeName, city;
	protected ArrayList<String> phoneNumbers;
	protected ArrayList<Suspect> possiblePartners;
	protected ArrayList<Suspect> suggestedPartners;
	private int partnersCounter;

//-------------------Constructors, Getters & Setters---------------
	
	public Suspect(String name, String codeName, String city)
	{
		this.name = name;
		this.codeName = codeName;
		this.city = city;
		phoneNumbers = new ArrayList<>();
		possiblePartners = new ArrayList<>();
		this.suggestedPartners= new ArrayList<>();
		this.partnersCounter=0;
	}
	
	
	public Suspect() 
	{
		this.name = null;
		this.codeName = null;
		this.city = null;
		this.phoneNumbers = new ArrayList<>();
		this.possiblePartners = new ArrayList<>();
		this.suggestedPartners= new ArrayList<>();
		this.partnersCounter=0;
	}


	public String getName() 
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getCodeName() 
	{
		return codeName;
	}


	public void setCodeName(String codeName) 
	{
		this.codeName = codeName;
	}


	public String getCity()
	{
		return city;
	}


	public void setCity(String city) 
	{
		this.city = city;
	}
	
	public int getPartnersCounter()
	{
		return this.partnersCounter;
	}
	
	public void setPartnersCounter(int c)
	{
		this.partnersCounter=c;
	}
	
	
//------------------------Adds number in suspect's phone list-------------------------------------
	public void addNumber(String Anumber)
	{
		this.phoneNumbers.add(Anumber);
	}
//-------------------Adds Possible Partner in Suspect's list if not exists already------------------------------------------	
	public void addPartner(Suspect ASuspect)
	{
		boolean exists=false;
		for (Suspect sus : this.possiblePartners)
		{
			if (sus.equals(ASuspect)) exists=true;
		}
		 
		if (!exists) 
			{
			this.possiblePartners.add(ASuspect);
			this.partnersCounter++;
			}
	}
	
//-------------------------------------------------Checks if two suspects are connected------------------------------------------------------	
	public boolean isConnectedTo(Suspect ASuspect)
	{
		boolean isConnected=false;
		for (Suspect sus : this.possiblePartners)
		{
			if (sus.equals(ASuspect)) isConnected=true;
		}
		 
		return isConnected;
	}

//--------------------------------------------Returns List of Partners two Suspect share-----------------------------------------------------------
	public ArrayList<Suspect> getCommonPartners(Suspect ASuspect)
	{
		ArrayList<Suspect>  commonPartners = new ArrayList<>() ;
		for(Suspect s1 : this.possiblePartners)
		{
			for (Suspect s2 : ASuspect.possiblePartners)
			{
				if (s1.equals(s2)) commonPartners.add(s1);
			}
		}
	return commonPartners;
	}

//-------------------------------------Prints suspect's possible partners' names and code names---------------------------------------------------------------
	public void printPossiblePartner()
	{
		for (Suspect sus : this.possiblePartners)
		{
			System.out.println("Name: " +sus.getName());
			System.out.println("Code name: " +sus.getCodeName());
			System.out.println("------------------------------------");
		}
	}
	
//-----------------------Finds suspect's suggested partners according to their partners' partners-------------------
	public ArrayList<Suspect> findSuggestedPartners()
	{
		ArrayList<Suspect> sug = new ArrayList<>();
		
		for(Suspect partner: this.possiblePartners)
		{
			for(Suspect partnerOfPartner : partner.possiblePartners)
			{
				if( !(partnerOfPartner.isConnectedTo(this) || this.isConnectedTo(partnerOfPartner)) && !(partnerOfPartner.equals(this)) && !(sug.contains(partnerOfPartner)))
				sug.add(partnerOfPartner);
			}	
		}
		return sug;
	}


	@Override
	public int compareTo(Suspect other) 
	{
		return (this.name.compareTo(other.name));
	
	}
	
}
