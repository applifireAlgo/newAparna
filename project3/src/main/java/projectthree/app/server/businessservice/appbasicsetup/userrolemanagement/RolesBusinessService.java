package projectthree.app.server.businessservice.appbasicsetup.userrolemanagement;
import projectthree.app.shared.appbasicsetup.userrolemanagement.Roles;
import java.util.List;

public interface RolesBusinessService {

    void update(Roles entity) throws Exception;

    void update(List<Roles> entity) throws Exception;
}
