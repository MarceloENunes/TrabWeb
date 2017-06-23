/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ContentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Conteudo;

/**
 *
 * @author regis
 */
@WebServlet(name = "AccountServlet", urlPatterns = {"/AccountServlet"})
public class AccountServlet extends HttpServlet {

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
        HttpSession session = request.getSession();

        if (session.getAttribute("logado") != null) {

            ArrayList<Conteudo> conteudos = new ArrayList();

            try {
                ContentDAO contentDAO = new ContentDAO();
                conteudos = contentDAO.getContent(request.getSession().getAttribute("username").toString());
            } catch (SQLException ex) {
                Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.setContentType("text/html;charset=UTF-8");

            String username = request.getParameter("username");

            // if (username != null) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "\n"
                        + "<head>\n"
                        + "    <meta charset=\"utf-8\">\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    <title>spotify</title>\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/css/home.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/css/Navigation-Clean1.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/css/spotify.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/css/styles.css\">\n"
                        + "</head>\n"
                        + "\n"
                        + "<body id=\"body-spotify\">\n"
                        + "    <header style=\"background-color:#000000;\">\n"
                        + "        <div class=\"container\" id=\"container-navbar\">\n"
                        + "            <nav class=\"navbar navbar-default navigation-clean\" id=\"navbar-spotify\">\n"
                        + "                <div class=\"container-fluid\">\n"
                        + "                    <div class=\"navbar-header\">\n"
                        + "                        <a class=\"navbar-brand navbar-link class-navbar-spofify\" href=\"#\" id=\"brand-spotify\"> <img src=\"assets/img/logo-spotify.png\" class=\"img-responsive\" style=\"height:auto;width:132px;\"></a>\n"
                        + "                        <button class=\"navbar-toggle collapsed class-navbar-spofify\" data-toggle=\"collapse\" data-target=\"#navcol-1\" id=\"toggle-spotify\"><span class=\"sr-only\">Toggle navigation</span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"collapse navbar-collapse\" id=\"navcol-1\">\n"
                        + "                        <ul class=\"nav navbar-nav navbar-right class-navbar-spofify\" id=\"nav-spotify\">\n"
                        + "                            <li role=\"presentation\"><a href=\"#\">Premium </a></li>\n"
                        + "                            <li role=\"presentation\"><a href=\"#\">Help </a></li>\n"
                        + "                            <li role=\"presentation\"><a href=\"#\">Download </a></li>\n"
                        + "                            <li role=\"presentation\"><a href=\"#\">| </a></li>\n"
                        + "                            <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" aria-expanded=\"false\" href=\"#\">Conta <i class=\"glyphicon glyphicon-user\"></i><span class=\"caret\"></span></a>\n"
                        + "                                <ul class=\"dropdown-menu\" role=\"menu\">\n"
                        + "                                    <li role=\"presentation\"><form action=\"./Logout\" method=\"post\">\n"
                        + "                                         <input type=\"submit\" value=\"Logout\" />\n"
                        + "                                         </form>"
                        + "                                     </li>\n"
                        + "                                </ul>\n"
                        + "                            </li>\n"
                        + "                        </ul>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </nav>\n"
                        + "        </div>\n"
                        + "    </header>\n"
                        + "    <div class=\"container\">\n"
                        + "        <div class=\"row\" id=\"main-row\">\n"
                        + "            <div class=\"col-md-12\" id=\"main-column\">\n"
                        + "                <div class=\"jumbotron border-simple\" id=\"content-account\" style=\"margin:0px;\">\n"
                        + "                    <h1 style=\"font-family:Roboto, sans-serif;font-weight:bold;\">3 months for R$ 16,90</h1>\n"
                        + "                    <p>Love unlimited music? Premium’s for you. &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </p>\n"
                        + "                    <p><a class=\"btn btn-default button-pink\" role=\"button\" href=\"#\">get premium</a></p>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div>\n"
                        + "        <div class=\"container container-account\">\n"
                        + "            <div class=\"row\">\n"
                        + "                <div class=\"col-md-8\">\n"
                        + "                    <div class=\"row\">\n"
                        + "                        <div class=\"col-md-12\">\n"
                        + "                            <h1>" + request.getSession().getAttribute("username") + " Account overview</h1></div>\n"
                        + "                        <div class=\"col-md-12\">\n"
                        + "                            <div class=\"col-md-6\" style=\"padding:0px;\">\n"
                        + "                                <div class=\"jumbotron jumbotron-account\">\n"
                        + "                                    <h3>Upload</h3>\n"
                        + "                                    <p><a class=\"btn btn-default btn-green btn-green-account\" role=\"button\" href=\"ContentServlet\">Upload</a></p>\n"
                        + "                                </div>\n"
                        + "                                 <div class=\"col-md-12\">\n"
                        + "                            <h1>Uploads Realizados</h1>");
                                                        conteudos.forEach((c) -> {
                                                            out.println("<li><h2>" + c.getTexto() + "</h2></li>");
                                                        });
                out.println(
                        "                             </div>\n"
                        + "                        <div class=\"col-md-12\">\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div class=\"container-fluid black-container\">\n"
                        + "        <div class=\"row\">\n"
                        + "            <div class=\"col-md-12 col-xs-12 col-md-2\">\n"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-md-12\">\n"
                        + "                        <div class=\"row\">\n"
                        + "                            <div class=\"col-md-12\"><img src=\"assets/img/logo-spotify.png\"></div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class=\"col-md-12 col-xs-6 col-sm-4 col-md-2\">\n"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-md-12\">\n"
                        + "                        <h4>COMPANY </h4></div>\n"
                        + "                </div>\n"
                        + "                <ul class=\"list-unstyled\">\n"
                        + "                    <li><a href=\"https://www.spotify.com/us/about-us/contact/\">About</a> </li>\n"
                        + "                    <li><a href=\"https://www.spotifyjobs.com/\">Jobs</a> </li>\n"
                        + "                    <li><a href=\"https://press.spotify.com/us/\">Press</a> </li>\n"
                        + "                    <li><a href=\"https://news.spotify.com/us/\">News</a> </li>\n"
                        + "                </ul>\n"
                        + "            </div>\n"
                        + "            <div class=\"col-md-12 col-xs-6 col-sm-4 col-md-2\">\n"
                        + "                <h4>COMMUNITIES </h4>\n"
                        + "                <ul class=\"list-unstyled\">\n"
                        + "                    <li><a href=\"https://artists.spotify.com/\">For Artists &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</a> </li>\n"
                        + "                    <li><a href=\"https://developer.spotify.com/\">Developers</a> </li>\n"
                        + "                    <li><a href=\"https://developer.spotify.com/\">Developers</a> </li>\n"
                        + "                    <li><a href=\"https://www.spotify.com/us/brands/\">Brands</a> </li>\n"
                        + "                </ul>\n"
                        + "            </div>\n"
                        + "            <div class=\"col-md-12 col-xs-6 col-sm-4 col-md-2\">\n"
                        + "                <h4>USEFUL LINKS</h4>\n"
                        + "                <ul class=\"list-unstyled\">\n"
                        + "                    <li><a href=\"https://support.spotify.com/?utm_source=www.spotify.com&amp;utm_medium=www_footer\">Help</a> </li>\n"
                        + "                    <li><a href=\"https://www.spotify.com/us/purchase/ecards/\">Gift</a> </li>\n"
                        + "                    <li><a href=\"https://www.spotify.com/us/redirect/webplayerlink/?utm_medium=www_footer\">Web Player</a> </li>\n"
                        + "                </ul>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <script src=\"assets/js/jquery.min.js\"></script>\n"
                        + "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\n"
                        + "</body>\n"
                        + "\n"
                        + "</html>");

            }
        } else {

            response.sendRedirect(request.getContextPath() + "/SignIn");
            // }

        }
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
