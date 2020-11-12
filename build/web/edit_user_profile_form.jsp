<%-- 
    Document   : edit_user_profile_form
    Created on : Nov 11, 2020, 9:25:57 AM
    Author     : HKDUC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>
<form action="profile_edit" method="POST">
    <div class="form-group">
        <label>Họ tên: </label>
        <input type="text" class="form-control" name="name" value="${sessionScope.user.name}" required>
    </div>
    <div class="form-group">
        <label>Số điện thoại:</label>
        <input type="phonenumber" class="form-control" name="phone" placeholder="Nhập số điện thoại" 
               pattern="[0-9]{9,12}" value="${sessionScope.user.phone}" required>
    </div>
    <div class="form-group">
        <label >Địa chỉ:</label>
        <input type="text" class="form-control" name="address" placeholder="Nhập địa chỉ" 
               value="${sessionScope.user.address}" required>
    </div>
    <div class="form-group">
        <label>Mật khẩu:</label>
        <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu"
               value="${sessionScope.user.password}" required>
    </div>
    <div style="text-align: center;">
        <button type="submit" class="btn btn-primary">Lưu</button>
        <a href="${pageContext.request.contextPath}" class="btn btn-secondary">Bỏ qua</a>
    </div>
</form>
<%@ include file = "template/footer.jsp"%>
