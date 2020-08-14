package com.jie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Jie Pu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Document {

    /**
     * PK is ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Title
     */
    private String title;

    /**
     * Upload Author
     */
    private String author;

    /**
     * Description
     */
    private String description;

    /**
     * Date
     */
    private Date date;

    /**
     * Format
     */

    private String format;

    /**
     * Save document
     */
    private String file;

    /**
     * FK User id
     */
    private Integer uid;

    /**
     * FK Category id
     */
    @ManyToOne // Mapping relations
    @JoinColumn(name = "cid")
    private Category category;

}
