package view.inventory;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

public class ItemTileContract extends ItemPanelDecorator implements ItemPanelParticipant{
	
	private JPanel panContract = null;
	
	private JLabel lblContract;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JLabel lblMaintenanceCost;
	
	private JTextField tfMainCost;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	
	public ItemTileContract(ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		panContract = new JPanel();
		panContract.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panContract.setBackground(Color.WHITE);
		
		panContract.setLayout(new MigLayout("", "[46.00][38.00][100,grow][100,grow][100.00,grow][51.00]", "[][][17][grow][17][][]"));
		
//		itemTileContractRepresentation = new JPanel();
		/* Labels */
		
		lblContract = new JLabel("Contract:");
		lblContract.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblContract, "cell 0 0,alignx left");
		
		lblStart = new JLabel("Start:");
		lblStart.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblStart, "flowx,cell 1 1");
		
		startDateChooser = new JDateChooser();
		startDateChooser.setOpaque(false);
		startDateChooser.setDate(new Date());
		startDateChooser.setBorder(null);
		startDateChooser.setFont(new Font("Arial", Font.BOLD, 20));
		startDateChooser.setDateFormatString("yyyy-MM-dd");
		startDateChooser.setBackground(Color.WHITE);
		startDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(startDateChooser, "cell 2 1 3 1,growx,aligny center");
		
		lblEnd = new JLabel("End:");
		lblEnd.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblEnd, "cell 1 3,alignx left");
		
		endDateChooser = new JDateChooser();
		endDateChooser.setOpaque(false);
		endDateChooser.setDate(new Date());
		endDateChooser.setBorder(null);
		endDateChooser.setFont(new Font("Arial", Font.BOLD, 20));
		endDateChooser.setDateFormatString("yyyy-MM-dd");
		endDateChooser.setBackground(Color.WHITE);
		endDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(endDateChooser, "cell 2 3 3 1,growx,aligny center");
		
		lblMaintenanceCost = new JLabel("Maintenance Cost:");
		lblMaintenanceCost.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblMaintenanceCost, "flowx,cell 0 5 2 1");
		
		
		/* Maintenance Cost */
		

		
		tfMainCost = new JTextField();
		tfMainCost.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(tfMainCost, "cell 2 5 3 1,growx");
		tfMainCost.setColumns(10);
		
		addItemPanelReference.assignToQuad(panContract, 4);

	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileContract();
		super.renderPanel();
	}
	
	public void renderItemTileContract()
	{
		panContract = new JPanel();
		panContract.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panContract.setBackground(Color.WHITE);
		
		panContract.setLayout(new MigLayout("", "[46.00][38.00][100,grow][100,grow][100.00,grow][51.00]", "[][][17][grow][17][][]"));
		
//		itemTileContractRepresentation = new JPanel();
		/* Labels */
		
		lblContract = new JLabel("Contract:");
		lblContract.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblContract, "cell 0 0,alignx left");
		
		lblStart = new JLabel("Start:");
		lblStart.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblStart, "flowx,cell 1 1");
		
		startDateChooser = new JDateChooser();
		startDateChooser.setOpaque(false);
		startDateChooser.setDate(new Date());
		startDateChooser.setBorder(null);
		startDateChooser.setFont(new Font("Arial", Font.BOLD, 20));
		startDateChooser.setDateFormatString("yyyy-MM-dd");
		startDateChooser.setBackground(Color.WHITE);
		startDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(startDateChooser, "cell 2 1 3 1,growx,aligny center");
		
		lblEnd = new JLabel("End:");
		lblEnd.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblEnd, "cell 1 3,alignx left");
		
		endDateChooser = new JDateChooser();
		endDateChooser.setOpaque(false);
		endDateChooser.setDate(new Date());
		endDateChooser.setBorder(null);
		endDateChooser.setFont(new Font("Arial", Font.BOLD, 20));
		endDateChooser.setDateFormatString("yyyy-MM-dd");
		endDateChooser.setBackground(Color.WHITE);
		endDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(endDateChooser, "cell 2 3 3 1,growx,aligny center");
		
		lblMaintenanceCost = new JLabel("Maintenance Cost:");
		lblMaintenanceCost.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(lblMaintenanceCost, "flowx,cell 0 5 2 1");
		
		
		/* Maintenance Cost */
		
		addItemPanelReference.assignToQuad(panContract, 4);
		
		tfMainCost = new JTextField();
		tfMainCost.setFont(new Font("Arial", Font.BOLD, 20));
		panContract.add(tfMainCost, "cell 2 5 3 1,growx");
		tfMainCost.setColumns(10);

		
	}

	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(tfMainCost.getText());
		infoList.add(startDateChooser.getDate());
		infoList.add(endDateChooser.getDate());
		return infoList.iterator();
	}
	
}