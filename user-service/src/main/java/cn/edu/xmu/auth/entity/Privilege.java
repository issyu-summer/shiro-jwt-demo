package cn.edu.xmu.auth.entity;

import cn.edu.xmu.auth.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author summer
 * @since 2021-02-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Privilege extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String privilegeName;


}
