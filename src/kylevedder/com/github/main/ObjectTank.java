/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;

import kylevedder.com.github.utils.CenteredRectangle;
import java.util.Random;
import kylevedder.com.github.animation.CustomAnimation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author Kyle
 */
public class ObjectTank extends ObjectEntityBoilerplate
{

    private float speed = 0f;
    private float angle = 0f;

    private CustomAnimation driveAnimation = null;

    private final float SCALE = 2;
    private final int TILE_WIDTH = 25;
    private final int TILE_HEIGHT = 30;
    private final int ANIMATION_DURATION = 100;
    private final float TURN_RATE = 2f;
    private final float DRIVE_SPEED = 1.5f;
    private final float DRIVE_SPEED_MULTIPLIER = 2f;

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
            SpriteSheet sheetForward = new SpriteSheet(sheet.getSubImage(0, 0, TILE_WIDTH * 10, TILE_HEIGHT).getFlippedCopy(true, false), TILE_WIDTH, TILE_HEIGHT);
            driveAnimation = new CustomAnimation(sheetForward, ANIMATION_DURATION);
        }
        catch (SlickException ex)
        {
            System.err.println("Tank Sprite not found!");
        }

    }

    @Override
    void update(long delta)
    {
        this.update(delta, null);
    }

    public void update(long delta, Input input)
    {
        updateDrive(input);
        if (speed == 0)
        {
            driveAnimation.freeze(delta);
        }
        else
        {
            driveAnimation.update(delta);
        }
        float oldX = this.rect.getCenterX();
        float oldY = this.rect.getCenterY();
        float oldAngle = this.rect.getAngle();
        this.rect.setCenterX(this.rect.getCenterX() + driveForwardX(speed, angle));
        this.rect.setCenterY(this.rect.getCenterY() + driveForwardY(speed, angle));
        this.rect.setAngle(angle);
//        System.out.println("Colliding: " + MainApp.gameEngine.physics.isColliding(newRect));
        if (MainApp.gameEngine.physics.isColliding(this.rect))
        {
            this.rect.setCenterX(oldX);
            this.rect.setCenterY(oldY);
            this.rect.setAngle(angle);
        }
    }

    @Override
    void render(float renderOffsetX, float renderOffsetY)
    {
        //gets the correct frame, reverses playback accordingly
        Image image = driveAnimation.getFrame(this.speed < 0);
        image.setCenterOfRotation((((float) image.getWidth()) * SCALE / 2), (((float) image.getHeight()) * SCALE / 2));
        image.setRotation(angle);
        image.draw(this.rect.getCenterX() - renderOffsetX - (image.getWidth() * SCALE / 2), this.rect.getCenterY() - renderOffsetY - (image.getHeight() * SCALE / 2), SCALE);
    }

    public void renderBoundingBox(Graphics g, float renderOffsetX, float renderOffsetY)
    {
        g.setColor(Color.red);
        //draw centering "X"
        g.drawLine(0, 0, MainApp.SCREEN_WIDTH, MainApp.SCREEN_HEIGHT);
        g.drawLine(MainApp.SCREEN_WIDTH, 0, 0, MainApp.SCREEN_HEIGHT);
        //draw center circle        
        g.drawOval(this.rect.getCenterX() - renderOffsetX - 16, this.rect.getCenterY() - renderOffsetY - 16, 32, 32);

        g.setColor(Color.green);
        //draw shape
        Shape s = this.rect.getPolygon();
        g.draw(s.transform(Transform.createTranslateTransform(-renderOffsetX, -renderOffsetY)));
    }
    
    @Override
    void renderBB(Graphics g, float renderOffsetX, float renderOffsetY)
    {
        renderBoundingBox(g, renderOffsetX, renderOffsetY);
    }

    /**
     * Takes input from the user and
     *
     * @param gc
     */
    private void updateDrive(Input input)
    {
        if (input != null)
        {
            float tankAngleAppend = 0;
            float tankSpeed = 0;
            int duration = ANIMATION_DURATION;
            //drive forward
            if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
            {
                tankSpeed += this.getDriveSpeed();
            }
            //drive forward
            if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
            {
                tankSpeed -= this.getDriveSpeed();
            }
            //turn left
            if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
            {
                tankAngleAppend -= this.getTurnRate();
            }
            //turn right
            if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
            {
                tankAngleAppend += this.getTurnRate();
            }

            //speed multiplier
            if (input.isKeyDown(Input.KEY_LSHIFT))
            {
                tankSpeed *= this.getDriveSpeedMultiplier();
                duration = (int) ((float) ANIMATION_DURATION / (this.getDriveSpeedMultiplier()));
            }

            this.driveAnimation.setDuration(duration);
            this.speed = tankSpeed;
            this.angle = wrapAngle(angle, tankAngleAppend);
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
