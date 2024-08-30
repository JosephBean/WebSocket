-- root 사용자로 접속 후 실행
USE mysql;

CREATE DATABASE company;

GRANT ALL ON company.* TO 'test'@'localhost';

FLUSH PRIVILEGES;

SHOW GRANTS FOR 'test'@'localhost';

-- test 사용자로 접속 후 실행
USE company;

CREATE TABLE `channel` (
	channelNo 	INT 				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	channelNm 	VARCHAR(50) 	NOT NULL,
	prefixe		VARCHAR(50)		NOT NULL,
	topic			VARCHAR(50)		NOT NULL
);

INSERT INTO `channel` (channelNm,prefixe,topic) VALUES ('CH-A','/topic/bean1','/msg/room');
INSERT INTO `channel` (channelNm,prefixe,topic) VALUES ('CH-B','/topic/bean2','/msg/room');
INSERT INTO `channel` (channelNm,prefixe,topic) VALUES ('CH-C','/topic/bean3','/msg/room');

COMMIT;

