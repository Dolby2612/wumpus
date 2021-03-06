import java.awt.Point;
import java.util.ArrayList;

public class AI extends Player
{
	public Point previousPosition;
	public ArrayList<Point> visited;
	
	public AI(int x, int y)
	{
		super(x, y);
		
		visited = new ArrayList<Point>();
	}

	public AI(Point position)
	{
		super(position);
		
		visited = new ArrayList<Point>();
	}
	
	@Override
	public void move(Point direction)
	{
		previousPosition = new Point(position.x, position.y);
		
		super.move(direction);
		
		visited.add(new Point(position.x, position.y));
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
		Point tempPosition = board.addPoints(position, tempDirection);
		
		while(tempPosition.equals(previousPosition) || visited.contains(tempPosition))
		{
			tempDirection = board.getRandomDirection();
			tempPosition = board.addPoints(position, tempDirection);
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
