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
   /* Program      : XXON_OM_FCST_RESERVATIONS_TRG.trg                                           */
   /* Date         : 22-sep-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle AR                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.0        KISHORE M              22-sep-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE TRIGGER "XXON_OM_FCST_RESERVATIONS_TRG" 
  BEFORE INSERT OR UPDATE

 ON  "XXON"."XXON_OM_FCST_RESERVATIONS#"     FOR EACH ROW
DECLARE
  v_entity_name    VARCHAR2(300) := 'XXON_OM_FCST_RESERVATIONS';
  v_attribute_name VARCHAR2(300) := 'SCHEDULE_SHIP_DATE';
BEGIN

  IF :new.schedule_ship_date <> nvl(:old.schedule_ship_date, sysdate) THEN

    INSERT INTO xxon_om_audit_attr_history
      (entity_name
      ,entity_key
      ,attribute_name
      ,old_attribute_value
      ,new_attribute_value
      ,user_id
      ,responsibility_id
      ,hist_creation_date)
    VALUES
      (v_entity_name
      ,:new.reservtn_id
      ,v_attribute_name
      ,:old.schedule_ship_date
      ,:new.schedule_ship_date
      ,fnd_global.user_id
      ,fnd_global.resp_id
      ,SYSDATE);

  END IF;
EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
/
show errors;
exit;


