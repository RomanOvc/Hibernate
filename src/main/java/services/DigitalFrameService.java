package services;

import dao.DigitalFrameDAO;
import models.DigitalFrame;

import models.PhotoFrame;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class DigitalFrameService extends SessionUtil implements DigitalFrameDAO {
    @Override
    public void add(DigitalFrame digitalFrame) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(digitalFrame);
        closeTransactionSession();
    }

    @Override
    public void getAll() throws SQLException {
        openTransactionSession();

        String sql = "Select * FROM digitalframe";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(DigitalFrame.class);
        List<DigitalFrame> digitalFrameList = query.list();

        //close session with a transaction
        closeTransactionSession();
        for (var l : digitalFrameList) {
            StringBuilder sb = new StringBuilder();
            sb.append("PlainFrame:[ id: ").append(l.getId()).append(";")
                    .append(" name: ").append(l.getPhotoFrame().getId()).append(";")
                    .append(" price: ").append(l.getPhotoFrame().getName()).append(";")
                    .append(" color ").append(l.getPhotoFrame().getColor()).append(";")
                    .append(" type: ").append(l.getPhotoFrame().getType()).append(";")
                    .append(" memory: ").append(l.getMemory()).append(";")
                    .append(" size: ").append(l.getSize()).append(";")
                    .append(" viewing angle: ").append(l.getViewing_angle()).append("]");
//                        .append(System.lineSeparator());
            System.out.println(sb);
        }
    }

    @Override
    public DigitalFrame getByIdPhotoFrame(int photoframe_id) throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM digitalframe WHERE photoframe_id = :photoframe_id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(DigitalFrame.class);
        query.setParameter("photoframe_id", photoframe_id);

        DigitalFrame digitalFrame = (DigitalFrame) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return digitalFrame;
    }

    @Override
    public void update(DigitalFrame digitalFrame) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(digitalFrame);
        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(DigitalFrame digitalFrame) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(digitalFrame);
        //close session with a transaction
        closeTransactionSession();
    }
}
