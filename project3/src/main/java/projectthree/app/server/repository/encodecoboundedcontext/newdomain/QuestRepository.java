package projectthree.app.server.repository.encodecoboundedcontext.newdomain;
import projectthree.app.server.repository.core.SearchInterface;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Answer;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "7", comments = "Repository for Quest Transaction table", complexity = Complexity.MEDIUM)
public interface QuestRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    public void deleteAnswer(List<Answer> answer);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String queId) throws Exception;
}
