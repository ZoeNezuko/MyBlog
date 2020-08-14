package com.jie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jie Pu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nav")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Nav {

    /**
     * PK is ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Corresponding to the path in Vue routing
     */
    private String path;

    /**
     * Render side nav bar
     */
    private String name;

    /**
     * Component name corresponding to Vue
     */
    private String component;

    /**
     * Used to render small icons in front of the navigation bar
     */
    private String icon;

    /**
     * Parent node id, used to store the hierarchical relationship of the navigation bar
     */

    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * Transient property for storing children node.
     */
    @Transient //Corresponding attributes that do not exist in the database
    private List<Nav> children;

}
