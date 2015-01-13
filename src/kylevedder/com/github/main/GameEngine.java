/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class GameEngine
{

    public final int WORLD_WIDTH = 100;
    public final int WORLD_HEIGHT = 100;

    final float PLAYER_START_X = 500f;
    final float PLAYER_START_Y = 500f;
    final float PLAYER_START_ANGLE = 45f;

    public float renderOffsetX = PLAYER_START_X;
    public float renderOffsetY = PLAYER_START_Y;

    private float tankAngleAppend = 0;
    private float tankSpeed = 0;

    ObjectTank tank = null;
    Physics physics = null;
    public ObjectGroundBoilerplate[][] tileArray = null;

    public GameEngine()
    {
        tileArray = new ObjectGroundBoilerplate[WORLD_WIDTH][WORLD_HEIGHT];
    }

    /**
     * Sets up the game engine for use
     *
     * @param gc
     * @throws SlickException
     */
    public void init(GameContainer gc) throws SlickException
    {
        System.out.println("Generating ground");
        genGround();
        System.out.println("Ground gened");
        tank = new ObjectTank(PLAYER_START_X, PLAYER_START_Y, PLAYER_START_ANGLE);
        physics = new Physics();

    }

    /**
     * Called every update cycle for updating movement
     *
     * @param gc
     * @param deltaTime
     * @throws SlickException
     */
    public void update(GameContainer gc, int deltaTime) throws SlickException
    {
        tank.update(deltaTime, gc.getInput());
        renderOffsetX = tank.getPosX() - (MainApp.SCREEN_WIDTH / 2);
        renderOffsetY = tank.getPosY() - (MainApp.SCREEN_HEIGHT / 2);
    }

    /**
     *
     * @param gc
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        //clears
        g.clear();
        //backgrond
        g.setColor(new Color(103, 194, 240));
        g.fillRect(0, 0, MainApp.SCREEN_WIDTH, MainApp.SCREEN_HEIGHT);

        //draws every object
        ObjectBoilerplate object;

        ObjectBoilerplate grnd;
        for (int x = 0; x < WORLD_WIDTH; x++)
        {
            for (int y = 0; y < WORLD_HEIGHT; y++)
            {
                if ((grnd = tileArray[x][y]) != null)
                {
                    grnd.render(renderOffsetX, renderOffsetY);
                }
            }
        }
        tank.render(renderOffsetX, renderOffsetY);
        tank.renderBoundingBox(g, renderOffsetX, renderOffsetY);

    }

    private void genGround()
    {
        for (int x = 0; x < WORLD_WIDTH; x++)
        {
            for (int y = 0; y < WORLD_HEIGHT; y++)
            {
                if (x == 0 || x == WORLD_WIDTH - 1 || y == 0 || y == WORLD_HEIGHT - 1)
                {

                }
                else
                {
                    tileArray[x][y] = new ObjectGroundDefault(x, y);
                }
            }
        }
    }
}
