package view.inventory.itemtile;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;
import view.inventory.PanelRegistry;

import com.toedter.calendar.JDateChooser;

public class ItemTileNonIT extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTile, ActionListener{

	private JPanel panNonIT;
	private JLabel lblType;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	
	private JComboBox cbType;
	private JDateChooser deliveryDateChooser;
	private JComboBox cbAssignee;
	
	

	public ItemTileNonIT(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileNonIT();
		super.renderPanel();
	}
	
	public void renderItemTileNonIT()
	{

		panNonIT = new JPanel();
		panNonIT.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panNonIT.setBackground(Color.WHITE);
		/* Layout */
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		panNonIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00,grow][38.00,grow][100,grow][100][100][31.00]", "[][][17][][17][][]"));
		
		
		/* Label */
		
		lblType = new JLabel("Type:");
		panNonIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		panNonIT.add(lblDeliveryDate, "flowx,cell 1 3 4 1");
		
		/* Type Combo Box */
		
		cbType = new JComboBox(typeStrings);
		cbType.setSelectedItem("Non-IT Assets");
		cbType.setBackground(Color.white);
		cbType.addActionListener(this);
		panNonIT.add(cbType, "cell 3 1 5 1,growx");
		

		
		deliveryDateChooser = new JDateChooser();
		deliveryDateChooser.setOpaque(false);
		deliveryDateChooser.setDate(new Date());
		deliveryDateChooser.setBorder(null);
		deliveryDateChooser.setDateFormatString("yyyy-MM-dd");
		deliveryDateChooser.setBackground(Color.WHITE);
		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
		panNonIT.add(deliveryDateChooser, "cell 5 3 3 1,growx,aligny center");
		
		lblAssignee = new JLabel("Assignee: ");
		panNonIT.add(lblAssignee, "cell 1 5 2 1");
		
		cbAssignee = new JComboBox();
		cbAssignee.setModel(new DefaultComboBoxModel(new String[] { "Shayane Tan",
				"Rissa Quindoza", "Gio Velez" }));
		cbAssignee.setBackground(Color.WHITE);
		panNonIT.add(cbAssignee, "cell 3 5 5 1,growx");
		addItemPanelReference.assignToQuad(panNonIT, 1);
		
	}

	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(cbAssignee.getSelectedItem().toString());
		return infoList.iterator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cbType)
		{
			PanelRegistry.getInstance().setCurrentType(cbType.getSelectedItem().toString());
		}
	}

	@Override
	public boolean checkInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void loadAssigneeList(Iterator iter) {
		 ArrayList<String> assigneeList = new ArrayList();
	     while(iter.hasNext()){
	    	 assigneeList.add(iter.next().toString());
	     }
	     cbAssignee.setModel(new DefaultComboBoxModel(assigneeList.toArray()));
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		cbAssignee.setSelectedItem(iter.next().toString());
		deliveryDateChooser.setDate((Date) iter.next());
	}
	@Override
	public void setType(String type) {
		cbType.setSelectedItem(type);
	}

	@Override
	public void setDeliveryDate(Date date) {
		deliveryDateChooser.setDate(date);
	}
	
}
