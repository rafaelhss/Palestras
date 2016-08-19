/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.Conselho;
import bean.HibernateUtil;
import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author rafa
 */
public class faceCallback extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            System.out.println("Opa... oi nois aquiisssi");
            Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
            String oauthCode = request.getParameter("code");
            try {
                facebook.getOAuthAccessToken(oauthCode);
                out.println("seu main: " + facebook.getUser(facebook.getId()).getEmail());
            
                User user = facebook.getUser(facebook.getId(), new Reading().fields("email"));
            
                request.getSession().setAttribute("email", user.getEmail());
                out.println("seu mainuser: " + user.getEmail());
                
                List<Conselho> lista = buscaConselhos(user.getEmail());
                
                request.getSession().setAttribute("lista", lista);
                
                //request.getRequestDispatcher("conselhos.jsp").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/conselhos.jsp");
                
            } catch (FacebookException e) {
                throw new ServletException(e);
            }
            
            
            
            
            
            
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

    private List<Conselho> geraConselhos() {
        
        List<Conselho> retorno = new ArrayList<>();
        
        Conselho c = new Conselho();
        c.setPergunta("Devo parar de comer bacon?");
        c.setResposta("Acho que sim, da cancer");
        retorno.add(c);
        
        Conselho c2 = new Conselho();
        c2.setPergunta("Calça vermelha combina com camisa azul?");
        c2.setResposta("Pendente de resposta");
        retorno.add(c2);
        
        
        
        return retorno;
        
        
    }

    private List<Conselho> buscaConselhos(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();  
          
        Criteria criteria = session.createCriteria(Conselho.class);
        
        criteria.add(Restrictions.eq("email", email));
        
        
        List<Conselho> result = criteria.list();
        
        session.close(); 
        
        return result;
		
    }

}
