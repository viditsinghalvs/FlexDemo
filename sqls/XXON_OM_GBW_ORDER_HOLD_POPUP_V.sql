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
   /* Program      : XXON_OM_GBW_ORDER_HOLD_POPUP_V.sql                                          */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_ORDER_HOLD_POPUP_V
(
   LINE_ID,
   HEADER_ID,
   HOLD_NAME,
   HOLD_RELEASE_FLAG,
   HOLD_RELEASE_REASON,
   HOLD_RELEASE_DATE,
   APPLIED_BY,
   RELEASED_BY,
   APPLIED_DATE
)
AS
   SELECT   oola.line_id,
            oola.header_id,
            ohd.name AS hold_name,
            ohs.released_flag AS hold_release_flag,
            ohr.release_reason_code AS hold_release_reason,
            ohr.creation_date AS hold_release_date,
            oe_holds_pvt.user_name (ooha.created_by) applied_by,
            oe_holds_pvt.user_name (ohr.created_by) released_by,
            ooha.creation_date applied_date
     FROM   oe_order_holds_all ooha,
            oe_order_lines_all oola,
            oe_hold_definitions ohd,
            oe_hold_sources_all ohs,
            oe_hold_releases ohr
    WHERE       1 = 1
            AND ooha.header_id = oola.header_id
            AND ooha.line_id IS NULL
            AND ohs.hold_source_id(+) = ooha.hold_source_id
            AND ohd.hold_id(+) = ohs.hold_id
            AND ohr.hold_release_id(+) = ohs.hold_release_id
    UNION
    SELECT   oola.line_id,
            oola.header_id,
            ohd.name AS hold_name,
            ohs.released_flag AS hold_release_flag,
            ohr.release_reason_code AS hold_release_reason,
            ohr.creation_date AS hold_release_date,
            oe_holds_pvt.user_name (ooha.created_by) applied_by,
            oe_holds_pvt.user_name (ohr.created_by) released_by,
            ooha.creation_date applied_date
     FROM   oe_order_holds_all ooha,
            oe_order_lines_all oola,
            oe_hold_definitions ohd,
            oe_hold_sources_all ohs,
            oe_hold_releases ohr
    WHERE       1 = 1
            AND ooha.header_id = oola.header_id
            AND ooha.line_id = oola.line_id
            AND ohs.hold_source_id(+) = ooha.hold_source_id
            AND ohd.hold_id(+) = ohs.hold_id
            AND ohr.hold_release_id(+) = ohs.hold_release_id;
show errors;
exit;