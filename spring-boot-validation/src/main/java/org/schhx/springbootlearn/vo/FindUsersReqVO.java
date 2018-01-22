package org.schhx.springbootlearn.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class FindUsersReqVO {

    @NotBlank(message = "姓名不能为空")
    private String username;
}
