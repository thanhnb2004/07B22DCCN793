<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-rt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>StatusRegister</title>
</head>
<body>
<h1>Trạng thái đăng kí</h1>
<c:choose>
    <c:when test="${not empty customer}">
        <p>Khách hàng ${customer.fullName} đã đăng kí thành công với mã ${customer.customerCode}.</p>
        <form action="customer" method="get">
            <button type="submit">Quay về trang chủ</button>
        </form>
    </c:when>
    <c:otherwise>
        <p>Không tìm thấy thông tin đăng kí.</p>
        <a href="register-member">Thử lại</a>
    </c:otherwise>
</c:choose>
</body>
</html>
