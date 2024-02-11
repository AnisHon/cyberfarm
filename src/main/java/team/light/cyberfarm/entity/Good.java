package team.light.cyberfarm.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Good {



    @Setter(AccessLevel.NONE)
    private Integer id;
    private String name;
    private Integer category;
    private Integer price; //cent
    private String detailText;
    private String producer;
    private String comment;
    private String picture01;
    private String picture02;

}
