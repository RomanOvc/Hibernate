package dao;

import models.DigitalFrame;

import models.PlainFrame;

import java.sql.SQLException;
import java.util.List;

public interface PlainFrameDAO {
    void add(PlainFrame plainFrame) throws SQLException;

    //read
    void getAll() throws SQLException;

    PlainFrame getByIdPhotoFrame(int id) throws SQLException;

    //update
    void update(PlainFrame plainFrame) throws SQLException;

    //delete
    void remove(PlainFrame plainFrame) throws SQLException;

}
