package com.enviroapps.eapharmics.ui.security;

import java.net.InetAddress;
import java.net.UnknownHostException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class WebAppEventListener implements ServletContextListener
{
	public WebAppEventListener()
	{
	}
	public void contextDestroyed(ServletContextEvent ctxEvent)
	{
		// do nothing
	}
	public void contextInitialized(ServletContextEvent ctxEvent)
	{
		try
		{
			System.out.println("Deleting left over user session from previous run");
			String localHostName = InetAddress.getLocalHost().getHostName();
//			String[] userSessionIds =
//				SecurityProviderProxyFactory
//					.getInstance()
//					.getSecurityProviderProxy()
//					.findUserSessionContextByAttribute(
//						SecurityConstants.WEB_SERVER_HOST_NAME_USER_SESSION_ATTRIBUTE_NAME,
//						localHostName);
//			SecurityProviderProxyFactory
//				.getInstance()
//				.getSecurityProviderProxy()
//				.deleteUserSessionContexts(userSessionIds);
			System.out.println("Deleted left over user session from previous run");
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
	}
}