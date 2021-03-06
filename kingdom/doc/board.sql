CREATE TABLE board(
    bno NUMBER(5)
        CONSTRAINT BRD_NO_PK PRIMARY KEY,
    title VARCHAR2(50 CHAR)
        CONSTRAINT BRD_TTL_NN NOT NULL,
    body VARCHAR2(4000)
        CONSTRAINT BRD_BODY_NN NOT NULL,
    bmno NUMBER(4)
        CONSTRAINT BRD_MNO_FK REFERENCES member(mno)
        CONSTRAINT BRD_MNO_NN NOT NULL,
    wdate   DATE DEFAULT sysdate
        CONSTRAINT BRD_DATE_NN NOT NULL,
    bclick NUMBER(6) DEFAULT 0
        CONSTRAINT BRD_CLICK_NN NOT NULL,
    isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT BRD_SHOW_NN NOT NULL
        CONSTRAINT BRD_SHOW_CK CHECK(isshow IN('Y', 'N'))
);

CREATE TABLE fileInfo(
    fno NUMBER(7)
        CONSTRAINT FI_NO_PK PRIMARY KEY,
    fbno NUMBER(5)
        CONSTRAINT FI_BNO_FK REFERENCES board(bno)
        CONSTRAINT FI_BNO_NN NOT NULL,
    oriname VARCHAR2(50 CHAR)
        CONSTRAINT FI_ONAME_NN NOT NULL,
    savename VARCHAR2(50 CHAR)
        CONSTRAINT FI_SNAME_UK UNIQUE
        CONSTRAINT FI_SNAME_NN NOT NULL,
    dir VARCHAR2(50 CHAR) DEFAULT '/img/upload/'
        CONSTRAINT FI_DIR_NN NOT NULL,
    len NUMBER
        CONSTRAINT FI_LEN_NN NOT NULL,
    fdate DATE DEFAULT sysdate
        CONSTRAINT FI_DATE_NN NOT NULL,
    fcount NUMBER DEFAULT 0
        CONSTRAINT FI_CNT_NN NOT NULL,
    isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT FI_SHOW_CK CHECK(isshow IN('Y', 'N'))
        CONSTRAINT FI_SHOW_NN NOT NULL
);