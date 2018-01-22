package org.schhx.springbootlearn.service;

import org.hibernate.validator.constraints.NotBlank;
import org.schhx.springbootlearn.entity.User;
import org.schhx.springbootlearn.vo.UserVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated //校验失败会抛出 ConstraintViolationException
public interface UserService {

    User createUser(@Valid UserVO userVO);

    User getUserById(@NotBlank(message = "id不能为空") String id);

    User getUserByName(@NotBlank(message = "姓名不能为空") String name);

}
