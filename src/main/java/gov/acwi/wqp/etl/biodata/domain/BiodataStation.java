package gov.acwi.wqp.etl.biodata.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BiodataStation {
	
	private Integer biodataSiteId;
	private String agencyCd;
	private String siteNo;
	private String organization;
	private String siteTypeLongName;
	private String hucCd;
	private String governmentalUnitCode;
	private String countryCd;
	private String stateCd;
	private String countyCd;
	private String geoPoint;
	private String stationNm;
	private String stationTypeName;
	private String organizationName;
	private String descriptionText;
	private String decLatitude;
	private String decLongitude;
	private String mapScale;
	private String geopositioningMethod;
	private String coordDatumCd;
	private String elevationValue;
	private String elevationUnit;
	private String elevationMethod;
	private String vdatumIdCode;
	private String drainAreaVa;
	private String drainAreaUnit;
	private String contribDrainAreaValue;
	private String contribDrainAreaUnit;
	private String geopositionAccyValue;
	private String geopositionAccyUnit;
	private String verticalAccuracyValue;
	private String verticalAccuracyUnit;
	private String natAqfrName;
	private String aqfrName;
	private String aqfrTypeName;
	private String constructionDate;
	private String wellDepthValue;
	private String wellDepthUnit;
	private String holeDepthValue;
	private String holeDepthUnit;
}