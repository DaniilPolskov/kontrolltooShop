<%-- 
    Document   : listBooks
    Created on : Feb 28, 2023, 11:10:00 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

       <h3 class="w-100 d-flex justify-content-center mt-5">Список обуви</h3>
       <div class="w-100 p-3 d-flex justify-content-center">
           <c:forEach var="shoe" items="${listShoes}">
            <div class="card m-2" style="width: 13rem;">
                <a class="text-decoration-none" href="shoe?id=${shoe.id}">
                </a>
             </div>
           </c:forEach>
       </div>
  