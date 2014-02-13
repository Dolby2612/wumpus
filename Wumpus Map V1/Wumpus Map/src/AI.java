import java.awt.Point;

public class AI extends Player
{
	public AI(int x, int y)
	{
		super(x, y);
	}

	public AI(Point position)
	{
		super(position);
	}
	
	public void act()
	{
		int temp = (int) (Math.random() * Board.DIRECTIONS.length);
		move(Board.DIRECTIONS[temp]);
	}
}