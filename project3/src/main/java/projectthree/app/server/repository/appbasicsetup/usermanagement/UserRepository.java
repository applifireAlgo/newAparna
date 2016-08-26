package projectthree.app.server.repository.appbasicsetup.usermanagement;
import com.spartan.server.interfaces.UserRepositoryInterface;
import projectthree.app.server.repository.core.SearchInterface;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projectthree.app.shared.appbasicsetup.usermanagement.PassRecovery;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "Repository for User Transaction table", complexity = Complexity.MEDIUM)
public interface UserRepository<T> extends SearchInterface, UserRepositoryInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    public void deletePassRecovery(List<PassRecovery> passrecovery);

    void update(T entity) throws Exception;

    List<T> findByUserAccessLevelId(String userAccessLevelId) throws Exception;

    List<T> findByUserAccessDomainId(String userAccessDomainId) throws Exception;

    T findById(String userId) throws Exception;
}
