package org.schhx.springbootlearn.exception.handler;

import org.schhx.springbootlearn.vo.ErrorVO;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ErrorVO error(){
        return ErrorVO.of("兜底");
    }
}
