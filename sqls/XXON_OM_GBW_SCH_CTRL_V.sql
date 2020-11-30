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
   /* Program      : XXON_OM_GBW_SCH_CTRL_V.sql                                                  */
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
CREATE OR REPLACE VIEW xxon_om_gbw_sch_ctrl_v
(
   scheduling_method,
   last_updated_by,
   last_update_date,
   ou_name,
   opn,
   pti,
   pkg,
   schctl_level,
   sch_control_id,
   inventory_item_id,
   new_row
)
AS
   SELECT   xmsc.scheduling_method,
            fu.user_name last_updated_by,
            xmsc.last_update_date,
            NULL AS ou_name,
            xxon_om_gbw_commons_pkg.get_opn (xmsc.inventory_item_id) AS opn,
            xmsc.pti AS pti,
            package_cd pkg,
            NULL schctl_level,
            sch_control_id AS sch_control_id,
            xmsc.inventory_item_id,
            'N' AS new_row
     FROM   xxon_om_sch_controls xmsc, fnd_user fu
    WHERE       1 = 1
            AND NVL (xmsc.enabled_flag, 'Y') = 'Y'
            AND xmsc.last_updated_by = fu.user_id;          
show errors;
exit;