/**
 * Copyright 2010
 */
package nl.surfnet.coin.mock;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.easymock.EasyMock;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.util.ReflectionUtils;


/**
 * All dependencies that are not resolved (because they are not declared in the Spring context) are
 * resolved by injecting EasyMock instances
 * 
 * @author oharsta
 * 
 */
public class EasyMockAutowireResolver extends AutowiredAnnotationBeanPostProcessor {

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        try {
            return super.postProcessPropertyValues(pvs, pds, bean, beanName);
        } catch (Throwable ex) {
            // this means that we have to construct a nice EasyMock and inject it
            Throwable t = ex;
            while (t.getCause() != null) {
                Throwable cause = t.getCause();
                if (cause instanceof NoSuchBeanDefinitionException) {
                    NoSuchBeanDefinitionException root = (NoSuchBeanDefinitionException) cause;
                    inject(bean, root.getBeanType());
                    break;
                } else {
                    t = cause;
                }
            }
            return pvs;
        }
    }

    

    /*
     * Find the field which has the type of the fieldClass and set the Field with an EasyMock
     */
    @SuppressWarnings("unchecked")
    private void inject(Object bean, Class fieldClass) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(fieldClass)) {
                ReflectionUtils.makeAccessible(field);
                try {
                    field.set(bean, EasyMock.createNiceMock(fieldClass));
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
