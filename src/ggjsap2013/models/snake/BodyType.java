package ggjsap2013.models.snake;


public enum BodyType {
    Alice("アリス", true),
    Mermaid("人魚姫", true),
    Akazukin("赤ずきん", true),
    Shirayuki("白雪姫", true),
    TinkerBell("ティンカーベル", true),
    ahiru("アヒル", false),
    kuma("クマ", false),
    lion("ライオン", false),
    panda("パンダ", false),
    usagi("うさぎ", false);
    
    
    private boolean isCharacter = false;
    private String name;
    
    private BodyType(String name, boolean isCharacter)
	{
    	this.isCharacter = isCharacter;
    	this.name = name;
	}
    
    public boolean isCharacter()
	{
		return isCharacter;
	}
    public boolean isAnimal()
    {
    	return ! isCharacter;
    }
    
    public String getName() {
        return name;
    }
}
