package com.data.transport.model.response;

import com.data.transport.common.constant.ResponseCode;
import lombok.Data;

@Data
public class ResponseEntity<T> {
    private Integer code;
    private String msg;
    private T data;
    public ResponseEntity() {

    }

    public ResponseEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <A> ResponseEntity<A> of(int code, String msg, A data){
        return new ResponseEntity<>(code, msg, data);
    }

    public static <A> ResponseEntity<A> of(ResponseCode responseCode, A data){
        return new ResponseEntity<>(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <A> ResponseEntity<A> success( A data){
        return ResponseEntity.of(ResponseCode.SUCCESS, data);
    }
    public static <A> ResponseEntity<A> error(ResponseCode responseCode) {
        return ResponseEntity.of(responseCode.getCode(), responseCode.getMessage(), null);
    }
}

