package projectthree.app.server.service.appbasicsetup.userrolemanagement;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projectthree.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "Service for UserRoleBridge Transaction table", complexity = Complexity.MEDIUM)
public abstract class UserRoleBridgeService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(UserRoleBridge entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<UserRoleBridge> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(UserRoleBridge entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<UserRoleBridge> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByRoleId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByUserId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
