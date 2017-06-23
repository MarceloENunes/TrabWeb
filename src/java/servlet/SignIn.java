/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author regis
 */
@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {
    public String uName;
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
        response.setContentType("text/html;charset=UTF-8");

        String password = request.getParameter("password");
        String username = request.getParameter("username");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "\n"
                    + "    <head>\n"
                    + "        <meta charset=\"utf-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <title>spotify</title>\n"
                    + "        <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto\">\n"
                    + "        <link rel=\"stylesheet\" href=\"assets/css/home.css\">\n"
                    + "        <link rel=\"stylesheet\" href=\"assets/css/Navigation-Clean1.css\">\n"
                    + "        <link rel=\"stylesheet\" href=\"assets/css/spotify.css\">\n"
                    + "        <link rel=\"stylesheet\" href=\"assets/css/styles.css\">\n"
                    + "    </head>\n"
                    + "\n"
                    + "    <body>\n"
                    + "        <div class=\"container container-login container-logo\">\n"
                    + "            <div class=\"row\" id=\"logo-row\">\n"
                    + "                <div class=\"col-md-12 text-center\" style=\"padding:0px;\"><img src=\"assets/img/spotify-logo.jpg\"></div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class=\"container container-login container-login-corpo\">\n"
                    + "            <div class=\"row\">\n"
                    + "                <div class=\"col-md-12\">\n"
                    + "                    <br>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"row\">\n"
                    + "                <div class=\"col-md-12\">\n"
                    + "                    <form action=\"SignIn\" method=\"post\" id=\"SignForm\">\n"
                    + "");
            
            boolean login = form(username, password, out);

            if (login) {

                try {

                    UsuarioDAO userDAO = new UsuarioDAO();

                    Usuario user = userDAO.getUser(username);

                    if (user != null) {

                        if (user.getSenha().equals(password)) {

                            RequestDispatcher rd = request.getRequestDispatcher("AccountServlet");
                            request.setAttribute("username", user.getLogin());
                            rd.forward(request, response);

                        } else {

                            out.println("<span class = \"error-message\"> Wrong password!. </span>");

                        }

                    } else {

                        out.println("<span class = \"error-message\"> Username is not in database!. </span>");
                    }

                } catch (SQLException ex) {

                    String errorSql = ex.getMessage();

                    out.println("<span class = \"error-message\">" + errorSql + "</span>");

                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

            out.println("</form>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"row\">\n"
                    + "                <div class=\"col-md-12 col-md-6\" style=\"background-color:rgb(255,255,255);\">\n"
                    + "                </div>\n"
                    + "                <div class=\"col-md-12 col-md-6\" style=\"background-color:rgb(255,255,255);\">\n"
                    + "                    <button class=\"btn btn-default btn-green btn-login\" form = SignForm type=\"submit\">log in</button>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"row text-center spotify-anchor-green\">\n"
                    + "                <div class=\"col-md-12\"><span><a href=\"https://www.spotify.com/br/password-reset/\">Forgot your username or password?</a> </span></div>\n"
                    + "                <div class=\"col-md-12\"><span>Don't have an account? </span><a href=\"/Trabalho/SignUp\">Sign Up</a></div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <script src=\"assets/js/jquery.min.js\"></script>\n"
                    + "        <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\n"
                    + "    </body>\n"
                    + "\n"
                    + "</html>");

        }
    }

    public boolean form(String username, String password, final PrintWriter out) {

        boolean register = true;

        if (username == null) {

            register = false;
            out.println("<input class=\"form-control input-form\" type=\"text\" name=\"username\" placeholder=\"Username\">\n");

        } else {

            if (username.isEmpty() || username.length() < 6) {

                register = false;
                out.println("<span class = \"error-message \"> Invalid username</span>");
            }

            out.printf("<input class=\"form-control input-form\" type=\"text\" name=\"username\" placeholder=\"Username\" value = %s>", username);

        }

        if (password == null) {

            register = false;
            out.println("<input class=\"form-control input-form\" type=\"text\" name=\"password\" placeholder=\"Password\">\n");

        } else {

            if (password.isEmpty()) {

                register = false;
                out.println("<span class = \"error-message\"> Invalid password </span>");

            }
            out.printf("<input class=\"form-control input-form\" type=\"text\" name=\"password\" placeholder=\"Password\" value = %s>", password);

        }

        return register;
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
        String name = request.getParameter("username");

        if (name != null) {
            request.getSession().setAttribute("logado", new Boolean(true));
            request.getSession().setAttribute("username", name);
            response.sendRedirect(request.getContextPath() + "/AccountServlet");
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect(request.getContextPath() + "/SignUp");
        }
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
