--
-- Copyright 2017 WSO2 Inc. (http://wso2.org)
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--
CREATE TABLE IF NOT EXISTS WORKERS_CONFIGURATION (
WORKERID VARCHAR(255) PRIMARY KEY,
HOST VARCHAR(500),
PORT INT
);
CREATE TABLE IF NOT EXISTS WORKERS_DETAILS (
 CARBONID VARCHAR(255) PRIMARY KEY ,
 WORKERID VARCHAR(255),
 JAVARUNTIMENAME VARCHAR(255),
 JAVAVMVERSION VARCHAR(255),
 JAVAVMVENDOR VARCHAR(255),
 JAVAHOME VARCHAR(255),
 JAVAVERSION VARCHAR(255),
 OSNAME VARCHAR(255),
 OSVERSION VARCHAR(255),
 USERHOME VARCHAR(255),
 USERTIMEZONE VARCHAR(255),
 USERNAME VARCHAR(255),
 USERCOUNTRY VARCHAR(255),
 REPOLOCATION VARCHAR(255),
 SERVERSTARTTIME VARCHAR(255),
 FOREIGN KEY (WORKERID) REFERENCES WORKERS_CONFIGURATION(WORKERID)
);

CREATE INDEX IDX_WORKERID_CONF ON WORKERS_CONFIGURATION (WORKERID);
CREATE INDEX IDX_WORKERID_GEN ON WORKERS_DETAILS (WORKERID);
