/**
 *Copyright (c) 2016 Aparna
 *Project : project3(V1.0)
 */


package projectthree.app.customexceptions;

import com.spartan.pluggable.exception.layers.ds.BusinessRuleInternalError;


/**
 *Author : Aparna  Sawant
 *Date :Fri Aug 26 09:02:31 UTC 2016
 */

public class DivideByZeroException extends BusinessRuleInternalError {


     private static final long serialVersionUID = 2878177583154637732L;


     /** Create DivideByZeroException with Alarm Id & Throwable.
      * @param _appAlarmId
      * @param _throwable
      */
     public DivideByZeroException(final String _appAlarmId, Throwable _throwable) {
          super("DivideByZeroException", _appAlarmId, _throwable);
     }

     /** Create DivideByZeroException with Message, Alarm Id & Throwable.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      */
     public DivideByZeroException(final String _msg, final String _appAlarmId, final Throwable _throwable) {
          super(_msg, _appAlarmId, _throwable);
     }

     /** Create DivideByZeroException with Message, Alarm Id, Throwable & Variable Arguments.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      * @param _params
      */
     public DivideByZeroException(final String _msg, final String _appAlarmId, final Throwable _throwable, final Object..._params) {
          super(_msg, _appAlarmId, _throwable, _params);
     }

}