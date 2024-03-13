import javax.swing.JOptionPane;

public class Game {
	public static int sumPlayer = 0;
	public static int sumDealer =0;
	public static int playerDrawnCard;
	public static int dealerDrawnCard;
	static int playerCounter=1; //used for position of where to place card (check CardPlacementPlayer)
	static int dealerCounter=1; // same here
	static int aceOnHandPlayer=0;//Used for AceChecker function (function that sets acevalue to 1
	static int aceOnHandDealer=0;//same here
	
	public static void ResetFunction(){
		sumPlayer=0;
		sumDealer=0;
		playerCounter=1;
		dealerCounter=1;
		aceOnHandPlayer=0;
		aceOnHandDealer=0;
		CardDeck.RestoreDeck(); //sets all cards to true and thus can be drawn from the deck again 
		BlackJackGame.cardLabelPlayer1.setIcon(null);
		BlackJackGame.cardLabelPlayer2.setIcon(null);
		BlackJackGame.cardLabelPlayer3.setIcon(null);
		BlackJackGame.cardLabelPlayer4.setIcon(null);
		BlackJackGame.cardLabelPlayer5.setIcon(null);
		BlackJackGame.cardLabelDealer1.setIcon(null);
		BlackJackGame.cardLabelDealer2.setIcon(null);
		BlackJackGame.cardLabelDealer3.setIcon(null);
		BlackJackGame.cardLabelDealer4.setIcon(null);
		BlackJackGame.cardLabelDealer5.setIcon(null);
		BlackJackGame.playerSumLabel.setText("Player Score: " +sumPlayer);
		BlackJackGame.dealerSumLabel.setText("Dealer Score: " +sumDealer);
		BlackJackGame.betButton.setText("Place Bet: " + BlackJackGame.moneyScale.getValue());
		
		BlackJackGame.betButton.setEnabled(true);
		BlackJackGame.moneyScale.setEnabled(true);
		BlackJackGame.drawButton.setEnabled(false);
		BlackJackGame.stayButton.setEnabled(false);	
		
		BlackJackGame.instructions.setText("To start the game you must place a bet first");
			
	}
	
	//Function below for player to draw a card, includes what happens if player goes bust.
	
	public static void PlayerDraw(){
		playerDrawnCard=CardDeck.RandomCard();
		while(CardDeck.DeckDrawn[playerDrawnCard]==false){
			playerDrawnCard=CardDeck.RandomCard();
			//Above does so if card selected has previously been drawn it will re-perform the operation,
			//thus two same cards cannot be drawn
		}					
		sumPlayer=sumPlayer + CardDeck.Deck[playerDrawnCard];
		CardDeck.DeckDrawn[playerDrawnCard]=false;
		//Above sets the card to a state where it cannot be drawn again
		AceCheckerPlayer(); //Checks if Ace values have to be reduced (and notes if aces have been drawn)
		BlackJackGame.playerSumLabel.setText("Player Score: " +sumPlayer);
		CardPlacementPlayer(); // Designates which spot the card will be added to
		
		
		
		/*
		 * When a player goes bust the bellow happens happens
		 */
		
		if (sumPlayer>21){
			int choise=JOptionPane.showOptionDialog(null,"You went bust! Do you wish to continue playing? " +
					"To play again press Yes, to quit press No.", "BlackJack Game", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null, null, null);
			
			if (choise==JOptionPane.YES_OPTION){ 
				ResetFunction();
				BlackJackGame.totalMoney.setText("Your total money: " +Gambling.money);
				BlackJackGame.moneyScale.setMaximum(Gambling.money);
				BlackJackGame.moneyScale.setMajorTickSpacing((Gambling.money/10));
				BlackJackGame.moneyScale.setPaintTicks(true);
				BlackJackGame.moneyScale.revalidate();
				BlackJackGame.moneyScale.setValue(Gambling.money/2);
				Gambling.BankruptCheck();
				
			}
			if (choise== JOptionPane.NO_OPTION){
				System.exit(0);
			}
			if (choise==JOptionPane.CLOSED_OPTION){
				System.exit(0);
			}
		}
	}
	
