/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.MastermindGame;

/**
 * Web application lifecycle listener.
 *
 * @author Blake
 */
public class MastermindListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        MastermindGame game = new MastermindGame();
        se.getSession().setAttribute("game", game);
        Logger.getLogger(MastermindListener.class.getName()).log(Level.INFO, "game added to session");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
