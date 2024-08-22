### 1. 배치 프로그램 설명

공공데이터포털에서 제공되는 "전국일반음식점표준데이터" CSV 파일을 읽어들여 음식점 정보를 MySQL 데이터베이스에 저장하는 프로그램입니다.

- `data.csv`의 데이터는 아래와 같습니다.   
![data.csv](https://github-production-user-asset-6210df.s3.amazonaws.com/152579397/360378162-b54f50f3-3647-446f-987d-efafaf13a8ff.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240822%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240822T104840Z&X-Amz-Expires=300&X-Amz-Signature=87c6f3616b570529dcb4606e1e7a58bb9f100db4795716aa6ddce8c2289e97c4&X-Amz-SignedHeaders=host&actor_id=152579397&key_id=0&repo_id=843668168)

- 데이터베이스 저장 데이터는 아래와 같습니다.
  - 테이블명: `restaurant`   
  ![restaurant](https://github-production-user-asset-6210df.s3.amazonaws.com/152579397/360378173-d24ef990-1013-492d-bd15-5e85fc737655.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240822%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240822T105126Z&X-Amz-Expires=300&X-Amz-Signature=cc125aef2107914cbd0b9ae0c26b5e0578ab35b1c7e9e6dbae820bba7bd971b4&X-Amz-SignedHeaders=host&actor_id=152579397&key_id=0&repo_id=843668168)
  
  - 테이블명: `error_log`   
  ![error_log](https://github-production-user-asset-6210df.s3.amazonaws.com/152579397/360378171-be985232-8a0c-4b47-a52d-84c6c3fd7abb.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240822%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240822T105144Z&X-Amz-Expires=300&X-Amz-Signature=f3f8ce1699a75a87d4b2c29bf50455cb1f3633faba0e20f32b71f3967d3d96ab&X-Amz-SignedHeaders=host&actor_id=152579397&key_id=0&repo_id=843668168)


### 2. 테이블 DDL 쿼리

배치 프로그램에서 사용하는 MySQL 데이터베이스 테이블 생성 쿼리는 아래와 같습니다.

- `restaurant` 테이블 DDL 쿼리
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

- `error_log` 테이블 DDL 쿼리
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
