package projectthree.app.server.businessservice.encodecoboundedcontext.newdomain;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projectthree.app.server.businessservice.encodecoboundedcontext.TestqoneBzService;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.lang.Override;
import java.util.List;
import projectthree.app.shared.encodecoboundedcontext.QoneRM;

@Component
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "CreateSimpleServiceImpl", complexity = Complexity.HIGH)
public class CreateSimpleServiceImpl implements CreateSimpleService {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private TestqoneBzService testqoneBzService;

    @Override
    public List<QoneRM> createSimpleService() throws Exception {
        java.util.List<projectthree.app.shared.encodecoboundedcontext.QoneRM> qoneRMList = testqoneBzService.executeQueryQone();
        return qoneRMList;
    }
}
