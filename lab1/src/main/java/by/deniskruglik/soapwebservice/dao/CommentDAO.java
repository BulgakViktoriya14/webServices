package by.deniskruglik.soapwebservice.dao;

import by.deniskruglik.soapwebservice.service.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDAO extends AbstractDAO<Comment> {
    public CommentDAO() {
        super(Comment.class);
    }
}
