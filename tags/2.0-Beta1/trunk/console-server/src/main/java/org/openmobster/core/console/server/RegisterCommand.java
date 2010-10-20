/**
 * Copyright (c) {2003,2010} {openmobster@gmail.com} {individual contributors as indicated by the @authors tag}.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openmobster.core.console.server;

import org.openmobster.core.security.IDMException;
import org.openmobster.core.security.Provisioner;
import org.openmobster.server.api.service.Request;
import org.openmobster.server.api.service.Response;
import org.openmobster.server.api.service.ServiceInfo;
import org.openmobster.server.api.service.MobileServiceBean;

/**
 * @author openmobster@gmail.com
 */
@ServiceInfo(uri="/console/registerCommand")
public class RegisterCommand implements MobileServiceBean
{
	private Provisioner provisioner;
	
	public RegisterCommand()
	{
		
	}
	
	
	public Provisioner getProvisioner()
	{
		return provisioner;
	}


	public void setProvisioner(Provisioner provisioner)
	{
		this.provisioner = provisioner;
	}



	public Response invoke(Request request)
	{
		try
		{
			String username = request.getAttribute("username");
			String password = request.getAttribute("password");
			
			this.provisioner.registerIdentity(username, password);
			
			return null;
		}
		catch(IDMException idmError)
		{
			Response response = new Response();
			response.setAttribute("idm-error", idmError.getMessage());
			response.setAttribute("idm-error-type", ""+idmError.getType());			
			return response;
		}
	}
}
