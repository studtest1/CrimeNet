package crime_net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;




public class MessageFrame extends JFrame
{
	private Suspect suspect;
	private Registry registry;
	private JPanel mainPanel=new JPanel();
	//-----contained on info panel-----------------------------------------------------------------------------------------------------------
	private JPanel infoPanel;
	private JTextArea name, codeName; 
	private JTextArea phoneList;
	//-------contained on SMS panel----------------------------
	private JPanel SMSpanel;
	private JTextField sms;
	private JTextArea showSms;
	private JButton findButton;
	//----------partnersPanel--
	private JPanel partnersPanel;
	private JLabel label;
	private JTextArea partners;
	//---------- Suggested partnersPanel--
	private JPanel sugpartnersPanel;
	private JLabel sugLabel;
	private JTextArea sugpartners;
	//---Button Panel----
	private JPanel buttonPanel;
	private JButton homeButton;
//------------------------------------------------------------Constructor---------------------------------------------------------------------	
	MessageFrame(Suspect ASuspect, Registry Aregistry)
	{
		this.suspect=ASuspect;
		this.registry=Aregistry;
		
		//--------InfoPanel-----------------------------------------------------------------------------
		infoPanel = new JPanel();
		name = new JTextArea(suspect.getName());
		name.setPreferredSize(new Dimension(100, 25));
		codeName = new JTextArea(suspect.getCodeName());
		codeName.setPreferredSize(new Dimension(100, 25));
		String numbers="";
		for(String pn : suspect.phoneNumbers)
		{
			numbers+=pn;
			numbers+="\n";
		}
		
		phoneList=new JTextArea(numbers);
		phoneList.setPreferredSize(new Dimension(90, 60));
		infoPanel.add(name);
        infoPanel.add(Box.createHorizontalStrut(10));
		infoPanel.add(codeName);
		infoPanel.add(Box.createHorizontalStrut(10));
		infoPanel.add(phoneList);
		infoPanel.setBorder(BorderFactory.createEtchedBorder());
	
		
		
		//------------SMSpanel------------------------------------------------
	    SMSpanel = new JPanel();
		sms = new JTextField();
		sms.setPreferredSize(new Dimension(100,20));
		showSms = new JTextArea();
		showSms.setPreferredSize(new Dimension(210, 200));
		showSms.setEditable(false);
		findButton = new  JButton("Find SMS");
		findButton.setPreferredSize(new Dimension(90, 40));
		findButton.addActionListener(new ButtonListener());
		
		SMSpanel.add(sms);
		SMSpanel.add(Box.createHorizontalStrut(10));
		SMSpanel.add(showSms);
		SMSpanel.add(Box.createHorizontalStrut(10));
		SMSpanel.add(findButton);
		SMSpanel.setBorder(BorderFactory.createEtchedBorder());
		
		
		//----------partnersPanel----------------------------
		partners=new JTextArea();
		label = new JLabel("Partners");
		partnersPanel=new JPanel();
		partners.setPreferredSize(new Dimension(210, 100));
		Collections.sort(this.suspect.possiblePartners);
		String temp="";
		for(Suspect sus : this.suspect.possiblePartners)
		{
			temp+=sus.getName()+", "+sus.getCodeName()+"\n";
		}
		partners.setText(temp);
		partners.setEditable(false);
		
		partnersPanel.add(label);
		partnersPanel.add(Box.createVerticalStrut(10));
		partnersPanel.add(partners);
		partnersPanel.setBorder(BorderFactory.createEtchedBorder());
		
		//----------SugpartnersPanel----------------------------
		sugpartnersPanel = new JPanel();
		sugpartners=new JTextArea(); 
		sugLabel = new JLabel("Suggested Partners--->");
		sugpartners.setPreferredSize(new Dimension(200, 100));
	
		Collections.sort(this.suspect.suggestedPartners);
		String temp1="";
		for(Suspect sus : this.suspect.suggestedPartners)
		{ 
			
			temp1+=sus.getName()+", "+sus.getCodeName()+"\n";
		}
		sugpartners.setText(temp1);
		sugpartners.setEditable(false);
		
		sugpartnersPanel.add(sugLabel);
		sugpartnersPanel.add(sugpartners);
		sugpartnersPanel.setBorder(BorderFactory.createEtchedBorder());
		
		//-------------Button Panel----------------
		buttonPanel =new JPanel(new BorderLayout());
		homeButton = new JButton("Back to Search Screen");
		homeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(homeButton) )
				{
					 JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
					 topFrame.dispose();
					Frame myFrame = new Frame(registry);
				}
				
			}});
		
		buttonPanel.add(homeButton, BorderLayout.CENTER);
		
		//--------FRAME------------------------------------------------------------
		
		infoPanel.setPreferredSize(new Dimension(450, 90));
		SMSpanel.setPreferredSize(new Dimension(500,230));
		partnersPanel.setPreferredSize(new Dimension(400,150));
		sugpartnersPanel.setPreferredSize(new Dimension(350,200));
		buttonPanel.setPreferredSize(new Dimension(190, 20));
		
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setSize(500, 850);
		mainPanel.add(infoPanel);
		mainPanel.add(SMSpanel);
		mainPanel.add(partnersPanel);
		mainPanel.add(sugpartnersPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(Box.createVerticalStrut(90));
		
		this.add(mainPanel);
		this.setSize(550, 850);
		this.setTitle("Suspect Page");
		this.setResizable(false);
		this.setVisible(true);
		

	}
	
	
//========================================BUTTON LISTENER======================================================================
	
	class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(findButton) )
			{
				String foundMessages="";
				String number_read=sms.getText();
				
				for(Communication com : registry.communication)
				{
					if (com instanceof SMS)
					{
							SMS message = (SMS) com;
							for(String suspects_number : suspect.phoneNumbers)
							{
								if( ( ( message.getNumber1().equals(suspects_number) && message.getNumber2().equals(number_read) ) || 
										( message.getNumber2().equals(suspects_number) && message.getNumber1().equals(number_read) ) )
										&& ( message.getContext().contains("Bomb") || message.getContext().contains("Attack") || message.getContext().contains("Gun ")
										|| message.getContext().contains("Explosives")) )
									foundMessages+=message.getContext()+"\n"; 
							}
					}
	
				}
				if (foundMessages.equals("")) showSms.setText("not found");
				else showSms.setText(foundMessages);
				
			}
			
			
			
			
			
		}
		
		
		
	}
	
	
//======================================================END OF BUTTON LISTENER====================================================================
	
}
