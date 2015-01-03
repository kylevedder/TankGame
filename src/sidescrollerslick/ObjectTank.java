/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sidescrollerslick;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class ObjectTank extends ObjectBoilerplate
{

    private float speed = 0f;
    private float angle = 0f;
    
    
    private Animation forward = null;
    private Animation backward = null;

    private final float SCALE = 2;
    private final int TILE_WIDTH = 25;
    private final int TILE_HEIGHT = 30;
    private final float TURN_RATE = 2f;
    private final float DRIVE_SPEED = 1.5f;
    private final float DRIVE_SPEED_MULTIPLIER = 1.5f;

    private Random r = null;

    public ObjectTank(float posX, float posY, float angle)
    {
        r = new Random(System.currentTimeMillis());
        rect = new CenteredRectangle(posX, posY, TILE_WIDTH * SCALE, TILE_HEIGHT * SCALE);
        this.angle = angle;
        this.speed = 0f;
        SpriteSheet sheet = null;
        try
        {
            sheet = new SpriteSheet(new Image("images/tank.png"), TILE_WIDTH, TILE_HEIGHT);
            SpriteSheet sheetBack = new SpriteSheet(sheet.getSubImage(0, 0, TILE_WIDTH * 10, TILE_HEIGHT), TILE_WIDTH, TILE_HEIGHT);
            SpriteSheet sheetForward = new SpriteSheet(sheet.getSubImage(0, 0, TILE_WIDTH * 10, TILE_HEIGHT).getFlippedCopy(true, false), TILE_WIDTH, TILE_HEIGHT);
            backward = new Animation(sheetBack, 100);
            backward.setAutoUpdate(false);
            forward = new Animation(sheetForward, 100);
            forward.setAutoUpdate(false);
        }
        catch (SlickException ex)
        {
            System.err.println("Tank Sprite not found!");
        }

    }
    

    @Override
    void update(long delta)
    {
        this.update(delta, this.speed, 0);
    }
    
    /**
     * Updates the drive speed and angleAppend
     * @param delta
     * @param speed
     * @param angleAppend 
     */
    public void update(long delta, float speed, float angleAppend)
    {
        this.speed = speed;
        this.angle = wrapAngle(angle, angleAppend);
        forward.update(delta);
        backward.update(delta);        
        this.rect.setCenterX(this.rect.getCenterX() + driveForwardX(speed, angle));
        this.rect.setCenterY(this.rect.getCenterY() + driveForwardY(speed, angle));
    }

    @Override
    void render(float renderOffsetX, float renderOffsetY)
    {
        Image image = getProperAnimation().getImage(forward.getFrame());
        image.setCenterOfRotation((((float) image.getWidth()) * SCALE / 2), (((float) image.getHeight()) * SCALE / 2));
        image.setRotation(angle);
        image.draw(this.rect.getCornerX() - renderOffsetX, this.rect.getCornerY() - renderOffsetY, SCALE);
        //<editor-fold defaultstate="collapsed" desc="Helper Render">
//        try
//        {
//            Image i = new SpriteSheet(new Image("images/sprites.png"), TILE_WIDTH, TILE_HEIGHT).getSubImage(0, 0);
//            i.setCenterOfRotation((((float) image.getWidth()) * SCALE / 2), (((float) image.getHeight()) * SCALE / 2));
//            i.setRotation(angle);
//            i.draw(this.rect.getCornerX() - renderOffsetX, this.rect.getCornerY() - renderOffsetY, SCALE);
//            Image i2 = new SpriteSheet(new Image("images/sprites.png"), TILE_WIDTH, TILE_HEIGHT).getSubImage(1, 0);
//            i2.draw(this.rect.getCenterX() - renderOffsetX, this.rect.getCenterY() - renderOffsetY, SCALE);
//
//        }
//        catch (SlickException ex)
//        {
//            Logger.getLogger(ObjectTank.class.getName()).log(Level.SEVERE, null, ex);
//        }
//</editor-fold>

    }
    
    Animation prevAnim = null;
    private Animation getProperAnimation()
    {
        if(this.speed < 0)
        {
            
            return (prevAnim = backward);
        }
        else if (this.speed > 0)
        {
            return (prevAnim = forward);
                    
        }
        else 
        {
            return (prevAnim = (prevAnim == null)? forward : prevAnim);
        }           
    }

    /**
     * Takes the append angle value and appends it to the angle.
     *
     * Wraps to [0 - 360]
     *
     * @param angle
     * @param appendValue
     * @return
     */
    private float wrapAngle(float angle, float appendValue)
    {
        return ((angle + appendValue) % 360f);
    }

    /**
     * Updates the X based upon dist to travel forward and angle
     *
     * @param dist
     * @param angle
     * @return
     */
    private float driveForwardX(float dist, float angle)
    {
        return (float) Math.sin(Math.toRadians(angle)) * dist;
    }

    /**
     * Updates the Y based upon dist to travel forward and angle
     *
     * @param dist
     * @param angle
     * @return
     */
    private float driveForwardY(float dist, float angle)
    {
        return -(float) Math.cos(Math.toRadians(angle)) * dist;
    }

    public float getPosX()
    {
        return this.rect.getCenterX();
    }

    public float getPosY()
    {
        return this.rect.getCenterY();
    }

    public float getTurnRate()
    {
        return TURN_RATE;
    }
    
    public float getDriveSpeed()
    {
        return DRIVE_SPEED;
    }
    
    public float getDriveSpeedMultiplier()
    {
        return DRIVE_SPEED_MULTIPLIER;
    }
    
    

}
