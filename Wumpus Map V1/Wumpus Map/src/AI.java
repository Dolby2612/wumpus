import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class AI extends Player
{
	public Point previousPosition;
	public ArrayList<Point> knownDangers;
	
	public AI(int x, int y)
	{
		super(x, y);
		
		knownDangers = new ArrayList<Point>();
	}

	public AI(Point position)
	{
		super(position);
		
		knownDangers = new ArrayList<Point>();
	}
	
	//Same as normal move method, except it saves the last position,
	//which has to be safe if the ai just moved from there
	@Override
	public void move(Point direction)
	{
		previousPosition = new Point(position.x, position.y);
		
		super.move(direction);
	}
	
	public void act()
	{
		int[] triggers = board.getPlayerTriggers();
		
		/*//Check for cave exit
		if(triggers[4] > 0)
		{
			addToMap(knownGoals, "e");
			
			if(hasTreasure)
			{
				move(getRandomDirectionForwards());
			}
		}
		//Check for treasure
		else if(triggers[3] > 0)
		{
			addToMap(knownGoals, "t");
			
			move(getRandomDirectionForwards());
		}
		//Check for danger
		else if(triggers[2] > 0 || triggers[1] > 0 || triggers[0] > 0)
		{
			addToMap(knownDangers, "d");
			
			if(previousPosition != null)
			{
				move(board.subtractPoints(position, previousPosition));
			}
			else
			{
				move(board.getRandomDirection());
			}
		}
		else
		{
			move(board.getRandomDirection());
		}*/
		
		//i cant do this, grab the free ai code from somewhere
	}
	
	private Point getRandomDirectionForwards()
	{
		Point tempDirection = board.getRandomDirection();
		
		while(board.addPoints(position, tempDirection).equals(previousPosition))
		{
			tempDirection = board.getRandomDirection();
		}
		
		return tempDirection;
	}
	
	private int numDangers()
	{
		int[] triggers = board.getPlayerTriggers();
		
		return triggers[0] + triggers[1] + triggers[2];
	}
}
