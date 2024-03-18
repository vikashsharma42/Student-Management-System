import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;


public class ShowAllRecordGUI 
{
	JFrame fr=new JFrame("SHOW-ALL-RECORD");
	//JLabel label = new JLabel(new ImageIcon(getClass().getResource("images/school3.png")));
	DefaultTableModel model=new DefaultTableModel();
	JTable table;
	public ShowAllRecordGUI () 
	{
		fr.setSize(600,500);
		fr.setResizable(false);
		fr.setLocationRelativeTo(null);
		table=new JTable(model);
		JScrollPane pa=new JScrollPane(table);
		pa.setForeground(Color.red);
		pa.setBackground(Color.green);
		//fr.add(label);
		fr.add(pa);
		dbConnection();
		addTableComponenet();
		fr.setVisible(true);
	}
	private void addTableComponenet()
	{
		Font font = new Font("tahoma",0, 17);
		table.setFont(font);
		table.setBackground(Color.pink);
		table.setForeground(Color.blue);
		table.setSelectionBackground(Color.orange);
		table.setSelectionForeground(Color.green);
		table.setGridColor(Color.red);
	}
	private void dbConnection() 
	{
		model.addColumn("Student Id");
		model.addColumn("Student Name");
		model.addColumn("Student City");
		model.addColumn("Student Age");
		model.addColumn("Student Deepartment");
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/jdbc2","root","vikAsh");
		    System.err.println("You Are Connected to Mr.Vikash's Database....");
		    String query="select * from studentinfo";
            Statement st=cn.createStatement();
            ResultSet rst=st.executeQuery(query);
            while(rst.next())
            {
            	Object[]data=new Object[5];
            	data[0]=rst.getString(1); 
            	data[1]=rst.getString(2);
            	data[2]=rst.getString(3);
            	data[3]=rst.getString(4);
            	data[4]=rst.getString(5);
            	model.addRow(data);
            }
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
