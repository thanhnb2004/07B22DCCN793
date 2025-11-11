<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-rt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>ImportFormView</title>
</head>
<body>
<h1>Nhập hàng từ nhà cung cấp</h1>
<c:if test="${empty supplier}">
    <p>Không tìm thấy nhà cung cấp.</p>
</c:if>
<c:if test="${not empty supplier}">
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <section>
        <h2>Thông tin nhà cung cấp</h2>
        <ul>
            <li>Mã: ${supplier.supplierCode}</li>
            <li>Tên pháp lí: ${supplier.legalName}</li>
            <li>Mã số thuế: ${supplier.taxCode}</li>
            <li>Địa chỉ: ${supplier.address}</li>
            <li>SĐT: ${supplier.phone}</li>
            <li>Email: ${supplier.email}</li>
        </ul>
    </section>
    <section>
        <h2>Thêm mặt hàng</h2>
        <form action="import/add-item" method="post">
            <input type="hidden" name="supplierId" value="${supplier.supplierCode}">
            <label for="productCode">Mã hàng:</label>
            <input type="text" id="productCode" name="productCode" required>
            <label for="productName">Tên hàng:</label>
            <input type="text" id="productName" name="productName">
            <label for="productType">Loại:</label>
            <input type="text" id="productType" name="productType">
            <label for="brand">Thương hiệu:</label>
            <input type="text" id="brand" name="brand">
            <label for="unit">Đơn vị tính:</label>
            <input type="text" id="unit" name="unit">
            <label for="sellingPrice">Đơn giá bán:</label>
            <input type="number" step="0.01" id="sellingPrice" name="sellingPrice">
            <label for="stock">Tồn kho hiện tại:</label>
            <input type="number" id="stock" name="stock">
            <label for="description">Mô tả:</label>
            <input type="text" id="description" name="description">
            <label for="quantity">Số lượng nhập:</label>
            <input type="number" id="quantity" name="quantity" min="1" required>
            <label for="unitPrice">Đơn giá nhập:</label>
            <input type="number" step="0.01" id="unitPrice" name="unitPrice" min="0" required>
            <label for="note">Ghi chú:</label>
            <input type="text" id="note" name="note">
            <button type="submit">Thêm sản phẩm</button>
        </form>
    </section>
    <section>
        <h2>Danh sách mặt hàng nhập</h2>
        <c:if test="${empty invoiceItems}">
            <p>Chưa có mặt hàng.</p>
        </c:if>
        <c:if test="${not empty invoiceItems}">
            <table border="1" cellpadding="4">
                <thead>
                <tr>
                    <th>Mã hàng</th>
                    <th>Tên</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Thành tiền</th>
                    <th>Ghi chú</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${invoiceItems}">
                    <tr>
                        <td>${item.product.productCode}</td>
                        <td>${item.product.name}</td>
                        <td>${item.quantity}</td>
                        <td>${item.unitPrice}</td>
                        <td>${item.totalAmount}</td>
                        <td>${item.note}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </section>
    <section>
        <h2>Lưu hóa đơn nhập</h2>
        <form action="import" method="post">
            <input type="hidden" name="supplierId" value="${supplier.supplierCode}">
            <label for="importNote">Ghi chú hóa đơn:</label>
            <input type="text" id="importNote" name="note" value="${invoiceNote}">
            <button type="submit" name="action" value="save">Lưu hóa đơn</button>
        </form>
    </section>
</c:if>
</body>
</html>
