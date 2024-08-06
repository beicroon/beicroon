package com.beicroon.starter.dao.template;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Component
public class DBTemplate {

    @Resource
    private TransactionTemplate transactionTemplate;

    public void execute(Runnable action) {
        this.transactionTemplate.executeWithoutResult(status -> {
            try {
                action.run();
            } catch (Exception ex) {
                status.setRollbackOnly();

                throw ex;
            }
        });
    }

    public void execute(Runnable action, Runnable then) {
        this.transactionTemplate.executeWithoutResult(status -> {
            try {
                action.run();
            } catch (Exception ex) {
                status.setRollbackOnly();

                throw ex;
            }
        });

        then.run();
    }

}
