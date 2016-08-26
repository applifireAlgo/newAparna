package projectthree.app.server.repository.encodecoboundedcontext.newdomain;
import org.springframework.stereotype.Repository;
import projectthree.app.server.repository.core.SearchInterfaceImpl;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Quest;
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
import projectthree.app.shared.encodecoboundedcontext.newdomain.Answer;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "7", comments = "Repository for Quest Transaction table", complexity = Complexity.MEDIUM)
public class QuestRepositoryImpl extends SearchInterfaceImpl implements QuestRepository<Quest> {

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
    public List<Quest> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Quest> query = emanager.createNamedQuery("Quest.findAll").getResultList();
        Log.out.println("JKUUJ324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Quest> object.
     * @return Quest
     * @Params Object of Quest
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Quest save(Quest entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        java.util.List<projectthree.app.shared.encodecoboundedcontext.newdomain.Answer> answer = new java.util.ArrayList<projectthree.app.shared.encodecoboundedcontext.newdomain.Answer>();
        for (java.util.Iterator iterator = entity.getAnswer().iterator(); iterator.hasNext(); ) {
            projectthree.app.shared.encodecoboundedcontext.newdomain.Answer childEntity = (projectthree.app.shared.encodecoboundedcontext.newdomain.Answer) iterator.next();
            if (childEntity.getPrimaryKey() != null) {
                projectthree.app.shared.encodecoboundedcontext.newdomain.Answer ans = emanager.find(Answer.class, childEntity.getPrimaryKey());
                answer.add(ans);
            } else {
                answer.add(childEntity);
            }
        }
        entity.setAnswer(answer);
        emanager.persist(entity);
        Log.out.println("JKUUJ322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Quest> object.
     * @return java.util.List<Quest>
     * @Params list of Quest
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Quest> save(List<Quest> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Quest obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("JKUUJ322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Quest> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Quest object = emanager.find(projectthree.app.shared.encodecoboundedcontext.newdomain.Quest.class, id);
        emanager.remove(object);
        Log.out.println("JKUUJ328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteAnswer(List<Answer> answer) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (projectthree.app.shared.encodecoboundedcontext.newdomain.Answer _answer : answer) {
            projectthree.app.shared.encodecoboundedcontext.newdomain.Answer s = emanager.find(projectthree.app.shared.encodecoboundedcontext.newdomain.Answer.class, _answer.getAnsId());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <Quest> object.
     * @Params Object of Quest
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Quest entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("JKUUJ321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Quest> object.
     * @Params list of Quest
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Quest> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Quest obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("JKUUJ321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Quest object by filtering on refernce key <queId>
     * @return Quest
     * @Params queId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Quest findById(String queId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Quest.findById");
        query.setParameter("queId", queId);
        Quest listOfQuest = (Quest) query.getSingleResult();
        Log.out.println("JKUUJ324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "QuestRepositoryImpl", "findById", "Total Records Fetched = " + listOfQuest);
        return listOfQuest;
    }
}
