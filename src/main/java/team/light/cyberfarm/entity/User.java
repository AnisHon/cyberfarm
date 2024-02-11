package team.light.cyberfarm.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
public class User {

    @Setter(AccessLevel.NONE)
    private Integer id;
    private String username;
    private String password;
    private String tel;
    private Integer gender;
    private Integer level;

    @Setter(AccessLevel.NONE)
    private Date createDate;
}
