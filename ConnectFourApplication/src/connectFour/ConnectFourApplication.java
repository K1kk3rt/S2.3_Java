package connectFour;

public class ConnectFourApplication {
	
	//construct
	//maak een nieuw model, die het spel opstart.
	public ConnectFourApplication() {
		new GameModel();
	}

	//wordt alseerste uitgevoerd als de applicatie opstart
	//maakt een nieuwe instantie van zichzelf waarna de constructor wordt uitgevoert.
	public static void main(String[] args) {
		new ConnectFourApplication();

	}

}
