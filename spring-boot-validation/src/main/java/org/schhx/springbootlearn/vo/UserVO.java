package org.schhx.springbootlearn.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.schhx.springbootlearn.group.Insert;
import org.schhx.springbootlearn.group.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Data
@Accessors(chain = true)
public class UserVO {

    @NotBlank(message = "姓名不能为空", groups = {Insert.class})
    private String username;

    @NotNull(message = "年龄不能为空", groups = {Insert.class})
    @Min(value = 18, message = "年龄不能小于18", groups = {Insert.class, Update.class, Default.class})
    private Integer age;
}
