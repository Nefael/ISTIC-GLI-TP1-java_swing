package data;

import java.util.ArrayList;

public class Data {
	
	private String title;
	
	private ArrayList<Item> items;
	
	public Data(){
		items = new ArrayList<Item>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addItem(Item item){
		this.items.add(item);
	}
	
	public void removeItem(int index){
		this.items.remove(index);
	}
	
	public ArrayList<Item> getItems(){
		return this.items;
	}
}
