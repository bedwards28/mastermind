/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MastermindGame;

/**
 *
 * @author Blake
 */
public class MastermindServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MastermindGame game = (MastermindGame) request.getSession().getAttribute("game");
        
        if(game == null) {
            game = new MastermindGame();
            request.getSession().setAttribute("game", game);
            Logger.getLogger(MastermindServlet.class.getName()).log(Level.INFO, "game added to session");
        }
        
        String msg = null;
        boolean winner = false;
        boolean gameOver = false;
        boolean quitGame = false;
        String url = null;
        
        String peg1 = null;
        String peg2 = null;
        String peg3 = null;
        String peg4 = null;
        
        int turn = game.getTurn();
        
        String checkGuess = request.getParameter("checkGuessButton");
        String newGame = request.getParameter("newGameButton");
        String quit = request.getParameter("quitGameButton");
        String rules = request.getParameter("rules");
        
        if(rules != null) {
            url = "/howtoplay.jsp";
        }
        else {
            url = "/Mastermind.jsp";
            msg = "backToGame";
        }
        
        if (newGame != null) {
            msg = "Game Started";
            game = new MastermindGame();
            request.getSession().setAttribute("game", game);
            Logger.getLogger(MastermindServlet.class.getName()).log(Level.INFO, "game added to session");
        }
        else if (checkGuess != null) {
            msg = "Checking guess";
            
            peg1 = request.getParameter("pegGuess0");
            peg2 = request.getParameter("pegGuess1");
            peg3 = request.getParameter("pegGuess2");
            peg4 = request.getParameter("pegGuess3");
            
            game.makeGuess(peg1, peg2, peg3, peg4);
            game.setHints();
            winner = game.isWinner();
            gameOver = game.isGameOver();
        }
        else if (quit != null) {
            quitGame = game.quitGame();
            gameOver = game.isGameOver();
        }
        
        request.setAttribute("msg", msg);
        request.setAttribute("currentGuessNumber", game.getTurn());
        request.setAttribute("guesses", game.getGuesses());
        request.setAttribute("hints", game.getHints());
        request.setAttribute("numGuesses", game.getNumGuesses());
        request.setAttribute("code", game.getCode());
        request.setAttribute("shield", game.getShield());
        request.setAttribute("winner", winner);
        request.setAttribute("gameOver", gameOver);
        request.setAttribute("quitGame", quitGame);
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
