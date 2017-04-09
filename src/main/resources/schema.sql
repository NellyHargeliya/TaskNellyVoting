drop table if exists `theme`;
CREATE TABLE THEME (
  idTheme     INTEGER NOT NULL AUTO_INCREMENT,
  nameTheme   VARCHAR(50),
  startVoting DATE,
  endVoting   DATE,
  urlTheme    VARCHAR(100),
  PRIMARY KEY (idTheme)
);
drop table if exists `THEME_OPTION`;
CREATE TABLE THEME_OPTION (
  idOption   INTEGER NOT NULL AUTO_INCREMENT,
  nameOption VARCHAR(50),
  quantity   INTEGER,
  idTheme    INTEGER
);
