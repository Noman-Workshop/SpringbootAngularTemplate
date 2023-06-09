package com.eastnetic.todoapp.menu.domain;
import com.eastnetic.todoapp.common.domain.entities.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuCreateRequest implements Serializable {

    private String name;
    private String url;
    private Long position;
    private Long featureId;
    private boolean activeUrl;
    private Long parentId;
}
