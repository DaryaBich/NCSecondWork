-- DROP
DROP TABLE IF EXISTS CHAT;
DROP TABLE IF EXISTS MESSAGE;
DROP TABLE IF EXISTS MESSAPROCESSING;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS USERINCHAT;

-- CREATE
CREATE TABLE CHAT(ID INT PRIMARY KEY, NAME VARCHAR(50));
CREATE TABLE MESSAGE(ID INT PRIMARY KEY, SENTBYUSERWITHID INT, TEXT VARCHAR(255), SENDINGDATE TIMESTAMP);
CREATE TABLE MESSAGEPROCESSING(ID INT PRIMARY KEY, IDCHAT INT, IDMESSAGE INT);
CREATE TABLE USERS(ID INT PRIMARY KEY, NAME VARCHAR(50), LOGIN VARCHAR(50), PASSWORD VARCHAR(50));
CREATE TABLE USERINCHAT(ID INT PRIMARY KEY, USERID INT, CHATID INT);

-- INSERT
INSERT INTO CHAT VALUES(1, 'Mom');
INSERT INTO CHAT VALUES(2, 'Kolya');
INSERT INTO CHAT VALUES(3, 'Vasilina');
INSERT INTO CHAT VALUES(4, 'Lisa');
INSERT INTO CHAT VALUES(5, 'Marina');
INSERT INTO CHAT VALUES(6, 'Roman');
INSERT INTO CHAT VALUES(7, 'Tatyana');
INSERT INTO CHAT VALUES(8, 'Darya');
INSERT INTO CHAT VALUES(9, 'Anton');
INSERT INTO CHAT VALUES(10, 'Denis');
INSERT INTO MESSAGE VALUES(1, 1, 'Hello', GETDATE());
INSERT INTO MESSAGEPROCESSING VALUES(1, 1,1);
INSERT INTO USERS VALUES(1, 'Vasya', 'test', 'test');
INSERT INTO USERINCHAT VALUES(1, 1, 1);
INSERT INTO USERINCHAT VALUES(2, 1, 2);
INSERT INTO USERINCHAT VALUES(3, 1, 3);
INSERT INTO USERINCHAT VALUES(4, 1, 4);
INSERT INTO USERINCHAT VALUES(5, 1, 5);
INSERT INTO USERINCHAT VALUES(6, 1, 6);
INSERT INTO USERINCHAT VALUES(7, 1, 7);
INSERT INTO USERINCHAT VALUES(8, 1, 8);
INSERT INTO USERINCHAT VALUES(9, 1, 9);
INSERT INTO USERINCHAT VALUES(10, 1, 10);