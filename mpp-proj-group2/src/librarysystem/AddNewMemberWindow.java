package librarysystem;

import java.awt.FlowLayout;
import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import javax.swing.JPanel;

import business.*;

public class AddNewMemberWindow extends JPanel implements LibWindow {
ControllerInterface controllerInterface = new SystemController();
DataAccess dc = new DataAccessFacade();

	static JTextField tbId;
	static JTextField tbfname;
	static JTextField tblname;
	static JTextField tbtelno;
	
	public static void main(String[] args) {
	     JFrame jf= new JFrame("Add New Library Menber ");
	     jf.setVisible(true);
	     jf.setSize(330,500);
	     jf.setLocationRelativeTo(null);// sets the frame to the center automatically
	     
	     JPanel panel=  new JPanel();
	     //jf.setLayout(new FlowLayout());
	     panel.setLayout(new FlowLayout());
	     jf.add(panel);
	     
	     JLabel lb1= new JLabel("Member Id:");
	     panel.add(lb1);
	     tbId= new JTextField(20);
	     panel.add(tbId);
	     
	     JLabel lb2= new JLabel("First name:");
	     panel.add(lb2);
	     tbfname= new JTextField(20);
	     panel.add(tbfname);
	     
	     JLabel lb3= new JLabel("Last name:");
	     panel.add(lb3);
	     tblname= new JTextField(20);
	     panel.add(tblname);
	     
	     JLabel lb4= new JLabel("Telephone Number:");
	     panel.add(lb4);
	     tbtelno= new JTextField(20);
	     panel.add(tbtelno);
	     
	
	     
	     JButton bt1= new JButton("Add member");
	     panel.add(bt1);
	     
	     
	     
	     
		}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		
	}
	
	   /*private void addNewAddBookButtonListener(JButton butn) {
	            butn.addActionListener(evt -> {
	            try {
	               String fName = tbfname.getText();
	                String lName =tblname.getText();
	                String phone = tbtelno.getText();
	                String street = streetField.getText();
	                String city = cityField.getText();
	                String state = "Alabama";
	                if (stateCombo.getSelectedItem() == null)
	                    state = stateCombo.getSelectedItem().toString();
	                String zipCode = zipField.getText();
	                LibraryMember member = dc(
	                        fName,
	                        lName,
	                        phone,
	                        street,
	                        city,
	                        state,
	                        zipCode);
	                memberIdLabel.setText("Member ID: " + member.getMemberId());
	                JOptionPane.showMessageDialog(AddMemberWindow.this, "Library member successfully added, Member ID:" + member.getMemberId(), "", JOptionPane.INFORMATION_MESSAGE);
	            } catch (LibrarySystemException e) {
	                JOptionPane.showMessageDialog(AddMemberWindow.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            
	        }
	   );}

}*/
}