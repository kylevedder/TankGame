/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import kylevedder.com.github.utils.CenteredRectangle;
import kylevedder.com.github.utils.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kyle
 */
public class ObjectGroundBarrier extends ObjectGroundBoilerplate
{

    private float scale = 2;

    private float width = 0;
    private float height = 0;

    public ObjectGroundBarrier(int x, int y)
    {
        this.image = null;
        this.canCollide = true;
        try
        {
            this.image = new Image("images/NewGround.png");
            width = this.image.getWidth();
            height = this.image.getHeight();
            this.scale = GameEngine.TILE_SIZE / width;
        }
        catch (SlickException ex)
        {
            Logger.getLogger(ObjectGroundDefault.class.getName()).log(Level.SEVERE, null, ex);
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
        if(Utils.isVisible(rect, image, scale, renderOffsetX, renderOffsetY))
        image.draw(rect.getCornerX() - renderOffsetX, rect.getCornerY() - renderOffsetY, scale);
    }

    @Override
    void renderBB(Graphics g, float renderOffsetX, float renderOffsetY)
    {
        g.setColor(Color.red);        
//        g.drawString((this.getRectangle().getCornerX() / 32) + "," + (this.getRectangle().getCornerY() / 32), (this.getRectangle().getCornerX()) - renderOffsetX, (this.getRectangle().getCornerY()) - renderOffsetY);
        g.drawRect(rect.getCornerX() - renderOffsetX, rect.getCornerY() - renderOffsetY, rect.getWidth(), rect.getHeight());
    }
    
    
}