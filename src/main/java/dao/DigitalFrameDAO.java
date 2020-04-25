package dao;

import models.DigitalFrame;

import models.PhotoFrame;

import java.sql.SQLException;
import java.util.List;

public interface DigitalFrameDAO {
    void add(DigitalFrame digitalFrame) throws SQLException;

    //read
    void getAll() throws SQLException;

    DigitalFrame getByIdPhotoFrame(int id) throws SQLException;

    //update
    void update(DigitalFrame digitalFrame) throws SQLException;

    //delete
    void remove(DigitalFrame digitalFrame) throws SQLException;
}
