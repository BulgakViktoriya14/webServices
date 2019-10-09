package by.deniskruglik.soapwebservice.service.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractModel {
    @Id
    @Column(name = "id")
    private Long id;
    private String nickname;
    private String passwordHash;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> friends = new ArrayList<>();

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Post> posts = new ArrayList<>();

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Like> likes = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Thread> threads = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> inviters = new ArrayList<>();

    @ManyToMany(mappedBy = "inviters")
    private List<User> invitedUsers = new ArrayList<>();

    public User() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void removeFriend(User user) {
        friends.remove(user);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setAuthor(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setAuthor(null);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setAuthor(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setAuthor(null);
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void addLike(Like like) {
        likes.add(like);
        like.setOwner(this);
    }

    public void removeLike(Like like) {
        likes.remove(like);
        like.setOwner(null);
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public void addThread(Thread thread) {
        threads.add(thread);
    }

    public void joinThread(Thread thread) {
        addThread(thread);
        thread.addParticipant(this);
    }

    public void removeThread(Thread thread) {
        threads.remove(thread);
    }

    public void leaveThread(Thread thread) {
        removeThread(thread);
        thread.removeParticipant(this);
    }
}
