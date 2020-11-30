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
   /* Program      : XXON_OM_GBW_RESCH_REASON_LOV_V.sql                                         */
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
   | 1.0.1        KISHORE M              11-DEC-2017     Initial  Creation                       |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW xxon_om_gbw_resch_reason_lov_v
(
   reason_code,
   meaning,
   resp_key
)
AS
   SELECT   lookup_code reason_code, 
            meaning, 
            'OM_SUPERUSER' AS resp_key
     FROM   fnd_lookup_values_vl
    WHERE       lookup_type = 'CANCEL_CODE'
            AND attribute1 = 'R'
            AND enabled_flag = 'Y'
            AND TRUNC (SYSDATE) BETWEEN start_date_active
            AND  NVL (end_date_active, SYSDATE);
show errors;
exit;