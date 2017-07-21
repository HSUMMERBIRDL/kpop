package com.kp.monitor.basis.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kp.monitor.data.po.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, String> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UserId = new Property(0, String.class, "userId", true, "USER_ID");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property Authority = new Property(2, int.class, "authority", false, "AUTHORITY");
        public final static Property DeptName = new Property(3, String.class, "deptName", false, "DEPT_NAME");
        public final static Property CreateTime = new Property(4, String.class, "createTime", false, "CREATE_TIME");
        public final static Property UserRealName = new Property(5, String.class, "userRealName", false, "USER_REAL_NAME");
        public final static Property Sex = new Property(6, String.class, "sex", false, "SEX");
        public final static Property Phone = new Property(7, String.class, "phone", false, "PHONE");
        public final static Property OfficePhone = new Property(8, String.class, "officePhone", false, "OFFICE_PHONE");
        public final static Property Email = new Property(9, String.class, "email", false, "EMAIL");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"USER_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: userId
                "\"USER_NAME\" TEXT," + // 1: userName
                "\"AUTHORITY\" INTEGER NOT NULL ," + // 2: authority
                "\"DEPT_NAME\" TEXT," + // 3: deptName
                "\"CREATE_TIME\" TEXT," + // 4: createTime
                "\"USER_REAL_NAME\" TEXT," + // 5: userRealName
                "\"SEX\" TEXT," + // 6: sex
                "\"PHONE\" TEXT," + // 7: phone
                "\"OFFICE_PHONE\" TEXT," + // 8: officePhone
                "\"EMAIL\" TEXT);"); // 9: email
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(1, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
        stmt.bindLong(3, entity.getAuthority());
 
        String deptName = entity.getDeptName();
        if (deptName != null) {
            stmt.bindString(4, deptName);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(5, createTime);
        }
 
        String userRealName = entity.getUserRealName();
        if (userRealName != null) {
            stmt.bindString(6, userRealName);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(7, sex);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(8, phone);
        }
 
        String officePhone = entity.getOfficePhone();
        if (officePhone != null) {
            stmt.bindString(9, officePhone);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(10, email);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(1, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
        stmt.bindLong(3, entity.getAuthority());
 
        String deptName = entity.getDeptName();
        if (deptName != null) {
            stmt.bindString(4, deptName);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(5, createTime);
        }
 
        String userRealName = entity.getUserRealName();
        if (userRealName != null) {
            stmt.bindString(6, userRealName);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(7, sex);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(8, phone);
        }
 
        String officePhone = entity.getOfficePhone();
        if (officePhone != null) {
            stmt.bindString(9, officePhone);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(10, email);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // userId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.getInt(offset + 2), // authority
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deptName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // createTime
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userRealName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // sex
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // phone
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // officePhone
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // email
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setUserId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAuthority(cursor.getInt(offset + 2));
        entity.setDeptName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCreateTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserRealName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSex(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPhone(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setOfficePhone(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setEmail(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final String updateKeyAfterInsert(User entity, long rowId) {
        return entity.getUserId();
    }
    
    @Override
    public String getKey(User entity) {
        if(entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getUserId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
