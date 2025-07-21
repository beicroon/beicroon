package com.beicroon.starter.dao.manager;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Component
public class DBManager {

    @Resource
    private TransactionTemplate transactionTemplate;

    public boolean execute(Runnable runner) {
        this.transactionTemplate.executeWithoutResult(status -> {
            try {
                runner.run();
            } catch (Exception ex) {
                status.setRollbackOnly();

                throw ex;
            }
        });

        return true;
    }

    public boolean execute(Runnable runner, Runnable then) {
        this.transactionTemplate.executeWithoutResult(status -> {
            try {
                runner.run();
            } catch (Exception ex) {
                status.setRollbackOnly();

                throw ex;
            }
        });

        then.run();

        return true;
    }

}
