package cn.edu.xmu.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author summer
 * @date 2021/2/26 19:03
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int code;
    private String msg;
    private T data;

    public Response<?> setResponseCode(ResponseCode responseCode){
        this.code=responseCode.getCode();
        this.msg=responseCode.getMsg();
        return this;
    }

}
