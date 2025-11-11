<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-rt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>RegisterMemberFormView</title>
</head>
<body>
<h1>Đăng kí thành viên</h1>
<c:if test="${not empty errors}">
    <ul style="color: red;">
        <c:forEach var="error" items="${errors}">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>
<form action="register-member" method="post">
    <label for="firstName">Tên đầu:</label>
    <input type="text" id="firstName" name="firstName" value="${param.firstName}"><br>
    <label for="lastName">Tên cuối:</label>
    <input type="text" id="lastName" name="lastName" value="${param.lastName}"><br>
    <label for="birthOfDate">Ngày sinh:</label>
    <input type="date" id="birthOfDate" name="birthOfDate" value="${param.birthOfDate}"><br>
    <label>Giới tính:</label>
    <label><input type="radio" name="gender" value="MALE" ${param.gender == 'MALE' ? 'checked' : ''}> Nam</label>
    <label><input type="radio" name="gender" value="FEMALE" ${param.gender == 'FEMALE' ? 'checked' : ''}> Nữ</label>
    <label><input type="radio" name="gender" value="OTHER" ${param.gender == 'OTHER' ? 'checked' : ''}> Khác</label><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${param.email}"><br>
    <label for="password">Mật khẩu:</label>
    <input type="password" id="password" name="password"><br>
    <label for="confirmPassword">Nhập lại mật khẩu:</label>
    <input type="password" id="confirmPassword" name="confirmPassword"><br>
    <label for="phone">Số điện thoại:</label>
    <input type="tel" id="phone" name="phone" value="${param.phone}"><br>
    <label for="address">Địa chỉ:</label>
    <input type="text" id="address" name="address" value="${param.address}"><br>
    <button type="submit">Đăng kí</button>
</form>
</body>
</html>
