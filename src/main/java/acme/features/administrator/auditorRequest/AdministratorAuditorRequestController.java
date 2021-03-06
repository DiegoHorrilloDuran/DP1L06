
package acme.features.administrator.auditorRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/auditor-request")
public class AdministratorAuditorRequestController extends AbstractController<Administrator, AuditorRequest> {

	// Internal state ---------------------------------

	@Autowired
	private AdministratorAuditorRequestListService		listService;

	@Autowired
	private AdministratorAuditorRequestShowService		showService;

	@Autowired
	private AdministratorAuditorRequestUpdateService	updateService;

	@Autowired
	private AdministratorAuditorRequestDeleteService	deleteService;


	// Constructors

	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
