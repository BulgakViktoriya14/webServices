package by.deniskruglik.soapwebservice.dao;

import by.deniskruglik.soapwebservice.service.models.Thread;
import org.springframework.stereotype.Component;

@Component
public class ThreadDAO extends AbstractDAO<Thread> {
    public ThreadDAO() {
        super(Thread.class);
    }
}
