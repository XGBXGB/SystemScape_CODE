package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public abstract class CellEdit extends JPanel implements ActionListener {
	private JButton edit;
	private JButton view;
	public CellEdit(){
		edit=new Button.ButtonBuilder().img("src/assets/Round/Note2.png", 30,
				30).build();
		view=new Button.ButtonBuilder().img("src/assets/Round/Preview.png", 30,
				30).build();
		
		edit.addActionListener(this);
		view.addActionListener(this);
		
		this.add(view);
		this.add(edit);
		
		this.repaint();
		this.revalidate();
	}
	public abstract Object get();
	public JButton getBtnEdit(){
		return edit;
	}
	public JButton getBtnView(){
		return view;
	}
}
