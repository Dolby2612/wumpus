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
	
	/*-----------------------------------------------------*/
	//Constructor
	
	public Board(int totalDangers, int maxAdjacentDangers, int size)
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
			//Get a random point with at least the specified number of empty adjacent spaces
			temp = getRandomEmptyPoint(4 - Math.max(0, maxAdjacentDangers));
			
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
		
		//Add Player, Wumpus, Treasure, and CaveExit to map
		//All have at least one empty space adjacent to them to allow movement
		playerPosition = getRandomEmptyPoint(1);
		gameObjects.get(playerPosition.y).set(playerPosition.x, new Player(playerPosition, size));
		
		wumpusPosition = getRandomEmptyPoint(1);
		gameObjects.get(wumpusPosition.y).set(wumpusPosition.x, new Wumpus(wumpusPosition, size));
		
		treasurePosition = getRandomEmptyPoint(1);
		gameObjects.get(treasurePosition.y).set(treasurePosition.x, new Treasure(treasurePosition));
		
		exitPosition = getRandomEmptyPoint(1);
		gameObjects.get(exitPosition.y).set(exitPosition.x, new CaveExit(exitPosition));
		
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
	
	
	/*-----------------------------------------------------*/
	//Map access methods
	
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
	
	public GameObject getGameObjectFromHash(Point p)
	{
		return hashMap.get(p);
	}
	
	public GameObject getGameObjectFromHash(int x, int y)
	{
		return getGameObjectFromHash(new Point(x, y));
	}
	
	
	/*-----------------------------------------------------*/
	//Utility methods
	
	public boolean isOccupant(Class<?> desiredOccupant, Point position)
	{
		return desiredOccupant.isInstance(getGameObject(position));
	}
	
	public int getSurrounding(Class<?> adjacentObject, Point position)
	{
		int numSurrounding = 0;
		
		Point[] directions = {Actor.NORTH, Actor.SOUTH, Actor.EAST, Actor.WEST};
		int tempX, tempY;
		for(int i = 0; i < directions.length; i ++)
		{
			//Wrap surrounding points around the edges of the map, i.e position 10 = 0, 11 = 1, etc.
			tempX = (position.x + size + directions[i].x) % size;
			tempY = (position.y + size + directions[i].y) % size;
			
			//Check that the current adjacent GameObject is a member of class adjacentObject
			//and increment numSurrounding if so
			if(isOccupant(adjacentObject, new Point(tempX, tempY)))
			{
				numSurrounding ++;
			}
		}
		
		return numSurrounding;
	}
	
	private Point getRandomEmptyPoint(int emptySurroundings)
	{
		//Get a random point on the map
		int randX = (int) (Math.random() * size);
		int randY = (int) (Math.random() * size);
		Point randPoint = new Point(randX, randY);
		
		//If the point is Empty and surrounded by no less than
		//emptySurroundings adjacent Empty spaces then return that point
		if(isOccupant(Empty.class, randPoint) && getSurrounding(Empty.class, randPoint) >= emptySurroundings)
		{
			return randPoint;
		}
		//If not, call the method again
		else
		{
			return getRandomEmptyPoint(emptySurroundings);
		}
	}
}