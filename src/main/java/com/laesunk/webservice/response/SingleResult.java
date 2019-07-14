package com.laesunk.webservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends Common {
    private T data;
}