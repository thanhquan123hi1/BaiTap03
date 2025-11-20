<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.08);
        }
        .card-header {
            background: linear-gradient(45deg, #4e73df, #224abe);
            color: white;
            border-radius: 12px 12px 0 0 !important;
            padding: 1rem 1.5rem;
        }
        .table thead th {
            background-color: #f1f3f5;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.85rem;
            color: #6c757d;
            border-bottom: 2px solid #dee2e6;
        }
        .btn-action {
            margin-right: 5px;
            transition: all 0.2s;
        }
        .btn-action:hover {
            transform: translateY(-2px);
        }
        .icon-img {
            width: 55px;
            height: 55px;
            object-fit: contain;
            border: 1px solid #dee2e6;
            background: #fff;
            padding: 4px;
            border-radius: 6px;
        }
    </style>
</head>

<body class="py-5">

<div class="container">
    <div class="card">

        <div class="card-header d-flex justify-content-between align-items-center">
            <h4 class="m-0"><i class="fa-solid fa-folder-open me-2"></i>Quản lý danh mục</h4>

            <a href="${pageContext.request.contextPath}/admin/categories/add" 
               class="btn btn-light text-primary fw-bold shadow-sm">
                <i class="fa-solid fa-plus me-1"></i> Thêm mới
            </a>
        </div>

        <div class="card-body">

            <!-- Thanh tìm kiếm -->
            <div class="row mb-3">
                <div class="col-md-4 ms-auto">
                    <div class="input-group">
                        <span class="input-group-text bg-white">
                            <i class="fa-solid fa-search text-muted"></i>
                        </span>
                        <input type="text" id="searchInput" class="form-control border-start-0" 
                               placeholder="Tìm kiếm danh mục...">
                    </div>
                </div>
            </div>

            <!-- Bảng danh mục -->
            <div class="table-responsive">
                <table class="table table-hover align-middle" id="cateTable">
                    <thead>
                        <tr>
                            <th class="text-center" style="width: 80px;">ID</th>
                            <th>Tên danh mục</th>
                            <th>Icon</th>
                            <th class="text-center" style="width: 180px;">Thao tác</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="c" items="${listcate}">
                            <tr>
                                <td class="text-center fw-bold text-secondary">#${c.cateId}</td>

                                <td class="fw-bold text-dark">${c.cateName}</td>

                                <td>
                                    <img src="${pageContext.request.contextPath}/uploads/category/${c.icons}"
                                         class="icon-img"
                                         onerror="this.src='https://via.placeholder.com/60?text=No+Image';">
                                </td>

                                <td class="text-center">
                                    <a class="btn btn-sm btn-outline-primary btn-action"
                                       href="${pageContext.request.contextPath}/admin/categories/edit?id=${c.cateId}"
                                       title="Chỉnh sửa">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </a>

                                    <a class="btn btn-sm btn-outline-danger btn-action"
                                       href="${pageContext.request.contextPath}/admin/categories/delete?id=${c.cateId}"
                                       onclick="return confirm('Bạn có chắc muốn xóa danh mục: ${c.cateName}?');"
                                       title="Xóa">
                                        <i class="fa-solid fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty listcate}">
                            <tr>
                                <td colspan="4" class="text-center py-5">
                                    <div class="text-muted">
                                        <i class="fa-regular fa-folder-open fa-3x mb-3 d-block opacity-50"></i>
                                        <span>Chưa có danh mục nào được tạo.</span>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

<!-- Search script -->
<script>
document.getElementById('searchInput').addEventListener('keyup', function() {
    var searchText = this.value.toLowerCase();
    var tableRows = document.querySelectorAll('#cateTable tbody tr');

    tableRows.forEach(function(row) {
        if (row.innerText.toLowerCase().includes(searchText)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});
</script>

</body>
</html>
