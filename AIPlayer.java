
public class AIPlayer extends Player
{
	Direction back; //This is the direction that the AI has just come from
	ArrayList<Space> dangerSpaces;
	boolean adjTreasure; //This is a flag to indicate if the AI is adjacent to the treasure
	boolean adjExit; //This flag indicates whether or not the AI is adjacent to the exit
	boolean goBack; //Indicates whether or not the AI player should move back
	boolean firstMove = true; //Is true for the first move only
	boolean movable = false; //indicates whether or not the AI should move into a given space
	
	public AIPlayer()
	{
		super();
	}

	public void takeTurn()
	{
		if(firstMove)
		{
			move(getRandomDirection());
		}
		else
		{
			if(goBack)
			{
				move(back);
				goBack = false
			}
			else
			{
				while(!movable)
				{
					d = getRandomDirection();
					movable = canMove(d);
				}
				move(d);
				
				switch(countDangers())
				{
					case 0: break;
					case 1: break;
					case 2:
					case 3: goBack = true;
				}
			}
		}
	}
}
