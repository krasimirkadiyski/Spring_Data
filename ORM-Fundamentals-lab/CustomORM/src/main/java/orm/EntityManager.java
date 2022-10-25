package orm;

import orm.annotataions.Column;
import orm.annotataions.Entity;
import orm.annotataions.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFields(entity);
        String valueList = this.getInputValues(entity);
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();
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
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(table);

        String sql =
                String.format("SELECT * FROM %s %s LIMIT 1", tableName, where == null ? "" : "WHERE " + where);


        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();
        if (!resultSet.next()){
            return  null;
        }
        return this.fillEntity(table, resultSet);
    }

    private E fillEntity(Class<E> table, ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        E entity = table.getDeclaredConstructor().newInstance();

//        for (Field declaredField : table.getDeclaredFields()) {
//            if (declaredField.isAnnotationPresent(Column.class)){
//                this.fillData(entity,declaredField, resultSet);
//            }else if (declaredField.isAnnotationPresent(Id.class)){
//                this.fillData(entity, declaredField,resultSet);
//            }
//        }


        return entity;

    }

    private String getInputValues(E entity) throws IllegalAccessException {
        List<String> result = new ArrayList<>();
        for (Field declaredField : entity.getClass().getDeclaredFields()) {
            if (declaredField.getAnnotation(Column.class) == null) {
                continue;
            }
            declaredField.setAccessible(true);
            Object value = declaredField.get(entity);
            result.add("\"" + value.toString() + "\"");
        }

        return String.join(",", result);
    }

    private String getDBFields(E entity) {
        return Arrays.stream(entity
                        .getClass()
                        .getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) != null)
                .map(f -> f.getAnnotation(Column.class).name()).collect(Collectors.joining(","));
    }

    private String getTableName(Class<?> clazz) {
        Entity annotation = clazz.getAnnotation(Entity.class);
        if (annotation == null) {
            throw new ORMException("Provided class does not have entity annotation");
        }
        return annotation.name();
    }
}
