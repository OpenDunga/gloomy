package ggjsap2013.models.snake;

public enum BodyType {
    A(true),
    B(true),
    C(true),
    D(true),
    E(true),
    ahiru(false),
    kuma(false),
    lion(false),
    panda(false),
    usagi(false);
    
    
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
