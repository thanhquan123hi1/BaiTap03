<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Manager Dashboard</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .welcome-banner {
            background: linear-gradient(135deg, #198754 0%, #0f5f3d 100%);
            color: white;
            padding: 2.2rem;
            border-radius: 15px;
            margin-bottom: 2rem;
            box-shadow: 0 4px 15px rgba(25, 135, 84, 0.3);
        }

        .dashboard-card {
            border: none;
            border-radius: 12px;
            transition: all 0.3s ease;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            height: 100%;
            text-decoration: none;
            display: block;
        }

        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0,0,0,0.1);
        }

        .icon-box {
            width: 55px;
            height: 55px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.4rem;
            margin-bottom: 1rem;
        }

        .action-btn {
            width: 35px;
            height: 35px;
            padding: 0;
        }

    </style>
</head>

<body class="container mt-4 mb-5">

    <!-- Banner -->
    <div class="welcome-banner d-flex justify-content-between align-items-center">
        <div>
            <h2 class="mb-1">Xin chào, ${username}! 👋</h2>
            <p class="mb-0 opacity-75">
                Đây là trang quản lý danh mục của bạn.
            </p>
        </div>
        <div class="d-none d-md-block">
            <i class="fa-solid fa-user-tie fa-3x opacity-50"></i>
        </div>
    </div>

    <!-- Section title -->
    <h5 class="text-muted mb-3">DANH MỤC CỦA BẠN</h5>

    <!-- Category Table -->
    <div class="card shadow-sm border-0">
        <div class="card-body">

            <c:if test="${empty listcate}">
                <div class="text-center py-4 text-muted">
                    <i class="fa-regular fa-folder-open fa-3x opacity-50 mb-2"></i>
                    <p>Hiện bạn chưa có danh mục nào.</p>
                </div>
            </c:if>

            <c:if test="${not empty listcate}">
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-light">
                            <tr>
                                <th class="text-center" style="width: 80px;">ID</th>
                                <th>Tên danh mục</th>
                                <th style="width: 120px;">Icon</th>
                                <th class="text-center" style="width: 150px;">Thao tác</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="c" items="${listcate}">
                                <tr>
                                    <td class="text-center fw-bold text-secondary">#${c.cateId}</td>

                                    <td class="fw-bold text-dark">${c.cateName}</td>

                                    <td>
                                        <img src="${pageContext.request.contextPath}/uploads/category/${c.icons}"
                                             class="img-thumbnail"
                                             style="width: 60px; height: 60px; object-fit: contain;"
                                             onerror="this.src='https://via.placeholder.com/60?text=No+Img'">
                                    </td>

                                    <td class="text-center">
                                        <!-- EDIT -->
                                        <a href="${pageContext.request.contextPath}/manager/categories/edit?id=${c.cateId}"
                                           class="btn btn-outline-primary btn-sm action-btn me-1">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </a>

                                        <!-- DELETE -->
                                        <a href="${pageContext.request.contextPath}/manager/categories/delete?id=${c.cateId}"
                                           onclick="return confirm('Bạn chắc chắn muốn xoá danh mục này?');"
                                           class="btn btn-outline-danger btn-sm action-btn">
                                            <i class="fa-solid fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

        </div>
    </div>

    <!-- Logout -->
    <div class="mt-4 text-end">
        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-danger px-4">
            <i class="fa-solid fa-right-from-bracket me-1"></i> Đăng xuất
        </a>
    </div>

</body>
</html>
