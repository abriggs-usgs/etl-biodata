select create_swap_table ('stewards', 'wqp', 'org_data');
select create_swap_table ('stewards', 'wqp', 'project_data');
select create_swap_table ('stewards', 'wqp', 'station');
select create_swap_table ('stewards', 'wqp', 'activity');
select create_swap_table ('stewards', 'wqp', 'result');
select create_swap_table ('stewards', 'wqp', 'r_detect_qnt_lmt');

create table if not exists ars_org_project
(organization                   VARCHAR(2500)
,organization_name              VARCHAR(2500)
,project_identifier             VARCHAR(2500)
,project_name                   VARCHAR(2500)
,project_description_text       VARCHAR(2500)
);

create table if not exists ars_station
(station_id                                        integer generated by default as identity (start with 1)
,monitoring_location_identifier                    VARCHAR(2500)
,monitoring_location_name                          VARCHAR(2500)
,monitoring_location_type_name                     VARCHAR(2500)
,monitoring_location_description_text              VARCHAR(2500)
,huc_eight_digit_code                              VARCHAR(2500)
,huc_twelve_digit_code                             VARCHAR(2500)
,drainage_area_measure_value                       VARCHAR(2500)
,drainage_area_measure_unit_code                   VARCHAR(2500)
,latitude_measure                                  VARCHAR(2500)
,longitude_measure                                 VARCHAR(2500)
,horizontal_collection_method_name                 VARCHAR(2500)
,horizontal_coordinate_reference_system_datum_name VARCHAR(2500)
,country_code                                      VARCHAR(2500)
,state_code                                        VARCHAR(2500)
,county_code                                       VARCHAR(2500)
);

create table if not exists ars_result
(result_id                                         integer generated by default as identity (start with 1)
,activity_identifier  VARCHAR(2500)
,activity_type_code  VARCHAR(2500)
,activity_media_name  VARCHAR(2500)
,activity_start_date  VARCHAR(2500)
,activity_start_time  VARCHAR(2500)
,activity_start_time_zone_code  VARCHAR(2500)
,measure_value  VARCHAR(2500)
,measure_unit_code  VARCHAR(2500)
,activity_depth_height_measure  VARCHAR(2500)
,project_identifier  VARCHAR(2500)
,monitoring_location_identifier  VARCHAR(2500)
,activity_comment_text  VARCHAR(2500)
,sample_collection_method_identifier  VARCHAR(2500)
,sample_collection_method_identifier_context  VARCHAR(2500)
,sample_collection_method_name  VARCHAR(2500)
,sample_collection_method_description_text  VARCHAR(2500)
,sample_collection_equipment_name  VARCHAR(2500)
,sample_collection_equipment_comment_text  VARCHAR(2500)
,result_detection_condition_text  VARCHAR(2500)
,characteristic_name  VARCHAR(2500)
,result_sample_fraction_text  VARCHAR(2500)
,result_measure_value  VARCHAR(2500)
,result_measure_unit_code  VARCHAR(2500)
,result_status_identifier  VARCHAR(2500)
,result_value_type_name  VARCHAR(2500)
,data_quality_precision_value  VARCHAR(2500)
,result_comment_text  VARCHAR(2500)
,result_analytical_method_identifier  VARCHAR(2500)
,result_analytical_method_identifier_context  VARCHAR(2500)
,result_analytical_method_name  VARCHAR(2500)
,result_analytical_method_description_text  VARCHAR(2500)
,detection_quantitation_limit_type_name  VARCHAR(2500)
,detection_quantitation_limit_measure_value  VARCHAR(2500)
,detection_quantitation_limit_measure_unit_code  VARCHAR(2500)
);

create table if not exists ars_site_type_to_primary
(site_type varchar(100)
,primary_site_type varchar(100)
);

create table if not exists ars_char_name_to_type
(characteristic_name            VARCHAR(500)
,characteristic_type            VARCHAR(500)
);