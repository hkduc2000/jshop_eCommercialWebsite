<%@page import="model.Category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>
<form action="item_edit" method="POST">
    <input type="hidden" name="ItemID" value="${item.itemID}">
    <div class="form-group">
        <label>Tên sản phẩm</label>
        <input type="text" name="ItemName" value="${item.itemName}" class="form-control" required>
    </div>
    <div class="form-group">
        <label>Danh mục sản phẩm</label>
        <select class="form-control" name="CategoryNo" value="${item.categoryNo}">
            <c:forEach items="${requestScope.categories}" var="ctg">
                <option value="${ctg.categoryNo}">${ctg.category}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="inputDescription">Mô tả sản phẩm</label>
        <textarea id="inputDescription" class="editable medium-editor-textarea" name="Description"
                  style="min-height: 300px; width: 100%; border: 1px black solid;" required>
            ${item.description}
        </textarea>
    </div>
    <div class="form-group">
        <label>Giá sản phẩm:</label>
        <c:forEach items="${variouscdt}" var="cdt">
            <input type="text" name="price${cdt.conditionNo}" class="form-control mt-2" 
                   placeholder="Nhập giá cho sản phẩm ${cdt.condition}" value="${item.statusList[cdt.conditionNo-1].price}" required>
        </c:forEach>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/medium-editor/5.23.3/js/medium-editor.js"></script>
<script>
    document.getElementsByName('CategoryNo')[0].value=${item.categoryNo};
    var editor = new MediumEditor('.editable', {
        toolbar: {
            allowMultiParagraphSelection: true,
            buttons: ['bold', 'italic', 'underline', 'image', 'anchor', 'h2', 'h3'],
            buttonLabels: 'fontawesome',
        },
        placeholder:{
            text:"haha"
        }
    });
</script>
<%@ include file = "template/footer.jsp"%>