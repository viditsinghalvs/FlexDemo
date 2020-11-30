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
   /* Program      : XXON_OM_GBW_AUDIT_SCHCT_LINE_V.sql                                          */
   /* Date         : 29-DEC-2017                                                                 */
   /* Application  : Custom GBW Application                                                      */
   /* Purpose      : Oracle OM                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.1        KISHORE M              29-DEC-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW XXON_OM_GBW_AUDIT_SCHCT_LINE_V
(
   AUDIT_USER_NAME,
   AUDIT_TIMESTAMP,
   ORDER_NUMBER,
   OPN,
   ATTRIBUTE_NAME,
   NEW_ATTRIBUTE_VALUE,
   OLD_ATTRIBUTE_VALUE,
   LINE_NUMBER,
   AUDIT_LEVEL,
   RESPONSIBILITY_NAME,
   HEADER_ID
)
AS
   SELECT   fu.user_name audit_user_name,
            sca.hist_creation_date audit_timestamp,
            NULL "ORDER_NUMBER",
            xxon_om_gbw_commons_pkg.get_opn (
               p_inventory_item_id   => sc.inventory_item_id
            )
               "OPN",
            DECODE (sca.attribute_name,
                    'SCHEDULING_METHOD', 'Scheduling Method',
                    sca.attribute_name)
               attribute_name,
            sca.new_attribute_value,
            sca.old_attribute_value,
            NULL "LINE_NUMBER",
            'Scheduling Control' "AUDIT_LEVEL",
            NULL "RESPONSIBILITY_NAME",
            sc.sch_control_id AS header_id
     FROM   xxon_om_audit_attr_history sca,
            xxon_om_sch_controls sc,
            fnd_user fu
    WHERE       sca.entity_name = 'XXON_OM_SCH_CONTROLS'
            AND sca.entity_key = sc.sch_control_id(+)
            AND sca.user_id = fu.user_id;
show errors;
exit;
