<%-- 
    Document   : checkout
    Created on : Nov 9, 2020, 9:45:36 AM
    Author     : HKDUC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>

<form class="w-50"  action="checkout" method="POST">
    <div class="form-group">
        <label>Tên người nhận: </label>
        <input class="form-control" name="receipientName" value="${user.name}">
    </div>
    <div class="form-group">
        <label>Địa chỉ người nhận: </label>
        <input class="form-control" name="receipientAddress" value="${user.address}">
    </div>
    <div class="form-group">
        <label>Số điện thoại người nhận: </label>
        <input class="form-control" name="receipientPhone" value="${user.phone}">
    </div>
    
    <button type="submit" class="btn btn-success">Hoàn tất</button>
    <a href="cart" class="btn btn-secondary">Quay lại giỏ hàng</a>
    
</form>
<%@ include file = "template/footer.jsp"%>