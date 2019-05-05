package session;

import org.hibernate.Session;

public class AutoCloseableSession implements AutoCloseable {

    public final Session session;

    public AutoCloseableSession(Session session) {
        this.session = session;
    }

    public Session getDBsession() {
        return session;
    }

    @Override
    public void close() { session.close();}
}
