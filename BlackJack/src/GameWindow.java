import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class BlackJackGame extends JFrame implements ActionListener,  ChangeListener
{
	
	Color casino= new Color(39,119,20);
	static ImageIcon cross = new ImageIcon("cross.png");
	static ImageIcon tick = new ImageIcon("tick.png");

	//Interface Components
	
	static JSlider moneyScale = new JSlider (1,Gambling.money);
	
	static JLabel playerSumLabel = new JLabel ("Player Score: " +Game.sumPlayer);
	static JLabel dealerSumLabel= new JLabel ("Dealer Score: " +Game.sumDealer);
	static JLabel totalMoney = new JLabel ("Your total money: " +Gambling.money);
	static JLabel moneyBet = new JLabel ("You wish to place this much: " +moneyScale.getValue());	
	static JLabel instructions = new JLabel ("BlackJack Game: To start the game you must place a bet first");
	JLabel spacing = new JLabel(" ");
	
	static JButton drawButton = new JButton (CardDeck.CardBack);
	static JButton stayButton = new JButton ("Stay", cross);
	static JButton betButton = new JButton ("Place Bet: 50", tick);
	static JButton rulesButton = new JButton("Read the Rules");
	
	static JLabel cardLabelPlayer1 = new JLabel(" ");
	static JLabel cardLabelPlayer2 = new JLabel(" ");
	static JLabel cardLabelPlayer3 = new JLabel(" ");
	static JLabel cardLabelPlayer4 = new JLabel(" ");
	static JLabel cardLabelPlayer5 = new JLabel(" ");
	static JLabel cardLabelDealer1 = new JLabel(" ");
	static JLabel cardLabelDealer2 = new JLabel(" ");
	static JLabel cardLabelDealer3 = new JLabel(" ");
	static JLabel cardLabelDealer4 = new JLabel(" ");
	static JLabel cardLabelDealer5 = new JLabel(" ");
	
	JPanel dealer = new JPanel(new GridLayout(1,5,5,15));
	JPanel player = new JPanel(new GridLayout(2,5,5,15));
	JPanel scores = new JPanel(new GridLayout(2,1,1,5));
	JPanel bet = new JPanel(new GridLayout(2,1,5,25));
	JPanel mid = new JPanel(new GridLayout(1,2,5,5));
	
	public BlackJackGame(){
		super("BlackJack Game");
		setSize (1350,700); //1350,730 max
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible (true);
		
		CardDeck.CardPics(); // Gives pictures for all cards
		
		Container contentArea = getContentPane();
		contentArea.setBackground(casino);
		player.setBackground(casino);
		dealer.setBackground(casino);
		scores.setBackground(casino);
		bet.setBackground(casino);
		mid.setBackground(casino);
		
		moneyScale.setMajorTickSpacing(Gambling.money/10);
		moneyScale.setPaintTicks(true);
		
		rulesButton.setBackground(Color.gray);
		
		drawButton.addActionListener(this);
		stayButton.addActionListener(this);
		betButton.addActionListener(this);
		moneyScale.addChangeListener(this);
		rulesButton.addActionListener(this);
		
		player.add(cardLabelPlayer1);
		player.add(cardLabelPlayer2);
		player.add(cardLabelPlayer3);
		player.add(cardLabelPlayer4);
		player.add(cardLabelPlayer5);
		player.add(drawButton);
		player.add(stayButton);
		player.add(rulesButton);
		player.add(betButton);
		player.add(moneyScale);
		scores.add(dealerSumLabel);
		scores.add(playerSumLabel);
		dealer.add(cardLabelDealer1);
		dealer.add(cardLabelDealer2);
		dealer.add(cardLabelDealer3);
		dealer.add(cardLabelDealer4);
		dealer.add(cardLabelDealer5);
		bet.add(totalMoney);
		bet.add(moneyBet);
		mid.add(spacing);
		mid.add(instructions);
		
		
		contentArea.add("South",player);
		contentArea.add("North",dealer);
		contentArea.add("West",scores);
		contentArea.add("East",bet);
		contentArea.add("Center",mid);
		
		setContentPane(contentArea);
		drawButton.setEnabled(false);
		stayButton.setEnabled(false);
		CardDeck.DeckValues();
		CardDeck.RestoreDeck();
		CardDeck.CardPics();
		
		//disables draw and stay until bet is placed as well as loads in all necessities for the cards
	}
	
	public void stateChanged(ChangeEvent event){
		moneyBet.setText("You wish to place this much: " +moneyScale.getValue());
		betButton.setText("Place Bet: " + moneyScale.getValue());
	}

	public void actionPerformed (ActionEvent event){
		
		if (event.getSource()==rulesButton){
			JOptionPane.showMessageDialog(null, "The aim of the game is to get as close to 21 without going above it. \n" +
					"If you or the dealer exceed 21 they loose, the person closest to 21 wins! \n" +
					"When the game starts you are on the bottom and recieve 2 cards, the dealer is on the top and recieves 1, \n" +
					"But first you must place your bet!");
		}
		
		if (event.getSource()== drawButton){
			Game.PlayerDraw();

		}
		
		if (event.getSource()== stayButton){
			drawButton.setEnabled(false);
			stayButton.setEnabled(false);
			Game.DealerDraw();
			
		}
		
		if (event.getSource()== betButton){
			drawButton.setEnabled(true);
			stayButton.setEnabled(true);
			betButton.setEnabled(false);
			moneyScale.setPaintLabels(false);
			moneyScale.setEnabled(false);
			Gambling.PlaceBet();
			instructions.setText(null);
			moneyBet.setText("You bet this much: " +Gambling.money2Bet);
			Game.StartUp();
		}
	}

	
	
}

public class GameWindow {
	public static void main(String[] args) {
		BlackJackGame Win = new BlackJackGame();

	}
}
