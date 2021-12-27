CREATE TABLE guestboard(
    bno NUMBER(4)
        CONSTRAINT GBRD_NO_PK PRIMARY KEY,
    bmno NUMBER(4)
        CONSTRAINT GBRD_MNO_FK REFERENCES member(mno)
        CONSTRAINT GBRD_MNO_NN NOT NULL,
    body VARCHAR2(4000)
        CONSTRAINT GBRD_BODY_NN NOT NULL,
    wdate DATE DEFAULT sysdate
        CONSTRAINT GBRD_DATE_NN NOT NULL,
    isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT GBRD_SHOW_CK CHECK(isshow IN('Y', 'N'))
        CONSTRAINT GBRD_SHOW_NN NOT NULL
);



-- 회원 추가
INSERT INTO member VALUES(999,	'제니',	    'jenny',	'12345',	'jenny@cnu.ac.kr',	'010-1111-1111',	'F',	15,	'2021/12/22',	'Y');
INSERT INTO member VALUES(1001,	'이재영',	'jy111',	'12345',	'jy111@cnu.org',	'010-2222-2222',	'M',	12,	'2021/12/24',	'Y');
INSERT INTO member VALUES(1002,	'김현우',	'woo',	    '12345',	'woo@cnu.org',	    '010-5000-5000',	'M',	12,	'2021/12/27',	'Y');
INSERT INTO member VALUES(1003,	'반장님',	'jjang',	'12345',	'jjang@cnu.org',	'010-1010-1010',	'F',	15,	'2021/12/27',	'Y');
INSERT INTO member VALUES(1004,	'박호식',	'phs',	    '12345',	'phs@cnu.org',	    '010-5656-5656',	'M',	12,	'2021/12/27',	'Y');
INSERT INTO member VALUES(1005,	'회장님',	'ajy',	    '12345',	'ajy@cnu.org',	    '010-7979-7979',	'F',	15,	'2021/12/27',	'Y');
INSERT INTO member VALUES(1006,	'오타벡',	'ta100',	'12345',	'ta100@cnu.org',	'010-7100-7100',	'M',	11,	'2021/12/27',	'Y');
INSERT INTO member VALUES(1007,	'장운창',	'woon',	    '12345',	'woon@cnu.org',	    '010-3333-3333',	'M',	12,	'2021/12/27',	'Y');
INSERT INTO member VALUES(1008,	'정세한',	'jsh',	    '12345',	'jsh@cnu.org',	    '010-3131-3131',	'M',	12,	'2021/12/27',	'Y');