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
   /* Program      : XXON_OM_GBW_OPN_LOV_V.sql                                              */
   /* Date         : 10-JUL-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle AR                                                                   */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_OPN_LOV_V
(
   INVENTORY_ITEM_ID,
   ORGANIZATION_ID,
   OPN,
   DESCRIPTION
)
AS
   SELECT   msib.inventory_item_id,
            msib.organization_id,
            msib.segment1 opn,
            msib.description
     FROM   mtl_system_items_b msib, mtl_parameters mp
    WHERE       msib.enabled_flag = 'Y'
            AND msib.organization_id = mp.organization_id
            AND mp.organization_id = mp.master_organization_id;

show err;
exit;