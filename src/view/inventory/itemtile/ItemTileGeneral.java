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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class ItemTileGeneral extends ItemPanelDecorator implements ItemPanelParticipant, TypeItemTile, ItemListener{
	
	private JPanel panGeneral;
	private JLabel lblType;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	
	private JComboBox cbType;
	private JComboBox cbAssignee;
	
	private JDateChooser deliveryDateChooser;
	
	public ItemTileGeneral(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileGeneralPanel();
		super.renderPanel();
	}
	
	public void renderItemTileGeneralPanel()
	{
		panGeneral = new JPanel();
		panGeneral.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panGeneral.setBackground(Color.WHITE);
		/* Layout */
		
		panGeneral.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][9.00][39.00][11.00][grow][17][][]"));
		
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		/* Labels */
		lblType = new JLabel("Type:");
		panGeneral.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		panGeneral.add(lblDeliveryDate, "flowx,cell 1 3 4 1,alignx right");
		
		deliveryDateChooser = new JDateChooser();
		deliveryDateChooser.setOpaque(false);
		deliveryDateChooser.setDate(new Date());
		deliveryDateChooser.setBorder(null);
		deliveryDateChooser.setDateFormatString("yyyy-MM-dd");
		deliveryDateChooser.setBackground(Color.WHITE);
		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
		panGeneral.add(deliveryDateChooser, "cell 5 3 3 1,grow");
		
		lblAssignee = new JLabel("Assignee:");
		panGeneral.add(lblAssignee, "flowx,cell 1 5 3 1");
		
		/* Type Combo Box */
		
		cbType = new JComboBox(typeStrings);
		cbType.setSelectedItem("General");
		cbType.setBackground(Color.white);
		cbType.addItemListener(this);
		panGeneral.add(cbType, "cell 3 1 5 1,growx");
		
		/* Assignee Combo Boxes */
		
		cbAssignee = new JComboBox();
		cbAssignee.setBackground(Color.white);
		cbAssignee.setModel(new DefaultComboBoxModel(new String[] { "Shayane Tan",
				"Rissa Quindoza", "Gio Velez" }));
		panGeneral.add(cbAssignee, "cell 4 5 4 1,growx");
		addItemPanelReference.assignToQuad(panGeneral, 1);

	}
	
	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(deliveryDateChooser.getDate());
		infoList.add(cbAssignee.getSelectedItem().toString());
		return infoList.iterator();
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			PanelRegistry.getInstance().setCurrentType(cbType.getSelectedItem().toString());
			System.out.println(cbType.getSelectedItem().toString());
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
