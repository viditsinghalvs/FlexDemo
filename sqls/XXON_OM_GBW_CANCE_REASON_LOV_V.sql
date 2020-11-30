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
   /* Program      : XXON_OM_GBW_CANCE_REASON_LOV_V.sql                                         */
   /* Date         : 11-DEC-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle AR                                                                   */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_CANCE_REASON_LOV_V
(
   REASON_CODE,
   MEANING
)
AS
/*SELECT   ffv.flex_value reason_code, 
         ffvt.description meaning
  FROM   fnd_flex_value_sets ffvs,
         fnd_flex_values ffv,
         fnd_flex_values_tl ffvt
 WHERE       ffvs.flex_value_set_id = ffv.flex_value_set_id
         AND ffv.flex_value_id = ffvt.flex_value_id
         AND ffvt.language = USERENV ('LANG')
         AND ffv.enabled_flag = 'Y'
         AND ffvs.flex_value_set_name = 'XXON_OM_CANCEL_REASONS';*/
select lookup_code reason_code,meaning from fnd_lookup_values_vl
 where lookup_type = 'CANCEL_CODE'
 and attribute1 = 'C'
 and enabled_flag = 'Y'
 and trunc(sysdate) between start_date_active and nvl(end_date_active,sysdate);         
show errors;
exit;