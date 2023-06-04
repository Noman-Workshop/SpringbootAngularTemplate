package com.eastnetic.todoapp.role.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest implements Serializable {

    @NotBlank
    @Length(min=4,max=256)
    private String name;
}
