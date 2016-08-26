package projectthree.app.server.repository.appbasicsetup.usermanagement;
import org.springframework.stereotype.Repository;
import projectthree.app.server.repository.core.SearchInterfaceImpl;
import projectthree.app.shared.appbasicsetup.usermanagement.Question;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "Repository for Question Master table Entity", complexity = Complexity.LOW)
public class QuestionRepositoryImpl extends SearchInterfaceImpl implements QuestionRepository<Question> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Question> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Question> query = emanager.createNamedQuery("Question.findAll").getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestionRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Question> object.
     * @return Question
     * @Params Object of Question
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Question save(Question entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestionRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Question> object.
     * @return java.util.List<Question>
     * @Params list of Question
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Question> save(List<Question> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Question obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestionRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Question> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Question object = emanager.find(projectthree.app.shared.appbasicsetup.usermanagement.Question.class, id);
        emanager.remove(object);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestionRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Question> object.
     * @Params Object of Question
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Question entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestionRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Question> object.
     * @Params list of Question
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Question> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Question obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestionRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Question object by filtering on refernce key <questionId>
     * @return Question
     * @Params questionId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Question findById(String questionId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Question.findById");
        query.setParameter("questionId", questionId);
        Question listOfQuestion = (Question) query.getSingleResult();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestionRepositoryImpl", "findById", "Total Records Fetched = " + listOfQuestion);
        return listOfQuestion;
    }
}
