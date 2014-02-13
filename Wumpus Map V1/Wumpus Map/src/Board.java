import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Board
{
	public static final Point NORTH = new Point(0, -1);
	public static final Point SOUTH = new Point(0, 1);
	public static final Point EAST = new Point(1, 0);
	public static final Point WEST = new Point(-1, 0);
	public static final Point[] DIRECTIONS = {NORTH, SOUTH, EAST, WEST};
	
	public static final String PLAYING = "playing";
	public static final String WIN = "win";
	public static final String LOSE = "lose";
	
	public int size;
	
	public HashMap<Point, GameObject> gameObjects;
	public ArrayList<Point> emptyPoints;
	
	public Player player;
	public Wumpus wumpus;
	public Treasure treasure;
	public CaveExit caveExit;
	
	public Board(int size, int totalDangers, int minFreeSpaceAroundDangers)
	{
		this.size = size;
		
		gameObjects = new HashMap<Point, GameObject>();
		emptyPoints = new ArrayList<Point>();
		
		Point tempPoint;
		
		int currentPits = 0;
		int totalPits = (int) (Math.random() * totalDangers + 1);
		int currentBats = 0;
		int totalBats = totalDangers - totalPits;
		
		for(int x = 0; x < size; x ++)
		{
			for(int y = 0; y < size; y ++)
			{
				tempPoint = new Point(x, y);
				gameObjects.put(tempPoint, new Empty(tempPoint));
				emptyPoints.add(tempPoint);
			}
		}
		
		try
		{
			while(currentPits < totalPits)
			{
				tempPoint = getRandomEmptyPoint(minFreeSpaceAroundDangers);
				
				gameObjects.remove(tempPoint);
				gameObjects.put(tempPoint, new Pit(tempPoint));
				
				//emptyPoints.remove(tempPoint.x * size + tempPoint.y);
				
				currentPits ++;
			}
			
			while(currentBats < totalBats)
			{
				tempPoint = getRandomEmptyPoint(minFreeSpaceAroundDangers);
				
				gameObjects.remove(tempPoint);
				gameObjects.put(tempPoint, new Superbat(tempPoint));
				
				//emptyPoints.remove(tempPoint.x * size + tempPoint.y);
				
				currentBats ++;
			}
			
			player = new Player(getRandomEmptyPoint(1));
			
			wumpus = new Wumpus(getRandomEmptyPoint(1));
			
			treasure = new Treasure(getRandomEmptyPoint(1));
			gameObjects.remove(treasure.position);
			gameObjects.put(treasure.position, treasure);
			
			caveExit = new CaveExit(getRandomEmptyPoint(1));
			gameObjects.remove(caveExit.position);
			gameObjects.put(caveExit.position, caveExit);
		}
		catch(BoardInvalidException e)
		{
			System.out.print("Board is invalid, terminating program.");
			System.exit(0);
		}
	}
	
	public GameObject getGameObject(Point position)
	{
		return gameObjects.get(position);
	}
	
	public Point addPoints(Point p1, Point p2)
	{
		return new Point(p1.x + p2.x, p1.y + p2.y);
	}
	
	public boolean isOccupant(Class<?> desiredOccupant, Point position)
	{
		return desiredOccupant.isInstance(gameObjects.get(position));
	}
	
	public int getSurrounding(Class<?> C, Point position)
	{
		int count = 0;
		
		for(Point currentPoint : DIRECTIONS)
		{
			if(C.isInstance(gameObjects.get(addPoints(position, currentPoint))))
			{
				count ++;
			}
			else if(wumpus != null && position.equals(wumpus.position))
			{
				count ++;
			}
		}
		
		return count;
	}
	
	public Point getRandomEmptyPoint(int minFreeSpaceAround) throws BoardInvalidException
	{
		ArrayList<GameObject> shuffledGameObjects = new ArrayList<GameObject>(gameObjects.values());
		Collections.shuffle(shuffledGameObjects);
		
		int i = 0;
		GameObject tempRandomObject;
		
		do
		{
			tempRandomObject = shuffledGameObjects.get(i);
			
			if(tempRandomObject instanceof Empty)
			{
				if(getSurrounding(Empty.class, tempRandomObject.position) >= minFreeSpaceAround)
				{
					return tempRandomObject.position;
				}
			}
			
			i ++;
		}
		while(i < shuffledGameObjects.size());
		
		throw new BoardInvalidException("No more legal points on map.");
	}
	
	public int[] getPlayerTriggers()
	{
		int[] triggerArray = new int[5];
		
		triggerArray[0] = getSurrounding(Pit.class, player.position);
		triggerArray[1] = getSurrounding(Superbat.class, player.position);
		triggerArray[2] = getSurrounding(Wumpus.class, player.position);
		triggerArray[3] = getSurrounding(Treasure.class, player.position);
		triggerArray[4] = getSurrounding(CaveExit.class, player.position);
		
		return triggerArray;
	}
	
	public String checkPlayerStatus()
	{
		GameObject standingOn = getGameObject(player.position);
		
		if(standingOn instanceof Pit)
		{
			return LOSE;
		}
		else if(standingOn instanceof Superbat)
		{
			try
			{
				player.position = getRandomEmptyPoint(1);
			}
			catch(BoardInvalidException e)
			{
				//do nothing?
			}
		}
		else if(standingOn instanceof Treasure)
		{
			player.hasTreasure = true;
		}
		else if(standingOn instanceof CaveExit)
		{
			if(player.hasTreasure)
			{
				return WIN;
			}
		}
		else if(player.position.equals(wumpus.position))
		{
			return LOSE;
		}
		
		return PLAYING;
	}
	
	public void link()
	{
		player.board = this;
		wumpus.board = this;
	}
}