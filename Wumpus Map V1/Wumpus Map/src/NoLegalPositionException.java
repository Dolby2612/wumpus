@SuppressWarnings("serial")
//Serial version ID not required here
public class NoLegalPositionException extends Exception
{
	public NoLegalPositionException(String arg0)
	{
		super(arg0);
	}

	public NoLegalPositionException(Throwable arg0)
	{
		super(arg0);
	}

	public NoLegalPositionException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public NoLegalPositionException(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}

}
