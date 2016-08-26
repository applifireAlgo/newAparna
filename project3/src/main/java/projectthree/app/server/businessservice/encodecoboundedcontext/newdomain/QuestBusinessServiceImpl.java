package projectthree.app.server.businessservice.encodecoboundedcontext.newdomain;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projectthree.app.server.repository.encodecoboundedcontext.newdomain.QuestRepository;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Quest;
import java.util.List;

@Component
public class QuestBusinessServiceImpl implements QuestBusinessService {

    @Autowired
    private QuestRepository questRepository;

    /**
     * Update the <Quest> object
     * @Params Object of Quest
     * @throws java.lang.Exception
     */
    @Override
    public void update(Quest entity) throws Exception {
        if (entity.isHardDelete()) {
            questRepository.delete(entity.getQueId());
        } else {
            questRepository.deleteAnswer(entity.getDeletedAnswerList());
            questRepository.update(entity);
        }
    }

    /**
     * Update the list of <Quest> object
     * @Params List of Quest Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<Quest> entity) throws Exception {
        for (Quest _quest : entity) {
            if (_quest.isHardDelete()) {
                questRepository.delete(_quest.getQueId());
            } else {
                questRepository.deleteAnswer(_quest.getDeletedAnswerList());
                questRepository.update(_quest);
            }
        }
    }
}
