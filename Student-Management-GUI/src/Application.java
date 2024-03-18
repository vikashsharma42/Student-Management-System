import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Application 
{
	JFrame fr = new JFrame("Student-Managemnt-Application");
	JLabel la = new JLabel(new ImageIcon(getClass().getResource("images/school.png")));
	JButton [] bt= new JButton[5];
	
	public Application()
	{
		fr.setSize(700, 680);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(3);
		fr.add(la);
		addButtons();
		fr.setVisible(true);
	}
	private void addButtons() 
	{
		la.setLayout(null);
		Font fo = new Font("elephant", Font.BOLD, 20);
		int y=70;
		String []str= {"Insert-","Show-All-","Delete-","Update-","Show-"};
		MenuListener listener=new MenuListener();
		for(int i=0;i<5;i++)
		{
			bt[i]=new JButton(str[i]+"Record");
			bt[i].addActionListener(listener);
			bt[i].setBounds(250, y, 230, 40);
			bt[i].setFont(fo);
			bt[i].setBackground(Color.orange);
			bt[i].setForeground(Color.green);
			bt[i].setBorder(BorderFactory.createLineBorder(Color.blue, 5));
			la.add(bt[i]);
			y+=80;
		}
	}
    class MenuListener implements ActionListener
    {
		public void actionPerformed(ActionEvent evt)
		{
			JButton bb=(JButton)evt.getSource();
			if(bb==bt[0])
			{
				new InsertRecordGUI();
			}
			else if(bb==bt[1])
			{
				new ShowAllRecordGUI();
			}
			else if(bb==bt[2])
			{
				new DeleteGUI();
			}
			else if(bb==bt[3])
			{
				new UpdateGUI();
			}
			else if(bb==bt[4])
			{
				new SelectRecordGUI();
			}
		}
    }
	public static void main(String[] args) 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Application();
	}
}
