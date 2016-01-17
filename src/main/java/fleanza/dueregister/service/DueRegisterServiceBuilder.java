package fleanza.dueregister.service;

import fleanza.dueregister.service.handler.DueRegisterLoader;
import fleanza.dueregister.service.handler.DueRegisterSorter;
import fleanza.dueregister.service.handler.DueRegisterValidator;
import fleanza.dueregister.service.handler.DueRegisterWriter;

/**
 * @author fleanza
 */
public class DueRegisterServiceBuilder {

	private DueRegisterValidator	validator;
	private DueRegisterLoader			loader;
	private DueRegisterSorter			sorter;
	private DueRegisterWriter			writer;

	public static DueRegisterServiceBuilder create() {
		return new DueRegisterServiceBuilder();
	}

	public DueRegisterServiceBuilder validator(DueRegisterValidator aValidator) {
		validator = aValidator;
		return this;
	}

	public DueRegisterServiceBuilder loader(DueRegisterLoader aLoader) {
		loader = aLoader;
		return this;
	}

	public DueRegisterServiceBuilder sorter(DueRegisterSorter aSorter) {
		sorter = aSorter;
		return this;
	}

	public DueRegisterServiceBuilder writer(DueRegisterWriter aWriter) {
		writer = aWriter;
		return this;
	}

	public DueRegisterService build() {
		return new DueRegisterService(validator,loader,sorter,writer);
	}
}
