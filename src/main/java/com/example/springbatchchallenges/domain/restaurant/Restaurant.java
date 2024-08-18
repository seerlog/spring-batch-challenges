package com.example.springbatchchallenges.domain.restaurant;

import com.example.springbatchchallenges.job.vo.RestaurantCsvVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "OPEN_SERVICE_NAME", nullable = false)
    private String openServiceName;

    @Column(name = "OPEN_SERVICE_ID", nullable = false)
    private String openServiceId;

    @Column(name = "OPEN_MUNICIPALITY_CODE", nullable = false)
    private String openMunicipalityCode;

    @Column(name = "CONTROL_NUMBER", nullable = false)
    private String controlNumber;

    @Column(name = "LICENSING_DATE", nullable = false)
    private LocalDate licensingDate;

    @Column(name = "LICENSING_CANCEL_DATE", nullable = false)
    private LocalDate licensingCancelDate;

    @Column(name = "BUSINESS_STATUS_CODE", nullable = false)
    private String businessStatusCode;

    @Column(name = "BUSINESS_STATUS_NAME", nullable = false)
    private String businessStatusName;

    @Column(name = "BUSINESS_DETAIL_STATUS_CODE", nullable = false)
    private String businessDetailStatusCode;

    @Column(name = "BUSINESS_DETAIL_STATUS_NAME", nullable = false)
    private String businessDetailStatusName;

    @Column(name = "CLOSING_DATE", nullable = false)
    private LocalDate closingDate;

    @Column(name = "SUSPENSION_START_DATE", nullable = false)
    private LocalDate suspensionStartDate;

    @Column(name = "SUSPENSION_END_DATE", nullable = false)
    private LocalDate suspensionEndDate;

    @Column(name = "REOPENING_DATE", nullable = false)
    private LocalDate reopeningDate;

    @Column(name = "LOCATION_PHONE", nullable = false)
    private String locationPhone;

    @Column(name = "LOCATION_AREA", nullable = false)
    private double locationArea;

    @Column(name = "LOCATION_ZIP_CODE", nullable = false)
    private String locationZipCode;

    @Column(name = "LOCATION_FULL_ADDRESS", nullable = false)
    private String locationFullAddress;

    @Column(name = "STREET_FULL_ADDRESS", nullable = false)
    private String streetFullAddress;

    @Column(name = "STREET_ZIP_CODE", nullable = false)
    private String streetZipCode;

    @Column(name = "BUSINESS_LOCATION_NAME", nullable = false)
    private String businessLocationName;

    @Column(name = "LAST_MODIFIED_DATETIME", nullable = false)
    private LocalDateTime lastModifiedDatetime;

    @Column(name = "DATA_RENEWAL_TYPE", nullable = false)
    private String dataRenewalType;

    @Column(name = "DATA_RENEWAL_DATE", nullable = false)
    private LocalDateTime dataRenewalDate;

    @Column(name = "BUSINESS_TYPE_NAME", nullable = false)
    private String businessTypeName;

    @Column(name = "COORDINATE_X", nullable = false)
    private double coordinateX;

    @Column(name = "COORDINATE_Y", nullable = false)
    private double coordinateY;

    @Column(name = "SANITATION_BUSINESS_TYPE_NAME", nullable = false)
    private String sanitationBusinessTypeName;

    @Column(name = "MALE_WORKERS_COUNT", nullable = false)
    private int maleWorkersCount;

    @Column(name = "FEMALE_WORKERS_COUNT", nullable = false)
    private int femaleWorkersCount;

    @Column(name = "BUSINESS_NEIGHBORHOOD_AREA_NAME", nullable = false)
    private String businessNeighborhoodAreaName;

    @Column(name = "GRADE_TYPE_NAME", nullable = false)
    private String gradeTypeName;

    @Column(name = "WATER_SUPPLY_TYPE", nullable = false)
    private String waterSupplyType;

    @Column(name = "EMPLOYEE_TOTAL_COUNT", nullable = false)
    private int employeeTotalCount;

    @Column(name = "HEAD_OFFICE_EMPLOYEE_COUNT", nullable = false)
    private int headOfficeEmployeeCount;

    @Column(name = "FACTORY_OFFICE_EMPLOYEE_COUNT", nullable = false)
    private int factoryOfficeEmployeeCount;

    @Column(name = "FACTORY_SALES_EMPLOYEE_COUNT", nullable = false)
    private int factorySalesEmployeeCount;

    @Column(name = "FACTORY_PRODUCE_EMPLOYEE_COUNT", nullable = false)
    private int factoryProduceEmployeeCount;

    @Column(name = "BUILDING_OWNERSHIP_TYPE", nullable = false)
    private String buildingOwnershipType;

    @Column(name = "GUARANTEE_AMOUNT", nullable = false)
    private int guaranteeAmount;

    @Column(name = "MONTHLY_RENT_AMOUNT", nullable = false)
    private int monthlyRentAmount;

    @Column(name = "MULTI_USE_ESTABLISHMENT_YN", nullable = false)
    private String multiUseEstablishmentYn;

    @Column(name = "FACILITY_TOTAL_SIZE", nullable = false)
    private double facilityTotalSize;

    @Column(name = "TRADITIONAL_ESTABLISHMENT_DESIGNATION_NUMBER", nullable = false)
    private String traditionalEstablishmentDesignationNumber;

    @Column(name = "TRADITIONAL_ESTABLISHMENT_MAIN_FOOD", nullable = false)
    private String traditionalEstablishmentMainFood;

    @Column(name = "HOMEPAGE", nullable = false)
    private String homepage;

    @Column(name = "EMPTY", nullable = false)
    private String empty;

    public static Restaurant of(RestaurantCsvVO restaurantCsvVO) {
        return new Restaurant(Long.valueOf(restaurantCsvVO.getNo()), restaurantCsvVO.getOpenServiceName(), restaurantCsvVO.getOpenServiceId(),
                restaurantCsvVO.getOpenMunicipalityCode(), restaurantCsvVO.getControlNumber(),
                LocalDate.parse(restaurantCsvVO.getLicensingDate()), LocalDate.parse(restaurantCsvVO.getLicensingCancelDate()),
                restaurantCsvVO.getBusinessStatusCode(), restaurantCsvVO.getBusinessStatusName(),
                restaurantCsvVO.getBusinessDetailStatusCode(), restaurantCsvVO.getBusinessDetailStatusName(),
                LocalDate.parse(restaurantCsvVO.getClosingDate()), LocalDate.parse(restaurantCsvVO.getSuspensionStartDate()),
                LocalDate.parse(restaurantCsvVO.getSuspensionEndDate()), LocalDate.parse(restaurantCsvVO.getReopeningDate()),
                restaurantCsvVO.getLocationPhone(), Double.parseDouble(restaurantCsvVO.getLocationArea()),
                restaurantCsvVO.getLocationZipCode(), restaurantCsvVO.getLocationFullAddress(),
                restaurantCsvVO.getStreetFullAddress(), restaurantCsvVO.getStreetZipCode(),
                restaurantCsvVO.getBusinessLocationName(), LocalDateTime.parse(restaurantCsvVO.getLastModifiedDatetime()),
                restaurantCsvVO.getDataRenewalType(), LocalDateTime.parse(restaurantCsvVO.getDataRenewalDate()),
                restaurantCsvVO.getBusinessTypeName(), Double.parseDouble(restaurantCsvVO.getCoordinateX()),
                Double.parseDouble(restaurantCsvVO.getCoordinateY()), restaurantCsvVO.getSanitationBusinessTypeName(),
                Integer.parseInt(restaurantCsvVO.getMaleWorkersCount()), Integer.parseInt(restaurantCsvVO.getFemaleWorkersCount()),
                restaurantCsvVO.getBusinessNeighborhoodAreaName(), restaurantCsvVO.getGradeTypeName(),
                restaurantCsvVO.getWaterSupplyType(), Integer.parseInt(restaurantCsvVO.getEmployeeTotalCount()),
                Integer.parseInt(restaurantCsvVO.getHeadOfficeEmployeeCount()), Integer.parseInt(restaurantCsvVO.getFactoryOfficeEmployeeCount()),
                Integer.parseInt(restaurantCsvVO.getFactorySalesEmployeeCount()), Integer.parseInt(restaurantCsvVO.getFactoryProduceEmployeeCount()),
                restaurantCsvVO.getBuildingOwnershipType(), Integer.parseInt(restaurantCsvVO.getGuaranteeAmount()),
                Integer.parseInt(restaurantCsvVO.getMonthlyRentAmount()), restaurantCsvVO.getMultiUseEstablishmentYn(),
                Double.parseDouble(restaurantCsvVO.getFacilityTotalSize()), restaurantCsvVO.getTraditionalEstablishmentDesignationNumber(),
                restaurantCsvVO.getTraditionalEstablishmentMainFood(), restaurantCsvVO.getHomepage(), restaurantCsvVO.getEmpty());
    }
}
