<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-rt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>ListSupplierView</title>
</head>
<body>
<h1>Danh sách nhà cung cấp</h1>
<form action="suppliers" method="get">
    <label for="keyword">Tìm kiếm theo tên:</label>
    <input type="text" id="keyword" name="keyword" value="${keyword}">
    <button type="submit">Tìm kiếm</button>
</form>
<h2>Thêm mới nhà cung cấp</h2>
<form action="suppliers" method="post">
    <input type="hidden" name="action" value="add">
    <label for="supplierCode">Mã nhà cung cấp:</label>
    <input type="text" id="supplierCode" name="supplierCode" required>
    <label for="legalName">Tên pháp lí:</label>
    <input type="text" id="legalName" name="legalName" required>
    <label for="taxCode">Mã số thuế:</label>
    <input type="text" id="taxCode" name="taxCode">
    <label for="supplierAddress">Địa chỉ:</label>
    <input type="text" id="supplierAddress" name="address">
    <label for="supplierPhone">Số điện thoại:</label>
    <input type="text" id="supplierPhone" name="phone">
    <label for="supplierEmail">Email:</label>
    <input type="email" id="supplierEmail" name="email">
    <label for="supplierNote">Ghi chú:</label>
    <input type="text" id="supplierNote" name="note">
    <button type="submit">Thêm mới</button>
</form>
<c:if test="${not empty suppliers}">
    <h2>Kết quả</h2>
    <table border="1" cellpadding="4">
        <thead>
        <tr>
            <th>Mã</th>
            <th>Tên pháp lí</th>
            <th>Địa chỉ</th>
            <th>SĐT</th>
            <th>Email</th>
            <th>Chọn</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="supplier" items="${suppliers}">
            <tr>
                <td>${supplier.supplierCode}</td>
                <td>${supplier.legalName}</td>
                <td>${supplier.address}</td>
                <td>${supplier.phone}</td>
                <td>${supplier.email}</td>
                <td><a href="import?supplierId=${supplier.supplierCode}">Nhập hàng</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
