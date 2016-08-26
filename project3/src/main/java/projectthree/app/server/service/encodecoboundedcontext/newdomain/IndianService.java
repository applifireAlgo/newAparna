package projectthree.app.server.service.encodecoboundedcontext.newdomain;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Indian;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Service for Indian Master table Entity", complexity = Complexity.LOW)
public abstract class IndianService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(Indian entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Indian> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Indian entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Indian> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByFromState(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
