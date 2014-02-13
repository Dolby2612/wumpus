import java.awt.Point;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Main
{

	public static void main(String[] args)
	{
		Board gameBoard = new Board(10, 20, 4);
		
		AI ai = new AI(gameBoard.player.position);
		gameBoard.player = ai;
		
		gameBoard.link();
		
		String playerStatus;
		
		printMap(gameBoard);
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			@Override
			  public void run(){
				if(playerStatus.equals(Board.PLAYING))
				{
					ai.act();
					playerStatus = gameBoard.checkPlayerStatus();
					printMap(gameBoard);
				}
			  }
		}, new Date(), 1000);
		//THIS IS BROKEN AS FUCK

	}
	
	public static void printMap(Board b)
	{
		clear();
		
		Point tempPoint;
		GameObject tempObject;
		
		for(int y = 0; y < b.size; y ++)
		{
			for(int x = 0; x < b.size; x ++)
			{
				tempPoint = new Point(x, y);
				
				if(tempPoint.equals(b.player.position))
				{
					System.out.print("P");
				}
				else if(tempPoint.equals(b.wumpus.position))
				{
					System.out.print("W");
				}
				else
				{
					tempObject = b.getGameObject(tempPoint);
					
					if(tempObject instanceof Empty)
					{
						System.out.print(" ");
					}
					else if(tempObject instanceof Pit)
					{
						System.out.print("O");
					}
					else if(tempObject instanceof Superbat)
					{
						System.out.print("B");
					}
					else if(tempObject instanceof Treasure)
					{
						if(!b.player.hasTreasure)
						{	
							System.out.print("T");
						}
						else
						{
							System.out.print(" ");
						}
					}
					else if(tempObject instanceof CaveExit)
					{
						System.out.print("E");
					}
				}
				
				System.out.print(" | ");
			}
			
			System.out.print("\n---------------------------------------\n");
		}
	}
	
	public static void clear()
	{
		for(int c = 0; c < 50; c ++)
		{
			System.out.println();
		}
	}

}
