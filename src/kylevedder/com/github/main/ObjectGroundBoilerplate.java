/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.main;


/**
 *
 * @author Kyle
 */
public abstract class ObjectGroundBoilerplate extends ObjectBoilerplate
{
    protected boolean canCollide = false;
    
    public boolean canCollide()
    {
        return canCollide;
    }
}
