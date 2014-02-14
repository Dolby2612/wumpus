import java.awt.Point;

public class Actor extends GameObject
{
	public Board board;
	
	public Actor(int x, int y)
	{
		super(x, y);
	}
	
	public Actor(Point position)
	{
		super(position);
	}
	
	public void move(Point direction)
	{
		position = board.addPoints(position, direction);
	}
}
