import java.awt.Point;

public class Player extends Actor
{
	public Player(int x, int y, int size)
	{
		super(x, y, size);
	}
	
	public Player(Point position, int size)
	{
		super(position, size);
	}
}