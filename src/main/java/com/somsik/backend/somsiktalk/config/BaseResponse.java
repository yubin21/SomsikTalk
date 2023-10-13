package com.somsik.backend.somsiktalk.config;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

@JsonPropertyOrder({"success", "message", "result"})
public class BaseResponse<T> {
    private Boolean success;
    @JsonInclude(Include.NON_NULL)
    private String message;
    @JsonIgnore
    private int code;
    @JsonInclude(Include.NON_NULL)
    private T result;

    public BaseResponse(T result) {
        this.success = true;
        this.code = HttpStatus.OK.value();
        this.result = result;
    }

    public BaseResponse(ResponseStatus status) {
        System.out.println("실패 BaseResponse");
        this.success = false;
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    public ResponseEntity<BaseResponse> convert() {
        System.out.println("convert call");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json; charset=UTF-8");
        System.out.println("headers: " + headers + "code: " + this.code);
        System.out.println("this: " + this);
        return ((BodyBuilder)ResponseEntity.status(this.code).headers(headers)).body(this);
    }

    public Boolean getSuccess() {
        return this.success;
    }
    public String getMessage() {
        return this.message;
    }
    public int getCode() {
        return this.code;
    }
    public T getResult() {
        return this.result;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public void setCode(int code) {
        this.code = code;
    }
    public void setResult(T result) {
        this.result = result;
    }
}
