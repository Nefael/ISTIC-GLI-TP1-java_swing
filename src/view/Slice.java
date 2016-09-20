package view;

import java.awt.Color;
import java.awt.geom.Arc2D;

class Slice {
	double value;
	Color color;
	private boolean selected = false;
	private Arc2D arc;
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Arc2D getArc() {
		return arc;
	}

	public void setArc(Arc2D arc) {
		this.arc = arc;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected(){
		return this.selected;
	}

	public Slice(String name, String description, double value, Color color) {
		this.value = value;
		this.color = color;
		this.name = name;
		this.description = description;
	}
}
