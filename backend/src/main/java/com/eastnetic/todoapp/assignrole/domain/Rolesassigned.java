package com.eastnetic.todoapp.assignrole.domain;
import com.eastnetic.todoapp.common.domain.entities.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table (name = "userroles", schema = "public")
@Table (name = "user_role", schema = "public")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rolesassigned extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="user_id", nullable = false)
    private Long userId;

    @Column (name="role_id", nullable = false)
    private Long roleId;

}
