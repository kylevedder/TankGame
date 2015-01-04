/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.utils;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author Kyle
 */
public class CenteredRectangle
{

//    private Rectangle r = null;
    private Polygon poly = null;
    private Rectangle r = null;
//    private Rectangle r = null;
    private float angle = 0;
    private float width = 0;
    private float height = 0;

    public CenteredRectangle(float posX, float posY, float size)
    {
        angle = 0;
        width = height = size;
        r = new Rectangle(posX - (size / 2), posY - (size / 2), size, size);
        poly = new Polygon(new float[]
        {
            posX - (size / 2), posY - (size / 2),
            posX + (size / 2), posY - (size / 2),
            posX + (size / 2), posY + (size / 2),
            posX - (size / 2), posY + (size / 2)
        });
    }

    public CenteredRectangle(float posX, float posY, float width, float height)
    {
        angle = 0;
        this.width = width;
        this.height = height;
        r = new Rectangle(posX - (width / 2), posY - (height / 2), width, height);
        poly = new Polygon(new float[]
        {
            posX - (width / 2), posY - (height / 2),
            posX + (width / 2), posY - (height / 2),
            posX + (width / 2), posY + (height / 2),
            posX - (width / 2), posY + (height / 2)
        });
    }

    private float getAngledX(float dist, float angle)
    {
        return (float) Math.sin(Math.toRadians(angle)) * dist;
    }

    private float getAngledY(float dist, float angle)
    {
        return -(float) Math.cos(Math.toRadians(angle)) * dist;
    }

    public void rotate(float rotationAmount)
    {
        
        if (rotationAmount != 0 && (float) Math.abs(rotationAmount) != 360f)
        {
            System.out.println("Rotating by " + rotationAmount + " degrees...");
            this.angle = Utils.wrapFloat(this.angle + rotationAmount, 0f, 360f);
            poly = (Polygon) poly.transform(Transform.createRotateTransform((float) Math.toRadians(rotationAmount), this.getCenterX(), this.getCenterY()));
        }
    }

    public void setAngle(float angle)
    {
        rotate((angle - this.angle) % 360f);
    }

    /**
     * Gets the polygon of the rectangle.
     *
     * @return
     */
    public Polygon getPolygon()
    {
        return poly;
    }

    public float getCenterX()
    {
        return poly.getCenterX();
    }

    public float getCenterY()
    {
        return poly.getCenterY();
    }

    public float getCornerX()
    {
        return poly.getX();
    }

    public float getCornerY()
    {
        return poly.getY();
    }

    public void setCenterX(float x)
    {
        this.poly.setCenterX(x);
    }

    public void setCenterY(float y)
    {
        this.poly.setCenterY(y);
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public boolean collides(Shape s)
    {
        return poly.intersects(s);
    }

    public boolean collides(CenteredRectangle c)
    {
        return poly.intersects(c.getPolygon());
    }

}
