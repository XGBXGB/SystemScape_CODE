package view.purchaseOrder;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.ItemData;
import net.miginfocom.swing.MigLayout;
import view.JTextFieldFilter;
import view.Message;
import view.PopUp;
import controller.PurchaseOrderController;

public class AddPOItem extends PopUp implements ActionListener, FocusListener{
	private JPanel panHeader, panCenter, panClose, panContent,panFooter,panWest,panEast, panSubmit;
	private JLabel lblItem,lblAmount,lblAmountValue,lblQuantity,lblDescription,lblPrice;
	private JTextArea txtDescription;
	private JTextField txtQuantity,txtPrice, txtItem;
	private JButton btnSubmit;
	private JScrollPane scrollPane;

	private PurchaseOrderController poController;
	private JLabel lblType;
	private JComboBox cmbType;
	private JFrame parent;
	
	public AddPOItem(JFrame parent, String type, PurchaseOrderController poController) 
	{
	
		super(parent);
		this.parent = parent;
		this.poController = poController;
		this.addFocusListener(this);
		this.setUndecorated(true);
		
		panCenter = new JPanel();
//		getContentPane().add(panCenter, BorderLayout.CENTER);

		panCenter.setBackground(Color.white);
		panCenter.setLayout(new BorderLayout(0, 0));
		panCenter.setSize(new Dimension(600,400));
		panCenter.setPreferredSize(new Dimension(500,500));
		
		panContent = new JPanel();
		panContent.setBackground(Color.white);
		panCenter.add(panContent, BorderLayout.CENTER);
		panContent.setLayout(new MigLayout("", "[grow][188.00,grow][][][]", "[][][][grow][][][][][][]"));
		
		lblItem = new JLabel("Item :");
		lblItem.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(lblItem, "cell 0 1,alignx left");
		
		txtItem = new JTextField("");
		txtItem.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				//txtItem.setBorder(border);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtItem.setBorder(border);
			}
		});
		txtItem.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(txtItem, "cell 1 1,growx");
		txtItem.setColumns(10);
		
		lblDescription = new JLabel("Item Description :");
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(lblDescription, "cell 0 2,alignx left");
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setPreferredSize(new Dimension(300, 150));
		panContent.add(scrollPane, "cell 1 3,growy");
		
		txtDescription = new JTextArea("");
		txtDescription.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				//txtItem.setBorder(border);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtDescription.setBorder(border);
			}
		});
		txtDescription.setLineWrap(true);
		scrollPane.setViewportView(txtDescription);
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(lblType, "cell 0 5,alignx left");
		
		String[] types={"IT Asset", "Non-IT Asset"};
		cmbType = new JComboBox(types);
		cmbType.setFont(new Font("Arial", Font.PLAIN, 18));
		cmbType.setPreferredSize(new Dimension(185, 32));
		cmbType.setBackground(Color.WHITE);
		panContent.add(cmbType, "cell 1 5,alignx left");
		
		lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(lblQuantity, "cell 0 6,alignx left");
		
		txtQuantity = new JTextField();
		txtQuantity.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				//txtItem.setBorder(border);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtQuantity.setBorder(border);
			}
		});
		txtQuantity.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		txtQuantity.setPreferredSize(new Dimension(10, 25));
		panContent.add(txtQuantity, "cell 1 6");
		txtQuantity.setColumns(10);
		
		lblPrice = new JLabel("Unit Price :");
		lblPrice.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(lblPrice, "cell 0 7");
	
		txtPrice = new JTextField("");
		txtPrice.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				//txtItem.setBorder(border);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtPrice.setBorder(border);
			}
		});
		txtPrice.setDocument(new JTextFieldFilter(JTextFieldFilter.FLOAT));
		txtPrice.setPreferredSize(new Dimension(10, 25));
		panContent.add(txtPrice, "cell 1 7");
		txtPrice.addActionListener(new TextAmountActionListener());
		txtPrice.setColumns(10);
		
		lblAmount = new JLabel("Amount :");
		lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(lblAmount, "cell 0 8");
		
		lblAmountValue = new JLabel("0.00");
		lblAmountValue.setFont(new Font("Arial", Font.PLAIN, 18));
		panContent.add(lblAmountValue, "cell 1 8");
		
		panSubmit = new JPanel();
		panSubmit.setBackground(Color.white);
		panCenter.add(panSubmit, BorderLayout.SOUTH);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.addActionListener(this);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		panSubmit.add(btnSubmit);
		
		setContent(panCenter);
		getClose().addActionListener(this);
		
		if(!type.equals("Hardware")){
			lblType.setVisible(false);
			cmbType.setVisible(false);
		}
		
		this.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == getClose())
		{
			this.setVisible(false); //you can't see me! - LOL
			this.dispose();
		}
		else if(e.getSource() == btnSubmit)
		{
			
			String error = checkFields();
			if(error.equals("") == true)
			{
				String item = txtItem.getText();
				String description = txtDescription.getText();
				int quantity = parseStringInt(txtQuantity.getText());
				float price = (float) parseStringFloat(txtPrice.getText());
				
				poController.addItem(new ItemData(item, description, price), quantity);
				this.setVisible(false); //you can't see me!
				this.dispose();
				
			}
			else if(error.equals("") == false)
			{
				Message msg = new Message(parent, Message.ERROR,  error);
			}
			else
			{
				clearFields();
			}
			

		}
		
	}
	
	public void clearFields()
	{
		txtDescription.setText("");
		txtQuantity.setText("");
		txtPrice.setText("");
		txtItem.setText("");
	}

	public int parseStringInt(String quantity)
	{
		return Integer.parseInt(quantity);
	}
	
	public double parseStringFloat(String  price)
	{
		return  Float.parseFloat(price);
	}
	
	public String checkFields()
	{
		String error = "";
		Border border = BorderFactory.createLineBorder(Color.RED, 2);
	
		if(txtItem.getText().equals("")){
			error+= "Item Name Field is empty \n";
			txtItem.setBorder(border);
		}
		if(txtDescription.getText().equals("")){
			error+= "Item Description Area is empty \n";
			txtDescription.setBorder(border);
		}
		if(txtQuantity.getText().equals("")){
			error+= "Quantity Field is empty \n";
			txtQuantity.setBorder(border);
		}
		if(txtPrice.getText().equals("")){
			error+= "Price Field is empty";
			txtPrice.setBorder(border);
		}

		return error;
	}

	class TextAmountActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int quantity = parseStringInt(txtQuantity.getText());
			float price = (float) parseStringFloat(txtPrice.getText());
			float result = quantity * price;
			 lblAmountValue.setText(String.valueOf(result));
		}
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		JFrame f=(JFrame) e.getSource();
		f.toFront();
	}
}
