package com.laesunk.webservice.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends Common {
    private List<T> list;
}
