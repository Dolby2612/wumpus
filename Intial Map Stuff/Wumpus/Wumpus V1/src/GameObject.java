import java.awt.Point;

public class GameObject
{
	//access GUI?
	public Space space;
	
	public Point position;
	
	public GameObject(int x, int y)
	{
		this.position = new Point(x, y);
	}
	
	public GameObject(Point position)
	{
		this.position = position;
	}
	
	public void link(Space linkedSpace)
	{
		this.space = linkedSpace;
		this.space.occupant = this;
	}
}
