/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.utils;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author Kyle
 */
public class CenteredRectangle
{

    private Rectangle r = null;
//    private Rectangle r = null;
    private float angle = 0; 

    public CenteredRectangle(float posX, float posY, float size)
    {
        r = new Rectangle(posX - (size / 2), posY - (size / 2), size, size);                
    }

    public CenteredRectangle(float posX, float posY, float width, float height)
    {
        r = new Rectangle(posX - (width / 2), posY - (height / 2), width, height);
    }
    
    private float getAngledX(float dist, float angle)
    {
         return (float) Math.sin(Math.toRadians(angle)) * dist;
    }
    
    private float getAngledY(float dist, float angle)
    {
         return -(float) Math.cos(Math.toRadians(angle)) * dist;
    }

    public Rectangle getRectangle()
    {
        return r;
    }

    public float getCenterX()
    {
        return r.getX() + (r.getWidth() / 2);
    }

    public float getCenterY()
    {
        return r.getY() + (r.getHeight() / 2);
    }
    
    public float getCornerX()
    {
        return r.getX();
    }

    public float getCornerY()
    {
        return r.getY();
    }

    public void setCenterX(float x)
    {
        this.r.setX(x - r.getWidth() / 2);
    }

    public void setCenterY(float y)
    {
        this.r.setY(y - r.getHeight() / 2);
    }

    public float getWidth()
    {
        return r.getWidth();
    }

    public float getHeight()
    {
        return r.getHeight();
    }

    public boolean collides(Shape s)
    {
        return r.intersects(s);
    }

    public boolean collides(CenteredRectangle c)
    {
        return r.intersects(c.getRectangle());
    }

}
