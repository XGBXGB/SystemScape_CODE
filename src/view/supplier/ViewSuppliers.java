package view.supplier;

import controller.SupplierController;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import model.Supplier;
import model.SupplierContact;

import view.CellEdit;
import view.Observer;
import view.ViewTemplate;

public class ViewSuppliers extends ViewTemplate implements Observer {

    SupplierController supplierController;

    public ViewSuppliers() {
        super();
        supplierController = SupplierController.getInstance();
        supplierController.registerObserver(this);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        setColCount(4);
        String headers[] = {"Supplier", "Address", "Contact #", ""};
        getModel().setColumnIdentifiers(headers);
        setColWidth(0, 150);
        setColWidth(1, 400);
        setColWidth(2, 30);
        setColWidth(3, 15);
        setColRendEdit();

    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        getModel().setRowCount(getModel().getRowCount() + 1);
        getModel().setValueAt("Rissa", getModel().getRowCount() - 1, 0);
        getModel().setValueAt("Grace", getModel().getRowCount() - 1, 1);
        getModel().setValueAt("Marie", getModel().getRowCount() - 1, 2);
        getModel().setValueAt(new CellEdit(), getModel().getRowCount() - 1, 3);
    }

    @Override
    public void update() {
        clearTable();
        Supplier supplier;
        Iterator data = supplierController.getAll();
        while (data.hasNext()) {
        	
        	
            supplier = (Supplier) data.next();
            
            System.out.println("SUPPLIER THINGY"+ supplier.getName());
            
            Iterator contactsIterator = supplier.getSupplierContactList();
            
            String contactsString = "";
            while (contactsIterator.hasNext()) {
            	
                contactsString = contactsString + ((SupplierContact) contactsIterator.next()).toString() + ", ";
                System.out.println(contactsString);
            
            }
            getModel().setRowCount(getModel().getRowCount() + 1);
            getModel().setValueAt(supplier.getName(), getModel().getRowCount() - 1, 0);
            getModel().setValueAt(supplier.getCountry() + ", " + supplier.getState() + ", " + supplier.getCity(), getModel().getRowCount() - 1, 1);
            getModel().setValueAt(contactsString, getModel().getRowCount() - 1, 2);
            getModel().setValueAt(new CellEdit(), getModel().getRowCount() - 1, 3);
        }
    }
    
    public void filterPopulate(Iterator data){
        clearTable();
        while(data.hasNext()){
            getModel().setRowCount(getModel().getRowCount() + 1);
            getModel().setValueAt(data.next(), getModel().getRowCount() - 1, 0);
            getModel().setValueAt(data.next(), getModel().getRowCount() - 1, 1);
            getModel().setValueAt(data.next(), getModel().getRowCount() - 1, 2);
            getModel().setValueAt(new CellEdit(), getModel().getRowCount() - 1, 3);
        }
    }
}
