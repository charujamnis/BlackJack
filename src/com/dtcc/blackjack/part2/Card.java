package com.dtcc.blackjack.part2;

public class Card {
	
	public Rank rank;
	public Suit suit;
	
	public Card(){}
	
	public Card(Rank rank, Suit suit)
	{
		this.rank=rank;
		this.suit=suit;
	}
	
	public Rank rank() {return rank;}
	public Suit suit()	{return suit;}
	
	public String getRankString(Rank rank)
	{
		   switch (rank.toString()) {
           case "TWO":   	return "2"; 
           case "THREE":  	return "3"; 
           case "FOUR":   	return "4"; 
           case "FIVE":   	return "5";
           case "SIX":   	return "6";
           case "SEVEN":   	return "7";
           case "EIGHT":   	return "8";
           case "NINE":   	return "9";
           case "TEN":  	return "10";
           case "JACK":		return "Jack";
           case "QUEEN": 	return "Queen";
           case "KING" :	return"King";
           case "ACE"  :	return "Ace";
           
           default : return"Error";
		   }
	}
	
	public String toString() {return rank+" of "+suit;}
}

