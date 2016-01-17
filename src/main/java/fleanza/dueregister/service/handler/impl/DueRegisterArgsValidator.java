package fleanza.dueregister.service.handler.impl;

import java.io.File;

import fleanza.dueregister.exception.DueRegisterException;
import fleanza.dueregister.service.handler.DueRegisterValidator;

/**
 * @author fleanza
 */
public class DueRegisterArgsValidator implements DueRegisterValidator {

	@Override
	public void validate(File fin, File fout) {

		if(!fin.exists()) {
			throw new DueRegisterException("Input filename arg not exist on filesystem");
		}

		if(!fin.isFile()) {
			throw new DueRegisterException("Input filename arg invalid");
		}

		if(fout.exists()) {
			throw new DueRegisterException("Output filename arg already exist on filesystem");
		}
	}
}
