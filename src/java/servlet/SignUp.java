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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

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

        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirmEmail");
        String password = request.getParameter("password");
        String username = request.getParameter("username");

        boolean register = true;
        boolean databaseCommited = false;

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(""
                    + "<!DOCTYPE html>\n"
                    + "<head>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Sign up  - Spotify</title>\n"
                    + "    <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"assets/css/styles.css\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <div id=\"logo\" class=\"text-center\">\n"
                    + "        <div class=\"container\" id=\"box-logo\" style=\"padding:5px;\"><img src=\"assets/img/spotify-logo.jpg\" id=\"logo-spotify\"></div>\n"
                    + "    </div>\n"
                    + "    <div id=\"form\" class=\"text-center container\" style=\"padding:0px;\">\n"
                    + "        <div class=\"jumbotron\" id=\"formulario\">\n"
                    + "            <div class=\"container\">\n"
                    + "                <br>\n"
                    + "                <div class=\"line-thru\">\n"
                    + "                    <div id=\"line\" style=\"margin:0px;\"></div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"container\">\n"
                    + "                <h2 class=\"center\">Sign up with your email adress</h2></div>\n"
                    + "\n"
                    + "            <div class=\"container\">\n"
                    + "                <form action=\"SignUp\" method=\"post\" id = \"form1\">\n"
                    + "                    <fieldset>\n"
                    + "");

            register = form(email, register, out, confirmEmail, password, username);

            if (register) {

                try {

                    int result = 0;
                    UsuarioDAO userDAO = new UsuarioDAO();
                    Usuario user = new Usuario(username, password, email);

                    result = userDAO.addUser(user);

                    if (result == 1) {

                        RequestDispatcher rd = request.getRequestDispatcher("AccountServlet");
                        request.setAttribute("username", user.getLogin());
                        rd.forward(request, response);
                        databaseCommited = true;

                    }

                } catch (SQLException ex) {

                    String errorSql = ex.getMessage();

                    if (errorSql.contains("UNIQUE")) {

                        out.println("<span class = \"error-message\"> We're sorry, that username is taken. </span>");

                    }
                    if (errorSql.contains("email_UNIQUE")) {

                        out.println("<span class = \"error-message\"> We're sorry, that email is taken. </span>");

                    }

                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

            out.println("        </fieldset>\n"
                    + "                </form>\n"
                    + "            </div>\n"
                    + "            <div class=\"container\">\n"
                    + "                <button class=\"btn btn-default btn  btn-green\" type=\"submit\" form=\"form1\" value=\"Submit\">\n"
                    + "                    SIGN UP \n"
                    + "                </button>\n"
                    + "            </div>\n"
                    + "            <div class=\"container\">\n"
                    + "            <br>"
                    + "                <a class=\"btn btn-default btn  btn-green\" href=\"./SignIn\">\n"
                    + "                    SIGN IN \n"
                    + "                </a>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "    <script src=\"assets/js/jquery.min.js\"></script>\n"
                    + "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\n"
                    + "</body>\n"
                    + "");

        }

    }

    private boolean form(String email, boolean register, final PrintWriter out, String confirmEmail, String password, String username) {

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        boolean matchFound = false;
        Matcher m;

        if (email == null) {

            register = false;
            out.printf("<input class=\"form-control text-field {\" type=\"text\" name=\"email\" placeholder=\"Email\">\n");
        } else {

            m = p.matcher(email);
            matchFound = m.matches();

            if (!matchFound) {

                register = false;
                out.println("<span class = \"error-message\"> The email address you supplied is invalid.</span>");
            }

            out.printf("<input class=\"form-control text-field {\" type=\"text\" name=\"email\" placeholder=\"Email\" value = %s>\n", email);

        }

        if (confirmEmail == null) {

            register = false;
            out.printf("<input class=\"form-control text-field {\" type=\"text\" name=\"confirmEmail\" placeholder=\"Confirm email\">");

        } else {

            if (confirmEmail.isEmpty()) {
                register = false;
                out.println("<span class = \"error-message\"> The email address you supplied is invalid</span>");
            }
            out.printf("<input class=\"form-control text-field {\" type=\"text\" name=\"confirmEmail\" placeholder=\"Confirm email\" value = %s>", confirmEmail);
        }

        if (password == null) {

            register = false;
            out.printf("<input class=\"form-control text-field \" type=\"password\" name=\"password\" placeholder=\"Password\" >");

        } else {

            if (password.isEmpty()) {

                register = false;
                out.println("<span class = \"error-message\"> Invalid password </span>");

            }
            out.printf("<input class=\"form-control text-field \" type=\"password\" name=\"password\" placeholder=\"Password\" value = %s>", password);

        }

        if (username == null) {

            register = false;
            out.printf("<input class=\"form-control text-field {\" type=\"text\" name=\"username\" placeholder=\"Username\">");

        } else {

            if (username.isEmpty() || username.length() < 6) {

                register = false;
                out.println("<span class = \"error-message \"> Invalid username </span>");
            }

            out.printf("<input class=\"form-control text-field {\" type=\"text\" name=\"username\" placeholder=\"Username\" value = %s>", username);
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
        processRequest(request, response);
    }
}
