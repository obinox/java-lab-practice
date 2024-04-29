package jpt;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	public static int seed;
	public static Random random;
	public static void main(String[] args) {
		seed = Integer.parseInt(args[0]);
		Deck deck = new Deck();
		
		random = new Random(Blackjack.seed);
		int nplayer = Integer.parseInt(args[1]);
		if (1>nplayer||nplayer>5) {
			throw new IllegalArgumentException("The number of players ranges from 1 to 5");
		}
		deck.shuffle(seed);
		Hand[] players = new Hand[6];
		
		players[0] = new Player();
		for (int i=1;i<nplayer;i++) {
			players[i] = new Computer();
		}
		// initial setting
		players[nplayer] = new House();
		for (int j=0;j<2;j++) {
			for (int i=0;i<nplayer+1;i++) {
				players[i].hit(deck);
			}
		}
		// display first
		players[nplayer].display(0);
		for (int i=0;i<nplayer;i++) {
			players[i].display(i);
		}
		System.out.println();
		
		if (players[nplayer].calc()!=21) {
			Scanner scn = new Scanner(System.in);
			//p1~pn
			for (int i=0;i<nplayer+1;i++) {
				players[i].game(scn, deck, i);
			}
			scn.close();
		}
		System.out.println("--- Game Results ---");
		players[nplayer].display(1);
		for (int i=0;i<nplayer;i++) {
			if (players[i].calc()>21) {
				System.out.print("[Lose] ");
			} else {
				if (players[nplayer].calc()>21) {
					System.out.print("[Win] ");
				} else {
					if (players[nplayer].calc()>players[i].calc()) {
						System.out.print("[Lose] ");
					} else if (players[nplayer].calc()<players[i].calc()) {
						System.out.print("[Win] ");
					} else {
						System.out.print("[Draw] ");
					}
				}
			}
			players[i].display(i);
		}
	}
}
//enum Value {
//	vA(1),v2(2),v3(3),v4(4),v5(5),v6(6),v7(7),v8(8),v9(9),
//	v10(10),vJ(10),vQ(10),vK(10);
//	private final int value;
//	Value(int value) {
//		this.value = value;
//	}
//}
//enum Suit {
//	ss(0),sh(1),sd(2),sc(3);
//	private final int suit;
//	Suit(int suit) {
//		this.suit = suit;
//	}
//}
class Card {
	public int value, suit;
	public String disp;
	public Card(){}
	public Card(int theValue, int theSuit) {
		this.value = switch(theValue) {
			case 1 -> 11; 
			case 2,3,4,5,6,7,8,9,10 -> theValue;
			case 11,12,13 -> 10;
			default -> 0;
		};
		this.suit = theSuit;
		this.disp = switch(theValue) {
			case 1 -> "A"; 
			case 2,3,4,5,6,7,8,9,10 -> theValue+"";
			case 11 -> "J";
			case 12 -> "Q";
			case 13 -> "K";
			default -> "";
		} + switch(theSuit) {
			case 0 -> "c";
			case 1 -> "h";
			case 2 -> "d";
			case 3 -> "s";
			default -> "";
		};
	}
	//0:c 1:h 2:d 3:s
}
class Deck {
//	private Card[] deck; <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<USE THIS AFTER
	public Card[] deck;
	private int cardsUsed;
	
	public void shuffle(int seed) {
		Random random = new Random(seed);
		for (int i=deck.length-1;i>0;i--) {
			int rand = (int)(random.nextInt(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed=0;
	}
	public Card dealCard() {
		if (cardsUsed == deck.length) {
			throw new IllegalStateException("No cards are left in the deck.");
		}
		cardsUsed++;
		return deck[cardsUsed - 1];
	}
	public Deck () {
		this.deck = new Card[52];
		for (int v=1;v<=13;v++) {
			for (int s=0;s<4;s++) {
				this.deck[(v-1)*4+s] = new Card(v,s);
			}
		}
	}
}
class Hand {
	public boolean canHit = true;
	public Card[] hand = new Card[21];
	public int A = 0;
	public int count = 0;
	Hand() {}
	public void hit(Deck d) {
		Card c = d.dealCard();
		this.hand[this.count] = c;
		this.count++;
		if (c.value==11) {
			A++;
		}
	}
	public void stand() { // or bust
		this.canHit = false;
	}
	public int calc() {
		int sum=0;
		int calA=A;
		for (int i=0;i<this.count;i++) {
			sum+=hand[i].value;
		}
		for (;calA>0&&sum>21;calA--) {
			sum-=10;
		}
		if (sum > 21) {
			this.stand();
		}
		return sum;
	}
	public void display() {
		String[] disps = new String[this.count];
		String disp = "";
		for (int i=0;i<this.count;i++) {
			disps[i]=hand[i].disp;
		}
		disp = String.join(", ",disps)+" ("+this.calc()+")"+(this.calc()<=21?"":" - Bust!");
		System.out.println(disp);
	}
	public void display(int i) {}
	public void game(Scanner scn, Deck deck, int i) {}
}
class Computer extends Hand {
	boolean is_hit;
	@Override
	public void display(int i) {
		System.out.print("Player"+(i+1)+": ");
		super.display();
	}
	@Override
	public void game(Scanner scn, Deck deck, int i) {
		System.out.println("--- Player"+(i+1)+" turn ---");
		this.display(i);
		while (this.canHit) {
			if (this.calc()<14) {
				this.hit(deck);
				System.out.println("Hit");
				this.display(i);
			} else if (this.calc()>17) {
				this.stand();
				System.out.println("Stand");
				this.display(i);
			} else {
				if (Blackjack.random.nextInt(2)==1) {
					this.hit(deck);
					System.out.println("Hit");
					this.display(i);
				} else {
					this.stand();
					System.out.println("Stand");
					this.display(i);
				}
			}
		}
		System.out.println();
	}
}
class Player extends Hand {
	@Override
	public void display(int i) {
		System.out.print("Player"+(i+1)+": ");
		super.display();
	}
	@Override
	public void game(Scanner scn, Deck deck, int i) {
		String input;
		System.out.println("--- Player"+(i+1)+" turn ---");
		this.display(i);
		while (this.canHit) {
			input = scn.nextLine();
			if (input.equals("Hit")) {
				this.hit(deck);
				this.display(i);
			} else if (input.equals("Stand")) {
				this.stand();
				this.display(i);
			} else {
				System.out.println("Please input 'Hit' or 'Stand'.");
				continue;
			}
		}
		System.out.println();
	}
}
class House extends Hand {
	@Override
	public void display(int k) {
		System.out.print("House: ");
		String[] disps = new String[this.count];
		String disp = "";
		for (int i=0;i<this.count;i++) {
			disps[i]=hand[i].disp;
		}
		disps[0] = (k==0?"HIDDEN":disps[0]);
		disp = String.join(", ",disps);
		System.out.println(disp+(k==0?"":" ("+this.calc()+")")+(this.calc()<=21?"":" - Bust!"));
	}
	@Override
	public void game(Scanner scn, Deck deck, int i) {
		System.out.println("--- House turn ---");
		this.display(i);
		while (this.canHit) {
			if (this.calc()<17) {
				this.hit(deck);
				System.out.println("Hit");
				this.display(i);
			} else {
				this.stand();
				System.out.println("Stand");
				this.display(i);
			}
		}
		System.out.println();
	}
}

