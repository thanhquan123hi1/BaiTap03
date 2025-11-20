package vn.Quan.model;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {

    private int id;
    private String username;
    private String email;
    private String fullname;
    private String images;
    private String password;
    private String phone;
    private int roleid;
    private LocalDateTime createDate;

    private List<CategoryModel> categories;
}