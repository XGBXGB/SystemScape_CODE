package view.inventory.itemtile;

import java.util.Date;
import java.util.Iterator;

public interface TypeItemTile {
	/**
	 * Sets the content to be loaded into the model of the assignee combo box
	 * @param iter
	 */
	public void loadAssigneeList(Iterator iter);
	/**
	 * Sets the type for the current inventoryItem template
	 * @param type
	 */
	public void setType(String type);
	/**
	 * Sets the delivery date in the JDateChooser
	 * @param date
	 */
	public void setDeliveryDate(Date date);
}
