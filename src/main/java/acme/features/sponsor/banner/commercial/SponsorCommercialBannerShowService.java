
package acme.features.sponsor.banner.commercial;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.banner.commercial.AuthenticatedCommercialBannerRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorCommercialBannerShowService implements AbstractShowService<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		CommercialBanner cm = this.repository.findOne(id);
		Sponsor actual = this.repository.findSponsor(principal.getAccountId());
		boolean res = actual != null && actual == cm.getSponsor();

		return res;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL");

	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {

		return this.repository.findOne(request.getModel().getInteger("id"));

	}

}
