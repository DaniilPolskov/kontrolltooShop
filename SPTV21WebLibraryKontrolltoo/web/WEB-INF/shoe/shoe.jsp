<%-- 
    Document   : book
    Created on : Mar 2, 2023, 11:05:35 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 p-3 d-flex justify-content-center">
    <div class="card m-2" style="width: 13rem;">
        <div class="card-body">
        <h5 class="card-title">Название обуви${shoe.shoeName}</h5>
        <p class="card-text">Цена: ${shoe.price}</p>
        <p class="card-text">Количество обуви на складе: ${book.quantity}</p>
        </div>
    </div>
</div>
