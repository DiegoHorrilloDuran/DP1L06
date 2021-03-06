
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.features.employer.descriptor.EmployerDescriptorRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	// Internal state -----------------------------

	@Autowired
	private EmployerDutyRepository			repository;

	@Autowired
	private EmployerDescriptorRepository	descriptorRepository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int descriptorId = request.getModel().getInteger("descriptorId");
		Descriptor d = this.descriptorRepository.findOneDescriptorById(descriptorId);
		entity.setDescriptor(d);

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "aproxTime");
		model.setAttribute("descriptorId", entity.getDescriptor().getId());

	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;
		Integer descriptorId;
		Descriptor descriptor;
		Duty result;

		result = new Duty();
		descriptorId = request.getModel().getInteger("descriptorId");
		descriptor = this.descriptorRepository.findOneDescriptorById(descriptorId);

		result.setDescriptor(descriptor);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean tooMuchTime;
		Integer dutiesTime = this.repository.sumDutiesTimeByDescriptorId(entity.getDescriptor().getId());
		if (entity.getAproxTime() == null) {
			errors.state(request, entity.getAproxTime() != null, "aproxTime", "javax.validation.constraints.NotNull.message");
		} else {
			tooMuchTime = dutiesTime + entity.getAproxTime() <= 100;
			errors.state(request, tooMuchTime, "aproxTime", "acme.validation.duty.aproxTime");
		}

	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		Integer descriptorId = request.getModel().getInteger("descriptorId");
		Descriptor d = this.descriptorRepository.findOneDescriptorById(descriptorId);
		entity.setDescriptor(d);
		this.repository.save(entity);

	}

}
