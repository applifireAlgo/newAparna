package projectthree.app.server.repository.encodecoboundedcontext.newdomain;
import org.springframework.stereotype.Repository;
import projectthree.app.server.repository.core.SearchInterfaceImpl;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Indian;
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
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Repository for Indian Master table Entity", complexity = Complexity.LOW)
public class IndianRepositoryImpl extends SearchInterfaceImpl implements IndianRepository<Indian> {

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
    public List<Indian> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Indian> query = emanager.createNamedQuery("Indian.findAll").getResultList();
        Log.out.println("JKUUJ324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Indian> object.
     * @return Indian
     * @Params Object of Indian
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Indian save(Indian entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("JKUUJ322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Indian> object.
     * @return java.util.List<Indian>
     * @Params list of Indian
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Indian> save(List<Indian> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Indian obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("JKUUJ322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Indian> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Indian object = emanager.find(projectthree.app.shared.encodecoboundedcontext.newdomain.Indian.class, id);
        emanager.remove(object);
        Log.out.println("JKUUJ328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Indian> object.
     * @Params Object of Indian
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Indian entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("JKUUJ321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Indian> object.
     * @Params list of Indian
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Indian> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Indian obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("JKUUJ321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of Indian objects by filtering on refernce key <fromState>
     * @return List<Indian>
     * @Params fromState of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Indian> findByFromState(String fromState) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Indian.findByFromState");
        query.setParameter("fromState", fromState);
        java.util.List<projectthree.app.shared.encodecoboundedcontext.newdomain.Indian> listOfIndian = query.getResultList();
        Log.out.println("JKUUJ324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "findByFromState", "Total Records Fetched = " + listOfIndian.size());
        return listOfIndian;
    }

    /**
     * Return Indian object by filtering on refernce key <foodId>
     * @return Indian
     * @Params foodId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Indian findById(String foodId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Indian.findById");
        query.setParameter("foodId", foodId);
        Indian listOfIndian = (Indian) query.getSingleResult();
        Log.out.println("JKUUJ324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianRepositoryImpl", "findById", "Total Records Fetched = " + listOfIndian);
        return listOfIndian;
    }
}
