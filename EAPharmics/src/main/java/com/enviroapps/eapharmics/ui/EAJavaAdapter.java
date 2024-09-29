package com.enviroapps.eapharmics.ui;

public class EAJavaAdapter { //extends JavaAdapter {

	//@TODO Sundar
	/* (non-Javadoc)
	 * @see flex.messaging.services.remoting.adapters.JavaAdapter#invoke(flex.messaging.messages.Message)
	 */
//	public Object invoke(Message message) {
//		int clientTimezoneOffset = 0;
//    	if (message.getHeader("timezoneOffset") != null) {
//    		clientTimezoneOffset = (Integer) message.getHeader("timezoneOffset");
//    	}
//        // Set the User object in the session attribute
//        FlexSession session = FlexContext.getFlexSession();
//        if(session != null) {
//            //incoming flex client timezone offset from UTC will be in minutes. convert it into milliseconds
//        	//The difference, in minutes, between universal time (UTC) and the computer's local time. 
//        	//Specifically, this value is the number of minutes you need to add to the computer's local time to equal UTC. 
//        	//If your computer's time is set later than UTC, the value will be negative. 
//            long clientOffset = clientTimezoneOffset*60*1000*-1;
//            //get the server timezone offset from UTC  
//            long serverOffset = TimeZone.getDefault().getOffset(new Date().getTime());
//            //save the time difference between client and server.
//            //adding this time to server gives the client's time
//            session.setAttribute(Constants.CLIENT_SERVER_TIME_DIFF, new Long(clientOffset - serverOffset));
//        	if (message.getHeader("timezoneOffset") != null) {
//        		if (serverOffset != clientOffset) {
//        			List<Object> list = ((RemotingMessage)message).getParameters();
//        			for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
//        				Object object = (Object) iterator.next();
//        				Utility.adjustDateForIncomingObject(object);        				
//        			}
//        		}
//        		Object returnObj = super.invoke(message);
//        		if (serverOffset != clientOffset && returnObj != null) {
//        			Utility.adjustDateForOutgoingObject(returnObj);
//        		}
//        		
//        		return returnObj;
//        	}
//        }
//        //no flex session. should not happen. just invoke the message and return the result
//		return super.invoke(message);
//	}
}
