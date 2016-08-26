package projectthree.app.server.service.encodecoboundedcontext.newdomain;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import projectthree.app.server.repository.encodecoboundedcontext.newdomain.IndianRepository;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Indian;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Service for Indian Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/Indian")
public class IndianServiceImpl extends IndianService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private IndianRepository<Indian> indianrepo;

    /**
     * Retrieve list of  <Indian> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<Indian> lstindian = indianrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("JKUUJ124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        responseBean.add("data", lstindian);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "findAll", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <Indian> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Indian
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Indian entity) throws Exception {
        indianrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("JKUUJ122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "save", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <Indian> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Indian>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Indian> entity, @RequestHeader("isArray") boolean request) throws Exception {
        indianrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("JKUUJ122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "save", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <Indian> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        indianrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("JKUUJ128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "delete", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <Indian> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Indian
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Indian entity) throws Exception {
        indianrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("JKUUJ123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "update", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <Indian> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Indian>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Indian> entity, @RequestHeader("isArray") boolean request) throws Exception {
        indianrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("JKUUJ123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "update", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Indian> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByFromState", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByFromState(@RequestBody FindByBean findByBean) throws Exception {
        List<Indian> lstindian = indianrepo.findByFromState((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("JKUUJ124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        responseBean.add("data", lstindian);
        Log.out.println("JKUUJ124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "findByFromState", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Indian> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        Indian lstindian = indianrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("JKUUJ124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Indian"));
        responseBean.add("data", lstindian);
        Log.out.println("JKUUJ124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "IndianServiceImpl", "findById", "Indian");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
