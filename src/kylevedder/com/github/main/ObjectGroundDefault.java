/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;

import kylevedder.com.github.utils.CenteredRectangleOld;
import java.util.logging.Level;
import java.util.logging.Logger;
import kylevedder.com.github.utils.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class ObjectGroundDefault extends ObjectGroundBoilerplate
{

    private float scale = 8;

    private float width = 0;
    private float height = 0;

    public ObjectGroundDefault(int x, int y)
    {
        this.image = null;
        this.canCollide = false;
        try
        {
            this.image = new Image("images/Ground.png");
            width = this.image.getWidth();
            height = this.image.getHeight();
            this.scale = GameEngine.TILE_SIZE / width;
        }
        catch (SlickException ex)
        {
            Logger.getLogger(ObjectGroundDefault.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rect = new CenteredRectangleOld(x * width * scale, y * height * scale, width * scale, height * scale);
    }

    @Override
    void update(long delta)
    {

    }

    @Override
    void render(float renderOffsetX, float renderOffsetY)
    {
        if (Utils.isVisible(rect, image, scale, renderOffsetX, renderOffsetY))
        {
            image.draw(rect.getCornerX() - renderOffsetX, rect.getCornerY() - renderOffsetY, scale);
        }
    }

    @Override
    void renderBB(Graphics g, float renderOffsetX, float renderOffsetY)
    {
        g.setColor(Color.white);        
        g.drawRect(rect.getCornerX() - renderOffsetX, rect.getCornerY() - renderOffsetY, rect.getWidth(), rect.getHeight());
    }
}
