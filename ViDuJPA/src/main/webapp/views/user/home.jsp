<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>User Home</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body { background-color: #fffbea; } /* Nền vàng nhạt */

        .banner {
            background: linear-gradient(135deg, #ffc107, #ff9800); /* Vàng → Cam */
            color: white;
            padding: 25px;
            border-radius: 12px;
            margin-bottom: 25px;
            box-shadow: 0 4px 15px rgba(255, 152, 0, 0.35);
        }

        .cate-icon {
            width: 60px;
            height: 60px;
            object-fit: contain;
            border-radius: 8px;
            background: #fff;
            border: 2px solid #ffeeba;      /* Viền vàng nhạt */
            padding: 5px;
        }

        .card {
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            border: 1px solid #ffe8a1;      /* Viền vàng nhạt */
        }

        .title-text {
            color: #d48806;                 /* Màu vàng đậm */
            font-weight: bold;
        }

        .table thead {
            background-color: #fff3cd;     /* Vàng nhạt */
        }

        .btn-logout {
            background-color: #ffb300;
            border: none;
        }
        .btn-logout:hover {
            background-color: #ff8f00;
        }

    </style>
</head>

<body class="container mt-4">

    <!-- Welcoming Banner -->
    <div class="banner d-flex justify-content-between align-items-center">
        <div>
            <h2 class="mb-1">Xin chào, ${username}! 👋</h2>
            <p class="mb-0">Dưới đây là toàn bộ danh mục có trong hệ thống.</p>
        </div>
        <i class="fa-solid fa-user fa-3x opacity-75"></i>
    </div>

    <h4 class="mb-3 title-text">Danh sách tất cả danh mục</h4>

    <div class="card p-3">

        <c:if test="${empty listcate}">
            <div class="text-center text-muted py-4">
                <i class="fa-regular fa-folder-open fa-3x mb-3"></i>
                <p>Không có danh mục nào.</p>
            </div>
        </c:if>

        <c:if test="${not empty listcate}">
            <table class="table table-hover align-middle">
                <thead class="table-light">
                <tr>
                    <th class="text-center" style="width: 70px;">ID</th>
                    <th>Tên danh mục</th>
                    <th style="width: 120px;">Icon</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="c" items="${listcate}">
                    <tr>
                        <td class="text-center fw-bold text-warning">#${c.cateId}</td>
                        <td class="fw-semibold text-dark">${c.cateName}</td>

                        <td>
                            <img src="${pageContext.request.contextPath}/uploads/category/${c.icons}"
                                 class="cate-icon"
                                 onerror="this.src='https://via.placeholder.com/60?text=No+Img'">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

    </div>

    <div class="mt-4 text-end">
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-logout text-white px-4">
            <i class="fa-solid fa-right-from-bracket me-1"></i> Đăng xuất
        </a>
    </div>

</body>
</html>
