import javax.swing.JOptionPane;

public class Gambling {

	public static int money=100;
	public static int money2Bet;
	
	public static void PlaceBet(){
		money2Bet= BlackJackGame.moneyScale.getValue();
		money= money-money2Bet; 
		//In the cases where player wins they receive double the amount that was bet and in a loss nothing happens 
		//since the money has already been drawn from players total pot
		BlackJackGame.totalMoney.setText("Your total money: " +money);
		
	}
	
	public static void BankruptCheck(){
		if(money<1){
			JOptionPane.showMessageDialog(null,"You went Bankrunpt! You Lose! The Game will now close. \n" +
					" If you wish to play again please restart the game");
			System.exit(0);
		}
	}
}
