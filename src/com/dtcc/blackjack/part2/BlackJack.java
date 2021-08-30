package com.dtcc.blackjack.part2;

import java.util.Objects;
import java.util.Scanner;

public class BlackJack {

	public BlackJack() {}
	
	public boolean isBlackJack(Card[] cards)
	{
		boolean isBlackJack=false;
		if(getTotalCardsValue(cards)==21) { isBlackJack=true;}
		else {isBlackJack=false;}
		return isBlackJack;	
	}
	
	public int getTotalCardsValue(Card[] cards)
	{
		int total=0;
		for(Card c:cards)
		{
			if(Objects.isNull(c)){}	//to check if card object is null/empty or not.
			else 
			{ 
				if((getRankIntValue(c.rank.toString())==11))
				{
					if(total>10) { total+=1;}
					else{total+=getRankIntValue(c.rank.toString());}	 
				}
				else
				{
					total+=getRankIntValue(c.rank.toString());
				}	
			}
		}
		return total;
	}
	
	public int getRankIntValue(String rank)
	{
		switch(rank)
		{
		 case "TWO":   	return 2; 
         case "THREE":  	return 3; 
         case "FOUR":   	return 4; 
         case "FIVE":   	return 5;
         case "SIX":   	return 6;
         case "SEVEN":   	return 7;
         case "EIGHT":   	return 8;
         case "NINE":   	return 9;
         case "TEN":  	return 10;
         case "JACK":		return 10;
         case "QUEEN": 	return 10;
         case "KING" :	return 10;
         case "ACE"  :	return 11;
         default : return 0;
		
		}
	}
	
	public boolean playAgain(String strPlayAgain)
	{
		return false;
	}
	
	//getRankString
	public void displayCards(Card[] cardArray)
	{
		String separator="";
		Card cardObj=new Card();
		for(Card c: cardArray)
		{
			if(Objects.isNull(c)){}
			else 
			{
				System.out.print(separator+cardObj.getRankString(c.rank)+" of "+c.suit);
				separator=", ";
			}
		}
	}
	
	public Result comparePlayerCardsValues(Card[] cardArray)
	{
		BlackJack bj=new BlackJack();
		Result strResult=null;
		int cardsTotal=0;
		
		cardsTotal=bj.getTotalCardsValue(cardArray);
		if(cardsTotal==21)
		{
			strResult=Result.WIN;
		}
		else if(cardsTotal>21)
		{
			strResult=Result.BUST;
		}
		else if(cardsTotal>=0 && cardsTotal<=20)
		{
			strResult=Result.NEXTMOVE;
		}
		return strResult;
	}
	
	public Result compareDealerCardsValues(Card[] cardDealerArray, Card[] cardPlayerArray)
	{
		BlackJack bj=new BlackJack();
		Result strResult=null;
		int cardsDealerTotal=0;
		int cardsPlayerTotal=0;
		
		cardsDealerTotal=bj.getTotalCardsValue(cardDealerArray);
		cardsPlayerTotal=bj.getTotalCardsValue(cardPlayerArray);

		if(cardsDealerTotal==21 )
		{
			strResult=Result.WIN;
		}
		else if(cardsDealerTotal>21 || cardsPlayerTotal==21)
		{
			strResult=Result.BUST;
		}
		else if(cardsDealerTotal>=0 && cardsDealerTotal<=17)
		{
			strResult=Result.NEXTMOVE;
		}
		else if(cardsDealerTotal>=17 && cardsDealerTotal<21)
		{
			if(cardsPlayerTotal<cardsDealerTotal && cardsPlayerTotal<=21) {strResult=Result.WIN;} 
			else if(cardsPlayerTotal>cardsDealerTotal && cardsPlayerTotal<=21) {strResult=Result.BUST;}
			else if(cardsDealerTotal==cardsPlayerTotal) {strResult=Result.TIE;}
		}
		else if(cardsDealerTotal==cardsPlayerTotal)
		{
			strResult=Result.TIE;
		}
			
		return strResult;
	}

	public static boolean playAgain() 	//play again y or n.
	{	
		Scanner input1=new Scanner(System.in);
		boolean yesOrNo=false;
		boolean properInput=true;
		String strPlayAgain;
		strPlayAgain=input1.next().toLowerCase().trim();
		while(properInput)
		{
			if(strPlayAgain.equalsIgnoreCase("y") || strPlayAgain.equalsIgnoreCase("yes") ) {properInput=false;yesOrNo=true;}
			else if(strPlayAgain.equalsIgnoreCase("n") || strPlayAgain.equalsIgnoreCase("no")) 
			{
				System.out.println("Goodbye...See you soon!!!");
				properInput=false; yesOrNo=false;
			}
			//else { System.out.println("Invalid input. Please run the program again.");yesOrNo=false;}	
		
			else 
			{
				System.out.println("Please enter 'y' or 'n'. Play again? (y/n)");
				strPlayAgain=input1.next().toLowerCase().trim();
				properInput=true;
				
			}	
		}
		
		return yesOrNo;
	}
	
	public boolean invalidMoveException (String strMoveException) {
		Scanner input = new Scanner(System.in);
		boolean isValid = false;
		
// This will check for 	valid input, if false it will print an exception <===============
		strMoveException = strMoveException.toUpperCase();
		switch (strMoveException)
		{
		
		case "DOUBLE": isValid = true; return isValid;	
		case "HIT": isValid = true; return isValid;	
		case "SPLIT": isValid = true; return isValid;	
		case "STAND": isValid = true; return isValid;	
		default: System.out.print("Please enter a valid move."); isValid=false; return isValid;
		}
	}
	
	public boolean checkIfSameRank(Rank rankCard1, Rank rankCard2)
	{
		boolean isSameRank=false;
		if(getRankIntValue(rankCard1.toString())== getRankIntValue(rankCard2.toString())) {isSameRank=true;}
		else {isSameRank=false;}
		return isSameRank;
	}
	
	public void displayOneHandCards(Card[] cardDealerArray,Card[] cardHand1Array)
	{
		BlackJack bj=new BlackJack();
		System.out.print("Dealer hand: ");
		bj.displayCards(cardDealerArray);
		System.out.print("\tYour Hand: " );
		bj.displayCards(cardHand1Array);
	}
	
	public void displayPlayerResult(Result result)
	{
		if(result.equals(Result.WIN))
		{
			System.out.println();
			System.out.println("Dealer Busts! You win! Play again (y/n): ");	
		}
		if(result.equals(Result.BUST))
		{
			System.out.println();
			System.out.println("BUST! Dealer Wins! Play again (y/n): ");
		}
		if(result.equals(Result.TIE))
		{
			System.out.println();
			System.out.println("PUSH! Player and Dealer got same points. Play again(y/n): ");

		}
		
	}
	
	public void displayDealerResult(Result result)
	{
		if(result.equals(Result.WIN))
		{
			System.out.println();
			System.out.println("BUST! Dealer Wins! Play again (y/n): ");	
		}
		if(result.equals(Result.BUST))
		{
			System.out.println();
			System.out.println("Dealer Busts! You win! Play again (y/n):");
		}
		if(result.equals(Result.TIE))
		{
			System.out.println();
			System.out.println("PUSH! Player and Dealer got same points. Play again (y/n): ");

		}
		
	}
	
	public String askForMove(Scanner input)
	{
		String strMove=null;
		System.out.println("Your MOVE (HIT or STAND ) : ");
		strMove=input.next().trim().toUpperCase();
		 return strMove;
	}
}




