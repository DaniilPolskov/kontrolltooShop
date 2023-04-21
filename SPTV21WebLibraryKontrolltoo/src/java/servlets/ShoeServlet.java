/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Buyer;
import entity.Shoe;
import entity.secure.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ShoeFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "ShoeServlet", urlPatterns = {
    "/addShoe",
    "/createShoe",
    "/listShoe",
    "/shoe",
    
})
public class ShoeServlet extends HttpServlet {
    @EJB ShoeFacade shoeFacade;
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав, авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("user");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав, авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        request.setAttribute("authUser", authUser);
        String path = request.getServletPath();
        switch (path) {
            case "/addShoe":
                if(!authUser.getRoles().contains("MANAGER")){
                    request.setAttribute("info", "У вас нет прав, Вы не менеджер!");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/shoes/addShoe.jsp").forward(request, response);
                break;
            case "/createShoe":
                String shoeName = request.getParameter("shoeName");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                if(shoeName.isEmpty() || price.isEmpty() || quantity.isEmpty()){
                    request.setAttribute("shoeName", shoeName);
                    request.setAttribute("price", price);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Заполните все поля.");
                    request.getRequestDispatcher("/addShoe").forward(request, response);
                    break;
                }
                Shoe shoe = new Shoe();
                shoe.setShoeName(shoeName);
                shoe.setPrice(Integer.parseInt(price));
                shoe.setQuantity(Integer.parseInt(quantity));
                shoeFacade.create(shoe);
                request.setAttribute("info", "Книга добавлена успешно");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listShoes":
                request.setAttribute("listShoes", shoeFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/shoes/listShoes.jsp").forward(request, response);
                break;
            case "/shoe":
                String id = request.getParameter("id");
                request.setAttribute("shoe", shoeFacade.find(Long.parseLong(id)));
                request.getRequestDispatcher("/WEB-INF/shoes/shoe.jsp").forward(request, response);
                break;
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
