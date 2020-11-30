  /*=============================================================================================*
   | Copyright  2000 - TRINITI Corporation.  All rights reserved.                                 |
   |                                                                                              |
   | This product (both software programs and documentation) is protected by US and international |
   | copyright laws. Unauthorized reproduction, reuse, distribution, de-compilation, or reverse   |
   | engineering of this program, or any portion of it is prohibited and may result in severe     |
   | civil and criminal penalties, prosecuted to the maximum extent allowable under law.          |
   |                                                                                              |
   | Program Documentation is licensed solely to support Triniti products and not for any other   |
   | purpose. The information contained in this document is subject to change without notice.     |
   |                                                                                              |
   | Triniti Corporation does not warrant that this document is error free. If you find any       |
   | errors in the documentation, please report them to us in writing.  Except as may be          |
   | expressly permitted in your license agreement, no part of these documents may be reproduced  |
   | or transmitted in any form or by any means, electronic or mechanical, for any purpose,       |
   | without the express written permission of Triniti Corporation.                               |
   *=============================================================================================*/
   /* *********************************************************************************************/
   /* HISTORY      :                                                                             */
   /* Program      : XXON_OM_GBW_INVRES_RESULT_V.sql                                             */
   /* Date         : 10-JUL-2017                                                                 */
   /* Application  : Custom GBW Application                                                      */
   /* Purpose      : Oracle OM                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.0        KISHORE M              10-JUL-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW XXON_OM_GBW_INVRES_RESULT_V
(
   MPN,
   HEADER_NUMBER,
   PTI4,
   PACKAGE_STD_CD,
   CORP_CD,
   RESERVTN_CUST_CD,
   SCHEDULE_SHIP_DATE,
   BALANCE_QUANTITY,
   RESERVTN_WK_END_DATE,
   REQUEST_DATE,
   PLANNED_DLVRY_DATE,
   CPN,
   INITIAL_QUANTITY,
   SS_FACILITY_LOC_CD,
   SCH_RETURN_CD,
   RELEASE_DATE,
   WORK_WEEK_NUM,
   TRANSMIT_DAY_OF_WK,
   RESERVTN_TYPE,
   CREATED_BY,
   CREATION_DATE,
   LAST_UPDATED_BY,
   LAST_UPDATE_DATE,
   LAST_UPDATE_LOGIN,
   DEMAND_TYPE,
   SOURCE_CODE,
   SITE_USE_CODE
)
AS
     SELECT   xofr.mpn,
              xofr.header_number,
              xofr.pti4,
              xmio.package_group_code AS package_std_cd,
              xofr.corp_cd,
              xofr.reservtn_cust_cd,
              xofr.schedule_ship_date,
              xofr.balance_quantity,
              xofr.reservtn_wk_end_date,
              xofr.request_ship_date,
              xofr.planned_dlvry_date,
              xofr.cpn,
              xofr.initial_quantity,
              xofr.ss_facility_loc_cd,
              xofr.sch_return_cd,
              xofr.release_date,
              xofr.work_week_num,
              xofr.transmit_day_of_wk,
              xofr.reservtn_type,
              xofr.created_by,
              xofr.creation_date,
              xofr.last_updated_by,
              xofr.last_update_date,
              xofr.last_update_login,
              reservtn_type AS demand_type,
              xofn.source   AS source_code,
              'SHIP_TO'     AS site_use_code
       FROM   xxon_om_fcst_reservations xofr,
              xxon_om_fms_network xofn,
              xxon_mtl_item_overflow xmio,
              oe_sys_parameters_all ospa
      WHERE       xofr.reservtn_type = 'I'
              AND xofr.reservtn_cust_cd = xofn.dest
              AND xofr.mpn = xmio.part_number
              AND ospa.parameter_code = 'INVENTORY MASTER ORG'
              AND xmio.org_id = ospa.parameter_value
              AND ospa.org_id = fnd_profile.VALUE ('ORG_ID')
   ORDER BY   xofr.schedule_ship_date ASC;
show errors;
exit;