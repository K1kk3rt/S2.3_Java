import java.util.Random;

public class main {

	public static void main(String[] args) {
		
		int[] aantalKeer = new int[18];
		int aantalWorpen = 200;
		
		int d1;
		int d2;
		int d3;
		
		int max = 5;
		int min =0;
		
		Random random = new Random();
		
		int i = 0;
		
		//gooi worpen en vul array
		while(i < aantalWorpen) {
			d1 = random.nextInt((max - min) + 1) + min;
			d2 = random.nextInt((max - min) + 1) + min;
			d3 = random.nextInt((max - min) + 1) + min;
			
			int waarde = d1 + d2 + d3;
			
			aantalKeer[waarde]++;
			i++;
		}
		
		//print resultaten
		for(int j = 0; j < aantalKeer.length; j++) {
			
			//print nummer
			System.out.printf("%-3s", j+1);
			
			int aantal = aantalKeer[j];
			int k = 0;
			while (k < aantal) {
				System.out.printf("%-2s", "X");
				k++;
			}
			
			System.out.println();
		}
		
	}

}
