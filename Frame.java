package crime_net;



import java.awt.event.*;

import javax.swing.*;

public class Frame extends JFrame
{
	private JPanel panel;
	private JTextField text;
	private JButton searchButton;
	private Registry registry;
	
	
//--------------------------------------Constructor-----------------------------------------
	public Frame(Registry aRegistry)
	{
		panel= new JPanel();
		text = new JTextField("Please enter suspect's name");
		searchButton = new JButton("Find");
		this.registry= aRegistry;
		
		panel.add(text);
		panel.add(searchButton);
		
		text.addFocusListener(new Listener());
		this.searchButton.addActionListener(new ButtonListener());
		
		panel.setSize(400, 100);
		this.add(panel);
		this.setVisible(true);
		this.setSize(470, 200);
		this.setResizable(false);
		this.setTitle("Find Suspect");
		
		
		
	}
	
	
	
//------------------------------------------------Action Listener-----------------------------------------------
	class Listener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e) 
		{
			if (e.getSource().equals(text)) text.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) 
		{
			if (text.getText().equals(""))
			{
				text.setText("Please enter suspect's name");
			}
		}

	}
	
//=====================================Button Listener==========================================================
	class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String textGiven = text.getText();
			Suspect suspect=null;
			
			if (e.getSource().equals(searchButton) && !((textGiven.equals("") || (textGiven.equals("Please enter suspect's name")))) )
				{
					boolean found=false;
					for(Suspect sus : registry.suspects)
					{
						if (textGiven.equalsIgnoreCase( sus.getName() ) )
						{
							found=true;
							suspect=sus;
						}
					}
				
				
				if (!found) JOptionPane.showMessageDialog(null, textGiven+" Not found","Message", JOptionPane.ERROR_MESSAGE);
				
				else 
					{
						
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
						topFrame.dispose();
						MessageFrame messageFrame = new MessageFrame(suspect, registry);
					}
				}
		}
		
	}
	
//===========================================end of ButtonListener==============================================
}
