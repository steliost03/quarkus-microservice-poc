package st.qurks.ms.poc.logging;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUserMSLogging
@Interceptor
public class DefaultUserMSInterceptor {
    @AroundInvoke
    Object logMethod(InvocationContext context) throws Exception {
        Logger logger = LoggerFactory.getLogger(context.getTarget().getClass());
        logger.debug("Initiated method: {}",context.getMethod().getName());
        return context.proceed();
    }
}
