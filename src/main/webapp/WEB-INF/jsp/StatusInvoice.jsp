<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-rt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>StatusInvoice</title>
</head>
<body>
<h1>Trạng thái hóa đơn nhập</h1>
<c:choose>
    <c:when test="${not empty importInvoice}">
        <p>Hóa đơn nhập ${importInvoice.importInvoiceCode} đã được lưu.</p>
        <section>
            <h2>Thông tin nhà cung cấp</h2>
            <ul>
                <li>Mã: ${importInvoice.supplier.supplierCode}</li>
                <li>Tên: ${importInvoice.supplier.legalName}</li>
                <li>Ghi chú: ${importInvoice.note}</li>
            </ul>
        </section>
        <section>
            <h2>Danh sách mặt hàng</h2>
            <table border="1" cellpadding="4">
                <thead>
                <tr>
                    <th>Mã hàng</th>
                    <th>Tên</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Thành tiền</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${importInvoice.items}">
                    <tr>
                        <td>${item.product.productCode}</td>
                        <td>${item.product.name}</td>
                        <td>${item.quantity}</td>
                        <td>${item.unitPrice}</td>
                        <td>${item.totalAmount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <p>Tổng tiền: ${importInvoice.totalAmount}</p>
        </section>
        <form action="warehouse" method="get">
            <button type="submit">Quay về trang nhân viên kho</button>
        </form>
    </c:when>
    <c:otherwise>
        <p>Không tìm thấy hóa đơn.</p>
        <a href="warehouse">Quay lại</a>
    </c:otherwise>
</c:choose>
</body>
</html>
