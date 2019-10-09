package by.deniskruglik.soapwebservice.dao;

import by.deniskruglik.soapwebservice.service.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
