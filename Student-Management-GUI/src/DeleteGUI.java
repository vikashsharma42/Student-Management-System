import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class DeleteGUI
{
	JFrame fr = new JFrame("Application");
	JLabel label = new JLabel(new ImageIcon(getClass().getResource("images/school5.png")));
	JLabel la = new JLabel("Enter Student Id:");
	JTextField tb = new JTextField();
	JButton bt = new JButton("Get Record");
	JButton delete = new JButton("Delete Record");
	JPanel pa = new JPanel();
	JLabel[] lah = new JLabel[4];
	JLabel[] tf = new JLabel[4];
	PreparedStatement ps, psu;
	
	public DeleteGUI() 
	{
		fr.setSize(700,600);
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
			String sql="select *from studentinfo where sid=?";
			String query = "delete from studentinfo where sid=?";
			ps = cn.prepareStatement(sql);
			psu=cn.prepareStatement(query);
		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
	}

	private void addComponents()
	{
		Font fo = new Font("fantasy",Font.BOLD, 30);
		Font font = new Font("arial",Font.BOLD, 25);
		label.setLayout(null);
		la.setBounds(50, 100, 240, 30);
		la.setForeground(Color.red);
		la.setFont(fo);
		label.add(la);
		tb.setBounds(300, 100, 315, 40);
		tb.setBackground(Color.cyan);
		tb.setForeground(Color.blue);
		tb.setBorder(BorderFactory.createLineBorder(Color.orange, 4));
		tb.setFont(font);
		label.add(tb);
		bt.setBounds(230, 200, 200, 40);
		bt.setBackground(Color.magenta);
		bt.setForeground(Color.white);
		bt.setFont(font);
		label.add(bt);
		bt.addActionListener(new showListener());
		
		pa.setBounds(130, 270, 400, 200);
		label.add(pa);
		pa.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		pa.setBackground(Color.orange);
		pa.setVisible(false);
		addLabel();
	}
	private void addLabel() 
	{
		pa.setLayout(new GridLayout(5, 2));
		String[] str = { "name", "age", "city","Deepartment"};
		Font fo = new Font("arial", 0, 20);
		for (int i = 0; i < 4; i++) 
		{
			
			lah[i] = new JLabel("Person " + str[i] + ": ");
			lah[i].setForeground(Color.blue);
			lah[i].setFont(fo);
			pa.add(lah[i]);
			tf[i] = new JLabel();
			tf[i].setBackground(Color.yellow);
			tf[i].setForeground(Color.magenta);
			tf[i].setBorder(BorderFactory.createLineBorder(Color.green, 2));
			tf[i].setFont(fo);
			lah[i].setBounds(20, 200, 400, 200);
			pa.add(tf[i]);
		}
		delete.setBackground(Color.PINK);
		delete.setFont(fo);
		delete.setForeground(Color.yellow);
		delete.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		pa.add(delete);
		delete.addActionListener(new DeleteListener());
	}
	class showListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			try 
			{
				ps.setString(1,tb.getText());
				ResultSet rst=ps.executeQuery();
				if(rst.next())
				{
					pa.setVisible(true);
					tf[0].setText(rst.getString(2));
					tf[1].setText(rst.getString(3));
					tf[2].setText(rst.getString(4));
					tf[3].setText(rst.getString(5));
				}
				else
				{
					pa.setVisible(false);
				    JOptionPane.showMessageDialog(fr,"Record Not FOUND..");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
	}
	class DeleteListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent evt) 
		{
			try 
			{
				psu.setString(1,tb.getText());
				psu.executeUpdate();
			    JOptionPane.showMessageDialog(fr,"Record has been deleted");
			    pa.setVisible(false);
			} 
			catch (Exception ex) 
			{
				System.out.println(ex);
			}
		}
	}
}
