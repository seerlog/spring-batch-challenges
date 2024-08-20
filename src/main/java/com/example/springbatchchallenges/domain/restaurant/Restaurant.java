package com.example.springbatchchallenges.domain.restaurant;

import com.example.springbatchchallenges.job.vo.RestaurantCsvVO;
import com.example.springbatchchallenges.utils.Util;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO", nullable = false)
    private Long no;

    @Column(name = "OPEN_SERVICE_NAME")
    private String openServiceName;

    @Column(name = "OPEN_SERVICE_ID")
    private String openServiceId;

    @Column(name = "OPEN_MUNICIPALITY_CODE")
    private String openMunicipalityCode;

    @Column(name = "CONTROL_NUMBER")
    private String controlNumber;

    @Column(name = "LICENSING_DATE")
    private LocalDate licensingDate;

    @Column(name = "LICENSING_CANCEL_DATE")
    private LocalDate licensingCancelDate;

    @Column(name = "BUSINESS_STATUS_CODE")
    private String businessStatusCode;

    @Column(name = "BUSINESS_STATUS_NAME")
    private String businessStatusName;

    @Column(name = "BUSINESS_DETAIL_STATUS_CODE")
    private String businessDetailStatusCode;

    @Column(name = "BUSINESS_DETAIL_STATUS_NAME")
    private String businessDetailStatusName;

    @Column(name = "CLOSING_DATE")
    private LocalDate closingDate;

    @Column(name = "SUSPENSION_START_DATE")
    private LocalDate suspensionStartDate;

    @Column(name = "SUSPENSION_END_DATE")
    private LocalDate suspensionEndDate;

    @Column(name = "REOPENING_DATE")
    private LocalDate reopeningDate;

    @Column(name = "LOCATION_PHONE")
    private String locationPhone;

    @Column(name = "LOCATION_AREA")
    private double locationArea;

    @Column(name = "LOCATION_ZIP_CODE")
    private String locationZipCode;

    @Column(name = "LOCATION_FULL_ADDRESS")
    private String locationFullAddress;

    @Column(name = "STREET_FULL_ADDRESS")
    private String streetFullAddress;

    @Column(name = "STREET_ZIP_CODE")
    private String streetZipCode;

    @Column(name = "BUSINESS_LOCATION_NAME")
    private String businessLocationName;

    @Column(name = "LAST_MODIFIED_DATETIME")
    private LocalDateTime lastModifiedDatetime;

    @Column(name = "DATA_RENEWAL_TYPE")
    private String dataRenewalType;

    @Column(name = "DATA_RENEWAL_DATE")
    private LocalDateTime dataRenewalDate;

    @Column(name = "BUSINESS_TYPE_NAME")
    private String businessTypeName;

    @Column(name = "COORDINATE_X")
    private double coordinateX;

    @Column(name = "COORDINATE_Y")
    private double coordinateY;

    @Column(name = "SANITATION_BUSINESS_TYPE_NAME")
    private String sanitationBusinessTypeName;

    @Column(name = "MALE_WORKERS_COUNT")
    private int maleWorkersCount;

    @Column(name = "FEMALE_WORKERS_COUNT")
    private int femaleWorkersCount;

    @Column(name = "BUSINESS_NEIGHBORHOOD_AREA_NAME")
    private String businessNeighborhoodAreaName;

    @Column(name = "GRADE_TYPE_NAME")
    private String gradeTypeName;

    @Column(name = "WATER_SUPPLY_TYPE")
    private String waterSupplyType;

    @Column(name = "EMPLOYEE_TOTAL_COUNT")
    private int employeeTotalCount;

    @Column(name = "HEAD_OFFICE_EMPLOYEE_COUNT")
    private int headOfficeEmployeeCount;

    @Column(name = "FACTORY_OFFICE_EMPLOYEE_COUNT")
    private int factoryOfficeEmployeeCount;

    @Column(name = "FACTORY_SALES_EMPLOYEE_COUNT")
    private int factorySalesEmployeeCount;

    @Column(name = "FACTORY_PRODUCE_EMPLOYEE_COUNT")
    private int factoryProduceEmployeeCount;

    @Column(name = "BUILDING_OWNERSHIP_TYPE")
    private String buildingOwnershipType;

    @Column(name = "GUARANTEE_AMOUNT")
    private int guaranteeAmount;

    @Column(name = "MONTHLY_RENT_AMOUNT")
    private int monthlyRentAmount;

    @Column(name = "MULTI_USE_ESTABLISHMENT_YN")
    private String multiUseEstablishmentYn;

    @Column(name = "FACILITY_TOTAL_SIZE")
    private double facilityTotalSize;

    @Column(name = "TRADITIONAL_ESTABLISHMENT_DESIGNATION_NUMBER")
    private String traditionalEstablishmentDesignationNumber;

    @Column(name = "TRADITIONAL_ESTABLISHMENT_MAIN_FOOD")
    private String traditionalEstablishmentMainFood;

    @Column(name = "HOMEPAGE")
    private String homepage;

    public static Restaurant of(RestaurantCsvVO restaurantCsvVO) {
        if(ObjectUtils.isEmpty(restaurantCsvVO.getNo())) {
            return new Restaurant();
        }
        return new Restaurant(Long.valueOf(restaurantCsvVO.getNo())
                , restaurantCsvVO.getOpenServiceName()
                , restaurantCsvVO.getOpenServiceId()
                , restaurantCsvVO.getOpenMunicipalityCode()
                , restaurantCsvVO.getControlNumber()
                , Util.parseDate(restaurantCsvVO.getLicensingDate())
                , Util.parseDate(restaurantCsvVO.getLicensingCancelDate())
                , restaurantCsvVO.getBusinessStatusCode()
                , restaurantCsvVO.getBusinessStatusName()
                , restaurantCsvVO.getBusinessDetailStatusCode()
                , restaurantCsvVO.getBusinessDetailStatusName()
                , Util.parseDate(restaurantCsvVO.getClosingDate())
                , Util.parseDate(restaurantCsvVO.getSuspensionStartDate())
                , Util.parseDate(restaurantCsvVO.getSuspensionEndDate())
                , Util.parseDate(restaurantCsvVO.getReopeningDate())
                , restaurantCsvVO.getLocationPhone()
                , Util.parseDouble(restaurantCsvVO.getLocationArea())
                , restaurantCsvVO.getLocationZipCode()
                , restaurantCsvVO.getLocationFullAddress()
                , restaurantCsvVO.getStreetFullAddress()
                , restaurantCsvVO.getStreetZipCode()
                , restaurantCsvVO.getBusinessLocationName()
                , Util.parseDateTime(restaurantCsvVO.getLastModifiedDatetime())
                , restaurantCsvVO.getDataRenewalType()
                , Util.parseDateTime(restaurantCsvVO.getDataRenewalDate())
                , restaurantCsvVO.getBusinessTypeName()
                , Util.parseDouble(restaurantCsvVO.getCoordinateX())
                , Util.parseDouble(restaurantCsvVO.getCoordinateY())
                , restaurantCsvVO.getSanitationBusinessTypeName()
                , Util.parseInt(restaurantCsvVO.getMaleWorkersCount())
                , Util.parseInt(restaurantCsvVO.getFemaleWorkersCount())
                , restaurantCsvVO.getBusinessNeighborhoodAreaName()
                , restaurantCsvVO.getGradeTypeName()
                , restaurantCsvVO.getWaterSupplyType()
                , Util.parseInt(restaurantCsvVO.getEmployeeTotalCount())
                , Util.parseInt(restaurantCsvVO.getHeadOfficeEmployeeCount())
                , Util.parseInt(restaurantCsvVO.getFactoryOfficeEmployeeCount())
                , Util.parseInt(restaurantCsvVO.getFactorySalesEmployeeCount())
                , Util.parseInt(restaurantCsvVO.getFactoryProduceEmployeeCount())
                , restaurantCsvVO.getBuildingOwnershipType()
                , Util.parseInt(restaurantCsvVO.getGuaranteeAmount())
                , Util.parseInt(restaurantCsvVO.getMonthlyRentAmount())
                , restaurantCsvVO.getMultiUseEstablishmentYn()
                , Util.parseDouble(restaurantCsvVO.getFacilityTotalSize())
                , restaurantCsvVO.getTraditionalEstablishmentDesignationNumber()
                , restaurantCsvVO.getTraditionalEstablishmentMainFood()
                , restaurantCsvVO.getHomepage());
    }

}
