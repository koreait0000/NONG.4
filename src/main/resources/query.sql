CREATE TABLE t_user(
   iuser INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '회원번호',
   email VARCHAR(50) UNIQUE NOT NULL COMMENT '이메일',
   provider VARCHAR(10) COMMENT '로그인계정경로',
   pw VARCHAR(100) NOT NULL COMMENT '비밀번호',
   nm VARCHAR(5) NOT NULL COMMENT '회원이름',
   nick VARCHAR(8) NOT NULL COMMENT '닉네임',
   tel CHAR(13) COMMENT '연락처',
   authCd CHAR(5) COMMENT '회원가입 인증코드, null이면 인증받은 상태, 값이 있으면 인증해야 되는 상태',
   profileImg VARCHAR(50) COMMENT '프로필사진',
   regdt DATETIME DEFAULT NOW() COMMENT '가입일자',
   INDEX idx_auth_cd (`authCd`)
);

CREATE TABLE t_board(
    iboard INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '게시물번호',
    provider VARCHAR(50) NOT NULL COMMENT '게시판 구분',
    iuser INT UNSIGNED COMMENT '회원번호',
    title VARCHAR(300) NOT NULL COMMENT '게시물 제목',
    ctnt VARCHAR(4000) NOT NULL COMMENT '게시물 내용',
    regdt DATETIME DEFAULT NOW() COMMENT '작성 날짜',
    delYN CHAR(1) NULL DEFAULT 'N' COMMENT '삭제유무',
    FOREIGN KEY (iuser) REFERENCES t_user (iuser)
);

CREATE TABLE t_cmt(
    icmt INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '댓글번호',
    iuser INT UNSIGNED COMMENT '회원번호',
    iboard INT UNSIGNED COMMENT '게시물번호',
    cmt VARCHAR(2000) NOT NULL COMMENT '댓글내용',
    regdt DATETIME DEFAULT NOW() COMMENT '작성 날짜',
    FOREIGN KEY (iuser) REFERENCES t_user (iuser),
    FOREIGN KEY (iboard) REFERENCES t_board (iboard)
);

CREATE TABLE t_board_img (
    iboardimg INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '이미지번호',
    iboard INT UNSIGNED NOT NULL COMMENT '게시물번호',
    img VARCHAR(50) NOT NULL COMMENT '이미지 명',
    PRIMARY KEY(iboardimg),
    FOREIGN KEY(iboard) REFERENCES t_board(iboard)
);

CREATE TABLE t_board_fav(
    iuser INT unsigned NOT NULL COMMENT '회원정보',
    iboard INT unsigned NOT NULL COMMENT '게시물번호',
    regdt DATETIME DEFAULT NOW() COMMENT '작성 날짜',
    PRIMARY KEY(iuser, iboard),
    FOREIGN KEY(iboard) REFERENCES t_board(iboard),
    FOREIGN KEY(iuser) REFERENCES t_user(iuser)
);

CREATE TABLE t_user_profile (
    iprofile INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회원프로필번호',
    iuser INT(10) UNSIGNED NOT NULL COMMENT '회원번호',
    profileImg VARCHAR(50) NOT NULL COMMENT '프로플사진',
    regdt DATETIME DEFAULT NOW() COMMENT '작성 날짜',
    PRIMARY KEY (iprofile),
    FOREIGN KEY(iuser) REFERENCES t_user(iuser)
);
