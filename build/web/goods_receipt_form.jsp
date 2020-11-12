<%-- 
    Document   : goods_receipt_form
    Created on : Nov 5, 2020, 3:47:33 PM
    Author     : HKDUC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>
<div class="row">
    <div id="carouselIndicators" class="carousel slide col-12 col-md-4" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselIndicators" data-slide-to="0" class="activate"></li>
                <c:forEach var = "i" begin = "1" end = "${item.imgPaths.size()}">
                <li data-target="#carouselIndicators" data-slide-to="${i}"></li>
                </c:forEach>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="${item.thumbnailPath}">
            </div>
            <c:forEach var = "i" begin = "0" end = "${item.imgPaths.size()-1}">
                <div class="carousel-item">
                    <img class="d-block w-100" src="${item.imgPaths[i]}">
                </div>
            </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="col-12 col-md-8">
        <h3>${item.itemName}</h3>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputCondition">Tình trạng</label>
            </div>
            <select class="custom-select" id="inputCondition" name="condition" onchange="updatePriceAndQuantity()">
                <c:forEach items="${variouscdt}" var="cdt">
                    <option value="${cdt.conditionNo}">${cdt.condition}</option>
                </c:forEach>
            </select>
        </div>
        <small>Số lượng còn lại: <span id="quantity">p</span></small>
        <a data-toggle="modal" data-target="#addModal" style=" cursor: pointer;">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
            <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
            </svg>
        </a>
        <a data-toggle="modal" data-target="#subtractModal" style=" cursor: pointer;">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-dash-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
            <path fill-rule="evenodd" d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z"/>
            </svg>
        </a>
        <p>Giá: <span id="price" class="addsep">0</span>đ</p>
        <form action="item_edit" method="GET" style="display: inline-block;">
            <input type="hidden" name="ItemID" value="${item.itemID}">
            <button type="submit" class="btn btn-outline-primary"> Chỉnh sửa sảm phẩm
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                </svg>
            </button>

        </form>
        <button data-toggle="modal" data-target="#confirmDelete" class="btn btn-outline-danger ml-2"> Xóa sảm phẩm
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
            </svg>
        </button>
    </div>
</div>
<div class="modal hide fade" id="confirmDelete">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Bạn có chắc muốn xóa sản phẩm này</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>1. Sản phẩm sẽ không còn xuất hiện trong các order nữa, kể cả các order đã hoàn thành nữa</p>
                <p>2. Đồng thời sẽ xóa dữ liệu của sản phẩm trong kho hàng, hình ảnh</p>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <form action="item_delete" method="POST">
                    <input type="hidden" name="ItemID" value="${item.itemID}">
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </form>
                <button class="btn btn-secondary" data-dismiss="modal">Bỏ qua</button>
            </div>
        </div>
    </div>
</div>

<div class="p-4 col-12 col-md-8" id="descriptionArea">
    <h3>Thông tin sản phẩm</h3>
    ${item.description}
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog" role="document">
        <div class="modal-content p-4">
            <h5 class="modal-title">Nhập số hàng cộng vào:</h5> <br>
            <form autocomplete="off" action="adjustquantity" method="POST">
                <input type="text" class="form-control" name="InputNum" placeholder="Nhập số hàng cộng thêm:">
                <input type="hidden" name="Sign" value="1">
                <input type="hidden" name="ConditionNo" value="">
                <input type="hidden" name="ItemID" value="${item.itemID}">
                <div style="text-align: center;" class="mt-3">
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="subtractModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog" role="document">
        <div class="modal-content p-4">
            <h5 class="modal-title">Nhập số hàng cần trừ đi:</h5><br>
            <form autocomplete="off" action="adjustquantity" method="POST">
                <input type="text" class="form-control" name="InputNum" placeholder="Nhập số hàng trừ đi">
                <input type="hidden" name="Sign" value="-1">
                <input type="hidden" name="ConditionNo" value="">
                <input type="hidden" name="ItemID" value="${item.itemID}">
                <div style="text-align: center;" class="mt-3">
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    var prices = [0];
    var quantity = [0];
    <c:forEach items="${item.statusList}" var="status">
    prices.push(${status.price});
    quantity.push(${status.quantity});
    </c:forEach>
    function updatePriceAndQuantity() {
        var ind = document.getElementById('inputCondition').value;
        document.getElementById('price').innerHTML = prices[ind];
        document.getElementById('quantity').innerHTML = quantity[ind];
        document.getElementsByName('ConditionNo')[0].value = ind;
        document.getElementsByName('ConditionNo')[1].value = ind;
        addThousandSep();
    }
    updatePriceAndQuantity();
</script>
<%@ include file = "template/footer.jsp"%>