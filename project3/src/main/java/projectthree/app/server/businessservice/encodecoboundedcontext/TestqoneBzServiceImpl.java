package projectthree.app.server.businessservice.encodecoboundedcontext;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Override;
import java.util.List;
import projectthree.app.shared.encodecoboundedcontext.QoneRM;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "TestqoneBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class TestqoneBzServiceImpl implements TestqoneBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<QoneRM> executeQueryQone() throws Exception {
        java.util.List<projectthree.app.shared.encodecoboundedcontext.QoneRM> listDtoInterface = new java.util.ArrayList<projectthree.app.shared.encodecoboundedcontext.QoneRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "875D7F02-AEB2-4F58-835B-C1485616BE0F");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("projectthree.app.shared.encodecoboundedcontext.QoneRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
