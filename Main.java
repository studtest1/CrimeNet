package crime_net;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args)
	{
		//Creation of suspect objects
		Suspect s1 = new Suspect("John Dow", "Sleepy Dog", "Barcelona");
		s1.addNumber("00496955444444");
		s1.addNumber("00496955333333");
		
		Suspect s2 = new Suspect("Danny Rust", "Rusty Knife", "London");
		s2.addNumber("00446999888888");
		
		Suspect s3 = new Suspect("Bob Robson", "Frozen Bear", "Oslo");
		s3.addNumber("00478484777777");
		s3.addNumber("00478484666666");
		s3.addNumber("00478484222222");
		
		Suspect s4 =  new Suspect("Nick Tomson", "Angry Dog", "Berlin");
		s4.addNumber("00493075055555");
		
		//Creation of communication objects
		Communication[] comms = new Communication[15];
		
		comms[0] = new PhoneCall("00496955444444", "00478484777777", 15, 10, 2024, 127);
		comms[1] = new PhoneCall("00496955444444", "00478484777777", 16, 10, 2024, 240);
		comms[2] = new PhoneCall("00446999888888", "00496955333333", 17, 10, 2024, 52);
		comms[3] = new PhoneCall("00446999888888", "00478484777777", 18, 10, 2024, 180);
		comms[4] = new PhoneCall("00478484666666", "00496955333333", 19, 10, 2024, 305);
		comms[5] = new PhoneCall("00496955444444", "00478484222222", 20, 10, 2024, 247);
		comms[6] = new PhoneCall("00478484222222", "00496955333333", 21, 10, 2024, 32);
		
		comms[7] = new SMS("00496955444444", "00478484777777", 10, 10, 2024, "fancy a drink tonight?");
		comms[8] = new SMS("00496955333333", "00446999888888", 11, 10, 2024, "Nitro Bomb prepared");
		comms[9] = new SMS("00446999888888", "00496955444444", 12, 10, 2024, "flying to Berlin tomorrow");
		comms[10] = new SMS("00478484777777", "00446999888888", 13, 10, 2024, "No internet connection today");
		comms[11] = new SMS("00478484777777", "00446999888888", 14, 10, 2024, "Gun Received from Rusty Knife");
		comms[12] = new SMS("00478484777777", "00446999888888", 15, 10, 2024, "Metro Attack ready");
		comms[13] = new SMS("00478484666666", "00446999888888", 16, 10, 2024, "Explosives downtown have been placed");
		comms[14] = new SMS("00493075055555", "00478484222222", 17, 10, 2024, "Call me!");
		
		//Creation of Registry object
		Registry registry = new Registry();
		
		registry.addSuspect(s1);
		registry.addSuspect(s2);
		registry.addSuspect(s3);
		registry.addSuspect(s4);
		
		
		for(int i=0; i<15; i++) registry.addCommunication(comms[i]);
		
		for (Suspect sus : registry.suspects)
		{
		
			sus.suggestedPartners = sus.findSuggestedPartners();
			
		}
		
		Frame myFrame = new Frame(registry);
		//registry, 
		
	}

}
