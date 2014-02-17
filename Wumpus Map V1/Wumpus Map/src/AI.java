import java.awt.Point;
import java.util.ArrayList;

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
		
		if(triggers[2] > 0)
		{
			shoot(board.getRandomDirection(), 5);
		}
		else if(previousPosition != null && numDangers() > 0)
		{
			moveBack();
		}
		else
		{
			move(getRandomDirectionForwards());
		}
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
	
	private void moveBack()
	{
		move(board.subtractPoints(position, previousPosition));
	}
	
	private int numDangers()
	{
		int[] triggers = board.getPlayerTriggers();
		
		return triggers[0] + triggers[1] + triggers[2];
	}
}
