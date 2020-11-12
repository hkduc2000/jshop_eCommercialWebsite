<%-- 
    Document   : add_to_cart_resutl
    Created on : Nov 8, 2020, 9:03:56 PM
    Author     : HKDUC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>

<div class="modal hide fade" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Đã thêm sản phẩm vào giỏ hàng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <a href="cart" class="btn btn-success">Thanh toán</a>
                <a href="list" class="btn btn-primary">Tiếp tục mua sắm</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(window).on('load', function () {
        $('#myModal').modal('show');
    });
</script>
<%@ include file = "template/footer.jsp"%>