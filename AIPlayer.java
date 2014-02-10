public class AIPlayer extends Player
{
	Direction back; //This is the direction that the AI has just come from
	ArrayList<Space> dangerSpaces;
	Point destination = null; //this is the point that the AI is trying to get to
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
			if(destination != null)
			{
				//note that destY is your destination Y coordinate
				//and posY is the AI's Y coordinate
				destY = destination.y;
				destx = destination.x;
				boolean canX = false; //is true if a move can be made along the x axis
				boolean canY = false;
				
				if(destX != posX)
				{
					
					if(destX > posX)
					{
						if(canMove(EAST))
						{
							move(EAST);
							canX = true;
						}
					}
					if(destX < posX)
					{
						if(canMove(WEST));
						{
							move(WEST);
							canX = true;
						}
					}
					//if you cannot move along the x axis, attempt to move along the y axis
					if(!canX)
					{
						if(canMove(NORTH))
						{
							move(NORTH);
							canX = true;
						}
						else
						{
							if(canMove(SOUTH))
							{
								move(SOUTH);
								canX = true;
							}
						}
					}
				}
				
				if(destY != posY && !canX)
				{
					if(destY > posY)
					{
						if(canMove(SOUTH)
						{
							move(SOUTH)
							canY = true;
						}
					}
					if(destY < posY)
					{
						if(canMove(NORTH))
						{
							move(NORTH)
							canY = true;
						}
					}
					//if you cannot move along the Y axis, try moving along the X axis
					if(!canY)
					{
						if(canMove(NORTH))
						{
							move(NORTH);
							canY = true;
						}
						if(canMove(SOUTH))
						{
							move(SOUTH);
							canY = true;
						}
					}
				}
			}
			if(adjTreasure)
			{
				d = getRandomDirection();
				move(d);
				//if on the treasure space
				if(!(this.board.treasure.position.equals(position)) && back != null)
				{
					goBack = true;
				}
			}
			
			if(adjExit && hasTreasure)
			{
				d = getRandomDirection();
				move(d);
				//if on the Exit space
				if(!(this.board.exit.position.equals(position)) && back != null)
				{
					goBack = true;
				}
			}
			
			}
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
