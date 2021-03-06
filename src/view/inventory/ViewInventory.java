package view.inventory;

import controller.AssignmentController;
import controller.ContractController;
import controller.EmployeeController;
import controller.ITAssetController;
import controller.InventoryItemController;
import controller.WarrantyController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import model.Contract;
import model.Employee;
import model.ITAsset;
import model.InventoryItem;
import model.Warranty;
import view.CellEdit;
import view.Observer;
import view.PanelCell;
import view.ViewTemplate;

public class ViewInventory extends ViewTemplate implements Observer,
		ActionListener {

	InventoryItemController iiController;
	ITAssetController itAssetController;
	AssignmentController assignmentController;
	EmployeeController employeeController;
	ContractController contractController;
	WarrantyController warrantyController;

	private DefaultTableModel tglModel;
	private TabInventory tab;

	public ViewInventory(TabInventory tab) {
		super();
		this.tab=tab;
		iiController = InventoryItemController.getInstance();
		itAssetController = ITAssetController.getInstance();
		assignmentController = AssignmentController.getInstance();
		employeeController = EmployeeController.getInstance();
		contractController = ContractController.getInstance();
		warrantyController = WarrantyController.getInstance();

		iiController.registerObserver(this);
	}

	@Override
	public void initialize() {
		setColCount(12);
		String headers[] = { "Item", "Description", "Type", "Location",
				"Asset Tag", "Service Tag", "Assignee", "Invoice#",
				"Delivery Date", "End of Contract", "End of Warranty", "" };

		// String headers[] = { "Item", "Description", "Type", "Quantity",
		// "Location", "Asset Tag", "Service Tag", "Assignee", "Invoice#",
		// "Delivery Date", "End of Contract", "End of Warranty", "" };
		getModel().setColumnIdentifiers(headers);
		setColRendEdit(new PanelCell(), new PanelCell());

		String headers2[] = { "Item", "Description", "Type", "Quantity" };
		tglModel = new DefaultTableModel(headers2, 4) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}

			public boolean isFocusable(int rowIndex, int mColIndex) {
				return true;
			}

			public boolean isCellSelectable(int rowIndex, int mColIndex) {
				return true;
			}
		};
		tglModel.setRowCount(0);
		activateToggle(tglModel);
		getToggle().addActionListener(this);

		packTable();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		clearTable();
		InventoryItem inventoryItem;
		ITAsset itAsset;
		Contract contract;
		Warranty warranty;
		Employee employee;
		Iterator data = iiController.getAll();
		int employeeid;

		while (data.hasNext()) {
			Object object = data.next();
			inventoryItem = (InventoryItem) object;
			employeeid = 0;
			warranty = (Warranty) warrantyController.getObject(String
					.valueOf(inventoryItem.getID()));
			contract = (Contract) contractController.getObject(String
					.valueOf(inventoryItem.getID()));
			Object idObject = assignmentController.getObject(String
					.valueOf(inventoryItem.getID()));
			if (idObject != null) {
				employeeid = (int) idObject;
			}

			getModel().setRowCount(getModel().getRowCount() + 1);
			getModel().setValueAt(inventoryItem.getName(),
					getModel().getRowCount() - 1, 0);
			getModel().setValueAt(inventoryItem.getDescription(),
					getModel().getRowCount() - 1, 1);
			getModel().setValueAt(inventoryItem.getClassification(),
					getModel().getRowCount() - 1, 2);
			// getModel().setValueAt(inventoryItem.getStatus(),
			// getModel().getRowCount() - 1, 3);
			getModel().setValueAt(inventoryItem.getLocation(),
					getModel().getRowCount() - 1, 3);
			getModel().setValueAt(inventoryItem.getInvoiceNo(),
					getModel().getRowCount() - 1, 7);

			if (object instanceof ITAsset) {
				inventoryItem = (ITAsset) object;

				getModel().setValueAt(((ITAsset) inventoryItem).getAssetTag(),
						getModel().getRowCount() - 1, 4);
				getModel().setValueAt(
						((ITAsset) inventoryItem).getServiceTag(),
						getModel().getRowCount() - 1, 5);
				getModel().setValueAt(
						((ITAsset) inventoryItem).getDeliveryDate(),
						getModel().getRowCount() - 1, 8);
			}

			if (employeeid != 0) {
				employee = (Employee) employeeController.getObject(String
						.valueOf(employeeid));
				getModel().setValueAt(employee.getName(),
						getModel().getRowCount() - 1, 6);
			}
			if (contract != null && warranty != null) {
				getModel().setValueAt(contract.getEndDate(),
						getModel().getRowCount() - 1, 9);
				getModel().setValueAt(warranty.getEndDate(),
						getModel().getRowCount() - 1, 10);
			}
			getModel().setValueAt(new InventoryCellEdit(inventoryItem, tab), getModel().getRowCount() - 1,
					11);
		}

		data = iiController.getAllQuantity();

		for (int i = 0; i < tglModel.getRowCount(); i++) {
			tglModel.removeRow(i);
		}
		tglModel.setRowCount(0);
		while (data.hasNext()) {
			inventoryItem = (InventoryItem) data.next();
			tglModel.setRowCount(tglModel.getRowCount() + 1);
			tglModel.setValueAt(inventoryItem.getName(), tglModel
					.getRowCount() - 1, 0);
			tglModel.setValueAt(inventoryItem.getDescription(), tglModel
					.getRowCount() - 1, 1);
			tglModel.setValueAt(inventoryItem.getClassification(), tglModel
					.getRowCount() - 1, 2);
			tglModel.setValueAt(inventoryItem.getQuantity(), tglModel
					.getRowCount() - 1, 3);
		}
		
		packTable();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		toggle(new PanelCell(), new PanelCell());
	}
}
