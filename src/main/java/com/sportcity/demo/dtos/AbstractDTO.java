package com.sportcity.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AbstractDTO <ID extends Serializable> implements Serializable{

    private ID id;
}
