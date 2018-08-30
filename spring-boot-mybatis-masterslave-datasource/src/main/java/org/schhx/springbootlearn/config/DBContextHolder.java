package org.schhx.springbootlearn.config;

public class DBContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getDbType() {
        String db = contextHolder.get();
        return db != null ? db : DataSourceConstant.MASTER;
    }

    public static void setDbType(String dbType) {
        if (!(dbType.equals(DataSourceConstant.MASTER) || dbType.equals(DataSourceConstant.SLAVE))) {
            contextHolder.set(DataSourceConstant.MASTER);
        } else {
            contextHolder.set(dbType);
        }
    }

    public static void clearDbType() {
        contextHolder.set(null);
    }
}
