package window.search;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import objects.Ocena;
import objects.Ocenialne;
import states.User;

public class OcenialneWindow extends JFrame{
	
	public OcenialneWindow(String title, Vector<Ocenialne> oc){
		super(title);
		this.setSize(430, 400);
		this.setMinimumSize(new Dimension(400,300));
		this.setLocation(300,300);
		JList ocList = new JList();
		ocList.setListData(oc);
		ocList.setCellRenderer(new OcenialneCellRenderer());
		JScrollPane scroll = new JScrollPane(ocList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setSize(400,400);
		this.setContentPane(scroll);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
