  CREATE TABLE "SCOTT"."MP3INFO" 
   (	"PLNO" NUMBER(4,0), 
	"MTITLE" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"ATITLE" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"SINGER" VARCHAR2(30 BYTE), 
	"GENRE" VARCHAR2(10 BYTE), 
	"RTIME" VARCHAR2(10 BYTE), 
	"RDATE" VARCHAR2(15 BYTE), 
	"AC" VARCHAR2(100 BYTE), 
	 PRIMARY KEY ("PLNO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;