import org.apache.commons.lang3.time.StopWatch;

public class Main
{

	public static void main(String[] args)
	{
		System.out.println("Would you like to customize the cave? Y/N");
		String input = EasyIn.getString();
		
		Board board = null;
		
		if(input.equalsIgnoreCase("y"))
		{
			System.out.println("Please enter the size of the cave, the desired number of dangers\nand how much empty space should be around each danger, seperated by spaces");
			input = EasyIn.getString();
			String[] params = input.split(" ");
			
			try
			{
				board = new Board(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
			}
			catch (NumberFormatException e)
			{
				System.out.println("Input invalid");
			}
			catch (BoardInvalidException e)
			{
				System.out.println("Board could not be constructed, try lowering the amount of dangers or the number of free adjacent spaces.");
			}
		}
		else
		{
			try
			{
				board = new Board(10, 10, 4);
			}
			catch (BoardInvalidException e)
			{
				System.out.println("Board could not be constructed, try lowering the amount of dangers or the number of free adjacent spaces.");
			}
		}
		
		if(board != null)
		{
			chooseGame(board);
		}
	}
	
	private static void chooseGame(Board board)
	{
		System.out.println("Please enter \"human\" to play the game yourself");
		System.out.println("Alternatively enter \"ai\" to watch an AI player");
		
		String chooseGame = EasyIn.getString();
		
		if(chooseGame.equalsIgnoreCase("human"))
		{
			playHumanGame(board);
		}
		else if(chooseGame.equalsIgnoreCase("ai"))
		{
			AI ai = new AI(board.player.position);
			playAIGame(ai, board, 500);
		}
		else
		{
			System.out.println("Input invalid, please re-enter");
			chooseGame(board);
		}
	}
	
	private static void playAIGame(AI ai, Board board, int visualDelayInMS)
	{
		board.player = ai;
		board.link();
		
		int turn = 0;
		String aiStatus = Board.PLAYING;
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		do
		{
			if(stopwatch.getTime() >= turn * visualDelayInMS)
			{
				turn ++;
				ai.act();
				aiStatus = board.checkPlayerStatus();
			}
		}
		while(aiStatus.equals(Board.PLAYING));
	}
	
	private static void playHumanGame(Board board)
	{
		System.out.println("To move, enter the direction of desired travel: n, s , e, w");
		System.out.println("To shoot, enter \"shoot\" followed by a direction, as for movement");
		System.out.println("To exit the game, type \"exit\"");
		
		board.link();
		
		String playerStatus = Board.PLAYING, input;
		
		do
		{
			System.out.println(board.player.getSenses(board.getPlayerTriggers()));
			
			input = EasyIn.getString();
			String[] inputSplit = input.split(" ");
			
			if(inputSplit[0].equalsIgnoreCase("shoot"))
			{
				if(inputSplit[1].equalsIgnoreCase("n"))
				{
					board.player.shoot(Board.NORTH, 5);
				}
				else if(inputSplit[1].equalsIgnoreCase("s"))
				{
					board.player.shoot(Board.SOUTH, 5);
				}
				else if(inputSplit[1].equalsIgnoreCase("e"))
				{
					board.player.shoot(Board.EAST, 5);
				}
				else if(inputSplit[1].equalsIgnoreCase("w"))
				{
					board.player.shoot(Board.WEST, 5);
				}
			}
			else if(inputSplit[0].equalsIgnoreCase("n"))
			{
				board.player.move(Board.NORTH);
			}
			else if(inputSplit[0].equalsIgnoreCase("s"))
			{
				board.player.move(Board.SOUTH);
			}
			else if(inputSplit[0].equalsIgnoreCase("e"))
			{
				board.player.move(Board.EAST);
			}
			else if(inputSplit[0].equalsIgnoreCase("w"))
			{
				board.player.move(Board.WEST);
			}
			else if(inputSplit[0].equalsIgnoreCase("exit"))
			{
				System.exit(0);
			}
			else
			{
				System.out.println("Input invalid");
			}
			
			playerStatus = board.checkPlayerStatus();
		}
		while(playerStatus.equals(Board.PLAYING));
	}
}
