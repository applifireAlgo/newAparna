package projectthree.app.server.service.organization.locationmanagement;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projectthree.app.shared.organization.locationmanagement.State;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "Service for State Master table Entity", complexity = Complexity.LOW)
public abstract class StateService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(State entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<State> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(State entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<State> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception;

    abstract HttpEntity<ResponseBean> findByCountryId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
