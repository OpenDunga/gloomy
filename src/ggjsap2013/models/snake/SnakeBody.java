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
        		case Alice:
        			this.skill = new Skill(Skill.TYPES.DAMAGE_ZERO);
        			break;
        			
        		case Mermaid:
        			this.skill = new Skill(Skill.TYPES.SLOW);
        			break;
        			
        		case Akazukin:
        			this.skill = new Skill(Skill.TYPES.BREAK);
        			break;
        			
        		case Shirayuki:
        			this.skill = new Skill(Skill.TYPES.POINT_DOUBLE);
        			break;
        			
        		case TinkerBell:
        			this.skill = new Skill(Skill.TYPES.FRIEND_POINT);
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
