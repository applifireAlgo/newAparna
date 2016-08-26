package projectthree.app.server.service.appbasicsetup.userrolemanagement;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projectthree.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "Service for AppMenus Master table Entity", complexity = Complexity.LOW)
public abstract class AppMenusService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(AppMenus entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<AppMenus> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(AppMenus entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<AppMenus> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
