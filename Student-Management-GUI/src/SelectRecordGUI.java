import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class SelectRecordGUI 
{
	JFrame fr = new JFrame("Application");
	JLabel label = new JLabel(new ImageIcon(getClass().getResource("images/school3.png")));
	JLabel la = new JLabel("Enter Student Id:");
	JTextField tb=new JTextField();
	JLabel[]lahead=new JLabel[4];
	JLabel[]latext=new JLabel[4];
	JPanel pa=new JPanel();
	JButton bt = new JButton("Show Record");
	PreparedStatement ps;
	public SelectRecordGUI () 
	{
		fr.setSize(700,590);
		fr.setResizable(false);
		fr.setLocationRelativeTo(null);
		fr.add(label);
		dbConnection();
		addComponents();
		fr.setVisible(true);
	}

	private void dbConnection() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/jdbc2","root","vikAsh");
		    System.err.println("You Are Connected to Mr.Vikash's Database....");
		    String query="select * from studentinfo where sid=?";
		    ps=cn.prepareStatement(query);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	private void addComponents()
	{
		label.setLayout(null);
		Font fo = new Font("fantasy",Font.BOLD,28);
		la.setBounds(80, 100, 240, 30);
		la.setFont(fo);
		la.setForeground(Color.orange);
		label.add(la);
		tb = new JTextField();
		tb.setBounds(320, 100, 300, 35);
		tb.setFont(fo);
		tb.setBackground(Color.CYAN);
		tb.setForeground(Color.orange);
		tb.setBorder(BorderFactory.createLineBorder(Color.green, 5));
		label.add(tb);
		bt.setBounds(280, 180, 150, 40);
		bt.setFont(new Font("arial", Font.PLAIN, 18));
		bt.setBackground(Color.red);
		bt.setForeground(Color.white);
		bt.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		label.add(bt);
		bt.addActionListener(new SelectListener());
	    addPanel();
	}
	private void addPanel()
	{
		pa.setBounds(118,240,450,240);
		pa.setBackground(Color.pink);
		pa.setBorder(BorderFactory.createLineBorder(Color.magenta, 5));
		pa.setVisible(false);
		label.add(pa);
		
		pa.setLayout(new GridLayout(5,2));
		String str[] = {"Name:","city:","Age:","Deepartment:"};
		Font font=new Font("arial",Font.BOLD,20);
		pa.setFont(new Font("arial",Font.BOLD,20));
		for(int i=0;i<4;i++)
		{
			lahead[i]=new JLabel("Student "+str[i]);
			lahead[i].setForeground(Color.blue);
			lahead[i].setFont(font);
			pa.add(lahead[i]);
		    latext[i]=new JLabel();
		    latext[i].setForeground(Color.yellow);
		    latext[i].setFont(font);
			pa.add(latext[i]);
		}
	}
	class SelectListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent evt) 
		{
			try 
			{
				ps.setString(1,tb.getText());
				ResultSet rst=ps.executeQuery();
				if (rst.next())
				{
					pa.setVisible(true);
					latext[0].setText(rst.getString(2));
					latext[1].setText(rst.getString(3));
					latext[2].setText(rst.getString(4));
					latext[3].setText(rst.getString(5));				
				}				
				else 
				{
					 pa.setVisible(false);
				     JOptionPane.showMessageDialog(fr,"Record Not Found");
				}
			} 
			catch (Exception ex) 
			{
				System.out.println(ex);
			}
		}
	}
}
