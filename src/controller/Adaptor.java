package controller;

import java.util.ArrayList;
import java.util.Iterator;

import data.*;
import view.View;

public class Adaptor {
	
	private ArrayList<View> views;
	
	private Data model;
	
	public Adaptor(Data model){
		this.views = new ArrayList<View>();
		this.model = model;
	}
	
	public  void addItem(Item item){
		this.model.addItem(item);
		notifyObservers();
	}
	
	public void addObserver(View observer){
		views.add(observer);
	}
	
	private void notifyObservers(){
		Iterator<View> it = views.iterator();
		while(it.hasNext()){
			it.next().update();
		}
	}

}
