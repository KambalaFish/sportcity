package com.sportcity.demo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AbstractDTO <ID extends Serializable> implements Serializable{
    @Getter @Setter
    private static Class entityClass;

    private ID id;
}
