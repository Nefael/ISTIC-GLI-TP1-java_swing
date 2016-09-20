package Launcher;

import data.*;
import view.*;
import controller.*;

public class Main {
	
	public static  void main(String[]  args){
		
		Data model = new Data();
		
		Item item1 = new Item();
		item1.setName("Internet");
		item1.setValue(33);
		item1.setDescription("le budget pour l'abonnement internet");
		
		Item item2 = new Item();
		item2.setName("Loyer");
		item2.setValue(280);
		item2.setDescription("le loyer mensuel");
		
		Item item3 = new Item();
		item3.setName("Transport");
		item3.setValue(30);
		item3.setDescription("l'abonnement pour les transports");
		
		Item item4 = new Item();
		item4.setName("Nourriture");
		item4.setValue(250);
		item4.setDescription("le miam qu'on met dans la bouche");
		
		Adaptor adaptor = new Adaptor(model);
		
		Controller controller = new Controller(adaptor);
		
		View view = new View(model, controller);
		
		adaptor.addObserver(view);
		adaptor.addItem(item1);
		adaptor.addItem(item2);
		adaptor.addItem(item3);
		adaptor.addItem(item4);
	}

}
