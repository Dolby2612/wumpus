import java.awt.Point;

public class Space
{
	//access GUI?
	public GameObject occupant;
	
	public Point position;
	
	public Space(int x, int y)
	{
		this.position = new Point(x, y);
	}
	
	public Space(Point position)
	{
		this.position = position;
	}
	
	public void link(GameObject linkedObject)
	{
		this.occupant = linkedObject;
		this.occupant.space = this;
	}
}
