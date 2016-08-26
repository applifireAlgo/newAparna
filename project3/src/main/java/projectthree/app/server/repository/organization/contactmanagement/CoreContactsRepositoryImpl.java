package projectthree.app.server.repository.organization.contactmanagement;
import org.springframework.stereotype.Repository;
import projectthree.app.server.repository.core.SearchInterfaceImpl;
import projectthree.app.shared.organization.contactmanagement.CoreContacts;
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
import projectthree.app.shared.organization.locationmanagement.Address;
import projectthree.app.shared.organization.contactmanagement.CommunicationData;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "Repository for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public class CoreContactsRepositoryImpl extends SearchInterfaceImpl implements CoreContactsRepository<CoreContacts> {

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
    public List<CoreContacts> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<CoreContacts> query = emanager.createNamedQuery("CoreContacts.findAll").getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <CoreContacts> object.
     * @return CoreContacts
     * @Params Object of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public CoreContacts save(CoreContacts entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        java.util.List<projectthree.app.shared.organization.locationmanagement.Address> address = new java.util.ArrayList<projectthree.app.shared.organization.locationmanagement.Address>();
        for (java.util.Iterator iterator = entity.getAddress().iterator(); iterator.hasNext(); ) {
            projectthree.app.shared.organization.locationmanagement.Address childEntity = (projectthree.app.shared.organization.locationmanagement.Address) iterator.next();
            if (childEntity.getPrimaryKey() != null) {
                projectthree.app.shared.organization.locationmanagement.Address ans = emanager.find(Address.class, childEntity.getPrimaryKey());
                address.add(ans);
            } else {
                address.add(childEntity);
            }
        }
        java.util.List<projectthree.app.shared.organization.contactmanagement.CommunicationData> communicationdata = new java.util.ArrayList<projectthree.app.shared.organization.contactmanagement.CommunicationData>();
        for (java.util.Iterator iterator = entity.getCommunicationData().iterator(); iterator.hasNext(); ) {
            projectthree.app.shared.organization.contactmanagement.CommunicationData childEntity = (projectthree.app.shared.organization.contactmanagement.CommunicationData) iterator.next();
            if (childEntity.getPrimaryKey() != null) {
                projectthree.app.shared.organization.contactmanagement.CommunicationData ans = emanager.find(CommunicationData.class, childEntity.getPrimaryKey());
                communicationdata.add(ans);
            } else {
                communicationdata.add(childEntity);
            }
        }
        entity.setAddress(address);
        entity.setCommunicationData(communicationdata);
        emanager.persist(entity);
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <CoreContacts> object.
     * @return java.util.List<CoreContacts>
     * @Params list of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<CoreContacts> save(List<CoreContacts> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            CoreContacts obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <CoreContacts> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        CoreContacts object = emanager.find(projectthree.app.shared.organization.contactmanagement.CoreContacts.class, id);
        emanager.remove(object);
        Log.out.println("ORGCM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteAddress(List<Address> address) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (projectthree.app.shared.organization.locationmanagement.Address _address : address) {
            projectthree.app.shared.organization.locationmanagement.Address s = emanager.find(projectthree.app.shared.organization.locationmanagement.Address.class, _address.getAddressId());
            emanager.remove(s);
        }
    }

    @Override
    @Transactional
    public void deleteCommunicationData(List<CommunicationData> communicationdata) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (projectthree.app.shared.organization.contactmanagement.CommunicationData _communicationdata : communicationdata) {
            projectthree.app.shared.organization.contactmanagement.CommunicationData s = emanager.find(projectthree.app.shared.organization.contactmanagement.CommunicationData.class, _communicationdata.getCommDataId());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <CoreContacts> object.
     * @Params Object of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(CoreContacts entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <CoreContacts> object.
     * @Params list of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<CoreContacts> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            CoreContacts obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <titleId>
     * @return List<CoreContacts>
     * @Params titleId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByTitleId(String titleId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByTitleId");
        query.setParameter("titleId", titleId);
        java.util.List<projectthree.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByTitleId", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <nativeLanguageCode>
     * @return List<CoreContacts>
     * @Params nativeLanguageCode of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByNativeLanguageCode(String nativeLanguageCode) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByNativeLanguageCode");
        query.setParameter("nativeLanguageCode", nativeLanguageCode);
        java.util.List<projectthree.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByNativeLanguageCode", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <genderId>
     * @return List<CoreContacts>
     * @Params genderId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByGenderId(String genderId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByGenderId");
        query.setParameter("genderId", genderId);
        java.util.List<projectthree.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByGenderId", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <timeZoneId>
     * @return List<CoreContacts>
     * @Params timeZoneId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByTimeZoneId(String timeZoneId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByTimeZoneId");
        query.setParameter("timeZoneId", timeZoneId);
        java.util.List<projectthree.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByTimeZoneId", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return CoreContacts object by filtering on refernce key <contactId>
     * @return CoreContacts
     * @Params contactId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public CoreContacts findById(String contactId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findById");
        query.setParameter("contactId", contactId);
        CoreContacts listOfCoreContacts = (CoreContacts) query.getSingleResult();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findById", "Total Records Fetched = " + listOfCoreContacts);
        return listOfCoreContacts;
    }
}
