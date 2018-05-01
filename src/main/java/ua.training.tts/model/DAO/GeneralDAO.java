package ua.training.tts.model.DAO;

import java.util.List;
import java.util.Map;

/**
 * General abstract DAO class for Factory pattern implementation for easy and flexible entitie's DAO implementations
 */
public interface GeneralDAO {
    void create(Map<String, String> map);
    Map<String, String> findById(int id);
    List<Map<String, String>> findAll();
    void update(Map<String, String> map);
    void delete(int id);
    void closeConnection();
}