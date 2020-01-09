
package acme.features.administrator.auditorRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuditorRequestListService implements AbstractListService<Administrator, AuditorRequest> {

	// Internal state -------------------------------------

	@Autowired
	private AdministratorAuditorRequestRepository repository;


	// AbstractListService<Administrator, AuditorRequest> interface

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm");

	}

	@Override
	public Collection<AuditorRequest> findMany(final Request<AuditorRequest> request) {
		assert request != null;

		Collection<AuditorRequest> result = this.repository.findMany();

		return result;
	}

}
