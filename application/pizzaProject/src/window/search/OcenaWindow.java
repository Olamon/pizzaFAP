package window.search;

import javax.swing.JFrame;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import objects.Ocena;

public class OcenaWindow extends JFrame {
	
	public OcenaWindow(String title, Vector<Ocena> oceny){
		super(title);
		
		this.setSize(430, 400);
		this.setMinimumSize(new Dimension(400,300));
		this.setLocation(300,300);
		JList ocenyList = new JList();
		ocenyList.setListData(oceny);
		ocenyList.setCellRenderer(new OcenaCellRenderer());
		JScrollPane scroll = new JScrollPane(ocenyList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setSize(400,400);
		this.setContentPane(scroll);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
