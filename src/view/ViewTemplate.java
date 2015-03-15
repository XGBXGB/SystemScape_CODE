package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.ColumnControlButton;
import org.jdesktop.swingx.table.TableColumnExt;


public abstract class ViewTemplate extends JPanel{
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private DefaultTableModel tglModel;
	private JXTable table;
	private JToggleButton tglButton;

	public ViewTemplate() {
		setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.CENTER);

		model = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				if(mColIndex==4) return true;
				return false;
			}

			public boolean isFocusable(int rowIndex, int mColIndex) {
				return false;
			}

			public boolean isCellSelectable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		System.out.println("MODEL: "+ model);
		 table = new JXTable(model);
		 table.setHorizontalScrollEnabled(true);
		 table.getTableHeader().setReorderingAllowed(false);
		// System.out.println("TABLE: "+ table);
		 ColumnControlButton columnControl = new ColumnControlButton(table) {

			    @Override
			    protected ColumnVisibilityAction createColumnVisibilityAction(
			            TableColumn column) {
			        if (column instanceof TableColumnExt
			                && !((TableColumnExt) column).isHideable())
			            return null;
			        return super.createColumnVisibilityAction(column);
			    }

			};
			
			table.setColumnControl(columnControl);
		 table.setColumnControlVisible(true);
		
		scrollPane.setViewportView(table);
		
		
		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 20));
		table.setRowHeight(55);
		model.setRowCount(0);
		
		JPanel panTop = new JPanel();
		panTop.setBackground(Color.white);
		FlowLayout flowLayout = (FlowLayout) panTop.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panTop, BorderLayout.NORTH);
		
		tglButton = new JToggleButton("Toggle View");
		tglButton.setVisible(false);
		panTop.add(tglButton);
		model.setRowCount(0);

		initialize();
		refresh();
	}
	
	public abstract void initialize();
	
	public abstract void refresh();
	
	public void activateToggle(DefaultTableModel model){
		tglButton.setVisible(true);
		tglModel = model;
	}
	
	public JToggleButton getToggle(){
		return tglButton;
	}
	
	public void toggle(){
		if(table.getModel()==model) table.setModel(tglModel);
		else{
			table.setModel(model);
			setColRendEdit();
		}
		
		packTable();
	}
	
	public void packTable(){
		table.packAll();
	}
	
	public void setColCount(int num){
		model.setColumnCount(num);
	}
	
	public void setColWidth(int index, int width){
		table.getColumnModel().getColumn(index).setPreferredWidth(width);
	}
	
	public void setColRendEdit(){
		table.getColumnModel().getColumn(table.getColumnCount()-1).setCellRenderer(new PanelCell());
		table.getColumnModel().getColumn(table.getColumnCount()-1).setCellEditor(new PanelCell());
		table.getColumnExt(table.getColumnCount()-1).setHideable(false);
	}
	
	public DefaultTableModel getModel(){
		return  model;
	}

	public void clearTable(){
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++) {
				model.setValueAt(null, i, j);
			}
		}
		
		model.setRowCount(0);
	} 
	
	

}
