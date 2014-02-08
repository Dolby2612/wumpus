import java.awt.Point;

public class Actor extends GameObject
{
	public static final Point NORTH = new Point(0, -1);
	public static final Point SOUTH = new Point(0, 1);
	public static final Point EAST = new Point(1, 0);
	public static final Point WEST = new Point(-1, 0);
	
	public int size;
	
	public Actor(int x, int y, int size)
	{
		super(x, y);
		this.size = size;
	}
	
	public Actor(Point position, int size)
	{
		super(position);
		this.size = size;
	}
	
	public void move(Point direction)
	{
		this.position.x = (this.position.x + size + direction.x) % size;
		this.position.y = (this.position.y + size + direction.y) % size;
	}
}
