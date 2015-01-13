/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kyle
 */
public class MainApp extends BasicGame
{
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    
    public static final boolean DEBUG = true;
    public static AppGameContainer app;
    public static GameEngine gameEngine;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException
    {       
        app = initApp("Tank Game");
    }

    /**
     * Initialization of the main game container
     * @return New Game Container
     * @throws SlickException 
     */
    private static AppGameContainer initApp(String title) throws SlickException
    {
        app = new AppGameContainer(new MainApp(title));
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.setTargetFrameRate(60);
        app.setAlwaysRender(DEBUG);        
        app.start();        
        return app;
    }
    
    public MainApp(String title)
    {
        super(title);
    }

    //Main hooks for slick
    @Override
    public void init(GameContainer gc) throws SlickException
    {        
        gameEngine = new GameEngine();
        gameEngine.init(gc);
    }

    @Override
    public void update(GameContainer gc, int deltaTime) throws SlickException
    {
        gameEngine.update(gc, deltaTime);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        gameEngine.render(gc, g);        
    }

}
