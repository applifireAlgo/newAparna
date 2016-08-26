package projectthree.app.server.repository.encodecoboundedcontext.newdomain;
import projectthree.app.server.repository.core.SearchInterface;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "9", comments = "Repository for Answer Transaction table", complexity = Complexity.MEDIUM)
public interface AnswerRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String ansId) throws Exception;
}
