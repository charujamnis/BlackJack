package com.dtcc.blackjack.part2;

public class Deck {

	public Card[] deck;
	
	public Deck()
	{	
		int index=0;
		deck=new Card[52];
		for(Suit s : Suit.values())
		{
			for(Rank r :Rank.values())
			{
				Card card=new Card();
				card.suit=s;
				card.rank=r;
				deck[index]=card;
				index++;
			}
		}
	}
	
	//shuffle cards.
	public void shuffle()
	{
		for(int i=0;i<deck.length;i++) //shuffle the cards.
		{
			int random=(int)(Math.random()*deck.length);
			Card temp=deck[i];
			deck[i]=deck[random];
			deck[random]=temp;
		}
	}
	
		
	public Card[] getAllCards()
	{
		Card[] allCards=new Card[52];
		shuffle();
		for(int i=0;i<allCards.length;i++)
		{
			allCards[i]=deck[i];
		}
		return allCards;
		
	}
	
	public String toString() 
	{
		for(Card c:deck)
		{
			System.out.println(c.rank+" of "+c.suit);
		}
		return "";
	}
}
