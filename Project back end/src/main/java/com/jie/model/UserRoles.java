package com.jie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Jie Pu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_roles")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserRoles {

    /**
     * PK is ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * User id
     */
    private Integer uid;

    /**
     * Role id
     */
    private Integer rid;

}