	//Function for what happens when dealer draws, includes cases for if dealer goes bust, if dealer ties or if dealer wins
	
	public static void DealerDraw(){
		while(sumDealer< sumPlayer || sumDealer< 17){
			dealerDrawnCard=CardDeck.RandomCard();
			while(CardDeck.DeckDrawn[dealerDrawnCard]==false){
				dealerDrawnCard=CardDeck.RandomCard();
			}
			sumDealer= sumDealer+ CardDeck.Deck[dealerDrawnCard];
			CardDeck.DeckDrawn[dealerDrawnCard]=false;
			AceCheckerDealer(); //Checks if Ace values have to be reduced
			BlackJackGame.dealerSumLabel.setText("Dealer Score: " +sumDealer);
			CardPlacementDealer();
			
		}
		
		/*
		 * If it ties
		 */
		if (sumDealer==sumPlayer){
			int choise=JOptionPane.showOptionDialog(null,"You tied! Do you wish to continue playing? " +
					"To play again press Yes, to quit press No.", "BlackJack Game", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null, null, null);
			
			if (choise==JOptionPane.YES_OPTION){ 
				ResetFunction();
				
				Gambling.money= Gambling.money +Gambling.money2Bet;
				Gambling.money2Bet=0;
				BlackJackGame.totalMoney.setText("Your total money: " +Gambling.money);
				BlackJackGame.moneyScale.setMaximum(Gambling.money);
				BlackJackGame.moneyScale.setMajorTickSpacing((Gambling.money/10));
				BlackJackGame.moneyScale.setPaintTicks(true);
				BlackJackGame.moneyScale.revalidate();
				BlackJackGame.moneyScale.setValue(Gambling.money/2);
			}
			if (choise== JOptionPane.NO_OPTION){
				System.exit(0);
			}
			if (choise==JOptionPane.CLOSED_OPTION){
				System.exit(0);
			}
		}
		
		/*
		 * If dealer wins
		 */
		
		if (sumDealer>sumPlayer && sumDealer< 22){
			int choise=JOptionPane.showOptionDialog(null,"You lose! Do you wish to continue playing? " +
					"To play again press Yes, to quit press No.", "BlackJack Game", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null, null, null);
			
			if (choise==JOptionPane.YES_OPTION){ 
				ResetFunction();
				Gambling.money2Bet=0;
				BlackJackGame.totalMoney.setText("Your total money: " +Gambling.money);
				BlackJackGame.moneyScale.setMaximum(Gambling.money);
				BlackJackGame.moneyScale.setMajorTickSpacing((Gambling.money/10));
				BlackJackGame.moneyScale.setPaintTicks(true);
				BlackJackGame.moneyScale.revalidate();
				BlackJackGame.moneyScale.setValue(Gambling.money/2);
				Gambling.BankruptCheck();
			}
			if (choise== JOptionPane.NO_OPTION){
				System.exit(0);
			}
			if (choise==JOptionPane.CLOSED_OPTION){
				System.exit(0);
			}
		}
		
		/*
		 * If dealer goes bust
		 */
		if (sumDealer>21){
			int choise=JOptionPane.showOptionDialog(null,"You win! Do you wish to continue playing? " +
					"To play again press Yes, to quit press No.", "BlackJack Game", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null, null, null);
			
			if (choise==JOptionPane.YES_OPTION){ 
				ResetFunction();
				
				Gambling.money= Gambling.money +(Gambling.money2Bet *2);
				Gambling.money2Bet=0;
				BlackJackGame.totalMoney.setText("Your total money: " +Gambling.money);
				BlackJackGame.moneyScale.setMaximum(Gambling.money);
				BlackJackGame.moneyScale.setMajorTickSpacing((Gambling.money/10));
				BlackJackGame.moneyScale.setPaintTicks(true);
				BlackJackGame.moneyScale.revalidate();
				BlackJackGame.moneyScale.setValue(Gambling.money/2);
			}
			if (choise== JOptionPane.NO_OPTION){
				System.exit(0);
			}
			if (choise==JOptionPane.CLOSED_OPTION){
				System.exit(0);
			}
		}
	}
	
