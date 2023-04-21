<%-- 
    Document   : listBooks
    Created on : Feb 28, 2023, 11:10:00 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

       <h3 class="w-100 d-flex justify-content-center mt-5">Выдать обувь покупателю</h3>
       <div class="w-100 p-3 d-flex justify-content-center">
                    <form action="createHistory" method="POST">
                    <div class="card-body">
                        <div class="mb-3 row ">
                            <select name="shoeId" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                <option value="#" disabled selected>Выберите обувь</option>
                                <c:forEach var="shoe" items="${listShoes}">
                                    <option value="${shoe.id}">${shoe.shoeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3 row ">
                            <select name="buyerId" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                <option value="#" disabled selected>Выберите покупателя</option>
                                <c:forEach var="buyer" items="${listBuyers}">
                                    <option value="${buyer.id}">${buyer.firstname} ${buyer.lastname}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3 row d-flex justify-content-center">    
                            <button type="submit" class="btn btn-secondary w-50">Выдать обувь</button>
                        </div>
                    </div>
                </form>
             </div>
           
       </div>
  