package com.cars.trip.onlinetrips.factory;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.Car;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.*;

@Component
public class Creator {

    Log log = LogFactory.getLog(Creator.class);

    static Creator instance;

    @PostConstruct
    public void postConstruct() {
        instance = this;
    }

    @Autowired
    private ApplicationContext applicationContext;

    public static void saveMore(Object... entities) {
        instance.saveEntities(entities);
    }

    public static Object save(Object entity) {
        return instance.saveEntity(entity);
    }

    public void saveEntities(Object... entities) {
        for (Object entity : entities) {
            saveEntity(entity);
        }
    }

    public Object saveEntity(Object entity) {
        return saveEntity(entity, false);
    }

    public Object saveEntity(Object entity, boolean deleteOthers) {
        try {
            Map props = PropertyUtils.describe(entity);
            List<Field> allFields = FieldUtils.getAllFieldsList(entity.getClass());
            for (Field field : allFields) {
                try {
                    field.setAccessible(true);
                    Object propValue = FieldUtils.readField(field, entity);
                    boolean id = fieldHasAnnotation(field, Id.class);

                    if(id) propValue = new Random().nextLong();

                    if ((propValue == null || (propValue instanceof String && (((String) propValue).length() == 0))) && !id) {
                        Class<?> fieldClass = field.getType();
                        if (fieldClass.isAssignableFrom(String.class)) {
                            propValue = "Test " + field.getName();
                        } else {
                            if (Date.class.equals(fieldClass)) {
                                propValue = new Date(System.currentTimeMillis());
                            } else if (Long.class.equals(fieldClass)){
                                propValue = 1L;
                            } else if (Set.class.equals(fieldClass)) {
                                propValue = new HashSet<>();
                            } else if (Car.class.equals(fieldClass)){
                                continue;
                            }else{
                                propValue = fieldClass.newInstance();

                            }
                        }
                        PropertyUtils.setProperty(entity, field.getName(), propValue);

                    }
                    saveChildEntity(propValue);
                } catch (IllegalAccessException e) {
                    log.info("Skipping " + field.getName() + ", as it is probably private");
                }
            }

            for (Object propName : props.keySet()) {
                Object propValue = props.get(propName);
                saveChildEntity(propValue);
            }

            JpaRepository dao = getDao(entity);
            if (deleteOthers) {
                dao.deleteAllInBatch();
            }
            dao.save(entity);
        } catch (Exception e) {
            throw new IllegalStateException("Problem", e);
        }
        return entity;

    }

    private boolean fieldHasAnnotation(Field field, Class annotationClass) {
        return field.getDeclaredAnnotationsByType(annotationClass).length > 0;
    }

    private JpaRepository getDao(Object entity) {
        String repoClassName = entity.getClass().getSimpleName();
        String repositoryBeanName = repoClassName.substring(0, 1).toLowerCase() + repoClassName.substring(1) + "Repository";
        return (JpaRepository) applicationContext.getBean(repositoryBeanName);
    }


    private void saveChildEntity(Object propValue) {
        if (propValue != null) {
            Class<?> valueClass = propValue.getClass();
            final boolean isEntity = isEntity(valueClass);
            if ((isEntity) && !valueClass.equals(User.class)) {

                saveEntity(propValue);
                String className = propValue.getClass().getSimpleName();
                String daoName = className.substring(0, 1).toLowerCase() + className.substring(1) + "Repository";

                JpaRepository jpaRepository = applicationContext.getBeansOfType(JpaRepository.class).get(daoName);
                jpaRepository.save(propValue);
            }
        }
    }

    private boolean isEntity(Class<?> valueClass) {
        return valueClass.getDeclaredAnnotationsByType(Entity.class).length > 0;
    }

}