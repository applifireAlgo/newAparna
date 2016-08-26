package projectthree.app.server.repository.encodecoboundedcontext.newdomain;
import org.springframework.stereotype.Repository;
import projectthree.app.server.repository.core.SearchInterfaceImpl;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Answer;
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
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "9", comments = "Repository for Answer Transaction table", complexity = Complexity.MEDIUM)
public class AnswerRepositoryImpl extends SearchInterfaceImpl implements AnswerRepository<Answer> {

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
    public List<Answer> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Answer> query = emanager.createNamedQuery("Answer.findAll").getResultList();
        Log.out.println("JKUUJ324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AnswerRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Answer> object.
     * @return Answer
     * @Params Object of Answer
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Answer save(Answer entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("JKUUJ322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AnswerRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Answer> object.
     * @return java.util.List<Answer>
     * @Params list of Answer
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Answer> save(List<Answer> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Answer obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("JKUUJ322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AnswerRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Answer> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Answer object = emanager.find(projectthree.app.shared.encodecoboundedcontext.newdomain.Answer.class, id);
        emanager.remove(object);
        Log.out.println("JKUUJ328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AnswerRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Answer> object.
     * @Params Object of Answer
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Answer entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("JKUUJ321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AnswerRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Answer> object.
     * @Params list of Answer
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Answer> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Answer obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("JKUUJ321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AnswerRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Answer object by filtering on refernce key <ansId>
     * @return Answer
     * @Params ansId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Answer findById(String ansId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Answer.findById");
        query.setParameter("ansId", ansId);
        Answer listOfAnswer = (Answer) query.getSingleResult();
        Log.out.println("JKUUJ324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AnswerRepositoryImpl", "findById", "Total Records Fetched = " + listOfAnswer);
        return listOfAnswer;
    }
}
