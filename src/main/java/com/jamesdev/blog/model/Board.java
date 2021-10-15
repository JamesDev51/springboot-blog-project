package com.jamesdev.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length=100)
    private String title;

    @Lob
    private String content;

    @ColumnDefault("0")
    private long count;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createdDate;

    @OneToMany(mappedBy = "board",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Comment> comments;


}
