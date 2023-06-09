package com.eastnetic.todoapp.menu.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuUpdateRequest implements Serializable {

    private Long id;
    private String url;
    private Long position;
    private Long featureId;
    private boolean activeUrl;
    private Long parentId;
}
