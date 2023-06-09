package com.eastnetic.todoapp.menu.domain;
import com.eastnetic.todoapp.menu.domain.MenuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse implements Serializable {

    private String RespMessage;
    //private List<MenuEntity> menu;
    private List<MenuEntity> menu;
    //private List<List<MenuResponse>> submenu;

}
