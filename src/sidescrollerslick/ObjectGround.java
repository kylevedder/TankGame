/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sidescrollerslick;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class ObjectGround extends ObjectBoilerplate
{

    private float scale = 2;

    private float width = 0;
    private float height = 0;
    private final int TILE_SIZE = 32;

    public ObjectGround(int x, int y)
    {
        this.image = null;
        try
        {
            this.image = new Image("images/NewGround.png");
            width = this.image.getWidth();
            height = this.image.getHeight();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(ObjectGround.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rect = new CenteredRectangle(x * width * scale, y * height * scale, width * scale, height * scale);
    }

    @Override
    void update(long delta)
    {

    }

    @Override
    void render(float renderOffsetX, float renderOffsetY)
    {        
        image.draw(rect.getCornerX() - renderOffsetX, rect.getCornerY() - rect.getHeight() - renderOffsetY, scale);
    }
}
