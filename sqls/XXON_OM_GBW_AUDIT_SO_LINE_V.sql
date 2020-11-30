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
   /* Program      : XXON_OM_GBW_AUDIT_SO_LINE_V.sql                                             */
   /* Date         : 29-DEC-2017                                                                 */
   /* Application  : Custom GBW Application                                                      */
   /* Purpose      : Oracle om                                                                   */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_AUDIT_SO_LINE_V
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
   SELECT   oaad.user_name audit_user_name,
            oaad.hist_creation_date audit_timestamp,
            TO_CHAR (oaad.order_number) "ORDER_NUMBER",
            xxon_om_gbw_commons_pkg.get_entity_att_value (
               p_entity_short_name   => opev.entity_short_name,
               p_entity_number       => oaad.entity_number,
               p_entity_attribute    => 'OPN'
            )
               "OPN",
            DECODE (oaad.attribute_display_name,
                    'Promise Date', 'Schedule Arrival Date',
                    oaad.attribute_display_name)
               attribute_name,
            oaad.new_display_value new_attribute_value,
            oaad.old_display_value old_attribute_value,
            xxon_om_gbw_commons_pkg.get_entity_att_value (
               p_entity_short_name   => opev.entity_short_name,
               p_entity_number       => oaad.entity_number,
               p_entity_attribute    => 'LINE_NUMBER'
            )
               "LINE_NUMBER",
            DECODE (opev.entity_short_name,
                    'LINE', 'Order Line',
                    'HEADER', 'Order Header',
                    opev.entity_short_name)
               audit_level,
            oaad.responsibility_name "RESPONSIBILITY_NAME",
            oaad.entity_number AS header_id
     FROM   xxon_oe_audit_attr_desc_v oaad,
            oe_pc_attributes_v opav,
            oe_pc_entities_v opev
    WHERE       1 = 1
            AND opav.entity_id = oaad.entity_id
            AND opav.attribute_id = oaad.attribute_id
            AND opav.entity_id = opev.entity_id
            AND opev.application_short_name = 'ONT'
    UNION ALL
    SELECT fu.user_name audit_user_name
          ,xoaah.hist_creation_date audit_timestamp
          ,to_char(ooha.order_number) "ORDER_NUMBER"
          ,xxon_om_gbw_commons_pkg.get_entity_att_value (
                   p_entity_short_name   => 'LINE',
                   p_entity_number       => to_number(xoaah.entity_key),
                   p_entity_attribute    => 'OPN'
                  ) "OPN"
           ,decode(xoaah.attribute_name
                 ,'STATUS'
                 ,'Status'
                 ,xoaah.attribute_name) attribute_name
           ,xoaah.new_attribute_value
           ,xoaah.old_attribute_value
           ,oola.line_number || '.' || nvl(oola.shipment_number, 1) "LINE_NUMBER"
           ,'Order Line' "AUDIT_LEVEL"
           ,frv.responsibility_name "RESPONSIBILITY_NAME"
           ,to_number(xoaah.entity_key) AS header_id
      FROM xxon_om_audit_attr_history xoaah
          ,oe_order_lines_all         oola
          ,oe_order_headers_all       ooha
          ,fnd_user                   fu
          ,fnd_responsibility_vl      frv
     WHERE xoaah.entity_name = 'SALES_ORDER_LINE'
       AND xoaah.entity_key = oola.line_id
       AND oola.header_id = ooha.header_id
       AND xoaah.user_id = fu.user_id
   AND xoaah.responsibility_id = frv.responsibility_id(+)
   UNION ALL
   SELECT   fu.user_name audit_user_name,
                   xoaah.hist_creation_date audit_timestamp,
                   TO_CHAR (ooha.order_number) "ORDER_NUMBER",
                   NULL "OPN",
                   DECODE (xoaah.attribute_name,
                           'STATUS','Status',
                           xoaah.attribute_name)
                      attribute_name,
                   xoaah.new_attribute_value,
                   xoaah.old_attribute_value,
                   NULL "LINE_NUMBER",
                   'Order Header' "AUDIT_LEVEL",
                   frv.responsibility_name "RESPONSIBILITY_NAME",
                   to_number(xoaah.entity_key) AS header_id
            FROM   xxon_om_audit_attr_history xoaah,
   								oe_order_headers_all ooha,
                   fnd_user fu,
                   fnd_responsibility_vl frv
           WHERE       xoaah.entity_name = 'SALES_ORDER_HEADER'
                   AND xoaah.entity_key = ooha.header_id
                   AND xoaah.user_id = fu.user_id
                AND xoaah.RESPONSIBILITY_ID = frv.RESPONSIBILITY_ID(+)
            ;
show errors;
exit;
