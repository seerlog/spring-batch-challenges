package com.example.springbatchchallenges.job.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
}
