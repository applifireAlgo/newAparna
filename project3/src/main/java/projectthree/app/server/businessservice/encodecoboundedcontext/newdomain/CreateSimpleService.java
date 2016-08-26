package projectthree.app.server.businessservice.encodecoboundedcontext.newdomain;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projectthree.app.shared.encodecoboundedcontext.QoneRM;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "CreateSimpleService", complexity = Complexity.HIGH)
public interface CreateSimpleService {

    public List<QoneRM> createSimpleService() throws Exception;
}
