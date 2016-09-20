package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.UIManager;

import data.Item;

class Pie extends JComponent {
	ArrayList<Slice> slices;
	Rectangle2D next;
	Rectangle2D previous;

	Pie(ArrayList<Item> SliceList) {
		slices = new ArrayList<Slice>();
		Item tmp;

		Iterator<Item> it = SliceList.iterator();
		while (it.hasNext()) {
			tmp = it.next();
			slices.add(new Slice(tmp.getName(), tmp.getDescription(), tmp.getValue(),
					new Color((int) (Math.random() * 0x1000000))));
		}
	}

	public void paint(Graphics g) {
		drawPie((Graphics2D) g, getBounds(), slices);
	}

	void drawPie(Graphics2D g, Rectangle area, ArrayList<Slice> slices) {
		double total = 0.0D;
		for (int i = 0; i < slices.size(); i++) {
			total += slices.get(i).value;
		}
		double curValue = 0.0D;
		double startAngle = 0;
		for (int i = 0; i < slices.size(); i++) {
			startAngle = curValue * 360 / total;
			double arcAngle = (slices.get(i).value * 360 / total);

			Arc2D arc;
			if (slices.get(i).isSelected()) {
				arc = new Arc2D.Double(area.width / 2 - (area.width / 2.25), area.height / 2 - (area.height / 2.25),
						area.width / 1.125, area.height / 1.125, startAngle, arcAngle, Arc2D.PIE);
				g.setColor(Color.BLACK);
				g.drawString(slices.get(i).getDescription()+" :", 5, 15);
				g.drawString(Double.toString(slices.get(i).value)+"€", 5, 30);

			} else {

				arc = new Arc2D.Double(area.width / 2 - (area.width / 2.5), area.height / 2 - (area.height / 2.5),
						area.width / 1.25, area.height / 1.25, startAngle, arcAngle, Arc2D.PIE);
			}
			g.setColor(slices.get(i).color);
			g.fill(arc);
			slices.get(i).setArc(arc);
			curValue += slices.get(i).value;
		}

		g.setColor(UIManager.getColor("Panel.background"));
		drawCenteredCircle(g, area.width / 2, area.height / 2, (int) (area.height / 1.6));
		g.setColor(Color.RED);
		drawCenteredCircle(g, area.width / 2, area.height / 2, (int) (area.height / 3));
		g.setColor(Color.BLACK);
		
		previous = new Rectangle2D.Double(area.width / 2 +10, area.height-30, 20, 20);
		next = new Rectangle2D.Double(area.width / 2 -30, area.height-30, 20, 20);
		
		g.fillPolygon(new int[] {area.width / 2 +10,area.width / 2 + 10, area.width / 2 +30}, new int[] {area.height - 30, area.height -10,area.height-20}, 3);
		g.fillPolygon(new int[] {area.width / 2 -10,area.width / 2 - 10, area.width / 2 -30}, new int[] {area.height - 30, area.height -10,area.height-20}, 3);

		g.draw(next);
		g.draw(previous);
		drawCenteredString(g, "Total : " + total + "€", area);
	}

	public Rectangle2D getPrevious() {
		return previous;
	}

	public Rectangle2D getNext() {
		return next;
	}
	
	public void setToNext(){
		for (int i = 0; i < slices.size(); i++) {
			if(slices.get(i).isSelected()){
				slices.get(i).setSelected(false);
				slices.get((i+1)%slices.size()).setSelected(true);
				break;
			}
		}
	}

	public void setToPrevious(){
		for (int i = 0; i < slices.size(); i++) {
			if(slices.get(i).isSelected()){
				slices.get(i).setSelected(false);
				if(i==0) slices.get(slices.size()-1).setSelected(true);
				else slices.get((i-1)%slices.size()).setSelected(true);
				break;
			}
		}
	}
	public ArrayList<Slice> getSlices() {
		return slices;
	}

	public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
		x = x - (r / 2);
		y = y - (r / 2);
		g.fillOval(x, y, r, r);
	}

	public void drawCenteredString(Graphics2D g2d, String text, Rectangle rect) {
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(text, g2d);
		int x = (int) (rect.getWidth() - r.getWidth()) / 2;
		int y = (int) (rect.getHeight() - r.getHeight()) / 2 + fm.getAscent();
		g2d.drawString(text, x, y);
	}
}
