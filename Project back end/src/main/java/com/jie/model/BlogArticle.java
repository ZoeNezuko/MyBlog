package com.jie.model;

import java.sql.Date;
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
@Table(name = "blog_article")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class BlogArticle {

    /**
     * PK is ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //@NotNull(message = "id null")
    private Integer id;

    /**
     * Article Title
     */
    private String title;

    /**
     * Article author
     */
    private String author;

    /**
     * Article abstract
     */
    private String summary;

    /**
     * Article date
     */
    private Date date;

    /**
     * HTML file source
     */
    private String htmlContent;

    /**
     * Markdown File source
     */
    private String mdContent;

    /**
     * FK User id
     */
    private Integer uid;

}
