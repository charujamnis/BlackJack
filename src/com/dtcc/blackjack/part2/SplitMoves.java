package com.dtcc.blackjack.part2;

import java.util.Scanner;

public class SplitMoves {
	
	public SplitMoves() {}
	
	public Result hitforHands(Card[] dealerCards, Card[] firstHand,String strHand)
	{
		Result hitfirstResult=null;
		Scanner input=new Scanner(System.in);
		BlackJack bj=new BlackJack();
		Result strResult=null;
		String strMove=null;
		
		strResult=bj.comparePlayerCardsValues(firstHand);
			if(strResult.equals(Result.WIN))
			{
				System.out.println();
				System.out.println("Dealer Busts! You win the "+strHand);	
				hitfirstResult=Result.WIN;
			}
			if(strResult.equals(Result.BUST))
			{
				System.out.println();
				System.out.println("BUST!! Dealer wins the "+strHand);
				hitfirstResult=Result.BUST;
			}
			if(strResult.equals(Result.NEXTMOVE))
			{
				//System.out.print("\nYour Move: ");
				//strMove=input.next().trim().toUpperCase();
				hitfirstResult=Result.NEXTMOVE;;
			}
			if(strResult.equals(Result.TIE))
			{
				System.out.println();
				System.out.println("PUSH!! Player and Dealer get same points in "+strHand);
				hitfirstResult=Result.TIE;
			}
			
		return hitfirstResult;
		
	}
	
	public Result compareHandValues(Card[] cardDealerArray, Card[] cardPlayerArray)
	{
		BlackJack bj=new BlackJack();
		Result strResult=null;
		int cardsDealerTotal=0;
		int cardsPlayerTotal=0;
		
		cardsDealerTotal=bj.getTotalCardsValue(cardDealerArray);
		cardsPlayerTotal=bj.getTotalCardsValue(cardPlayerArray);

		if(cardsPlayerTotal > cardsDealerTotal)
		{
			strResult=Result.WIN;
		}
		else if(cardsPlayerTotal<cardsDealerTotal || cardsDealerTotal==21)
		{
			strResult=Result.BUST;
		}
		else if(cardsDealerTotal==cardsPlayerTotal)
		{
			strResult=Result.TIE;
		}
			
		return strResult;
	}
	
	public void printPlayerResult(Result result,String strHand)
	{
		if(result.equals(Result.WIN))
		{
			System.out.println();
			System.out.println("Dealer Busts! You win the "+strHand);	
		}
		if(result.equals(Result.BUST))
		{
			System.out.println();
			System.out.println("BUST!!! Dealer wins the "+strHand);
		}
		if(result.equals(Result.TIE))
		{
			System.out.println();
			System.out.println("PUSH, Player and Dealer got same points in "+strHand);

		}
		
	}
	
	public void displayAllHandsCards(Card[] cardDealerArray, Card[] cardHand1Array,Card[]cardHand2Array )
	{
		BlackJack bj=new BlackJack();
		System.out.print("Dealer hand: ");
		bj.displayCards(cardDealerArray);
		System.out.print("\tYour First Hand: " );
		bj.displayCards(cardHand1Array);
		
		System.out.print("\t Your Second Hand : ");
		bj.displayCards(cardHand2Array);
	}
	
	public String splitInputWhenRanksDifferent(boolean isSameRank,String strMove,Scanner input)
	{
		BlackJack blackjack=new BlackJack();
		String strNewMove=null;
		boolean strResult2 = false;
		strNewMove=strMove;
		while(isSameRank!=true)
		{
			if(strMove.equalsIgnoreCase(Move.SPLIT.toString()) && isSameRank!=true)
			{
					System.out.println("Ranks are not same.Please choose proper move.");
					strMove=blackjack.askForMove(input);
					strResult2=blackjack.invalidMoveException(strMove); 
					while(strResult2==false)
					{
						System.out.println();
						strMove=blackjack.askForMove(input);
						strResult2=blackjack.invalidMoveException(strMove);
					}
				if(strMove.equals(Move.HIT.toString()) || strMove.equals(Move.STAND.toString())|| strMove.equals(Move.DOUBLE.toString())) 
				{strNewMove=strMove; isSameRank=true;}
				else {isSameRank=false;}
			}
			else if(strMove.equals(Move.HIT.toString()) || strMove.equals(Move.STAND.toString())|| strMove.equals(Move.DOUBLE.toString()))
			{
				strNewMove=strMove;
				isSameRank=true;
			}
		}
		return strNewMove;
	}
}
