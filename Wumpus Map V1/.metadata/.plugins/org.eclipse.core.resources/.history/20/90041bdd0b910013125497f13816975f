import java.awt.Point;

public class Actor extends GameObject
{
	public Actor(int x, int y)
	{
		super(x, y);
	}
	
	public Actor(Point position)
	{
		super(position);
	}
	
	public void move(Direction d)
	{
		switch(d)
		{
			case NORTH:
				this.position = new Point(this.position.x, this.position.y - 1);
				break;
			case SOUTH:
				this.position = new Point(this.position.x, this.position.y + 1);
				break;
			case EAST:
				this.position = new Point(this.position.x + 1, this.position.y);
				break;
			case WEST:
				this.position = new Point(this.position.x - 1, this.position.y);
				break;
		}
	}
}
