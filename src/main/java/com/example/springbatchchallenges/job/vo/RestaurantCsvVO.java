package com.example.springbatchchallenges.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.batch.item.file.transform.FieldSet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class RestaurantCsvVO {
    private String no;
    private String openServiceName;
    private String openServiceId;
    private String openMunicipalityCode;
    private String controlNumber;
    private String licensingDate;
    private String licensingCancelDate;
    private String businessStatusCode;
    private String businessStatusName;
    private String businessDetailStatusCode;
    private String businessDetailStatusName;
    private String closingDate;
    private String suspensionStartDate;
    private String suspensionEndDate;
    private String reopeningDate;
    private String locationPhone;
    private String locationArea;
    private String locationZipCode;
    private String locationFullAddress;
    private String streetFullAddress;
    private String streetZipCode;
    private String businessLocationName;
    private String lastModifiedDatetime;
    private String dataRenewalType;
    private String dataRenewalDate;
    private String businessTypeName;
    private String coordinateX;
    private String coordinateY;
    private String sanitationBusinessTypeName;
    private String maleWorkersCount;
    private String femaleWorkersCount;
    private String businessNeighborhoodAreaName;
    private String gradeTypeName;
    private String waterSupplyType;
    private String employeeTotalCount;
    private String headOfficeEmployeeCount;
    private String factoryOfficeEmployeeCount;
    private String factorySalesEmployeeCount;
    private String factoryProduceEmployeeCount;
    private String buildingOwnershipType;
    private String guaranteeAmount;
    private String monthlyRentAmount;
    private String multiUseEstablishmentYn;
    private String facilityTotalSize;
    private String traditionalEstablishmentDesignationNumber;
    private String traditionalEstablishmentMainFood;
    private String homepage;
    private String empty;

    public static List<String> getFieldNames() {
        Field[] declaredFields = RestaurantCsvVO.class.getDeclaredFields();
        List<String> result = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            result.add(declaredField.getName());
        }
        return result;
    }

    public static RestaurantCsvVO of(String no) {
        return RestaurantCsvVO.builder()
                .no(no)
                .build();
    }

    public static RestaurantCsvVO of(FieldSet fieldSet) {
        return RestaurantCsvVO.builder()
                .no(fieldSet.readString("no"))
                .openServiceName(fieldSet.readString("openServiceName"))
                .openServiceId(fieldSet.readString("openServiceId"))
                .openMunicipalityCode(fieldSet.readString("openMunicipalityCode"))
                .controlNumber(fieldSet.readString("controlNumber"))
                .licensingDate(fieldSet.readString("licensingDate"))
                .licensingCancelDate(fieldSet.readString("licensingCancelDate"))
                .businessStatusCode(fieldSet.readString("businessStatusCode"))
                .businessStatusName(fieldSet.readString("businessStatusName"))
                .businessDetailStatusCode(fieldSet.readString("businessDetailStatusCode"))
                .businessDetailStatusName(fieldSet.readString("businessDetailStatusName"))
                .closingDate(fieldSet.readString("closingDate"))
                .suspensionStartDate(fieldSet.readString("suspensionStartDate"))
                .suspensionEndDate(fieldSet.readString("suspensionEndDate"))
                .reopeningDate(fieldSet.readString("reopeningDate"))
                .locationPhone(fieldSet.readString("locationPhone"))
                .locationArea(fieldSet.readString("locationArea"))
                .locationZipCode(fieldSet.readString("locationZipCode"))
                .locationFullAddress(fieldSet.readString("locationFullAddress"))
                .streetFullAddress(fieldSet.readString("streetFullAddress"))
                .streetZipCode(fieldSet.readString("streetZipCode"))
                .businessLocationName(fieldSet.readString("businessLocationName"))
                .lastModifiedDatetime(fieldSet.readString("lastModifiedDatetime"))
                .dataRenewalType(fieldSet.readString("dataRenewalType"))
                .dataRenewalDate(fieldSet.readString("dataRenewalDate"))
                .businessTypeName(fieldSet.readString("businessTypeName"))
                .coordinateX(fieldSet.readString("coordinateX"))
                .coordinateY(fieldSet.readString("coordinateY"))
                .sanitationBusinessTypeName(fieldSet.readString("sanitationBusinessTypeName"))
                .maleWorkersCount(fieldSet.readString("maleWorkersCount"))
                .femaleWorkersCount(fieldSet.readString("femaleWorkersCount"))
                .businessNeighborhoodAreaName(fieldSet.readString("businessNeighborhoodAreaName"))
                .gradeTypeName(fieldSet.readString("gradeTypeName"))
                .waterSupplyType(fieldSet.readString("waterSupplyType"))
                .employeeTotalCount(fieldSet.readString("employeeTotalCount"))
                .headOfficeEmployeeCount(fieldSet.readString("headOfficeEmployeeCount"))
                .factoryOfficeEmployeeCount(fieldSet.readString("factoryOfficeEmployeeCount"))
                .factorySalesEmployeeCount(fieldSet.readString("factorySalesEmployeeCount"))
                .factoryProduceEmployeeCount(fieldSet.readString("factoryProduceEmployeeCount"))
                .buildingOwnershipType(fieldSet.readString("buildingOwnershipType"))
                .guaranteeAmount(fieldSet.readString("guaranteeAmount"))
                .monthlyRentAmount(fieldSet.readString("monthlyRentAmount"))
                .multiUseEstablishmentYn(fieldSet.readString("multiUseEstablishmentYn"))
                .facilityTotalSize(fieldSet.readString("facilityTotalSize"))
                .traditionalEstablishmentDesignationNumber(fieldSet.readString("traditionalEstablishmentDesignationNumber"))
                .traditionalEstablishmentMainFood(fieldSet.readString("traditionalEstablishmentMainFood"))
                .homepage(fieldSet.readString("homepage"))
                .empty(fieldSet.readString("empty"))
                .build();
    }
}
