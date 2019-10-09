package by.deniskruglik.soapwebservice.service.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Thread")
@Table(name = "threads")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Thread extends AbstractModel {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToMany(mappedBy = "threads")
    private List<User> participants = new ArrayList<>();

    @OneToMany(
            mappedBy = "thread",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Message> messages = new ArrayList<>();

    public Thread() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void addParticipant(User user) {
        participants.add(user);
    }

    public void includeParticipant(User user) {
        addParticipant(user);
        user.addThread(this);
    }

    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public void excludeParticipant(User user) {
        removeParticipant(user);
        user.removeThread(this);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setThread(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setThread(null);
    }
}
