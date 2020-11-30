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
   /* Program      : XXON_OM_GBW_DEMAND_BANK_LOV_V.sql                                           */
   /* Date         : 11-DEC-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle OM                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.0        KISHORE M              11-DEC-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW XXON_OM_GBW_DEMAND_BANK_LOV_V
(
   DEMAND_BANK,
   DEMAND_BANK_DESC
)
AS
SELECT ffvv.flex_value  AS demand_bank,
       ffvv.description AS demand_bank_desc
  FROM fnd_flex_value_sets ffvs
      ,fnd_flex_values_vl  ffvv
WHERE ffvs.flex_value_set_id = ffvv.flex_value_set_id
   AND ffvs.flex_value_set_name = 'XXON_INVENTORY_BANKS'
   AND ffvv.enabled_flag = 'Y'
   AND nvl(ffvv.start_date_active,trunc(SYSDATE)) <= trunc(SYSDATE)
   AND nvl(ffvv.end_date_active,trunc(SYSDATE)) >= trunc(SYSDATE);
show errors;
exit;