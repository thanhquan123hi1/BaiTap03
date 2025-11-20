package vn.Quan.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "categories") 
@Entity
@Table(name = "users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "images", length = 500)
    private String images;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "roleid")
    private int roleid;

    @Column(name = "createDate")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;
}