	public static void StartUp(){
		//For the player (gets 2 cards)
		for(int f=1; f<3; f++){
			playerDrawnCard=CardDeck.RandomCard();
			while(CardDeck.DeckDrawn[playerDrawnCard]==false){
				playerDrawnCard=CardDeck.RandomCard();
			}
			sumPlayer=sumPlayer + CardDeck.Deck[playerDrawnCard];
			CardDeck.DeckDrawn[playerDrawnCard]=false;
			//This does so if the same card is drawn again it will redraw a different card
			AceCheckerPlayer(); //Checks if Ace values have to be reduced (and notes if aces have been drawn)
			BlackJackGame.playerSumLabel.setText("Player Score: " +sumPlayer);
			CardPlacementPlayer();
		}
		
		//For the dealer (gets 1 card)
		dealerDrawnCard=CardDeck.RandomCard();
		while(CardDeck.DeckDrawn[dealerDrawnCard]==false){
			dealerDrawnCard=CardDeck.RandomCard();
		}
		sumDealer=sumDealer + CardDeck.Deck[dealerDrawnCard];
		CardDeck.DeckDrawn[dealerDrawnCard]=false; //This does so if the same card is drawn again it will redraw a different card due to the above
		AceCheckerDealer(); //Checks if Ace values have to be reduced
		BlackJackGame.dealerSumLabel.setText("Dealer Score: " +sumDealer);
		CardPlacementDealer();
	
	}
	
	//The two functions below decide in what slot the cards are placed
	
	public static void CardPlacementPlayer(){
		
		switch(playerCounter){
			case 1: BlackJackGame.cardLabelPlayer1.setIcon(CardDeck.DeckPics[playerDrawnCard]);
					playerCounter++;
					break;
			case 2: BlackJackGame.cardLabelPlayer2.setIcon(CardDeck.DeckPics[playerDrawnCard]);
					playerCounter++;
					break;
			case 3: BlackJackGame.cardLabelPlayer3.setIcon(CardDeck.DeckPics[playerDrawnCard]);
					playerCounter++;
					break;
			case 4: BlackJackGame.cardLabelPlayer4.setIcon(CardDeck.DeckPics[playerDrawnCard]);
					playerCounter++;
					break;
			case 5: BlackJackGame.cardLabelPlayer5.setIcon(CardDeck.DeckPics[playerDrawnCard]);
			//In rare event of having to draw more than 5 cards, the last card will continually be replaced
		}
	}
	
	public static void CardPlacementDealer(){
		
		switch(dealerCounter){
			case 1: BlackJackGame.cardLabelDealer1.setIcon(CardDeck.DeckPics[dealerDrawnCard]);
					dealerCounter++;
					break;
			case 2: BlackJackGame.cardLabelDealer2.setIcon(CardDeck.DeckPics[dealerDrawnCard]);
					dealerCounter++;
					break;
			case 3: BlackJackGame.cardLabelDealer3.setIcon(CardDeck.DeckPics[dealerDrawnCard]);
					dealerCounter++;
					break;
			case 4: BlackJackGame.cardLabelDealer4.setIcon(CardDeck.DeckPics[dealerDrawnCard]);
					dealerCounter++;
					break;
			case 5: BlackJackGame.cardLabelDealer5.setIcon(CardDeck.DeckPics[dealerDrawnCard]);
					//In rare event of having to draw more than 5 cards, the last card will continually be replaced
		}
	}
	
	public static void AceCheckerPlayer(){
		if(playerDrawnCard>47){
			aceOnHandPlayer++; //Makes note if player has ace on hand that is valued 11
		}
		if(sumPlayer>21 && aceOnHandPlayer>0){
			aceOnHandPlayer--; 
			// Removed the note of an ace valued 11 as it is "reduced" to a value of 1 by removing 10 from players score
			sumPlayer= sumPlayer -10; 
		}
	}
	
	public static void AceCheckerDealer(){
		if(dealerDrawnCard>47){
			aceOnHandDealer++;
		}
		if(sumDealer>21 && aceOnHandDealer>0){
			aceOnHandDealer--;
			sumDealer= sumDealer -10; 
		}
	}

}