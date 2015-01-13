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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kyle
 */
public class ObjectGroundBarrier extends ObjectBoilerplate
{

    private float scale = 8;

    private float width = 0;
    private float height = 0;

    public ObjectGroundBarrier(int x, int y)
    {
        this.image = null;
        try
        {
            this.image = new Image("images/Ground.png");
            width = this.image.getWidth();
            height = this.image.getHeight();
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
        image.draw(rect.getCornerX() - renderOffsetX, rect.getCornerY() - rect.getHeight() - renderOffsetY, scale);
    }
}