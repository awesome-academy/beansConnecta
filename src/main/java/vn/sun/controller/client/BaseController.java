package vn.sun.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import vn.sun.services.client.UserServices;

public class BaseController {

	@Autowired
	protected UserServices userServices;
	@Autowired
	protected MessageSource messageSource;
}
