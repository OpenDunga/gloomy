package ggjsap2013.models.map.item;

import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.BodyType;
import ggjsap2013.models.snake.SnakeBody;
import ggjsap2013.models.snake.SnakeModel;

public class Item implements Block
{
	public static enum TYPES {
		A,
		B,
		C
	}
	public static final TYPES getType(String typeName)
	{
		return TYPES.A;
	}
	
	
	private final TYPES type;
	
	
	public Item(TYPES type)
	{
		this.type = type;
	}
	
	public TYPES getType()
	{
		return type;
	}

    @Override
    public void intersects(SnakeModel snake, MapModel map) 
    {
        switch(type) {
        case A:
            snake.getBodies().addBody(new SnakeBody(BodyType.A));
            break;
        case B:
            snake.getBodies().addBody(new SnakeBody(BodyType.B));
            break;
        case C:
            snake.getBodies().addBody(new SnakeBody(BodyType.C));
            break;
        }
        map.createRandomBlock();
    }
    
    @Override
    public String toString() {
        return type.toString();
    }
}
