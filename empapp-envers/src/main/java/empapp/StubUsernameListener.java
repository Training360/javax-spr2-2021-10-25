package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class StubUsernameListener implements RevisionListener {

    private RequestDetails requestDetails;

    @Override
    public void newRevision(Object o) {
        // Java 17-ben
        //        if (o instanceof EmployeeRevisionEntity revision) {
//            revision.setUsername("user1234");
//            log.info("StubUsernameListener {} {}", requestDetails.getIp(), requestDetails.getPostman());
//            revision.setIpAddress(requestDetails.getIp());
//            revision.setPostmanToken(requestDetails.getPostman());
//
//        }

        EmployeeRevisionEntity revision = (EmployeeRevisionEntity) o;
        revision.setUsername("user1234");
        log.info("StubUsernameListener {} {}", requestDetails.getIp(), requestDetails.getPostman());
        revision.setIpAddress(requestDetails.getIp());
        revision.setPostmanToken(requestDetails.getPostman());
    }
}
