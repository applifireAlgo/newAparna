package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.locationmanagement.Address;

@SourceCodeAuthorClass(createdBy = "shweta.zagade@algorhythm.co.in", updatedBy = "shweta.zagade@algorhythm.co.in", versionNumber = "3", comments = "Repository for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public interface CoreContactsRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    public void deleteCommunicationData(List<CommunicationData> communicationdata);

    public void deleteAddress(List<Address> address);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByTitleId(String titleId) throws Exception;

    List<T> findByNativeLanguageCode(String nativeLanguageCode) throws Exception;

    List<T> findByGenderId(String genderId) throws Exception;

    List<T> findByTimeZoneId(String timeZoneId) throws Exception;

    T findById(String contactId) throws Exception;
}
