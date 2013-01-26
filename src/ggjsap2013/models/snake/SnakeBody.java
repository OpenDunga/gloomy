package ggjsap2013.models.snake;

import ggjsap2013.models.skill.Skill;

public class SnakeBody {
    
    private final BodyType	type;
    private final Skill		skill;
    
    public SnakeBody(BodyType type)
    {
        this.type = type;
        
        if (type.isCharacter()) {
        	switch (type) {
        		case A:
        			this.skill = new Skill(Skill.TYPES.DAMAGE_ZERO);
        			break;
        			
        		case B:
        			this.skill = new Skill(Skill.TYPES.SLOW);
        			break;
        			
        		case C:
        			this.skill = new Skill(Skill.TYPES.DAMAGE_ZERO);
        			break;
        			
        		case D:
        			this.skill = new Skill(Skill.TYPES.DAMAGE_ZERO);
        			break;
        			
        		case E:
        			this.skill = new Skill(Skill.TYPES.DAMAGE_ZERO);
        			break;
        			
        		default:
        			this.skill = null;
        			break;
        	}

        } else {
        	this.skill = null;
        }
    }

    public BodyType getType() {
        return type;
    }
    
    public Skill getSkill()
	{
		return skill;
	}
    
}
