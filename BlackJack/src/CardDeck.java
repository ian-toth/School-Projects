import javax.swing.ImageIcon;

public class CardDeck {
	
	public static int RandomCard(){
		int randomNr;
		randomNr= (int) Math.ceil(Math.random()*52 -1);
		return randomNr;
	}

	public static int [] Deck = new int [52];
	//Function that creates all the values for the cards in the deck
	public static void DeckValues(){
		Deck [0]=2;
		Deck [1]=2;
		Deck [2]=2;
		Deck [3]=2;
		Deck [4]=3;
		Deck [5]=3;
		Deck [6]=3;
		Deck [7]=3;
		Deck [8]=4;
		Deck [9]=4;
		Deck [10]=4;
		Deck [11]=4;
		Deck [12]=5;
		Deck [13]=5;
		Deck [14]=5;
		Deck [15]=5;
		Deck [16]=6;
		Deck [17]=6;
		Deck [18]=6;		
		Deck [19]=6;
		Deck [20]=7;
		Deck [21]=7;
		Deck [22]=7;		
		Deck [23]=7;		
		Deck [24]=8;		
		Deck [25]=8;
		Deck [26]=8;
		Deck [27]=8;
		Deck [28]=9;
		Deck [29]=9;
		Deck [30]=9;
		Deck [31]=9;
		Deck [32]=10;
		Deck [33]=10;
		Deck [34]=10;
		Deck [35]=10;
		Deck [36]=10;//Jacks
		Deck [37]=10;
		Deck [38]=10;
		Deck [39]=10;
		Deck [40]=10;//Queens
		Deck [41]=10;
		Deck [42]=10;
		Deck [43]=10;
		Deck [44]=10;//Kings
		Deck [45]=10;
		Deck [46]=10;
		Deck [47]=10;
		Deck[48] =11; //Aces (check Game class for AceChecker function which can reduce value of aces)
		Deck[49] =11;
		Deck[50] =11;
		Deck[51] =11;
		
		
	}
	
	public static  boolean [] DeckDrawn = new boolean[52];
	//Function that restores deck and makes it possible to draw the cards (if undrawn boolean is true, if already drawn false)
	//Check Game class under the PlayerDraw functions for code
	public static void RestoreDeck(){
		for(int i=0; i<52; i++){
			DeckDrawn[i]=true;
		}
	}
	
	public static ImageIcon [] DeckPics = new ImageIcon[52];
	public static ImageIcon CardBack= new ImageIcon("Back.png");
	//Function that adds all the pictures of the cards so when a card is drawn its picture can appear
	public static void CardPics(){
		DeckPics[0] = new ImageIcon("Spade2.png");
		DeckPics[1] = new ImageIcon("Club2.png");
		DeckPics[2] = new ImageIcon("Heart2.png");
		DeckPics[3] = new ImageIcon("Diamond2.png");
		DeckPics[4] = new ImageIcon("Spade3.png");
		DeckPics[5] = new ImageIcon("Club3.png");
		DeckPics[6] = new ImageIcon("Heart3.png");
		DeckPics[7] = new ImageIcon("Diamond3.png");
		DeckPics[8] = new ImageIcon("Spade4.png");
		DeckPics[9] = new ImageIcon("Club4.png");
		DeckPics[10] = new ImageIcon("Heart4.png");
		DeckPics[11] = new ImageIcon("Diamond4.png");
		DeckPics[12] = new ImageIcon("Spade5.png");
		DeckPics[13] = new ImageIcon("Club5.png");
		DeckPics[14] = new ImageIcon("Heart5.png");
		DeckPics[15] = new ImageIcon("Diamond5.png");
		DeckPics[16] = new ImageIcon("Spade6.png");
		DeckPics[17] = new ImageIcon("Club6.png");
		DeckPics[18] = new ImageIcon("Heart6.png");
		DeckPics[19] = new ImageIcon("Diamond6.png");
		DeckPics[20] = new ImageIcon("Spade7.png");
		DeckPics[21] = new ImageIcon("Club7.png");
		DeckPics[22] = new ImageIcon("Heart7.png");
		DeckPics[23] = new ImageIcon("Diamond7.png");
		DeckPics[24] = new ImageIcon("Spade8.png");
		DeckPics[25] = new ImageIcon("Club8.png");
		DeckPics[26] = new ImageIcon("Heart8.png");
		DeckPics[27] = new ImageIcon("Diamond8.png");
		DeckPics[28] = new ImageIcon("Spade9.png");
		DeckPics[29] = new ImageIcon("Club9.png");
		DeckPics[30] = new ImageIcon("Heart9.png");
		DeckPics[31] = new ImageIcon("Diamond9.png");
		DeckPics[32] = new ImageIcon("Spade10.png");
		DeckPics[33] = new ImageIcon("Club10.png");
		DeckPics[34] = new ImageIcon("Heart10.png");
		DeckPics[35] = new ImageIcon("Diamond10.png");
		DeckPics[36] = new ImageIcon("SpadeJ.png");
		DeckPics[37] = new ImageIcon("ClubJ.png");
		DeckPics[38] = new ImageIcon("HeartJ.png");
		DeckPics[39] = new ImageIcon("DiamondJ.png");
		DeckPics[40] = new ImageIcon("SpadeQ.png");
		DeckPics[41] = new ImageIcon("ClubQ.png");
		DeckPics[42] = new ImageIcon("HeartQ.png");
		DeckPics[43] = new ImageIcon("DiamondQ.png");
		DeckPics[44] = new ImageIcon("SpadeK.png");
		DeckPics[45] = new ImageIcon("ClubK.png");
		DeckPics[46] = new ImageIcon("HeartK.png");
		DeckPics[47] = new ImageIcon("DiamondK.png");
		DeckPics[48] = new ImageIcon("SpadeA.png");
		DeckPics[49] = new ImageIcon("ClubA.png");
		DeckPics[50] = new ImageIcon("HeartA.png");
		DeckPics[51] = new ImageIcon("DiamondA.png");
	}
}
