package com.massacre.codigotutoria.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * Created by saurabh on 19/7/17.
 */
public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {
    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        if(sqlEx.getErrorCode()==-12345){
            return new DeadlockLoserDataAccessException(task,sqlEx);
        }
        return null;
    }
}
