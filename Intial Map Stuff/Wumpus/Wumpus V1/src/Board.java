import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class Board
{
	public int size;
	public int totalDangers;
	public ArrayList<ArrayList<Space>> spaces;
	public ArrayList<ArrayList<GameObject>> gameObjects;
	public HashMap<Point, GameObject> hashMap;
	
	public Point playerPosition;
	public Point wumpusPosition;
	public Point treasurePosition;
	public Point exitPosition;
	
	public Board(int size, int totalDangers)
	{
		this.size = size;
		this.totalDangers = totalDangers;
		
		spaces = new ArrayList<ArrayList<Space>>();
		gameObjects = new ArrayList<ArrayList<GameObject>>();
		hashMap = new HashMap<Point, GameObject>();
		
		//Randomly assign number of Pits and Superbats
		int currentPits = 0;
		int totalPits = (int) (Math.random() * totalDangers + 1);
		int currentBats = 0;
		int totalBats = totalDangers - totalPits;
		
		int row, col;
		//Create empty map
		for(row = 0; row < size; row ++)
		{
			spaces.add(new ArrayList<Space>());
			gameObjects.add(new ArrayList<GameObject>());
			
			for(col = 0; col < size; col ++)
			{
				//Fill map with Space and Empty objects
				spaces.get(row).add(new Space(col, row));
				gameObjects.get(row).add(new Empty(col, row));
			}
		}
		//Randomly fill map with Superbats and Pits
		Point temp;
		for(int i = 0; i < totalDangers; i ++)
		{
			temp = getRandomEmptyPoint();
			
			//Check if enough Pits have already been placed
			if(currentPits < totalPits)
			{
				currentPits ++;
				gameObjects.get(temp.y).set(temp.x, new Pit(temp));
			}
			//Check if enough Superbats have already been placed
			else if(currentBats < totalBats)
			{
				currentBats ++;
				gameObjects.get(temp.y).set(temp.x, new Superbat(temp));
			}
		}
		//Link Spaces and GameObjects together and create HashMap
		for(row = 0; row < size; row ++)
		{
			for(col = 0; col < size; col ++)
			{
				//Link each Space with the GameObject that shares its position
				getSpace(col, row).link(getGameObject(col, row));
				//Add each GameObject to the HashMap
				hashMap.put(new Point(col, row), getGameObject(col, row));
			}
		}
	}
	
	public Space getSpace(int x, int y)
	{
		return spaces.get(y).get(x);
	}
	
	public Space getSpace(Point p)
	{
		return getSpace(p.x, p.y);
	}
	
	public GameObject getGameObject(int x, int y)
	{
		return gameObjects.get(y).get(x);
	}
	
	public GameObject getGameObject(Point p)
	{
		return getGameObject(p.x, p.y);
	}
	
	private Point getRandomEmptyPoint(int emptySurroundings)
	{
		int randX = (int) (Math.random() * size);
		int randY = (int) (Math.random() * size);
		Point randPoint = new Point(randX, randY);
		
		//
		
		if(getGameObject(randPoint) instanceof Empty)
		{
			return randPoint;
		}
		else
		{
			return getRandomEmptyPoint(emptySurroundings);
		}
	}
	
	public int getSurrounding(Class<GameObject> c, Point position)
	{
		int tempEmpty = 0;
		
		if(getGameObject(position.x + 1, position.y) instanceof c)
		{
			tempEmpty ++;
		}
		else if(getGameObject(position.x - 1, position.y) instanceof c)
		{
			tempEmpty ++;
		}
		else if(getGameObject(position.x, position.y + 1) instanceof c)
		{
			tempEmpty ++;
		}
		else if(getGameObject(position.x, position.y - 1) instanceof c)
		{
			tempEmpty ++;
		}
		
		return tempEmpty;
	}
}
