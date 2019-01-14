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
 * Callback on failure.
 */
//失败时处理器
public interface FailureHandler {

    /**
     * On begin failure.
     *
     * @param tx    the tx
     * @param cause the cause
     */
    //启动事务失败
    void onBeginFailure(GlobalTransaction tx, Throwable cause);

    /**
     * On commit failure.
     *
     * @param tx    the tx
     * @param cause the cause
     */
    //提交事务失败
    void onCommitFailure(GlobalTransaction tx, Throwable cause);

    /**
     * On rollback failure.
     *
     * @param tx    the tx
     * @param cause the cause
     */
    //回滚事务失败
    void onRollbackFailure(GlobalTransaction tx, Throwable cause);
}
