<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-rt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>SearchItemView</title>
</head>
<body>
<h1>Tìm kiếm mặt hàng</h1>
<form action="import/search-item" method="get">
    <label for="keyword">Từ khóa:</label>
    <input type="text" id="keyword" name="keyword" value="${keyword}">
    <button type="submit">Tìm kiếm</button>
</form>
<c:if test="${not empty products}">
    <table border="1" cellpadding="4">
        <thead>
        <tr>
            <th>Mã hàng</th>
            <th>Tên</th>
            <th>Loại</th>
            <th>Thương hiệu</th>
            <th>Đơn vị</th>
            <th>Đơn giá bán</th>
            <th>Chọn</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productCode}</td>
                <td>${product.name}</td>
                <td>${product.productType}</td>
                <td>${product.brand}</td>
                <td>${product.unit}</td>
                <td>${product.sellingPrice}</td>
                <td>
                    <form action="import/add-item" method="post" style="display: inline;">
                        <input type="hidden" name="supplierId" value="${supplierId}">
                        <input type="hidden" name="productCode" value="${product.productCode}">
                        <input type="hidden" name="productName" value="${product.name}">
                        <input type="hidden" name="productType" value="${product.productType}">
                        <input type="hidden" name="brand" value="${product.brand}">
                        <input type="hidden" name="unit" value="${product.unit}">
                        <input type="hidden" name="sellingPrice" value="${product.sellingPrice}">
                        <label>Số lượng: <input type="number" name="quantity" min="1" value="1"></label>
                        <label>Đơn giá nhập: <input type="number" step="0.01" name="unitPrice" min="0" value="0"></label>
                        <button type="submit">Chọn</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
