package services;

import dao.PlainFrameDAO;


import models.DigitalFrame;
import models.PlainFrame;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class PlainFrameService extends SessionUtil implements PlainFrameDAO {
    @Override
    public void add(PlainFrame plainFrame) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.save(plainFrame);

        closeTransactionSession();
    }

    @Override
    public void getAll() throws SQLException {
        openTransactionSession();

        String sql = "Select * FROM plainframe";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(PlainFrame.class);
        List<PlainFrame> plainFrameList = query.list();

        //close session with a transaction
        closeTransactionSession();


        for (var l : plainFrameList) {
            StringBuilder sb = new StringBuilder();
            sb.append("PlainFrame:[ id: ").append(l.getId()).append(";")
                    .append(" name: ").append(l.getPhotoFrame().getId()).append(";")
                    .append(" price: ").append(l.getPhotoFrame().getName()).append(";")
                    .append(" color ").append(l.getPhotoFrame().getColor()).append(";")
                    .append(" type: ").append(l.getPhotoFrame().getType()).append(";")
                    .append(" material: ").append(l.getMaterial()).append(";")
                    .append(" width: ").append(l.getWidth()).append(";")
                    .append(" material insert: ").append(l.getMaterial_insert()).append("]");
//                        .append(System.lineSeparator());
            System.out.println(sb);
        }
    }

    @Override
    public PlainFrame getByIdPhotoFrame(int photoframe_id) throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM plainframe WHERE photoframe_id = :photoframe_id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(PlainFrame.class);
        query.setParameter("photoframe_id", photoframe_id);

        PlainFrame plainFrame = (PlainFrame) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return plainFrame;
    }


    @Override
    public void update(PlainFrame plainFrame) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.update(plainFrame);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(PlainFrame plainFrame) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.remove(plainFrame);

        //close session with a transaction
        closeTransactionSession();
    }
}
