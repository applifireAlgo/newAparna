package projectthree.app.server.businessservice.encodecoboundedcontext;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projectthree.app.shared.encodecoboundedcontext.QoneRM;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "TestqoneBzService", complexity = Complexity.HIGH)
public interface TestqoneBzService {

    public List<QoneRM> executeQueryQone() throws Exception;
}
