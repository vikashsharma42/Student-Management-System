import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UpdateGUI extends JFrame 
{
	private static final int Bold = 0;
	JLabel la = new JLabel("Enter Student Id:");
	JLabel label = new JLabel(new ImageIcon(getClass().getResource("images/school6.png")));
	JTextField tb = new JTextField();
	JButton bt = new JButton("Show Record");
	JButton update = new JButton("Update Record");
	JPanel pa = new JPanel();
	JLabel[] lah = new JLabel[4];
	JTextField[] tf = new JTextField[4];
	PreparedStatement ps, psu;

	public UpdateGUI()
	{
		super("SHOW-RECORD");
		setSize(700,600);
		setResizable(false);
		setLocationRelativeTo(null);
		add(label);
		addInfo();
		dbConnection();
		setVisible(true);
	}

	private void addInfo() 
	{
		Font fo = new Font("fantasy",Bold, 30);
		Font font = new Font("arial",Bold, 25);
		label.setLayout(null);
		la.setBounds(50, 100, 240, 30);
		la.setForeground(Color.red);
		la.setFont(fo);
		label.add(la);
		tb.setBounds(300, 100, 300, 40);
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
		
		pa.setBounds(120, 270, 450, 250);
		label.add(pa);
		pa.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		pa.setBackground(Color.orange);
		pa.setVisible(false);
		addLabel();
	}

	private void addLabel() 
	{
		pa.setLayout(new GridLayout(5, 2));
		String[] str = { "name", "city", "age", "Deepartment" };
		Font fo = new Font("arial", 0, 20);
		for (int i = 0; i < 4; i++) 
		{
			
			lah[i] = new JLabel("Student " + str[i] + ": ");
			lah[i].setForeground(Color.blue);
			lah[i].setFont(fo);
			pa.add(lah[i]);
			tf[i] = new JTextField();
			tf[i].setBackground(Color.yellow);
			tf[i].setForeground(Color.magenta);
			tf[i].setBorder(BorderFactory.createLineBorder(Color.green, 2));
			tf[i].setFont(fo);
			lah[i].setBounds(20, 200, 500, 200);
			pa.add(tf[i]);
		}
		update.setBackground(Color.PINK);
		update.setFont(fo);
		update.setForeground(Color.yellow);
		update.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		pa.add(update);
		update.addActionListener(new updateListener());
	}
	private void dbConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/jdbc2", "root", "vikAsh");
			System.err.println("You Are Connected to Mr.Vikash's Database....");
			String sql="select *from studentinfo where sid=?";
			String query = "update studentinfo set name=?,city=?,age=?, deepartment=? where sid=?";
			ps = cn.prepareStatement(sql);
			psu=cn.prepareStatement(query);

		} catch (Exception ex) 
		{
			System.out.println(ex);
		}
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
				    JOptionPane.showMessageDialog(UpdateGUI.this, "Record Not FOUND..");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}

	}
	class updateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			try
			{
				psu.setString(1,tf[0].getText());
				psu.setString(2,tf[1].getText());
				psu.setString(3,tf[2].getText());
				psu.setString(4,tf[3].getText());
				psu.setString(5,tb.getText());
				psu.executeUpdate();
			    JOptionPane.showMessageDialog(UpdateGUI.this, "Record has been updated..");
			    pa.setVisible(false);
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}

	}
}
