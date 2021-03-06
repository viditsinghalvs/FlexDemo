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
   /* Program      : xxon_om_sch_controls_trg
(.sql                                              */
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

CREATE OR REPLACE TRIGGER xxon_om_sch_controls_trg
  BEFORE INSERT OR UPDATE ON "XXON"."XXON_OM_SCH_CONTROLS#"
  FOR EACH ROW
DECLARE
  v_entity_name    VARCHAR2(300) := 'XXON_OM_SCH_CONTROLS';
  v_attribute_name VARCHAR2(300) := 'SCHEDULING_METHOD';
BEGIN

  IF :new.scheduling_method <> nvl(:old.scheduling_method, 'K') THEN
  
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
      ,:new.sch_control_id
      ,v_attribute_name
      ,:old.scheduling_method
      ,:new.scheduling_method
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

