<%-- 
    Document   : item_detail
    Created on : Nov 4, 2020, 2:23:51 PM
    Author     : HKDUC
--%>

<%@page import="model.Item"%>
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
    <div class="col-12 col-md-8  pl-3">
        <h3>${item.itemName}</h3>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputCondition">Tình trạng</label>
            </div>
            <select class="custom-select" id="inputCondition" onchange="updatePriceAndQuantity()">
                <c:forEach items="${variouscdt}" var="cdt">
                    <option value="${cdt.conditionNo}">${cdt.condition}</option>
                </c:forEach>
            </select>
        </div>
        <small>Số lượng còn lại: <span id="quantity">0</span></small>
        <p>Giá: <span class="addsep" id="price">0</span>đ</p>
        <form action="add_to_cart" method="POST">
            <input type="hidden" name="itemID" value="${item.itemID}">
            <input type="hidden" name="conditionNo" id="conditionNo" value="1">
            <div class="form-group row">
                <label class="mr-3 ml-3">Số lượng:</label>
                <input class="form-control col-4 col-sm-3 col-md-2" name="buyQuantity" id="buyQuantity"
                       type="number" value="1" onchange="updatePriceAndQuantity()">
            </div>
            <button type="submit" id="addToCart" class="btn btn-outline-success"> Thêm vào giỏ hàng
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-cart-plus" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>
                <path fill-rule="evenodd" d="M8.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H9v1.5a.5.5 0 0 1-1 0V8H6.5a.5.5 0 0 1 0-1H8V5.5a.5.5 0 0 1 .5-.5z"/>
                </svg>
            </button>
        </form>
    </div>
</div>
<div class="p-4 col-12 col-md-8" id="descriptionArea">
    <h3>Thông tin sản phẩm</h3>
    ${item.description}
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
        document.getElementById('conditionNo').value = ind;
        addThousandSep();
        var buyQuantity = document.getElementById("buyQuantity").value;
        if (quantity[ind]<buyQuantity){
            document.getElementById('addToCart').disabled = true;
            document.getElementById('addToCart').innerHTML = "Hết hàng";
        } else {
            document.getElementById('addToCart').disabled = false;
            document.getElementById('addToCart').innerHTML = "Thêm vào giỏ hàng";
        }
    }
    updatePriceAndQuantity();
</script>
<%@ include file = "template/footer.jsp"%>