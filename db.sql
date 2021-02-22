# 데이터 베이스 생성
DROP DATABASE IF EXISTS untact;
CREATE DATABASE untact;
USE untact;

# 게시물 테이블 생성
CREATE TABLE article(
   id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물, 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목1 입니다.",
`body`= "내용1 입니다.";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목2 입니다.",
`body`= "내용2 입니다.";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목3 입니다.",
`body`= "내용3 입니다.";


# 회원 테이블 생성
CREATE TABLE `member`(
   id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw VARCHAR(100) NOT NULL,
    `name` CHAR(30) NOT NULL,
    `nickname` CHAR(30) NOT NULL,
    `email` CHAR(100) NOT NULL,
    `cellphoneNo` CHAR(20) NOT NULL
);

# 로그인 ID로 검색했을 때
ALTER TABLE `member` ADD UNIQUE INDEX (`loginId`);

# 회원, 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "user1",
loginPw = "user1",
`name` = "홍길동",
nickname = "강바람",
cellphoneNo = "01012341234",
email = "dd31391@gmail.com"
;

# 게시물 테이블에 회원번호 칼럼 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# 기존 게시물의 작성자를 회원1로 지정
UPDATE article
SET memberId = 1
WHERE memberId = 0;


# 회원, 테스트 데이터 생성, user2
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "user2",
loginPw = "user2",
`name` = "임꺽정",
nickname = "이또한지나가라라",
cellphoneNo = "01012341234",
email = "dd31391@gmail.com"
;

SELECT A.*,
IFNULL(M.nickname, "탈퇴회원") AS extra__writer
FROM article AS A
LEFT JOIN `member` AS M
ON A.memberId = M.id
WHERE A.id = 2;