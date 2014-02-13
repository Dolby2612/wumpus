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
		position.x = (position.x + board.size + direction.x) % board.size;
		position.y = (position.y + board.size + direction.y) % board.size;
	}
}
