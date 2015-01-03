/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sidescrollerslick;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class ObjectEnemy extends ObjectBoilerplate
{
    final private int ANIM_SPEED = 200;
    private DirectionEnum direction = DirectionEnum.RIGHT;
    private Animation animLeft;
    private Animation animRight;
    private SpriteSheet sheet;

    public ObjectEnemy(float x, float y, float sx, float sy, SpriteSheet sheet)
    {
        this.sheet = sheet;
        SpriteSheet tempSheet = new SpriteSheet(this.sheet.getSubImage(0, 0, (this.sheet.getWidth() / 4), this.sheet.getHeight()), this.sheet.getHeight(), this.sheet.getHeight());
        this.animRight = new Animation(tempSheet, ANIM_SPEED);
        tempSheet = new SpriteSheet(this.sheet.getSubImage((this.sheet.getWidth() / 4), 0, (this.sheet.getWidth() / 4), this.sheet.getHeight()), this.sheet.getHeight(), this.sheet.getHeight());
        this.animLeft = new Animation(tempSheet, ANIM_SPEED);        
        this.rect = new CenteredRectangle(x, y, sx, sy);
    }
    
    @Override
    void update(long delta)
    {
        
    }

    @Override
    void render(float renderOffsetX, float renderOffsetY)
    {        
    }
    
}
