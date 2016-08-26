package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "Service for UserAccessDomain Master table Entity", complexity = Complexity.LOW)
public abstract class UserAccessDomainService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(UserAccessDomain entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<UserAccessDomain> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(UserAccessDomain entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<UserAccessDomain> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
