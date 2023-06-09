package com.eastnetic.todoapp.rolemenu.domain;

import com.eastnetic.todoapp.common.domain.entities.BaseEntity;
import com.eastnetic.todoapp.menu.domain.MenuEntity;
import com.eastnetic.todoapp.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table (name = "role_menu", schema = "public")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleMenu extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="role_id", nullable = false)
    private Long roleId;
    @Column (name="menu_id", nullable = false)
    private Long menuId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id")
//    private MenuEntity menu;

}
