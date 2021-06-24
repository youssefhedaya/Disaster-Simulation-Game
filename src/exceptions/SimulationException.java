package exceptions;

@SuppressWarnings("serial")
abstract public class SimulationException extends Exception
{
	
public SimulationException()
{
	super();
}
	
 public SimulationException(String message)
{
	super(message);
}
		
}
