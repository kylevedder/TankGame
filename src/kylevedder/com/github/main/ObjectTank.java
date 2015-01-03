/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;

import kylevedder.com.github.utils.Utils;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

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
     *
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

    public void update(long delta, Input input)
    {
        updateDrive(input);

        forward.update(delta);
        backward.update(delta);

        this.rect.setCenterX(this.rect.getCenterX() + driveForwardX(speed, angle));
        this.rect.setCenterY(this.rect.getCenterY() + driveForwardY(speed, angle));
    }

    @Override
    void render(float renderOffsetX, float renderOffsetY)
    {
        Image image = getProperImage();
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

    Image prevImg = null;
    int currentFrameCounter = 0;

    private Image getProperImage()
    {

        if (this.speed < 0)
        {

            currentFrameCounter = Utils.wrap(currentFrameCounter - 1, 0, forward.getFrameCount() - 1);            
        }
        else if (this.speed > 0)
        {
            currentFrameCounter = currentFrameCounter = Utils.wrap(currentFrameCounter + 1, 0, forward.getFrameCount() - 1);            
        }
        System.out.println("current frame: " + currentFrameCounter);
        return forward.getImage(currentFrameCounter);
    }

    Image prevAnim = null;

    private Image getProperImage2()
    {
        if (this.speed < 0)
        {

            return (prevAnim = forward.getImage(forward.getFrameCount() - forward.getFrame() - 1));
        }
        else if (this.speed > 0)
        {
            return (prevAnim = forward.getImage(forward.getFrame()));

        }
        else
        {
            return (prevAnim = (prevAnim == null) ? forward.getImage(0) : prevAnim);
        }
    }

    /**
     * Takes input from the user and
     *
     * @param gc
     */
    private void updateDrive(Input input)
    {
        float tankAngleAppend = 0;
        float tankSpeed = 0;
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
        }

        this.speed = tankSpeed;
        this.angle = wrapAngle(angle, tankAngleAppend);
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