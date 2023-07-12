package st.qurks.ms.poc.filter.interceptor;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UserFilterLogging
@Interceptor
public class UserFilterInterceptor {

    @AroundInvoke
    Object logMethod(InvocationContext context) throws Exception {
        Logger logger = LoggerFactory.getLogger(context.getTarget().getClass());
        logger.trace("Triggered filter method -> {}", context.getMethod().getName());
        return context.proceed();
    }
}
