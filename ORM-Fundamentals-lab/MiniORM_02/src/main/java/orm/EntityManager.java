package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E>{
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    // entity is current instance of User first = new User("Second", 2, LocalDate.now());
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idColumn = getId(entity.getClass());
        idColumn.setAccessible(true);
        Object value = idColumn.get(entity);

        if (value == null || (long) value <= 0){
            return  doInsert(entity);
        }

        return  doUpdate(entity, idColumn);

    }

    private boolean doUpdate(E entity, Field primaryKey) {

        String query = String.format("Update % SET");
        return  false;
    }

    private boolean doInsert(E entity) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        String tableFields = getTableFields(entity.getClass());
        String tableValues = getTableValues(entity);
        String query = String.format("INSERT INTO %s (%s) VALUES (%s)",tableName,tableFields,tableValues);
        return this.connection.prepareStatement(query).execute();
    }




    @Override
    public Iterable<E> find(Class<E> table) {
        return null;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table, String where) {
        return null;
    }
    private String getTableName(Class<?> aClass) {
        String tName = aClass.getAnnotation(Entity.class).name();
        return tName;
    }
    private String getTableFields(Class<?> aClass) {
        String tableFields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class) && f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(","));
        return tableFields;
    }
    private String getTableValues(E entity) throws IllegalAccessException {
        List<String> values = new ArrayList<>();
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Id.class)){
                continue;
            }
            declaredField.setAccessible(true);
            String colValue = declaredField.get(entity).toString();
            values.add("\"" + colValue.toString() + "\"");
        }
        return String.join(",",values);
    }

    private Field getId(Class<?> entity){
        Field fieldId = Arrays.stream(entity.getDeclaredFields()).filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
        return fieldId;
    }
}
