package fruitmachine;

import java.util.Random;
import java.util.Scanner;

public class fruitmachine {

	public static void main(String[] args) {
		//maak rollen (dit staat in de class omdat anders de methode roll niet die variabelen kan aanpassen
		int rol1;
		int rol2;
		int rol3;
		
		//zet het aantal credits op 10
		int credits = 10;
		
		//maak random getal tusson de 0 en de 9
		int min = 0;
		int max = 9;
		Random random = new Random();
		
		
		while(true) {
			
			//speel alleen als het aantal credits meer dan 0 is, ander print game over.
			if(credits >0) {
				//geef rollen een random getal tussen de 0 en 9
				rol1 = random.nextInt((max - min) + 1) + min;
				rol2 = random.nextInt((max - min) + 1) + min;
				rol3 = random.nextInt((max - min) + 1) + min;
		
				//print rollen
				System.out.println("rol1: " + rol1);
				System.out.println("rol2: " + rol2);
				System.out.println("rol3: " + rol3);
				
				//verminder aantal credits met 1
				credits = credits -1;
				
				//als er drie gelijke cijfers in beeld staan -> alle rollen hetzelfde -> aantal credits + 5
				//rol1 == rol2 == rol3 snapt java niet. Daarom: && betekent EN dus rol1 == rol2 moet waar zijn, en rol2==rol3 moet waar zijn.
				//als dat allebei zo is, zijn ze alle drie gelijk.
				if((rol1 == rol2) && (rol2 == rol3)) {
					//alle rollen zijn gelijk. Als rol1 7 is, 20 credits. Anders 5.
					if(rol1 == 7) {
						credits = credits + 20;
					}
					else {
						credits = credits + 5;
					}
				}
				
				//als rol3 2 is, en als rol2 2 is, wordt het aantal credits verhoogd met 3. anders + 1. 
				if(rol3 == 2) {
					if(rol2==2) {
						credits=credits + 3;
					}
					else {
						credits=credits+1;
					}
				}
				
				//laat het aantal credits zien
				System.out.println("credits: " + credits);
				
				//laat de gebruiker nog een ronde starten -> op de knop drukken
				System.out.println("druk op een toets om nog een ronde te starten!");
				try {
					System.in.read();
				} 
				catch(Exception e) {
					
				}
				
			}
			else {
				System.out.println("Game over! Geen credits meer!");
			}
		}
		
	}

}
