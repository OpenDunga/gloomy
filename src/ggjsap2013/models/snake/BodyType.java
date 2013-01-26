package ggjsap2013.models.snake;

public enum BodyType {
    A(true),
    B(true),
    C(true),
    D(true),
    E(true),
    a(false),
    b(false),
    c(false),
    d(false),
    e(false);
    
    
    private boolean isCharacter = false;
 
    
    private BodyType(boolean isCharacter)
	{
    	this.isCharacter = isCharacter;
	}
    
    public boolean isCharacter()
	{
		return isCharacter;
	}
    public boolean isAnimal()
    {
    	return ! isCharacter;
    }
}
