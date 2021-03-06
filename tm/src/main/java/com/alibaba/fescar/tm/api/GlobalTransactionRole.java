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
 * Role of current thread involve in a global transaction.
 */
public enum GlobalTransactionRole {

    /**
     * The Launcher.
     */
    // The one begins the current global transaction
    // 全局事务角色，发起者(主服务）
    Launcher,

    /**
     * The Participant.
     */
    // The one just joins into a existing global transaction.
    // 全局事务角色，参与者(从服务)
    Participant
}
