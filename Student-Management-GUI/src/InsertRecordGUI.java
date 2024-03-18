import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class InsertRecordGUI
{
	JFrame fr = new JFrame("Application");
	JLabel label = new JLabel(new ImageIcon(getClass().getResource("images/school5.png")));
	JLabel[] la = new JLabel[5];
	JTextField[] tb = new JTextField[5];
	JButton bt = new JButton("Save record");
	PreparedStatement ps;
	
	public InsertRecordGUI() 
	{
		fr.setSize(850,750);
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
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/jdbc2", "root", "vikAsh");
			System.err.println("You Are Connected to Mr.Vikash's Database....");
			String query = "insert into studentinfo values(?,?,?,?,?)";
			ps = cn.prepareStatement(query);
		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
	}

	private void addComponents()
	{
		label.setLayout(null);
		int y = 100;
		Font fo = new Font("arial",Font.BOLD,28);
		String str[] = { "Id:", "Name:","city:","Age:","Deepartment:"};
		for (int i = 0; i < 5; i++) {
			la[i] = new JLabel("Student " + str[i]);
			la[i].setBounds(80, y, 300, 30);
			la[i].setFont(fo);
			la[i].setForeground(Color.red);
			label.add(la[i]);
			tb[i] = new JTextField();
			tb[i].setBounds(400, y, 350, 40);
			tb[i].setFont(fo);
			tb[i].setBackground(Color.CYAN);
			tb[i].setForeground(Color.blue);
			tb[i].setBorder(BorderFactory.createLineBorder(Color.orange, 5));
			label.add(tb[i]);
			y += 65;
		}
		bt.setBounds(355, 500, 150, 40);
		bt.setFont(new Font("arial", Font.BOLD, 20));
		bt.setBackground(Color.red);
		bt.setForeground(Color.blue);
		bt.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		label.add(bt);
		bt.addActionListener(new SaveListener());
	}
	class SaveListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent evt) 
		{
			String v1 = tb[0].getText();
			String v2 = tb[1].getText();
			String v3 = tb[2].getText();
			String v4 = tb[3].getText();
			String v5 = tb[4].getText();
			try 
			{
				ps.setString(1, v1);
				ps.setString(2, v2);
				ps.setString(3, v3);
				ps.setString(4, v4);
				ps.setString(5, v5);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(fr,"record has been inserted....");
				for(int i=0;i<5;i++)
				{
					tb[i]=null;
				}
			} 
			catch (Exception ex) 
			{
				System.out.println(ex);
			}
		}
	}
}
