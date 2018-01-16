package org.schhx.springbootlearn.entity.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private String id;

    private String cellphone;

    private String password;

}