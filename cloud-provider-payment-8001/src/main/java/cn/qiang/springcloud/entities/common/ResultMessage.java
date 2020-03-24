package cn.qiang.springcloud.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage<T> {
    private Integer code;
    private String msg;
    private T data;
}
