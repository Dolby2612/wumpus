import java.awt.Point;

public class Wumpus extends Actor
{
	public boolean alive;
	
	public Wumpus(int x, int y)
	{
		super(x, y);
		alive = true;
	}
	
	public Wumpus(Point position)
	{
		super(position);
		alive = true;
	}
}
