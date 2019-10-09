package by.deniskruglik.soapwebservice.dao;

import by.deniskruglik.soapwebservice.service.models.AbstractModel;
import by.deniskruglik.soapwebservice.service.models.Model;

import java.util.List;

public interface DAO<T extends AbstractModel> {
    T create(T obj);

    void delete(T obj);

    T getById(Long id);

    void update(T obj);

    List<T> getAll();
}
