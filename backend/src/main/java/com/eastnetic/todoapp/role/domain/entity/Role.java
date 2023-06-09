package com.eastnetic.todoapp.role.domain.entity;

import com.eastnetic.todoapp.common.domain.entities.BaseEntity;
import com.eastnetic.todoapp.menu.domain.MenuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="roles",schema = "public")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menu",
            joinColumns =@JoinColumn(name = "roles_id"),
            inverseJoinColumns =  @JoinColumn(name = "menu_id")
    )
    private Set<MenuEntity> role_menu = new HashSet<>();
}
