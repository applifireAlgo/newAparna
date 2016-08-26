package projectthree.app.server.service.encodecoboundedcontext.newdomain;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Quest;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "7", comments = "Service for Quest Transaction table", complexity = Complexity.MEDIUM)
public abstract class QuestService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(Quest entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Quest> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Quest entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Quest> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
