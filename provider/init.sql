CREATE DATABASE provider;

CREATE USER 'app'@'%' IDENTIFIED BY 'passwd';

GRANT ALL PRIVILEGES ON `provider`.* TO 'app'@'%';

FLUSH PRIVILEGES;
