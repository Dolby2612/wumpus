
@SuppressWarnings("serial")
//Serial version ID not required here
public class BoardInvalidException extends Exception
{
	public BoardInvalidException(){}

	public BoardInvalidException(String arg0)
	{
		super(arg0);
	}

	public BoardInvalidException(Throwable arg0)
	{
		super(arg0);
	}

	public BoardInvalidException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public BoardInvalidException(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}

}
