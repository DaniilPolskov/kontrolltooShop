
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Shoe;
import entity.Buyer;
import entity.History;
import entity.Buyer;
import entity.secure.User;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ShoeFacade;
import session.HistoryFacade;
import session.BuyerFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "HistoryServlet", urlPatterns = {
    "/takeOnShoe",
    "/createHistory",
    "/updateHistory",
    "/listHistories",
    "/showStat",
    "/calcStat",
    
})
public class HistoryServlets extends HttpServlet {
    
    @EJB BuyerFacade buyerFacade;
    @EJB ShoeFacade shoeFacade;
    @EJB HistoryFacade historyFacade;
    
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
            case "/takeOnShoe":
                List<Buyer> listBuyers = buyerFacade.findAll();
                List<Shoe> listShoes = shoeFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.setAttribute("listShoes", listShoes);
                request.getRequestDispatcher("/WEB-INF/history/takeOnShoe.jsp").forward(request, response);
                break;
            case "/createHistory":
                String shoeId = request.getParameter("shoeId");
                String buyerId = request.getParameter("buyerId");
                if(shoeId == null || shoeId.isEmpty() || buyerId == null || buyerId.isEmpty()){
                    request.setAttribute("info","Книга или читатель не выбраны!");
                    request.getRequestDispatcher("/takeOnShoe").forward(request, response);
                    break;
                }
                Shoe shoe = shoeFacade.find(Long.parseLong(shoeId));
                if(shoe.getQuantity()>0){
                    shoe.setQuantity(shoe.getQuantity()-1);
                    shoeFacade.edit(shoe);
                    Buyer buyer = buyerFacade.find(Long.parseLong(buyerId));
                    History history = new History();
                    history.setShoe(shoe);
                    history.setBuyer(buyer);
                    history.setTakeOnShoe(new GregorianCalendar().getTime());
                    historyFacade.create(history);
                    request.setAttribute("info","Книга выдана");
                    
                }else{
                    request.setAttribute("info","Книга не выдана, все экземпляры читаются");
                    
                }
                request.getRequestDispatcher("/takeOnShoe").forward(request, response);
                break;
            case "/returnShoe":
                List<History> listHistoryWithTakedShoes = historyFacade.getHistoriesWithTakedShoes();
                request.setAttribute("listHistoryWithTakedShoes", listHistoryWithTakedShoes);
                request.getRequestDispatcher("/WEB-INF/history/returnShoe.jsp").forward(request, response);
                break;
            case "/updateHistory":
                String historyId = request.getParameter("historyId");
                if(historyId == null || historyId.isEmpty()){
                    request.setAttribute("info","Книга не выбрана");
                    request.getRequestDispatcher("/returnShoe").forward(request, response);
                    break;
                }
                History history = historyFacade.find(Long.parseLong(historyId));
                history.setReturnShoe(new GregorianCalendar().getTime());
                shoe = shoeFacade.find(history.getShoe().getId());
                shoe.setQuantity(shoe.getQuantity()+1);
                shoeFacade.edit(shoe);
                historyFacade.edit(history);
                request.setAttribute("info","Книга возвращена");
                request.getRequestDispatcher("/returnShoe").forward(request, response);
                break;
            case "/listHistories":
                request.setAttribute("listBuyers", buyerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/buyers/listBuyers.jsp").forward(request, response);
                break;
            case "/showStat":
                LocalDateTime localDateTime = LocalDateTime.now();
                int year = localDateTime.getYear();
                request.setAttribute("year", year);
                request.getRequestDispatcher("/WEB-INF/history/showStat.jsp").forward(request, response);
                break;
            case "/calcStat":
                String selectDay = request.getParameter("selectDay");
                String selectMonth = request.getParameter("selectMonth");
                String selectYear = request.getParameter("selectYear");
                Map<Shoe,Integer> mapStat = new HashMap<>();
                List<History> listHistoryStat = historyFacade.getListHistory(selectYear,selectMonth,selectDay);
                listShoes = shoeFacade.findAll();
                for (int i = 0; i < listShoes.size(); i++) {
                    Shoe b = listShoes.get(i);
                    int n = 0;
                    for (int j = 0; j < listHistoryStat.size(); j++) {
                        Shoe buyerShoe = listHistoryStat.get(j).getShoe();
                        if(buyerShoe.equals(b)){
                            if(mapStat.get(b) != null) n = mapStat.get(b); 
                            mapStat.put(b,n+1);
                        }
                    }
                }
                request.setAttribute("mapStat", mapStat);
                request.setAttribute("selectDay", selectDay);
                request.setAttribute("selectMonth", selectMonth);
                request.setAttribute("selectYear", selectYear);
                if(selectDay.isEmpty() && selectMonth.isEmpty() && !selectYear.isEmpty()){
                        request.setAttribute("period", "За год");
                }else if(selectDay.isEmpty() && !selectMonth.isEmpty() && !selectYear.isEmpty()){
                    request.setAttribute("period", "За месяц");
                }else if(!selectDay.isEmpty() && !selectMonth.isEmpty() && !selectYear.isEmpty()){
                    request.setAttribute("period", "За день");
                }
                request.getRequestDispatcher("/showStat").forward(request, response);
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
