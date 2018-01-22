package org.schhx.springbootlearn.vo;

import lombok.Data;

import java.util.Collections;

@Data
public class ResultVO<T> {


    private String msg;

    private String original_msg;

    private T data;

    private ResultVO(String msg, String originalMsg, T data) {
        this.msg = msg;
        this.original_msg = originalMsg;
        this.data = data;
    }

    public static <T> ResultVO success(T data) {
        return new ResultVO<>("处理成功", "处理成功", data);
    }

    public static ResultVO error() {
        return new ResultVO<>("处理失败", "处理失败", Collections.EMPTY_MAP);
    }

    public static ResultVO error(String originalMsg) {
        return new ResultVO<>("处理失败", originalMsg, Collections.EMPTY_MAP);
    }

    public static ResultVO error(String msg, String originalMsg) {
        return new ResultVO<>(msg, originalMsg, Collections.EMPTY_MAP);
    }
}
