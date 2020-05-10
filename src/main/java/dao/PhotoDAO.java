package dao;

import models.DigitalFrame;
import models.Photo;

import java.sql.SQLException;
import java.util.Set;

public interface PhotoDAO {
    void add(Photo photo) throws SQLException;

    void getAll() throws SQLException;

    Photo getByIdPhoto(int id) throws SQLException;

    void  getByName(String name) throws SQLException;
    //delete
    void remove(Photo photo) throws SQLException;
}
