package by.deniskruglik.soapwebservice.dao;

import by.deniskruglik.soapwebservice.service.models.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDAO extends AbstractDAO<Post> {
    public PostDAO() {
        super(Post.class);
    }
}
