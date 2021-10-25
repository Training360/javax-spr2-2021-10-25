package empapp;

import org.hibernate.envers.RevisionListener;

public class StubUsernameListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        EmployeeRevisionEntity revision = (EmployeeRevisionEntity) o;
        revision.setUsername("user1234");
        revision.setIpAddress("123.123.123.123");
    }
}
