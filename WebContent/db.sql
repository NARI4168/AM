# DB 생성
DROP DATABASE IF EXISTS articlemanager;
CREATE DATABASE articlemanager;
USE articlemanager;

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(255) NOT NULL,
    `body` LONGTEXT NOT NULL,
    memberId INT(10) NOT NULL,
    writer CHAR(20) NOT NULL,
    hit int(10) NOT NULL DEFAULT '0'
    `like` int(10) NOT NULL DEFAULT '0'

);

# 게시물 테이블 데이터 생성

INSERT INTO article 
SET regDate = NOW(),
title = '제목1',
`body` = '내용1';
memberId = 2;
writer ='admin';
hit=3;

INSERT INTO article 
SET regDate = NOW(),
title = '제목2',
`body` = '내용2';
memberId = 2;
writer ='admin';
hit=1;

INSERT INTO article 
SET regDate = NOW(),
title = '제목3',
`body` = '내용3';
memberId = 2;
writer ='admin';
hit=0;

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL UNIQUE,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(100) NOT NULL
);

# 회원 테이블 데이터 생성
INSERT INTO `member` 
SET regDate = NOW(),
loginId = 'admin',
`loginPw` = 'admin',
`name` = '관리자';

INSERT INTO `member` 
SET regDate = NOW(),
loginId = 'user1',
`loginPw` = 'user1',
`name` = '유저1';

INSERT INTO `member` 
SET regDate = NOW(),
loginId = 'user2',
`loginPw` = 'user2',
`name` = '유저2';

# 게시물 테이블에 memberId 칼럼 추가
#ALTER TABLE `article` ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER regDate;

# 기존 게시물은 그냥 2번 회원이 전부 작성한걸로 정한다.
#UPDATE article
#SET memberId = 1
#WHERE memberId = 0;

#댓글 테이블 생성
CREATE TABLE articleReply (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `body` TEXT NOT NULL,
    regDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    articleId INT(10) UNSIGNED NOT NULL,
    writer CHAR(20)UNSIGNED NOT NULL
);

#댓글 데아터 생성
INSERT INTO articleReply 
SET regDate = NOW(),
`body` =`댓글1`;
memberId = 1;
articleId = 1;
writer='admin';

INSERT INTO articleReply 
SET regDate = NOW(),
`body` =`댓글2`;
memberId = 1;
articleId = 1;
writer='admin';

INSERT INTO articleReply 
SET regDate = NOW(),
`body` =`댓글3`;
memberId = 1;
articleId = 1;
writer='admin';


#좋아요 테이블 생성
CREATE TABLE `like`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
articleId INT(10) UNSIGNED NOT NULL,
memberId INT(10) UNSIGNED NOT NULL,
likecheck INT(10) DEFAULT '0' NOT NULL
);

#member테이블에 image칼럼 추가
ALTER TABLE `member` ADD COLUMN image CHAR(200);
#member테이블에 imagePath 칼럼 추가
ALTER TABLE `member` ADD COLUMN imagePath CHAR(200) AFTER `name`;