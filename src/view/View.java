package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import data.*;


public class View extends JFrame implements MouseListener
{

	Graphics2D g2d;	
	Data model;
	Controller controller;
	
	Pie pieChart;
	
	public View(Data im, Controller ic) {
		model = im;
		controller = ic;
		addMouseListener(this);
		createAndShowGUI();
	}
	
	public void update(){
		ArrayList<Item> itemList = model.getItems();
		if(pieChart!=null)this.remove(pieChart);
		pieChart = new Pie(itemList);
		this.add(pieChart);
	}
	
	private void createAndShowGUI() {
		this.setTitle("GLI-TP1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Dimension d = getSize();
		g2d = (Graphics2D) g;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		Iterator<Slice> it = pieChart.slices.iterator();
		Slice tmp;
		
		if(pieChart.getNext().contains(arg0.getX(), arg0.getY())){
			pieChart.setToNext();
			repaint();
			return;
		}
		
		if(pieChart.getPrevious().contains(arg0.getX(), arg0.getY())){
			pieChart.setToPrevious();
			repaint();
			return;
		}
		
		while (it.hasNext()) {
			tmp = it.next();
			if(tmp.getArc().contains(arg0.getX(), arg0.getY())
				&& (Point2D.distance(getBounds().width/2, getBounds().height/2, arg0.getX(), arg0.getY())>=(getBounds().height / 3.2))){
				tmp.setSelected(true);
			}
			else{
				tmp.setSelected(false);
			}
		}
		
		
		
		repaint();
		System.out.println("Mouse at "+arg0.getX()+"x"+arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}