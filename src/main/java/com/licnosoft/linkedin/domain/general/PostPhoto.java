package com.licnosoft.linkedin.domain.general;

import com.licnosoft.linkedin.config.ValidationConstants;
import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.posts.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "post_photos")
public class PostPhoto implements Serializable, PlainEntity<PostPhoto>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = ValidationErrorMessages.TITLE_NOT_BLANK)
    private String title;

    @Column(name = "ordering")
    @NotNull(message = ValidationErrorMessages.ORDERING_NOT_BLANK)
    @Min(value = ValidationConstants.ORDERING_MIN,message = ValidationErrorMessages.ORDERING_SMALLER_THAN_MIN)
    private Integer ordering = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @NotNull(message = ValidationErrorMessages.ANNOUNCEMENT_NOT_BLANK)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id")
    @NotNull(message = ValidationErrorMessages.URL_NOT_BLANK)
    private File file;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "PostPhoto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ordering=" + ordering +
                ", post=" + post +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostPhoto that = (PostPhoto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
