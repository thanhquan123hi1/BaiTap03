<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Sửa danh mục</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body {
            background-color: #f1f3f5;
        }

        .edit-card {
            background: #fff;
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.08);
        }

        .banner {
            background: linear-gradient(135deg, #198754, #0d5c39);
            color: white;
            padding: 1.8rem;
            border-radius: 12px;
            margin-bottom: 25px;
            box-shadow: 0px 3px 12px rgba(0,0,0,0.1);
        }

        .img-preview {
            width: 120px;
            height: 120px;
            border-radius: 10px;
            border: 1px solid #dee2e6;
            object-fit: contain;
            background: #fff;
            padding: 8px;
        }
    </style>
</head>

<body class="container py-4">

    <!-- Banner -->
    <div class="banner d-flex justify-content-between align-items-center">
        <div>
            <h3 class="mb-1">Chỉnh sửa danh mục</h3>
            <p class="mb-0 opacity-75">Bạn có thể cập nhật tên và icon tại đây.</p>
        </div>
        <i class="fa-solid fa-pen-to-square fa-2x opacity-50"></i>
    </div>

    <div class="edit-card">

        <!-- Hiển thị thông báo lỗi -->
        <c:if test="${alert != null}">
            <div class="alert alert-danger text-center fw-bold">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/manager/categories/edit"
              method="post" enctype="multipart/form-data">

            <input type="hidden" name="cate_id" value="${cate.cateId}">

            <!-- Tên category -->
            <div class="mb-3">
                <label class="form-label fw-semibold">Tên danh mục</label>
                <input type="text"
                       name="cate_name"
                       class="form-control"
                       value="${cate.cateName}"
                       required>
            </div>

            <!-- Icon cũ -->
            <div class="mb-3">
                <label class="form-label fw-semibold">Icon hiện tại</label><br>

                <img src="${pageContext.request.contextPath}/uploads/category/${cate.icons}"
                     class="img-preview"
                     id="oldPreview"
                     onerror="this.src='https://via.placeholder.com/120?text=No+Img'">
            </div>

            <!-- Chọn icon mới -->
            <div class="mb-3">
                <label class="form-label fw-semibold">Chọn icon mới (nếu muốn)</label>
                <input type="file"
                       name="iconFile"
                       class="form-control"
                       accept="image/*"
                       onchange="previewNewImg(event)">
            </div>

            <!-- Preview ảnh mới -->
            <div class="mb-3">
                <label class="form-label fw-semibold">Preview icon mới:</label><br>
                <img id="newPreview"
                     class="img-preview"
                     style="display: none;">
            </div>

            <div class="mt-4 d-flex justify-content-between">

                <a href="${pageContext.request.contextPath}/manager/home"
                   class="btn btn-secondary px-4">
                    <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                </a>

                <button type="submit" class="btn btn-success px-4">
                    <i class="fa-solid fa-floppy-disk me-1"></i> Lưu thay đổi
                </button>
            </div>

        </form>
    </div>

    <script>
        function previewNewImg(event) {
            const file = event.target.files[0];
            const preview = document.getElementById('newPreview');

            if (file) {
                preview.src = URL.createObjectURL(file);
                preview.style.display = "block";
            }
        }
    </script>

</body>
</html>
