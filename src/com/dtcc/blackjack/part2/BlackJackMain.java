package com.dtcc.blackjack.part2;

import java.util.Scanner;

public class BlackJackMain 
{
	public static void main(String[] args) 
	{
		boolean yesOrNO=false;
		do
		{
			Card[] allCards=new Card[52];
			Card[] playerCards=new Card[10];
			Card[] dealerCards=new Card[10];
			Card[] firstHand=new Card[10];
			Card[] secondHand=new Card[10];
			Card[] firstDealerCard=new Card[1];
			
			int playerIndex=2;
			int dealerIndex=2;
			int deckIndex=4;
			int firstHandIndex=1;
			int secondHandIndex=1;
			int standCount=0;
			
						
			String strMove=null;
			Result strResult;
			Result getResult;
			Result hitForFirstHand;
			Result hitForSecondHand;
			
			boolean doubleFlag = false;
			boolean strResult2 = false;
			boolean hitFlag=false;
			boolean dealerLoop=false;
			boolean hitfirsthand=false;
			boolean hitsecondhand=false;
			boolean isSameRank=false;
		
			Card card=new Card();
			Deck deck=new Deck();
			BlackJack blackjack=new BlackJack();
			SplitMoves newMove=new SplitMoves(); 
			Scanner input=new Scanner(System.in);
			allCards=deck.getAllCards();
			
			//add player, dealer cards to their own Card array.
			dealerCards[0]=allCards[0];dealerCards[1]=allCards[1];
			playerCards[0]=allCards[2];playerCards[1]=allCards[3];
			firstDealerCard[0]=allCards[0];
			
			System.out.println("*************** WELCOME TO BLACKJACK **************");
			blackjack.displayOneHandCards(firstDealerCard, playerCards);
			if(blackjack.isBlackJack(playerCards)) 
			{
				if(blackjack.isBlackJack(dealerCards))
				{
					System.out.println();
					System.out.println("PUSH!!! Dealer and you have BLACKJACK");
					System.out.println("You want to play again (y/n): ");
					yesOrNO=blackjack.playAgain();
				}
				else
				{
				System.out.println();
				System.out.println("CONGRATULATIONS !!! YOU WIN THE BLACKJACK");
				System.out.println("You want to play again (y/n): ");
				yesOrNO=blackjack.playAgain();
				}
			}
			else 
			{
				System.out.println();
				strMove=blackjack.askForMove(input);
				strResult2=blackjack.invalidMoveException(strMove); 
				while(!strResult2)
				{
					System.out.println();
					strMove=blackjack.askForMove(input);
					strResult2=blackjack.invalidMoveException(strMove);
				}				
				
				isSameRank= blackjack.checkIfSameRank(playerCards[0].rank,playerCards[1].rank);
				strMove=newMove.splitInputWhenRanksDifferent(isSameRank,strMove,input);
				
				//HIT CODE
				if(strMove.equalsIgnoreCase(Move.HIT.toString()))
				{
					hitFlag=true;
					while(hitFlag==true)
					{
						playerCards[playerIndex++]=allCards[deckIndex++];
						blackjack.displayOneHandCards(firstDealerCard,playerCards);
						strResult=blackjack.comparePlayerCardsValues(playerCards);
						blackjack.displayPlayerResult(strResult);
						if(strResult.equals(Result.WIN) || strResult.equals(Result.BUST) || strResult.equals(Result.TIE)) 
						{hitFlag=false; yesOrNO=BlackJack.playAgain();}
						else if(strResult.equals(Result.NEXTMOVE))
						{
							System.out.println();
							strMove=blackjack.askForMove(input);
							strResult2=blackjack.invalidMoveException(strMove); 
							while(!strResult2)
							{
								System.out.println();
								strMove=blackjack.askForMove(input);
								strResult2=blackjack.invalidMoveException(strMove);
							}	
							if(strMove.equals(Move.HIT.toString())) {hitFlag=true;}
							else if(strMove.equals(Move.STAND.toString())){hitFlag=false;}
							else if(strMove.equals(Move.DOUBLE.toString())) {hitFlag=false;}
							else if (strMove.equals(Move.SPLIT.toString())) {hitFlag=false;}
											
						}																			
					   }						
					}
				
				//STAND CODE
				if(strMove.equalsIgnoreCase(Move.STAND.toString())) 
				{
					dealerLoop=true;
					dealerCards[dealerIndex++]=allCards[deckIndex++];
					blackjack.displayOneHandCards(dealerCards,playerCards);
					
					while(dealerLoop==true)
					{
						strResult=blackjack.compareDealerCardsValues(dealerCards, playerCards);
						blackjack.displayDealerResult(strResult);
						if(strResult.equals(Result.WIN) || strResult.equals(Result.BUST) || strResult.equals(Result.TIE)) 
						{dealerLoop=false; yesOrNO=BlackJack.playAgain();}
						else if(strResult.equals(Result.NEXTMOVE))
						{
							System.out.println();
							dealerCards[dealerIndex++]=allCards[deckIndex++];
							blackjack.displayOneHandCards(dealerCards,playerCards);
							dealerLoop=true;
						}
						else
						{
							System.out.println("Technical issue. >>> Play again(y/n): ");
							yesOrNO=BlackJack.playAgain();
						}
					}
				}
					// Double code
					if(strMove.equalsIgnoreCase(Move.DOUBLE.toString()))
					{
						
						doubleFlag = true;
						playerCards[playerIndex++]=allCards[deckIndex++];
						blackjack.displayOneHandCards(dealerCards,playerCards);
						strResult=blackjack.comparePlayerCardsValues(playerCards);
						blackjack.displayPlayerResult(strResult);
						
						if(strResult.equals(Result.WIN) || strResult.equals(Result.BUST) || strResult.equals(Result.TIE))
						{
							doubleFlag=false; 
							yesOrNO=BlackJack.playAgain();
						}
						else if(strResult.equals(Result.NEXTMOVE)) //no decision here
						{
							while(doubleFlag==true)
							{
								System.out.println();
								dealerCards[dealerIndex++]=allCards[deckIndex++];
								blackjack.displayOneHandCards(dealerCards,playerCards);
								strResult=blackjack.compareDealerCardsValues(dealerCards, playerCards);
								blackjack.displayDealerResult(strResult);
								if( strResult.equals(Result.WIN) || strResult.equals(Result.BUST) || strResult.equals(Result.TIE))
								{
									System.out.println();
									doubleFlag=false;
									yesOrNO=BlackJack.playAgain();
								}
								else if(strResult.equals(Result.NEXTMOVE))
								{
									System.out.println();
									doubleFlag=true;
								}
							}
							
						}
					}	
					
				//SPLIT CODE
				if(strMove.equalsIgnoreCase(Move.SPLIT.toString()) && isSameRank==true)
				{
						firstHand[0]=allCards[2]; secondHand[0]=allCards[3];
						newMove.displayAllHandsCards(firstDealerCard,firstHand,secondHand);
						System.out.println();
						strMove=blackjack.askForMove(input);
						strResult2=blackjack.invalidMoveException(strMove); 
						while(!strResult2)
						{
							System.out.println();
							strMove=blackjack.askForMove(input);
							strResult2=blackjack.invalidMoveException(strMove);
						}
						
						if(strMove.equalsIgnoreCase(Move.DOUBLE.toString()) ||strMove.equalsIgnoreCase(Move.SPLIT.toString()) ) 
						{
							System.out.println("Please select HIT or STAND.");
							strMove=blackjack.askForMove(input);
							strResult2=blackjack.invalidMoveException(strMove); 
							while(!strResult2)
							{
								System.out.println();
								strMove=blackjack.askForMove(input);
								strResult2=blackjack.invalidMoveException(strMove);
							}
						}
						if(strMove.equalsIgnoreCase(Move.HIT.toString()))
						{
							hitfirsthand=true;
							while(hitfirsthand==true)
							{
								firstHand[firstHandIndex++]=allCards[deckIndex++];
								newMove.displayAllHandsCards(firstDealerCard,firstHand,secondHand);
								hitForFirstHand=newMove.hitforHands(dealerCards,firstHand,"first hand");
								
								if(hitForFirstHand.equals(Result.NEXTMOVE)) 
								{	
									System.out.println();
									strMove=blackjack.askForMove(input);
									strResult2=blackjack.invalidMoveException(strMove); 
									while(!strResult2)
									{
										System.out.println();
										strMove=blackjack.askForMove(input);
										strResult2=blackjack.invalidMoveException(strMove);
									}
									
									if(strMove.equals(Move.HIT.toString())) {hitfirsthand=true;}
									else if(strMove.equals(Move.STAND.toString())) {standCount++; hitfirsthand=false;}
									else if(strMove.equals(Move.DOUBLE.toString()) || strMove.equals(Move.SPLIT.toString())) 
									{
										System.out.println("Please select HIT or STAND.");
										strMove=blackjack.askForMove(input);
										strResult2=blackjack.invalidMoveException(strMove); 
										while(!strResult2)
										{
											System.out.println();
											strMove=blackjack.askForMove(input);
											strResult2=blackjack.invalidMoveException(strMove);
										}
										
									}
									else {hitfirsthand=false; System.out.println("Technical Error >>> Do you want to play again (y/n"); yesOrNO=BlackJack.playAgain();}
								}
								 else if(hitForFirstHand.equals(Result.WIN) || hitForFirstHand.equals(Result.BUST) || hitForFirstHand.equals(Result.TIE)) 
								 { 
									strMove=blackjack.askForMove(input);
								 	strResult2=blackjack.invalidMoveException(strMove); 
									while(!strResult2)
									{
										System.out.println();
										strMove=blackjack.askForMove(input);
										strResult2=blackjack.invalidMoveException(strMove);
									}
									
								 	 strMove="STAND";
								 	 standCount++;
								 	 hitfirsthand=false;
								 }
							}
						}
						if(strMove.equalsIgnoreCase(Move.STAND.toString()) && standCount==1)
						{
							
							standCount++;secondHand[0]=allCards[3];
							newMove.displayAllHandsCards(firstDealerCard,firstHand,secondHand);
							System.out.println();
							strMove=blackjack.askForMove(input);
							strResult2=blackjack.invalidMoveException(strMove); 
							while(!strResult2)
							{
								System.out.println();
								strMove=blackjack.askForMove(input);
								strResult2=blackjack.invalidMoveException(strMove);
							}
							
							if(strMove.equalsIgnoreCase(Move.HIT.toString()))
							{
								hitsecondhand=true;
								while(hitsecondhand==true)
								{
									secondHand[secondHandIndex++]=allCards[deckIndex++];
									newMove.displayAllHandsCards(firstDealerCard,firstHand,secondHand);
									hitForSecondHand=newMove.hitforHands(dealerCards,secondHand,"second hand");
									if(hitForSecondHand.equals(Result.NEXTMOVE)) 
									{
										System.out.println();
										strMove=blackjack.askForMove(input);
										strResult2=blackjack.invalidMoveException(strMove); 
										while(!strResult2)
										{
											System.out.println();
											strMove=blackjack.askForMove(input);
											strResult2=blackjack.invalidMoveException(strMove);
										}
										
										if(strMove.equals(Move.HIT.toString())) {hitsecondhand=true;}
										else if(strMove.equals(Move.STAND.toString())) {hitsecondhand=false;}
										else if(strMove.equals(Move.DOUBLE.toString()) || strMove.equals(Move.SPLIT.toString()))
										{
											System.out.println("Please select HIT or STAND.");
											strMove=blackjack.askForMove(input);
											strResult2=blackjack.invalidMoveException(strMove); 
											while(!strResult2)
											{
												System.out.println();
												strMove=blackjack.askForMove(input);
												strResult2=blackjack.invalidMoveException(strMove);
											}
											
											if(strMove.equals(Move.STAND.toString())) {standCount++;hitsecondhand=false;}
											
										}
										else {hitfirsthand=false; System.out.println("Technical Error >>> Do you want to play again (y/n"); yesOrNO=BlackJack.playAgain();}
									}
									else if(hitForSecondHand.equals(Result.WIN) || hitForSecondHand.equals(Result.BUST) ||hitForSecondHand.equals(Result.TIE) ) 
									{
										hitsecondhand=false;
										
										if(blackjack.getTotalCardsValue(firstHand)<=20)
										{
											getResult=newMove.compareHandValues(dealerCards,firstHand);
											newMove.printPlayerResult(getResult,"first hand");
											System.out.println("Do you want to play again.(y/n): ");
											yesOrNO=BlackJack.playAgain();
										}
										else 
										{
											System.out.println("Do you want to play again.(y/n): ");
											yesOrNO=BlackJack.playAgain();
										}
										
									}
								}
							}
						
							if(strMove.equals(Move.STAND.toString()) && standCount>=2)
							{
								standCount++;
								newMove.displayAllHandsCards(dealerCards,firstHand,secondHand);
								getResult=newMove.compareHandValues(dealerCards,secondHand);
								newMove.printPlayerResult(getResult,"second hand");
								
								if(blackjack.getTotalCardsValue(firstHand)<=20)
								{
									getResult=newMove.compareHandValues(dealerCards,firstHand);
									newMove.printPlayerResult(getResult,"first hand");
									System.out.println("Do you want to play again.(y/n): ");
									yesOrNO=BlackJack.playAgain();
								}
								else
								{
									System.out.println("Do you want to play again.(y/n): ");
									yesOrNO=BlackJack.playAgain();
									
								}
							}
					}
				}
			}
		
		}while(yesOrNO==true);
	}
}


