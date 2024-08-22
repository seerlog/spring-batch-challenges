### 1. 배치 프로그램 설명

공공데이터포털에서 제공되는 "전국일반음식점표준데이터" CSV 파일을 읽어들인 후 음식점 정보를 MySQL 데이터베이스에 저장하는 프로그램입니다.

- `data.csv`의 데이터는 아래와 같습니다.   
![data.csv](https://private-user-images.githubusercontent.com/152579397/360378162-b54f50f3-3647-446f-987d-efafaf13a8ff.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjQzMjQ3MjIsIm5iZiI6MTcyNDMyNDQyMiwicGF0aCI6Ii8xNTI1NzkzOTcvMzYwMzc4MTYyLWI1NGY1MGYzLTM2NDctNDQ2Zi05ODdkLWVmYWZhZjEzYThmZi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwODIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDgyMlQxMTAwMjJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mMTIyNzQxYjg4ZDMyMWFiNzFhNzczNTk1NGE4Y2M3MGM1Nzc5NTYyNmFjMzBjOTAxODI1NDk0YTI3MWVhMDg2JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.oAcN4KHAjrK5YKvro5xG7JMKtsvi8BiLGW17dxdpNU8)

- 배치 프로그램 수행 후 데이터베이스에 저장된 데이터는 아래와 같습니다.
  - 테이블명: `restaurant`   
  ![restaurant](https://private-user-images.githubusercontent.com/152579397/360378173-d24ef990-1013-492d-bd15-5e85fc737655.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjQzMjQ3MjIsIm5iZiI6MTcyNDMyNDQyMiwicGF0aCI6Ii8xNTI1NzkzOTcvMzYwMzc4MTczLWQyNGVmOTkwLTEwMTMtNDkyZC1iZDE1LTVlODVmYzczNzY1NS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwODIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDgyMlQxMTAwMjJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zMTkzNjgwZGEzYTAxMGQxOWFmYWM3NjA4NGFhMGQ0M2I1NDFjNmMxM2JjZWQ2MGJhOTY4ZWFiNWJhZjFlZDhiJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.lufJvaxlLBE7Jv0Mloy7KFsc9AwRNkCm74pPxhsvwcg)
  
  - 테이블명: `error_log`   
  ![error_log](https://private-user-images.githubusercontent.com/152579397/360435304-ca151d9f-f4ad-4812-af1c-04fe10dddd27.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjQzMzEyNTcsIm5iZiI6MTcyNDMzMDk1NywicGF0aCI6Ii8xNTI1NzkzOTcvMzYwNDM1MzA0LWNhMTUxZDlmLWY0YWQtNDgxMi1hZjFjLTA0ZmUxMGRkZGQyNy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwODIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDgyMlQxMjQ5MTdaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hYjQxYzBiZTMyZjhlMDhhYzY2ZjBkNmYwMmIyNGQ5ZWI4MjI0MmRjMmYzNDhhMDg0YjM5YjViZmJmMzQyY2E3JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.By28t5fmv8QwrpSGQkf-62DftzzuC6sAzLUVyCQXcFI)


### 2. 테이블 DDL 쿼리

배치 프로그램에서 사용하는 MySQL 데이터베이스명은 `NATIONAL_RESTAURANT_STANDARD`입니다.   
사용하는 테이블은 `restaurant`와 `error_log`로 DDL 쿼리는 아래와 같습니다.

- `restaurant`
```sql
CREATE TABLE `restaurant` (
  `NO` bigint NOT NULL COMMENT '번호',
  `OPEN_SERVICE_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '개방서비스명',
  `OPEN_SERVICE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '개방서비스아이디',
  `OPEN_MUNICIPALITY_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '개방자치단체코드',
  `CONTROL_NUMBER` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '관리번호',
  `LICENSING_DATE` date DEFAULT NULL COMMENT '인허가일자',
  `LICENSING_CANCEL_DATE` date DEFAULT NULL COMMENT '인허가취소일자',
  `BUSINESS_STATUS_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '영업상태구분',
  `BUSINESS_STATUS_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '영업상태명',
  `BUSINESS_DETAIL_STATUS_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '상세영업상태코드',
  `BUSINESS_DETAIL_STATUS_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '상세영업상태명',
  `CLOSING_DATE` date DEFAULT NULL COMMENT '폐업일자',
  `SUSPENSION_START_DATE` date DEFAULT NULL COMMENT '휴업시작일자',
  `SUSPENSION_END_DATE` date DEFAULT NULL COMMENT '휴업종료일자',
  `REOPENING_DATE` date DEFAULT NULL COMMENT '재개업일자',
  `LOCATION_PHONE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '소재지전화',
  `LOCATION_AREA` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '소재지면적',
  `LOCATION_ZIP_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '소재지우편번호',
  `LOCATION_FULL_ADDRESS` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '소재지전체주소',
  `STREET_FULL_ADDRESS` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '도로명전체주소',
  `STREET_ZIP_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '도로명우편번호',
  `BUSINESS_LOCATION_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '사업장명',
  `LAST_MODIFIED_DATETIME` datetime DEFAULT NULL COMMENT '최종수정시점',
  `DATA_RENEWAL_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '데이터갱신구분',
  `DATA_RENEWAL_DATE` datetime DEFAULT NULL COMMENT '데이터갱신일자',
  `BUSINESS_TYPE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '업태구분명',
  `COORDINATE_X` double DEFAULT NULL COMMENT '좌표정보 X',
  `COORDINATE_Y` double DEFAULT NULL COMMENT '좌표정보 Y',
  `SANITATION_BUSINESS_TYPE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '위생업태명',
  `MALE_WORKERS_COUNT` int DEFAULT NULL COMMENT '남성종사자수',
  `FEMALE_WORKERS_COUNT` int DEFAULT NULL COMMENT '여성종사자수',
  `BUSINESS_NEIGHBORHOOD_AREA_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '영업장주변구분명',
  `GRADE_TYPE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '등급구분명',
  `WATER_SUPPLY_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '급수시설구분명',
  `EMPLOYEE_TOTAL_COUNT` int DEFAULT NULL COMMENT '총직원수',
  `HEAD_OFFICE_EMPLOYEE_COUNT` int DEFAULT NULL COMMENT '본사직원수',
  `FACTORY_OFFICE_EMPLOYEE_COUNT` int DEFAULT NULL COMMENT '공장사무직직원수',
  `FACTORY_SALES_EMPLOYEE_COUNT` int DEFAULT NULL COMMENT '공장판매직직원수',
  `FACTORY_PRODUCE_EMPLOYEE_COUNT` int DEFAULT NULL COMMENT '공장생산직직원수',
  `BUILDING_OWNERSHIP_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '건물소유구분명',
  `GUARANTEE_AMOUNT` int DEFAULT NULL COMMENT '보증액',
  `MONTHLY_RENT_AMOUNT` int DEFAULT NULL COMMENT '월세액',
  `MULTI_USE_ESTABLISHMENT_YN` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '다중이용업소여부',
  `FACILITY_TOTAL_SIZE` double DEFAULT NULL COMMENT '시설총규모',
  `TRADITIONAL_ESTABLISHMENT_DESIGNATION_NUMBER` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '전통업소지정번호',
  `TRADITIONAL_ESTABLISHMENT_MAIN_FOOD` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '전통업소주된음식',
  `HOMEPAGE` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '홈페이지',
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

- `error_log`
```sql
CREATE TABLE `error_log` (
  `NO` bigint NOT NULL,
  `CONTENT` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

### 3. 배치 프로그램 실행 방법

data.csv 파일의 크기가 크기 때문에 GitHub에 업로드되지 않는 부분이 있었습니다.
그래서 아래처럼 `resources` 폴더에 `data.csv` 파일을 추가하신 후 배치 프로그램을 실행시켜 주시면 됩니다.

![resources](https://private-user-images.githubusercontent.com/152579397/360402774-baab4b46-d420-4aae-80a5-7d63775dfd1f.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjQzMjQ2MzIsIm5iZiI6MTcyNDMyNDMzMiwicGF0aCI6Ii8xNTI1NzkzOTcvMzYwNDAyNzc0LWJhYWI0YjQ2LWQ0MjAtNGFhZS04MGE1LTdkNjM3NzVkZmQxZi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwODIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDgyMlQxMDU4NTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT01ZDEwMmM2OTE3NmE1ZWQzZWEzNDFkMTBjMzdlMGNlNWVhMWEyYzE1ZTM4YjNjNzc1YjMxMmQyMjY3NTE5ZTEyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.ulzf580nXnlYj79J8mW5B-gl3UPmaOpM_vg4gXX1hsk)
