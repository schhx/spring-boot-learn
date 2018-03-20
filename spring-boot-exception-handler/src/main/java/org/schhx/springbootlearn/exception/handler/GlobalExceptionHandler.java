package org.schhx.springbootlearn.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.schhx.springbootlearn.exception.BaseException;
import org.schhx.springbootlearn.vo.ErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.function.Supplier;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorVO handleException(NoHandlerFoundException e) {
        return ErrorVO.of("请求路由不存在", e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BaseException.class)
    public ErrorVO handleException(HttpServletRequest request, BaseException e) {
        if (e.isNeedLog()) {
            logError(request, e);
        }
        return ErrorVO.of(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorVO handleException(HttpServletRequest request, Exception e) {
        logError(request, e);
        return ErrorVO.of("未知异常", e.getMessage());
    }

    private void logError(HttpServletRequest request, Exception e) {
        StringBuilder messageBuilder = new StringBuilder("Unhandled exception in request:\n");
        messageBuilder.append(request.getMethod());
        messageBuilder.append(" ");
        messageBuilder.append(request.getRequestURI());
        messageBuilder.append("\nHeader:");
        toIterable(request::getHeaderNames).forEach(name -> {
            messageBuilder.append("\n");
            String value = request.getHeader(name);
            messageBuilder.append(name);
            messageBuilder.append(": ");
            messageBuilder.append(value);
        });
        messageBuilder.append("\n");
        if (request.getParameterMap().size() > 0) {
            StringBuilder parameters = getRequestParameters(request);
            messageBuilder.append(parameters);
        }
        log.error(messageBuilder.toString(), e);
    }

    private StringBuilder getRequestParameters(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder("request parameters:");
        stringBuilder.append("\n");
        final Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((k, v) -> {
            stringBuilder.append(k);
            stringBuilder.append(": ");
            stringBuilder.append(Arrays.toString(v));
            stringBuilder.append("\n");
        });
        return stringBuilder;
    }

    private <T> Iterable<T> toIterable(Supplier<Enumeration<T>> enumerationSupplier) {
        return () -> CollectionUtils.toIterator(enumerationSupplier.get());
    }
}
