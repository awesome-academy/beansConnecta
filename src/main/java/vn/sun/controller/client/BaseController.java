package vn.sun.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import vn.sun.services.client.UsersServices;

public class BaseController {

	@Autowired
	protected UsersServices usersServices;
	@Autowired
	protected MessageSource messageSource;
}
