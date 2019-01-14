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

/**
 * Callback for executing business logic in a global transaction.
 */
public interface TransactionalExecutor {

    /**
     * Execute the business logic here.
     *
     * @return What the business logic returns.
     * @throws Throwable Any throwable during executing.
     */
    //执行业务功能逻辑
    Object execute() throws Throwable;

    /**
     * Global transacton timeout in MILLISECONDS.
     *
     * @return timeout in MILLISECONDS.
     */
    //全局事务超时时间
    int timeout();

    /**
     * Given name of the global transaction instance.
     *
     * @return Given name.
     */
    //全局事务名称
    String name();

    /**
     * The enum Code.
     */
    //事务状态
    enum Code {

        /**
         * Begin failure code.
         */
        //启动事务失败
        BeginFailure,

        /**
         * Commit failure code.
         */
        //提交事务失败
        CommitFailure,

        /**
         * Rollback failure code.
         */
        //回滚事务失败
        RollbackFailure,

        /**
         * Rollback done code.
         */
        //回滚事务完成
        RollbackDone
    }

    /**
     * The type Execution exception.
     */
    //事务执行异常
    class ExecutionException extends Exception {

        //全局事务
        private GlobalTransaction transaction;

        //事务状态码
        private Code code;

        //执行事务过程原异常信息
        private Throwable originalException;

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction the transaction
         * @param cause       the cause
         * @param code        the code
         */
        public ExecutionException(GlobalTransaction transaction, Throwable cause, Code code) {
            this(transaction, cause, code, null);
        }

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction       the transaction
         * @param code              the code
         * @param originalException the original exception
         */
        public ExecutionException(GlobalTransaction transaction, Code code, Throwable originalException) {
            this(transaction, null, code, originalException);
        }

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction       the transaction
         * @param cause             the cause
         * @param code              the code
         * @param originalException the original exception
         */
        public ExecutionException(GlobalTransaction transaction, Throwable cause, Code code, Throwable originalException) {
            this(transaction, null, cause, code, originalException);
        }

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction       the transaction
         * @param message           the message
         * @param cause             the cause
         * @param code              the code
         * @param originalException the original exception
         */
        public ExecutionException(GlobalTransaction transaction, String message, Throwable cause, Code code, Throwable originalException) {
            super(message, cause);
            this.transaction = transaction;
            this.code = code;
            this.originalException = originalException;
        }

        /**
         * Gets transaction.
         *
         * @return the transaction
         */
        public GlobalTransaction getTransaction() {
            return transaction;
        }

        /**
         * Sets transaction.
         *
         * @param transaction the transaction
         */
        public void setTransaction(GlobalTransaction transaction) {
            this.transaction = transaction;
        }

        /**
         * Gets code.
         *
         * @return the code
         */
        public Code getCode() {
            return code;
        }

        /**
         * Sets code.
         *
         * @param code the code
         */
        public void setCode(Code code) {
            this.code = code;
        }

        /**
         * Gets original exception.
         *
         * @return the original exception
         */
        public Throwable getOriginalException() {
            return originalException;
        }

        /**
         * Sets original exception.
         *
         * @param originalException the original exception
         */
        public void setOriginalException(Throwable originalException) {
            this.originalException = originalException;
        }
    }
}
