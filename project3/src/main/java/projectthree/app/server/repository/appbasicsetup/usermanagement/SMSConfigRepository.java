package projectthree.app.server.repository.appbasicsetup.usermanagement;
import projectthree.app.config.annotation.Complexity;

import projectthree.app.config.annotation.SourceCodeAuthorClass;

import projectthree.app.server.repository.core.SearchInterface;

import java.util.List;

@SourceCodeAuthorClass(createdBy = "", updatedBy = "", versionNumber = "1", comments = "Repository for SMSConfig Master table Entity", complexity = Complexity.LOW)
public interface SMSConfigRepository<T> extends SearchInterface {

	List<T> findAll() throws Exception;

	T save(T entity) throws Exception;

	List<T> save(List<T> entity) throws Exception;

	void delete(String id) throws Exception;

	void update(T entity) throws Exception;

	void update(List<T> entity) throws Exception;

	T findById(String configId) throws Exception;
}
