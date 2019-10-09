package by.deniskruglik.soapwebservice.service.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "Like")
@Table(name = "likes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Like extends AbstractModel {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Like() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
