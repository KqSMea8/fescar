/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.alibaba.fescar.tm.api;

import com.alibaba.fescar.core.exception.TransactionException;

/**
 * Template of executing business logic with a global transaction.
 */
public class TransactionalTemplate {

    /**
     * Execute object.
     *
     * @param business the business
     * @return the object
     * @throws TransactionalExecutor.ExecutionException the execution exception
     */
    public Object execute(TransactionalExecutor business) throws TransactionalExecutor.ExecutionException {

        // 1. get or create a transaction，开启事物
        //本地线程变量中获取全局事物，不存在则创建一个新的
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();

        // 2. begin transaction,开启事物
        try {
            tx.begin(business.timeout(), business.name());

        } catch (TransactionException txe) {
            throw new TransactionalExecutor.ExecutionException(tx, txe,
                TransactionalExecutor.Code.BeginFailure);

        }

        Object rs = null;
        try {

            // Do Your Business
            //执行业务逻辑
            rs = business.execute();

        } catch (Throwable ex) {

            // 3. any business exception, rollback.
            try {
                //异常回滚
                tx.rollback();

                // 3.1 Successfully rolled back
                //回滚成功
                throw new TransactionalExecutor.ExecutionException(tx, TransactionalExecutor.Code.RollbackDone, ex);

            } catch (TransactionException txe) {
                // 3.2 Failed to rollback
                //回滚失败
                throw new TransactionalExecutor.ExecutionException(tx, txe,
                    TransactionalExecutor.Code.RollbackFailure, ex);

            }

        }

        // 4. everything is fine, commit.
        try {
            //提交
            tx.commit();

        } catch (TransactionException txe) {
            // 4.1 Failed to commit
            // 提交失败
            throw new TransactionalExecutor.ExecutionException(tx, txe,
                TransactionalExecutor.Code.CommitFailure);

        }
        return rs;
    }

}
