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
@Table(name = "comments")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})

public class Comments {

    /**
     * PK is ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Content
     */
    private String content;

    /**
     * FK Article id
     */
    @ManyToOne
    @JoinColumn(name = "aid")
    private BlogArticle blogArticle;

    /**
     * FK User id
     */
    private Integer uid;

}
