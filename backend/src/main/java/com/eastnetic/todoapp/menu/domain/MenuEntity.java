package com.eastnetic.todoapp.menu.domain;
import com.eastnetic.todoapp.common.domain.entities.BaseEntity;
import com.eastnetic.todoapp.role.domain.entity.Role;
import com.eastnetic.todoapp.menu.domain.MenuEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@Table (name = "menu", schema = "public")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuEntity extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="name", nullable = false)
    private String name;

    @Column (name="url", nullable = false)
    private String url;

    @Column (name="position")
    private Long position;

    @Column (name="feature_id", nullable = false)
    private Long featureId;

    @Column (name="active_url", nullable = false)
    private boolean activeUrl;

    @Column (name="parent_id")
    private Long parentId;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuEntity> submenus;


}
