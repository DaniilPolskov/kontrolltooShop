<%-- 
    Document   : listBuyers
    Created on : 21.04.2023, 9:17:34
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h3 class="w-100 d-flex justify-content-center mt-5">Список покупателей</h3>
   <div class="w-100 p-3 d-flex justify-content-center">
        <div class="card m-2 border-0" style="width: 43rem;">
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr><th scope="col">№</th><th scope="col">Покупатель</th><th scope="col">Товары покупателя</th></tr>
                    </thead>   
                    <c:forEach var="buyer" items="${listBuyers}" varStatus="status">
                         <tr>
                             <td>${status.index+1}</td>
                             <td class="d-flex justify-content-start">${buyer.firstname} ${buyer.lastname}. ${buyer.balance}</td>
                             <td></td>
                         </tr>
                         <c:forEach var="shoe" items="${author.shoes}" varStatus="status2">
                            <tr>
                                <td></td>
                                <td class="d-flex justify-content-start"><a class="text-decoration-none" href="shoe?id=${shoe.id}">${status2.index+1}. ${shoe.shoeName}</a></td>
                            </tr>
                         </c:forEach>
                    </c:forEach>
                </table>
            </div>
         </div>
   </div>
