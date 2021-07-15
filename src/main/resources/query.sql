CREATE TABLE t_user(
   iuser INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '회원번호',
   email VARCHAR(50) UNIQUE NOT NULL COMMENT '이메일',
   pw VARCHAR(100) NOT NULL COMMENT '비밀번호',
   nm VARCHAR(5) NOT NULL COMMENT '회원이름',
   nick VARCHAR(8) NOT NULL COMMENT '닉네임',
   tel CHAR(13) COMMENT '연락처',
   authCd CHAR(5) COMMENT '회원가입 인증코드, null이면 인증받은 상태, 값이 있으면 인증해야 되는 상태',
   regdt DATETIME DEFAULT NOW() COMMENT '가입일자',
   INDEX idx_auth_cd (`authCd`)
);

CREATE TABLE t_board(
    iboard INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '글번호',
    title VARCHAR(300) NOT NULL COMMENT '글제목',
    ctnt VARCHAR(4000) NOT NULL COMMENT '글내용',
    iuser INT UNSIGNED NOT NULL COMMENT '회원번호',
    regdt DATETIME DEFAULT NOW() COMMENT '글작성 시기',
    FOREIGN KEY(iuser) REFERENCES t_user(iuser)
);