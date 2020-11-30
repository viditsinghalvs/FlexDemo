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
   /* Program      : xxon_om_gbw_fcst_reserv_v.sql                                               */
   /* Date         : 30-Apr-2017                                                                 */
   /* Application  : Custom GBW Application                                                      */
   /* Purpose      : Oracle OM                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.2        KISHORE M              30-Apr-2018     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW xxon_om_gbw_fcst_reserv_v
(
   opn,
   customer_code,
   cust5_code,
   order_hold,
   order_type,
   demand_type,
   order_number,
   rsd,
   ssd,
   reschedule_reason,
   quantity,
   pegging_detail,
   product_category,
   lead_time,
   max_quantity_lead_time,
   mpq,
   mp_date,
   coi,
   scheduling_return,
   csr,
   split_flag,
   item_planner,
   pkg,
   pal,
   operating_unit,
   operating_unit_name,
   status,
   inventory_item_id,
   line_id,
   header_id,
   reservtn_id,
   demand_bank,
   line_number,
   organization_id,
   legacy_so,
   rush_order_flag,
   sad,
   rd,
   legacy_line_number,
   site_use_code,
   ssd_rescedule_counter,
   po_number
)
AS
   SELECT   mpn opn,
            corp_cd AS customer_code,
            reservtn_cust_cd AS cust5_code,
            NULL order_hold,
            'Forecast Reservation' order_type,
            'FR' AS demand_type,
            header_number AS order_number,
            request_ship_date AS rsd,
            schedule_ship_date AS ssd,
            NULL reschedule_reason,
            balance_quantity AS quantity,
            NULL pegging_detail,
            NULL product_category,
            NULL lead_time,
            NULL max_quantity_lead_time,
            NULL mpq,
            NULL mp_date,
            NULL coi,
            sch_return_cd AS scheduling_return,
            NULL csr,
            NULL split_flag,
            xxon_om_gbw_commons_pkg.get_planner (
               p_inventory_item_id   => NULL,
               p_org_id              => NULL,
               p_mpn                 => mpn
            )
               item_planner,
            package_std_cd AS pkg,
            pti4 pal,
            NULL operating_unit,
            NULL operating_unit_name,
            NULL status,
            NULL inventory_item_id,
            reservtn_id AS line_id,
            NULL header_id,
            reservtn_id,
            NULL demand_bank,
            TO_CHAR (work_week_num) || '.' || TO_CHAR (line_number)
               AS line_number,
            NULL AS organization_id,
            NULL AS legacy_so,
            NULL AS rush_order_flag,
            NULL AS sad,
            request_date AS rd,
            NULL AS legacy_line_number,
            NULL AS site_use_code,
            NULL AS ssd_rescedule_counter,
            NULL AS cust_po_number
     FROM   xxon_om_fcst_reservations
    WHERE   reservtn_type = 'F' 
        AND balance_quantity > 0;
show errors;
exit;