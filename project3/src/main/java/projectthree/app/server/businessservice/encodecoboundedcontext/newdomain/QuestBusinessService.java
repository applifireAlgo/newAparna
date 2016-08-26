package projectthree.app.server.businessservice.encodecoboundedcontext.newdomain;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Quest;
import java.util.List;

public interface QuestBusinessService {

    void update(Quest entity) throws Exception;

    void update(List<Quest> entity) throws Exception;
}
