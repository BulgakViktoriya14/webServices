package by.deniskruglik.soapwebservice.dao;

import by.deniskruglik.soapwebservice.service.models.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeDAO extends AbstractDAO<Like> {
    public LikeDAO() {
        super(Like.class);
    }
}
