package dao;

import models.PhotoFrame;

import java.sql.SQLException;
import java.util.List;

public interface PhotoFrameDAO {
    void add(PhotoFrame photoFrame) throws SQLException;

    //read
    void getAll() throws SQLException;


    PhotoFrame getById(int id) throws SQLException;

    //update
    void update(PhotoFrame photoFrame) throws SQLException;

    //delete
    void remove(PhotoFrame photoFrame) throws SQLException;
}
