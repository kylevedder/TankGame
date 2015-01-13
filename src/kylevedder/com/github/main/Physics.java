/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;

import java.util.ArrayList;
import kylevedder.com.github.utils.CenteredRectangle;
import kylevedder.com.github.utils.Utils;

/**
 *
 * @author Kyle
 */
public class Physics
{

    ArrayList<ObjectBoilerplate> entities = null;
    private final int CHECK_AREA = 2;

    public Physics()
    {
        entities = new ArrayList<>();
    }

    /**
     * Adds an entity to check for collisions with.
     *
     * @param object
     */
    public void regEntity(ObjectBoilerplate object)
    {
        entities.add(object);
    }

    /**
     * Checks for ground collisions and entity collisions
     *
     * @param c CenteredRectangle of the BB of the checker
     * @return if isColliding
     */
    public boolean isColliding(CenteredRectangle c)
    {
        //check for collide with other entities
        for (ObjectBoilerplate entity : entities)
        {
            if(c.collides(entity.getRectangle()))return true;
        }
        
        //get Xs and Ys to check
        int midX = (int) ((c.getCenterX() - (c.getCenterX() % c.getWidth())) / c.getWidth());
        int midY = (int) ((c.getCenterY() - (c.getCenterY() % c.getHeight())) / c.getHeight());
        int maxX = Utils.clampInt(midX + CHECK_AREA, 0, MainApp.gameEngine.WORLD_WIDTH);
        int minX = Utils.clampInt(midX - CHECK_AREA, 0, MainApp.gameEngine.WORLD_WIDTH);
        int maxY = Utils.clampInt(midY + CHECK_AREA, 0, MainApp.gameEngine.WORLD_WIDTH);
        int minY = Utils.clampInt(midY - CHECK_AREA, 0, MainApp.gameEngine.WORLD_WIDTH);

        //check for each collision
        for (int x = minX; x <= maxX; x++)
        {
            for (int y = minY; y <= maxY; y++)
            {
                if(MainApp.gameEngine.tileArray[x][y].rect.collides(c)) return true;
            }
        }
        return false;
    }

}
