package vn.Quan.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM CategoryEntity c")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private int cateId;

    @Column(name = "cate_name", length = 255)
    private String cateName;

    @Column(name = "icons", length = 500)
    private String icons;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
