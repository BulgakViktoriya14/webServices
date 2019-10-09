package by.deniskruglik.soapwebservice.service.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Comment")
@Table(name = "comments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends AbstractModel {
    @Id
    @Column(name = "id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @OneToMany(
            mappedBy = "answerTo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> answers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment answerTo;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getAnswers() {
        return answers;
    }

    public void addAnswer(Comment comment) {
        answers.add(comment);
        comment.setAnswerTo(this);
    }

    public void removeAnswer(Comment comment) {
        answers.remove(comment);
        comment.setAnswerTo(null);
    }

    public void setAnswers(List<Comment> answers) {
        this.answers = answers;
    }

    public Comment getAnswerTo() {
        return answerTo;
    }

    public void setAnswerTo(Comment answerTo) {
        this.answerTo = answerTo;
    }
}
