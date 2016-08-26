package projectthree.app.server.service.aspect.encodecoboundedcontext;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import projectthree.app.server.businessservice.encodecoboundedcontext.newdomain.CreateSimpleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class Sampleaspect {

    @Autowired
    private CreateSimpleService createsimpleservice;

    @After("afterauthenticate()")
    public void afterAuthenticateServiceauthenticate(JoinPoint joinPoint) throws Throwable {
        createsimpleservice.createSimpleService();
    }

    @Pointcut("execution(* projectthree.app.server.service.appbasicsetup.aaa.AuthenticateService.authenticate(..))")
    public void afterauthenticate() {
    }
}
