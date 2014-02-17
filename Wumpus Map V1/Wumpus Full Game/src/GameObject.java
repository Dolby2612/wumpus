import java.awt.Point;

public class GameObject
{
	public Point position;
	
	public GameObject(int x, int y)
	{
		this.position = new Point(x, y);
	}
	
	public GameObject(Point position)
	{
		this.position = position;
	}
}
