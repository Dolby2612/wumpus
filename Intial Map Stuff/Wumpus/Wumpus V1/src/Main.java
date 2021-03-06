
public class Main
{
	public static void main(String[] args)
	{
		Board b = new Board(10, 20);
		printMap(b);
	}
	
	public static void printMap(Board b)
	{
		GameObject temp;
		System.out.print(" ");
		for(int i = 0; i < b.spaces.size(); i ++)
		{
			for(int j = 0; j < b.spaces.get(i).size(); j ++)
			{
				temp = b.getGameObject(j, i);
				
				if(temp instanceof Empty)
				{
					System.out.print(" ");
				}
				else if(temp instanceof Pit)
				{
					System.out.print("O");
				}
				else if(temp instanceof Superbat)
				{
					System.out.print("B");
				}
				else if(temp instanceof Player)
				{
					System.out.print("P");
				}
				else if(temp instanceof Wumpus)
				{
					System.out.print("W");
				}
				else if(temp instanceof Treasure)
				{
					System.out.print("T");
				}
				else if(temp instanceof CaveExit)
				{
					System.out.print("E");
				}
				System.out.print(" | ");
			}
			System.out.print("\n---------------------------------------\n ");//39 dashes
		}
	}
}
